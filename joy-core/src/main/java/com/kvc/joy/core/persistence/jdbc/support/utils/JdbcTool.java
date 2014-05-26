package com.kvc.joy.core.persistence.jdbc.support.utils;

import com.kvc.joy.commons.exception.SystemException;
import com.kvc.joy.commons.lang.string.StringTool;
import com.kvc.joy.commons.log.Log;
import com.kvc.joy.commons.log.LogFactory;
import com.kvc.joy.core.init.support.properties.JoyProperties;
import com.kvc.joy.core.persistence.jdbc.dao.BaseJdbcDao;
import com.kvc.joy.core.persistence.jdbc.model.vo.IMdRdbDataSrc;
import com.kvc.joy.core.persistence.jdbc.support.db.DbSupportFactory;
import com.kvc.joy.core.persistence.jdbc.support.db.RowMapper;
import com.kvc.joy.core.persistence.orm.jpa.JpaTool;
import com.kvc.joy.core.spring.utils.CoreBeanFactory;
import com.kvc.joy.core.sysres.datasrc.model.po.TSysDataSrc;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.*;
import java.util.*;

/**
 * 
 * @since 1.0.0
 * @author 唐玮琳
 * @time 2012-12-28 下午9:55:56
 */
public class JdbcTool extends BaseJdbcDao {

	protected static final Log logger = LogFactory.getLog(JdbcTool.class);
	private static final Map<String, DataSource> dataSourceMap = new HashMap<String, DataSource>();

	private JdbcTool() {
	}

	private static JdbcTool getInstance() {
		return (JdbcTool) CoreBeanFactory.getJdbcTool();
	}

	/**
	 * 
	 * 
	 * @param dsId
	 * @return
	 * @since 1.0.0
	 * @author 唐玮琳
	 * @time 2013年11月10日 下午11:21:39
	 */
	public static Connection getConnectionByDsId(String dsId) {
		IMdRdbDataSrc mdRdbConn = JpaTool.get(TSysDataSrc.class, dsId);
		if (mdRdbConn == null && JoyProperties.DB_DATASOURCEID.equals(dsId)) {
			return getConnectionByJndi(JoyProperties.DB_JNDI);
		}
		return getConnection(mdRdbConn);
	}
	
	/**
	 * 
	 * 
	 * @param jndi
	 * @return
	 * @since 1.0.0
	 * @author 唐玮琳
	 * @time 2013年11月10日 下午11:19:13
	 */
	public static Connection getConnectionByJndi(String jndi) {
		DataSource dataSource = getDataSourceByJndi(jndi);
		if (dataSource != null) {
			try {
				return dataSource.getConnection();
			} catch (SQLException e) {
				logger.error("通过JNDI：" + jndi + "获取连接出错！");
			}
		}
		return null;
	}

	/**
	 * 根据数据源id获取数据库连接(没从连接池中获取)
	 * 
	 * @param mdRdbConn 数据源
	 * @return 数据库连接
	 * @author 唐玮琳
	 * @time 2012-11-2 下午2:29:43
	 */
	public static Connection getConnection(IMdRdbDataSrc mdRdbConn) {
		if (mdRdbConn == null) {
			return null;
		}
		Connection conn = null;
		String jndi = mdRdbConn.getJndiName();
		if (StringTool.isNotBlank(jndi)) {
			conn = getConnectionByJndi(jndi);
		} else {
			try {
				Properties info = new Properties();
				info.setProperty("remarksReporting", "true");
				info.setProperty("user", mdRdbConn.getUsername());
				info.setProperty("password", mdRdbConn.getPassword());
				conn = DriverManager.getConnection(mdRdbConn.getUrl(), info);
			} catch (Exception e) {
				logger.error(e);
			}
		}
		return conn;
	}

	/**
	 * 关闭数据连接
	 * 
	 * @param conn 数据连接
	 */
	public static void closeConnection(Connection conn) {
        if (conn != null) {
            try {
                if (conn.isClosed() == false) {
                    conn.close();
                }
            } catch (SQLException e) {
                logger.error("关闭数据连接出错！", e);
            }
        }
	}

	/**
	 * 关闭Statement
	 * 
	 * @param statement Statement
	 * @since 1.0.0
	 * @author 唐玮琳
	 * @time 2013年11月23日 下午8:45:59
	 */
	public static void closeStatement(Statement statement) {
        if (statement != null) {
            try {
//                if (statement.isClosed() == false) { // isClosed是JDBC新规范引入的抽象方法，有些jbdc驱动未实现它会报错，如ojdbc14, ojdbc5_g等
                    statement.close();
//                }
            } catch (SQLException e) {
                logger.error("关闭Statement出错！", e);
            }
        }
    }

	/**
	 * 关闭ResultSet
	 * 
	 * @param resultSet ResultSet
	 * @since 1.0.0
	 * @author 唐玮琳
	 * @time 2013年11月23日 下午8:45:16
	 */
    public static void closeResultSet(ResultSet resultSet) {
        if (resultSet != null) {
            try {
//                if (resultSet.isClosed() == false) { // isClosed是JDBC新规范引入的抽象方法，有些jbdc驱动未实现它会报错，如ojdbc14, ojdbc5_g等
                    resultSet.close();
//                }
            } catch (SQLException e) {
                logger.error("关闭ResultSet出错！", e);
            }
        }
    }

	/**
	 * 获取数据源
	 * 
	 * @param jndi jndi名称
	 * @return
	 * @author 唐玮琳
     * @since 1.0.0
	 * @time 2013-2-8 下午2:59:18
	 */
	public static DataSource getDataSourceByJndi(String jndi) {
		DataSource dataSource = dataSourceMap.get(jndi);
		if (dataSource == null) {
			if (StringTool.isNotBlank(jndi)) {
				InitialContext context = null;
				try {
					context = new InitialContext();
					dataSource = (DataSource) context.lookup(jndi);
				} catch (NamingException e) {
                    if(context != null) {
                        try {
                            dataSource = (DataSource) context.lookup("java:comp/env/" + jndi);
                        } catch (NamingException ex) {
                            logger.error("以JNDI: " + jndi + "获取数据源失败！", ex);
                        }
                    }
				}
			}
			if (dataSource != null) {
				dataSourceMap.put(jndi, dataSource);
			}
		}
		return dataSource;
	}

	public static JdbcTemplate jdbcTemplate() {
		return getInstance().getJdbcTemplate();
	}

	
	/**
     * Executes this query with these parameters against this connection.
     *
     * @param query  The query to execute.
     * @param params The query parameters.
     * @return The query results.
     * @throws SQLException when the query execution failed.
     */
    public static List<Map<String, String>> queryForList(Connection conn, String query, String... params) {
        PreparedStatement statement = null;
        ResultSet resultSet = null;

        List<Map<String, String>> result;
        try {
            statement = conn.prepareStatement(query);
            for (int i = 0; i < params.length; i++) {
                statement.setString(i + 1, params[i]);
            }
            resultSet = statement.executeQuery();

            result = new ArrayList<Map<String, String>>();
            while (resultSet.next()) {
                Map<String, String> rowMap = new HashMap<String, String>();
                for (int i = 1; i <= resultSet.getMetaData().getColumnCount(); i++) {
                    rowMap.put(resultSet.getMetaData().getColumnLabel(i), resultSet.getString(i));
                }
                result.add(rowMap);
            }
        } catch (Exception e) {
        	throw new SystemException(e);
        } finally {
            JdbcTool.closeResultSet(resultSet);
            JdbcTool.closeStatement(statement);
        }


        return result;
    }

    /**
     * Executes this query with these parameters against this connection.
     *
     * @param query  The query to execute.
     * @param params The query parameters.
     * @return The query results as a list of strings.
     * @throws SQLException when the query execution failed.
     */
    public static List<String> queryForStringList(Connection conn, String query, String... params) {
        PreparedStatement statement = null;
        ResultSet resultSet = null;

        List<String> result;
        try {
            statement = conn.prepareStatement(query);
            for (int i = 0; i < params.length; i++) {
                statement.setString(i + 1, params[i]);
            }
            resultSet = statement.executeQuery();

            result = new ArrayList<String>();
            while (resultSet.next()) {
                result.add(resultSet.getString(1));
            }
        } catch (Exception e) {
        	throw new SystemException(e);
        } finally {
        	JdbcTool.closeResultSet(resultSet);
        	JdbcTool.closeStatement(statement);
        }

        return result;
    }

    /**
     * Executes this query with these parameters against this connection.
     *
     * @param query  The query to execute.
     * @param params The query parameters.
     * @return The query result.
     * @throws SQLException when the query execution failed.
     */
    public static int queryForInt(Connection conn, String query, String... params) {
        PreparedStatement statement = null;
        ResultSet resultSet = null;

        int result;
        try {
            statement = conn.prepareStatement(query);
            for (int i = 0; i < params.length; i++) {
                statement.setString(i + 1, params[i]);
            }
            resultSet = statement.executeQuery();
            resultSet.next();
            result = resultSet.getInt(1);
        } catch (Exception e) {
        	throw new SystemException(e);
        } finally {
        	JdbcTool.closeResultSet(resultSet);
        	JdbcTool.closeStatement(statement);
        }

        return result;
    }

    /**
     * Executes this query with these parameters against this connection.
     *
     * @param query  The query to execute.
     * @param params The query parameters.
     * @return The query result.
     * @throws SQLException when the query execution failed.
     */
    public static String queryForString(Connection conn, String query, String... params) {
        PreparedStatement statement = null;
        ResultSet resultSet = null;

        String result;
        try {
            statement = conn.prepareStatement(query);
            for (int i = 0; i < params.length; i++) {
                statement.setString(i + 1, params[i]);
            }
            resultSet = statement.executeQuery();
            result = null;
            if (resultSet.next()) {
                result = resultSet.getString(1);
            }
        } catch (Exception e) {
        	throw new SystemException(e);
        } finally {
        	JdbcTool.closeResultSet(resultSet);
        	JdbcTool.closeStatement(statement);
        }

        return result;
    }

    /**
     * Executes this sql statement using a PreparedStatement.
     *
     * @param sql    The statement to execute.
     * @param params The statement parameters.
     * @throws SQLException when the execution failed.
     */
    public static void execute(Connection conn, String sql, Object... params) {
        PreparedStatement statement = null;
        try {
            statement = prepareStatement(conn, sql, params);
            statement.execute();
        } catch (Exception e) {
        	throw new SystemException(e);
        } finally {
        	JdbcTool.closeStatement(statement);
        }
    }

    /**
     * 批量执行
     *
     * @param conn 连接
     * @param sqls sql语句集合
     * @author 唐玮琳
     * @since 1.0.0
     * @time 2013-2-8 下午2:59:18
     */
    public static void executeBatch(Connection conn, Collection<String> sqls) {
        Statement statement = null;
        try {
            statement = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
            conn.setAutoCommit(false);
            for(String sql : sqls) {
                if (StringTool.isNotBlank(sql)) {
                    statement.execute(sql.trim());
                }
            }
            conn.commit();
        } catch (Exception e) {
            throw new SystemException(e);
        } finally {
            JdbcTool.closeStatement(statement);
        }
    }

    /**
     * Executes this sql statement using an ordinary Statement.
     *
     * @param sql The statement to execute.
     * @throws SQLException when the execution failed.
     */
    public static void executeStatement(Connection conn, String sql) {
        Statement statement = null;
        try {
            statement = conn.createStatement();
            statement.execute(sql);
        } catch (Exception e) {
        	throw new SystemException(e);
        } finally {
        	JdbcTool.closeStatement(statement);
        }
    }

    /**
     * Executes this update sql statement.
     *
     * @param sql    The statement to execute.
     * @param params The statement parameters.
     * @throws SQLException when the execution failed.
     */
    public static void update(Connection conn, String sql, Object... params) {
        PreparedStatement statement = null;
        try {
            statement = prepareStatement(conn, sql, params);
            statement.executeUpdate();
        } catch (Exception e) {
        	throw new SystemException(e);
        } finally {
        	JdbcTool.closeStatement(statement);
        }
    }

    /**
     * Creates a new prepared statement for this sql with these params.
     *
     * @param sql    The sql to execute.
     * @param params The params.
     * @return The new prepared statement.
     * @throws SQLException when the statement could not be prepared.
     */
    private static PreparedStatement prepareStatement(Connection conn, String sql, Object[] params) throws SQLException {
        PreparedStatement statement = conn.prepareStatement(sql);
        for (int i = 0; i < params.length; i++) {
            if (params[i] == null) {
            	int nullType = DbSupportFactory.createDbSupport(conn).getNullType();
                statement.setNull(i + 1, nullType);
            } else if (params[i] instanceof Integer) {
                statement.setInt(i + 1, (Integer) params[i]);
            } else if (params[i] instanceof Boolean) {
                statement.setBoolean(i + 1, (Boolean) params[i]);
            } else {
                statement.setString(i + 1, (String) params[i]);
            }
        }
        return statement;
    }

    /**
     * Executes this query and map the results using this row mapper.
     *
     * @param query     The query to execute.
     * @param rowMapper The row mapper to use.
     * @param <T>       The type of the result objects.
     * @return The list of results.
     * @throws SQLException when the query failed to execute.
     */
    public static <T> List<T> query(Connection conn, String query, RowMapper<T> rowMapper) {
        Statement statement = null;
        ResultSet resultSet = null;

        List<T> results;
        try {
            statement = conn.createStatement();
            resultSet = statement.executeQuery(query);

            results = new ArrayList<T>();
            while (resultSet.next()) {
                results.add(rowMapper.mapRow(resultSet));
            }
        } catch (Exception e) {
        	throw new SystemException(e);
        } finally {
        	JdbcTool.closeResultSet(resultSet);
        	JdbcTool.closeStatement(statement);
        }

        return results;
    }
	
}

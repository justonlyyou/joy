package com.kvc.joy.core.persistence.jdbc.support.utils;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import net.sf.ehcache.CacheException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.JdbcTemplate;

import com.kvc.joy.commons.lang.string.StringTool;
import com.kvc.joy.core.init.support.JoyPropeties;
import com.kvc.joy.core.persistence.jdbc.dao.BaseJdbcDao;
import com.kvc.joy.core.persistence.jdbc.model.vo.DbMetaData;
import com.kvc.joy.core.persistence.jdbc.model.vo.IMdRdbConn;
import com.kvc.joy.core.persistence.jdbc.model.vo.MdRdbColumn;
import com.kvc.joy.core.persistence.jdbc.model.vo.MdRdbPrimaryKey;
import com.kvc.joy.core.persistence.jdbc.model.vo.MdRdbTable;
import com.kvc.joy.core.persistence.jdbc.support.enums.RdbObjectType;
import com.kvc.joy.core.persistence.orm.jpa.JpaTool;
import com.kvc.joy.core.spring.utils.CoreBeanFactory;
import com.kvc.joy.core.sysres.datasrc.model.po.TSysDataSrc;

/**
 * 
 * @since 1.0.0
 * @author 唐玮琳
 * @time 2012-12-28 下午9:55:56
 */
public class JdbcTool extends BaseJdbcDao {

	private static Logger logger = LoggerFactory.getLogger(JdbcTool.class);
	private static Map<String, DataSource> dataSourceMap = new HashMap<String, DataSource>();

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
		IMdRdbConn mdRdbConn = JpaTool.get(TSysDataSrc.class, dsId);
		if (mdRdbConn == null && JoyPropeties.DB_DATASOURCEID.equals(dsId)) {
			return getConnectionByJndi(JoyPropeties.DB_JNDI);
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
	public static Connection getConnection(IMdRdbConn mdRdbConn) {
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
				logger.error(e.getMessage(), e);
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
		try {
			if (conn != null && !conn.isClosed()) {
				conn.close();
			}
		} catch (SQLException e) {
			logger.error(e.getMessage(), e);
		}
	}

	/**
	 * 获取数据源
	 * 
	 * @param jndi jndi名称
	 * @return
	 * @author 唐玮琳
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
					try {
						dataSource = (DataSource) context.lookup("java:comp/env/" + jndi);
					} catch (NamingException ex) {
						logger.error("以JNDI: " + jndi + "获取数据源失败！", ex);
					}
				}
			}
			if (dataSource != null) {
				dataSourceMap.put(jndi, dataSource);
			}
		}
		return dataSource;
	}

	/**
	 * 获取给定数据源下的所有表的所有元数据信息
	 * 
	 * @param dsId 数据源ID
	 * @return Map<String, MdRdbTable>
	 * @author 唐玮琳
	 * @time 2012-11-1 下午5:03:48
	 */
	public static Map<String, MdRdbTable> getTables(String dsId) {
		return getRelationalObjects(dsId, RdbObjectType.TABLE);
	}
	
	/**
	 * 获取表的所有列
	 * 
	 * @param dsId 数据源id
	 * @param tableName 表名
	 * @return
	 * @author 唐玮琳
	 * @time 2012-12-18 下午3:16:14
	 */
	public static Map<String, MdRdbColumn> getColumns(String dsId, String tableName) {
		if (StringTool.isBlank(tableName)) {
			return null;
		}
		if (StringTool.isBlank(dsId)) {
			dsId = JoyPropeties.DB_DATASOURCEID;
		}
		return CoreBeanFactory.getMdRdbColumnCacheService().getColumns(dsId, tableName.toLowerCase());
	}

	/**
	 * 获取表的某一列
	 * 
	 * @param dsId 数据源id
	 * @param tableName 表名
	 * @param columnName 列名
	 * @return
	 * @author 唐玮琳
	 * @time 2012-12-18 下午3:21:37
	 */
	public static MdRdbColumn getColumn(String dsId, String tableName, String columnName) {
		if (StringTool.isBlank(tableName) || StringTool.isBlank(columnName)) {
			return null;
		}
		if (StringTool.isBlank(dsId)) {
			dsId = JoyPropeties.DB_DATASOURCEID;
		}
		Map<String, MdRdbColumn> columns = getColumns(dsId, tableName);
		return columns.get(columnName.toLowerCase());
	}

	/**
	 * 获取主键
	 * 
	 * @param dsId 数据源id
	 * @param tableName 表名
	 * @return 主键对象
	 * @author 唐玮琳
	 * @time 2012-12-18 下午3:37:42
	 */
	public static MdRdbPrimaryKey getPrimaryKey(String dsId, String tableName) {
		if (StringTool.isBlank(tableName)) {
			return null;
		}
		if (StringTool.isBlank(dsId)) {
			dsId = JoyPropeties.DB_DATASOURCEID;
		}
		return CoreBeanFactory.getMdRdbPrimaryKeyCacheService().getPrimaryKey(dsId, tableName.toLowerCase());
	}
	
	/**
	 * 根据数据源ID、对象名从缓存中取得该对象的元数据信息
	 * 
	 * @param dsId 数据源ID(为空取系统默认数据源id)
	 * @param name 对象名(为空返回null)
	 * @return MdDbTable
	 * @author 唐玮琳
	 * @throws CacheException
	 * @time 2012-12-14 下午4:54:03
	 */
	public static MdRdbTable getRelationalObject(String dsId, String name) {
		if (StringTool.isBlank(name)) {
			return null;
		}
		if (StringTool.isBlank(dsId)) {
			dsId = JoyPropeties.DB_DATASOURCEID;
		}
		Map<String, MdRdbTable> tableMap = CoreBeanFactory.getMdRdbTableCacheService().getTables(dsId);
		return tableMap.get(name.toLowerCase());
	}
	
	/**
	 * 根据数据源ID、对象类型，从缓存中取得对应表的元数据信息
	 * 
	 * @param dsId 数据源ID(为空取系统默认数据源id)
	 * @param objTypes 对象类型数组
	 * @return Map<String, MdRdbTable>
	 * @throws CacheException
	 * @author 唐玮琳
	 * @time 2012-12-14 下午5:26:01
	 */
	public static Map<String, MdRdbTable> getRelationalObjects(String dsId, RdbObjectType... objTypes) {
		if (StringTool.isBlank(dsId)) {
			dsId = JoyPropeties.DB_DATASOURCEID;
		}
		Map<String, MdRdbTable> tableMap = CoreBeanFactory.getMdRdbTableCacheService().getTables(dsId);
		return filterRelationalObject(tableMap);
	}
	
	private static Map<String, MdRdbTable> filterRelationalObject(Map<String, MdRdbTable> tableMap, RdbObjectType... objTypes) {
		Set<String> objTypeSet = new HashSet<String>();
		if (objTypes != null) {
			for (RdbObjectType rdbObjectType : objTypes) {
				objTypeSet.add(rdbObjectType.getCode());
			}
		}
		Map<String, MdRdbTable> results = new LinkedHashMap<String, MdRdbTable>();
		
		for (MdRdbTable table : tableMap.values()) {
			if (objTypeSet.isEmpty() || objTypeSet.contains(table.getType())) {
				results.put(table.getName(), table);
			}
		}
		return results;
	}

	public static final JdbcTemplate jdbcTemplate() {
		return getInstance().getJdbcTemplate();
	}

	/**
	 * 
	 * 
	 * @param dsId
	 * @return
	 * @since 1.0.0
	 * @author 唐玮琳
	 * @time 2013年11月16日 上午1:05:04
	 */
	public static DbMetaData getDbMetaData(String dsId) {
		if (StringTool.isBlank(dsId)) {
			dsId = JoyPropeties.DB_DATASOURCEID;
		}
		Connection conn = JdbcTool.getConnectionByDsId(dsId);
		DbMetaData dbMetaData = null;
		try {
			dbMetaData = getDbMetaData(conn);
		} finally {
			JdbcTool.closeConnection(conn);	
		}
		return dbMetaData;
	}
	
	/**
	 * 
	 * 注：调用者必须自己负责关闭连接
	 * @param conn
	 * @return
	 * @since 1.0.0
	 * @author 唐玮琳
	 * @time 2013年11月16日 上午1:07:38
	 */
	public static DbMetaData getDbMetaData(Connection conn) {
		DbMetaData dbMetaData = null;
		try {
			DatabaseMetaData metaData = conn.getMetaData();
			dbMetaData = new DbMetaData(metaData);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
		return dbMetaData;
	}
	
}

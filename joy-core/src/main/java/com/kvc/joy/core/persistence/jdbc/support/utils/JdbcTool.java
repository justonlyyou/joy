package com.kvc.joy.core.persistence.jdbc.support.utils;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
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
import org.springframework.stereotype.Service;

import com.kvc.joy.commons.lang.string.StringTool;
import com.kvc.joy.core.persistence.jdbc.dao.BaseJdbcDao;
import com.kvc.joy.core.persistence.jdbc.model.vo.DbMetaData;
import com.kvc.joy.core.persistence.jdbc.model.vo.IMdRdbConn;
import com.kvc.joy.core.persistence.jdbc.model.vo.MdRdbColumn;
import com.kvc.joy.core.persistence.jdbc.model.vo.MdRdbPrimaryKey;
import com.kvc.joy.core.persistence.jdbc.model.vo.MdRdbTable;
import com.kvc.joy.core.persistence.jdbc.support.enums.RdbObjectType;
import com.kvc.joy.core.persistence.orm.jpa.JpaTool;
import com.kvc.joy.core.spring.utils.CoreBeanFactory;
import com.kvc.joy.core.spring.utils.SpringBeanTool;
import com.kvc.joy.core.support.consts.BaseSysConsts;
import com.kvc.joy.core.sysres.datasrc.model.po.TSysDataSrc;

/**
 * 
 * @author 唐玮琳
 * @time 2012-12-28 下午9:55:56
 */
 @Service
public class JdbcTool extends BaseJdbcDao {

	private static Logger logger = LoggerFactory.getLogger(JdbcTool.class);
	private static Map<String, DataSource> dataSourceMap = new HashMap<String, DataSource>();

	private JdbcTool() {
	}
	
	private static JdbcTool getInstance() {
		return SpringBeanTool.getBean(JdbcTool.class);
	}

	public static Connection getConnectionDirect(String datasourceId) { //TODO
		IMdRdbConn mdRdbConn = JpaTool.get(TSysDataSrc.class, datasourceId);
		return getConnectionDirect(mdRdbConn);
	}

	/**
	 * 根据数据源id获取数据库连接(没从连接池中获取)
	 * 
	 * @param mdRdbConn 数据源
	 * @return 数据库连接
	 * @author 唐玮琳
	 * @time 2012-11-2 下午2:29:43
	 */
	public static Connection getConnectionDirect(IMdRdbConn mdRdbConn) { // TODO
		if (mdRdbConn == null) {
			return null;
		}
		Connection conn = null;
		try {
			Properties info = new Properties();
			info.setProperty("remarksReporting", "true");
			info.setProperty("user", mdRdbConn.getUsername());
			info.setProperty("password", mdRdbConn.getPassword());
			conn = DriverManager.getConnection(mdRdbConn.getUrl(), info);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
		return conn;
	}
	
	/**
	 * 
	 * 
	 * @param datasourceId
	 * @return
	 * @author 唐玮琳
	 * @time 2013-2-8 下午3:14:11
	 */
	public static Connection getConnection(String datasourceId) { //TODO
		IMdRdbConn mdRdbConn = JpaTool.get(TSysDataSrc.class, datasourceId);
		return getConnection(mdRdbConn);
	}
	
	/**
	 * 
	 * 
	 * @param mdRdbConn
	 * @return
	 * @author 唐玮琳
	 * @time 2013-2-8 下午3:14:07
	 */
	public static Connection getConnection(IMdRdbConn mdRdbConn) { //TODO
		try {
			return getDataSource(mdRdbConn).getConnection();
		} catch (SQLException e) {
			logger.error("获取数据库连接出错！", e);
		}
		return null;
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
	 * @param mdRdbConn
	 * @return
	 * @author 唐玮琳
	 * @time 2013-2-8 下午2:59:18
	 */
	public static DataSource getDataSource(IMdRdbConn mdRdbConn) {
		String jndiName = mdRdbConn.getJndiName();
		DataSource dataSource = dataSourceMap.get(jndiName);
		if(dataSource == null) {
			if (StringTool.isNotBlank(jndiName)) {
				InitialContext context = null;
				try {
					context = new InitialContext();
					dataSource = (DataSource) context.lookup(jndiName);
				} catch (NamingException e) {
					try {
						dataSource = (DataSource) context.lookup("java:comp/env/" + jndiName);
					} catch (NamingException ex) {
						logger.error("以JNDI: " + jndiName + "获取数据源失败！", ex);
					}
				}
			}
		}
		return dataSource;
	}
	
	/**
	 * 获取给定数据源下的所有二维关系对象(以"OBJ_TYPE_"打头的该类的常量)的所有元数据信息
	 * 
	 * @param datasourceId 数据源ID
	 * @return List<MdDbTable>
	 * @author 唐玮琳
	 * @time 2012-11-1 下午5:00:13
	 */
	public static List<MdRdbTable> getRelationalObjects(String datasourceId) {
		return getRelationalObjects(datasourceId, (RdbObjectType[]) null);
	}

	/**
	 * 获取给定数据源下的所有表的所有元数据信息
	 * 
	 * @param datasourceId 数据源ID
	 * @return List<MdDbTable>
	 * @author 唐玮琳
	 * @time 2012-11-1 下午5:03:48
	 */
	public static List<MdRdbTable> getTables(String datasourceId) {
		return getRelationalObjects(datasourceId, RdbObjectType.TABLE);
	}

	/**
	 * 获取给定数据源下的所有视图的元数据信息
	 * 
	 * @param datasourceId 数据源ID
	 * @return List<MdDbTable>
	 * @author 唐玮琳
	 * @time 2012-11-1 下午5:03:48
	 */
	public static List<MdRdbTable> getViews(String datasourceId) {
		return getRelationalObjects(datasourceId, RdbObjectType.VIEW);
	}

	/**
	 * 获取给定数据源下的所有同义词的所有元数据信息
	 * 
	 * @param datasourceId 数据源ID
	 * @return List<MdDbTable>
	 * @author 唐玮琳
	 * @time 2012-11-1 下午5:03:48
	 */
	public static List<MdRdbTable> getSynonym(String datasourceId) {
		return getRelationalObjects(datasourceId, RdbObjectType.SYNONYM);
	}

	/**
	 * 获取给定数据源下的某些二维关系对象类型(以"OBJ_TYPE_"打头的该类的常量)的所有元数据信息
	 * 
	 * @param datasourceId 数据源ID
	 * @param objTypes 对象类型可变数组
	 * @return List<MdDbTable>
	 * @author 唐玮琳
	 * @time 2012-11-1 下午4:52:43
	 */
	public static List<MdRdbTable> getRelationalObjects(String datasourceId, RdbObjectType... objTypes) {
		List<MdRdbTable> list = null;
		try {
			list = get(datasourceId, objTypes);
			Collections.sort(list);
		} catch (CacheException e) {
			logger.error(e.getMessage(), e);
		}
		return list;
	}

	/**
	 * 获取指定数据源、表名所对应的对象
	 * 
	 * @param datasourceId 数据源id
	 * @param tableName 表名
	 * @return 数据对象
	 * @author 唐玮琳
	 * @time 2012-11-7 上午9:41:12
	 */
	public static MdRdbTable getRelationalObject(String datasourceId, String tableName) {
		try {
			return get(datasourceId, tableName);
		} catch (CacheException e) {
			logger.error(e.getMessage(), e);
		}
		return null;
	}

	/**
	 * 获取表的所有列
	 * 
	 * @param datasourceId 数据源id
	 * @param tableName 表名
	 * @return
	 * @author 唐玮琳
	 * @time 2012-12-18 下午3:16:14
	 */
	public static List<MdRdbColumn> getColumns(String datasourceId, String tableName) {
		return CoreBeanFactory.getMdRdbColumnCacheService().getColumns(datasourceId, tableName);
	}

	/**
	 * 获取表的某一列
	 * 
	 * @param datasourceId 数据源id
	 * @param tableName 表名
	 * @param columnName 列名
	 * @return
	 * @author 唐玮琳
	 * @time 2012-12-18 下午3:21:37
	 */
	public static MdRdbColumn getColumn(String datasourceId, String tableName, String columnName) {
		List<MdRdbColumn> columns = getColumns(datasourceId, tableName);
		for (MdRdbColumn column : columns) {
			if (column.getName().equalsIgnoreCase(columnName)) {
				return column;
			}
		}
		return null;
	}

	/**
	 * 获取主键
	 * 
	 * @param datasourceId 数据源id
	 * @param tableName 表名
	 * @return 主键对象
	 * @author 唐玮琳
	 * @time 2012-12-18 下午3:37:42
	 */
	public static MdRdbPrimaryKey getPrimaryKey(String datasourceId, String tableName) {
		return CoreBeanFactory.getMdRdbPrimaryKeyCacheService().getPrimaryKey(datasourceId, tableName);
	}

	/**
	 * 根据数据源ID、表名从缓存中取得该表的元数据信息
	 * 
	 * @param datasourceId 数据源ID
	 * @param tableName 表名(大写)
	 * @return MdDbTable
	 * @author 唐玮琳
	 * @throws CacheException
	 * @time 2012-12-14 下午4:54:03
	 */
	private static MdRdbTable get(String datasourceId, String tableName) throws CacheException {
		String key = getKey(datasourceId, tableName);
		return CoreBeanFactory.getMdRdbTableCacheService().get(key);
	}

	/**
	 * 根据数据源ID、对象类型，从缓存中取得对应表的元数据信息
	 * 
	 * @param datasourceId 数据源ID
	 * @param objTypes 对象类型数组
	 * @return List<MdDbTable>
	 * @throws CacheException
	 * @author 唐玮琳
	 * @time 2012-12-14 下午5:26:01
	 */
	private static List<MdRdbTable> get(String datasourceId, RdbObjectType... objTypes) throws CacheException {
		Set<String> objTypeSet = new HashSet<String>();
		if (objTypes != null) {
			for (RdbObjectType rdbObjectType : objTypes) {
				objTypeSet.add(rdbObjectType.getCode());
			}
		}

		List<MdRdbTable> results = new ArrayList<MdRdbTable>();
		Map<String, MdRdbTable> map = CoreBeanFactory.getMdRdbTableCacheService().getMap();
		Set<String> keys = map.keySet();
		String dsId = getKey(datasourceId, "");
		for (String key : keys) {
			if (key.startsWith(dsId)) {
				MdRdbTable obj = map.get(key);
				if (objTypeSet.isEmpty() || objTypeSet.contains(obj.getType())) {
					results.add(obj);
				}
			}
		}
		return results;
	}

	private static String getKey(String datasourceId, String tableName) {
		if (StringTool.isBlank(datasourceId)) {
			datasourceId = BaseSysConsts.SYS_DFT_DATASOURCE_ID;
		}
		return datasourceId + "-" + tableName.toLowerCase();
	}
	
	public static final JdbcTemplate jdbcTemplate() {
		return getInstance().getJdbcTemplate();
	}
	
	/**
	 * <p>
	 * 根据数据源ID获取数据库元数据对象 
	 * </p>
	 * 
	 * @param datasourceId 数据源ID
	 * @return 数据库元数据对象 
	 * @since 1.0.0
	 * @author 唐玮琳
	 * @time 2013-11-10 上午10:13:34
	 */
	public static DbMetaData getDbMetaData(String datasourceId) {
		if (StringTool.isBlank(datasourceId)) {
			return null;
		}
		TSysDataSrc ds = JpaTool.get(TSysDataSrc.class, datasourceId);
		return getDbMetaData(ds);
	}
	
	/**
	 * <p>
	 * 根据数据源获取数据库元数据对象 
	 * </p>
	 * 
	 * @param datasource 数据源
	 * @return 数据库元数据对象 
	 * @since 1.0.0
	 * @author 唐玮琳
	 * @time 2013-11-10 上午10:13:34
	 */
	public static DbMetaData getDbMetaData(TSysDataSrc datasource) {
		DbMetaData dbMetaData = null;
		if (datasource != null) {
			Connection conn = null;
			try {
				conn = JdbcTool.getConnectionDirect(datasource);
				DatabaseMetaData metaData = conn.getMetaData();
				dbMetaData = new DbMetaData(metaData);
			} catch (Exception e) {
				logger.error(e.getMessage(), e);
			} finally {
				JdbcTool.closeConnection(conn);
			}
		}
		return dbMetaData;
	}

}

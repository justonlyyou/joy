package com.kvc.joy.core.persistence.jdbc.support.utils;

import com.kvc.joy.commons.lang.string.StringTool;
import com.kvc.joy.commons.log.Log;
import com.kvc.joy.commons.log.LogFactory;
import com.kvc.joy.core.init.support.properties.JoyProperties;
import com.kvc.joy.core.persistence.jdbc.model.vo.*;
import com.kvc.joy.core.persistence.jdbc.support.enums.RdbObjectType;
import com.kvc.joy.core.spring.utils.CoreBeanFactory;
import net.sf.ehcache.CacheException;

import java.sql.Connection;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

/**
 * 关系数据库元数据工具类
 * 
 * @since 1.0.0
 * @author 唐玮琳
 * @time 2013年11月24日 下午10:31:43
 */
public class MdRdbTool {

	protected static final Log logger = LogFactory.getLog(MdRdbTool.class);
	
	private MdRdbTool() {
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
			dsId = JoyProperties.DB_DATASOURCEID;
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
			dbMetaData = new DbMetaData(conn);
		} catch (Exception e) {
			logger.error(e);
		}
		return dbMetaData;
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
	 * 
	 * 
	 * @param conn
	 * @return
	 * @since 1.0.0
	 * @author 唐玮琳
	 * @time 2013年11月24日 下午10:21:13
	 */
	public static Map<String, MdRdbTable> getTables(RdbConnection conn) {
		Map<String, MdRdbTable> tableMap = CoreBeanFactory.getMdRdbTableCacheService().getTables(conn);
		return filterRelationalObject(tableMap);
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
			dsId = JoyProperties.DB_DATASOURCEID;
		}
		RdbConnection connection = new RdbConnection(dsId);
		try {
			return getColumns(connection, tableName);
		} finally {
			JdbcTool.closeConnection(connection.getConnection());
		}
	}
	
	/**
	 * 
	 * 
	 * @param conn
	 * @param tableName
	 * @return
	 * @since 1.0.0
	 * @author 唐玮琳
	 * @time 2013年11月24日 下午10:25:18
	 */
	public static Map<String, MdRdbColumn> getColumns(RdbConnection conn, String tableName) {
		return CoreBeanFactory.getMdRdbColumnCacheService().getColumns(conn, tableName.toLowerCase());
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
			dsId = JoyProperties.DB_DATASOURCEID;
		}
		Map<String, MdRdbColumn> columns = getColumns(dsId, tableName);
		return columns.get(columnName.toLowerCase());
	}
	
	/**
	 * 
	 * 
	 * @param conn
	 * @param tableName
	 * @param columnName
	 * @return
	 * @since 1.0.0
	 * @author 唐玮琳
	 * @time 2013年11月24日 下午10:26:29
	 */
	public static MdRdbColumn getColumn(RdbConnection conn, String tableName, String columnName) {
		Map<String, MdRdbColumn> columns = getColumns(conn, tableName);
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
			dsId = JoyProperties.DB_DATASOURCEID;
		}
		RdbConnection connection = new RdbConnection(dsId);
		try {
			return getPrimaryKey(connection, tableName);
		} finally {
			JdbcTool.closeConnection(connection.getConnection());
		}
	}
	
	/**
	 * 
	 * 
	 * @param conn
	 * @param tableName
	 * @return
	 * @since 1.0.0
	 * @author 唐玮琳
	 * @time 2013年11月24日 下午10:27:14
	 */
	public static MdRdbPrimaryKey getPrimaryKey(RdbConnection conn, String tableName) {
		return CoreBeanFactory.getMdRdbPrimaryKeyCacheService().getPrimaryKey(conn, tableName.toLowerCase());
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
			dsId = JoyProperties.DB_DATASOURCEID;
		}
		RdbConnection connection = new RdbConnection(dsId);
		try {
			return getRelationalObject(connection, name);
		} finally {
			JdbcTool.closeConnection(connection.getConnection());
		}
	}
	
	/**
	 * 
	 * 
	 * @param conn
	 * @param name
	 * @return
	 * @since 1.0.0
	 * @author 唐玮琳
	 * @time 2013年11月24日 下午10:28:22
	 */
	public static MdRdbTable getRelationalObject(RdbConnection conn, String name) {
		Map<String, MdRdbTable> tableMap = CoreBeanFactory.getMdRdbTableCacheService().getTables(conn);
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
			dsId = JoyProperties.DB_DATASOURCEID;
		}
		RdbConnection connection = new RdbConnection(dsId);
		try {
			return getRelationalObjects(connection, objTypes);
		} finally {
			JdbcTool.closeConnection(connection.getConnection());
		}
	}
	
	/**
	 * 
	 * 
	 * @param conn
	 * @param objTypes
	 * @return
	 * @since 1.0.0
	 * @author 唐玮琳
	 * @time 2013年11月24日 下午10:30:04
	 */
	public static Map<String, MdRdbTable> getRelationalObjects(RdbConnection conn, RdbObjectType... objTypes) {
		Map<String, MdRdbTable> tableMap = CoreBeanFactory.getMdRdbTableCacheService().getTables(conn);
		return filterRelationalObject(tableMap, objTypes);
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
	
}

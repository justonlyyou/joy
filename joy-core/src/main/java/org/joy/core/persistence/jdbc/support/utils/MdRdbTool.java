package org.joy.core.persistence.jdbc.support.utils;

import net.sf.ehcache.CacheException;
import org.joy.commons.lang.string.StringTool;
import org.joy.commons.log.Log;
import org.joy.commons.log.LogFactory;
import org.joy.core.init.support.properties.JoyProperties;
import org.joy.core.persistence.jdbc.model.vo.*;
import org.joy.core.persistence.jdbc.support.enums.RdbObjectType;
import org.joy.core.spring.utils.CoreBeanFactory;
import org.springframework.util.LinkedCaseInsensitiveMap;

import java.sql.Connection;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * 关系型数据库元数据工具类
 * 
 * @since 1.0.0
 * @author Kevice
 * @time 2013年11月24日 下午10:31:43
 */
public class MdRdbTool {

	protected static final Log logger = LogFactory.getLog(MdRdbTool.class);
	
	private MdRdbTool() {
	}
	
	/**
	 * 返回指定数据源id的数据库元数据信息
	 * 
	 * @param dsId 数据源id
	 * @return 数据库元数据信息
	 * @since 1.0.0
	 * @author Kevice
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
	 * 返回指定连接对应的数据库元数据信息 <br>
	 * 注：调用者必须自己负责关闭连接
     *
	 * @param conn jdbc连接
	 * @return 数据库元数据信息
	 * @since 1.0.0
	 * @author Kevice
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
	 * 返回给定数据源下的数据库中所有表的所有元数据信息 <br>
     * 注：不包括表中的列信息
	 * 
	 * @param dsId 数据源ID
	 * @return Map<表名, MdRdbTable>
	 * @author Kevice
	 * @time 2012-11-1 下午5:03:48
	 */
	public static Map<String, MdRdbTable> getTables(String dsId) {
		return getRelationalObjects(dsId, RdbObjectType.TABLE);
	}
	
	/**
	 * 返回给定连接对应的数据库中所有表的所有元数据信息 <br>
     * 注：不包括表中的列信息
	 * 
	 * @param conn 连接信息
	 * @return Map<表名, MdRdbTable>
	 * @since 1.0.0
	 * @author Kevice
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
	 * @return Map<列名，MdRdbColumn>
	 * @author Kevice
	 * @time 2012-12-18 下午3:16:14
     * @since 1.0.0
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
	 * 获取表的所有列
	 * 
	 * @param conn 数据库连接
	 * @param tableName 表名
	 * @return Map<列名，MdRdbColumn>
	 * @since 1.0.0
	 * @author Kevice
	 * @time 2013年11月24日 下午10:25:18
	 */
	public static Map<String, MdRdbColumn> getColumns(RdbConnection conn, String tableName) {
		return CoreBeanFactory.getMdRdbColumnCacheService().getColumns(conn, tableName);
	}

	/**
	 * 获取表的某一列
	 * 
	 * @param dsId 数据源id
	 * @param tableName 表名
	 * @param columnName 列名
	 * @return MdRdbColumn
	 * @author Kevice
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
		return columns.get(columnName);
	}
	
	/**
	 * 获取表的某一列
	 * 
	 * @param conn 数据库连接
	 * @param tableName 表名
	 * @param columnName 列名
	 * @return MdRdbColumn
	 * @since 1.0.0
	 * @author Kevice
	 * @time 2013年11月24日 下午10:26:29
	 */
	public static MdRdbColumn getColumn(RdbConnection conn, String tableName, String columnName) {
		Map<String, MdRdbColumn> columns = getColumns(conn, tableName);
		return columns.get(columnName);
	}

	/**
	 * 获取主键
	 * 
	 * @param dsId 数据源id
	 * @param tableName 表名
	 * @return 主键对象
	 * @author Kevice
	 * @time 2012-12-18 下午3:37:42
     * @since 1.0.0
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
	 * 获取主键
	 * 
	 * @param conn 数据库连接
	 * @param tableName 表名
	 * @return 主键对象
	 * @since 1.0.0
	 * @author Kevice
	 * @time 2013年11月24日 下午10:27:14
	 */
	public static MdRdbPrimaryKey getPrimaryKey(RdbConnection conn, String tableName) {
		return CoreBeanFactory.getMdRdbPrimaryKeyCacheService().getPrimaryKey(conn, tableName);
	}
	
	/**
	 * 根据数据源ID、对象名从缓存中取得该对象的元数据信息
	 * 
	 * @param dsId 数据源ID(为空取系统默认数据源id)
	 * @param name 对象名(为空返回null)
	 * @return MdDbTable
	 * @author Kevice
	 * @throws CacheException
	 * @time 2012-12-14 下午4:54:03
     * @since 1.0.0
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
	 * 根据数据库连接、对象名从缓存中取得该对象的元数据信息
	 * 
	 * @param conn 数据库连接
	 * @param name 对象名
	 * @return 对象的元数据信息
	 * @since 1.0.0
	 * @author Kevice
	 * @time 2013年11月24日 下午10:28:22
	 */
	public static MdRdbTable getRelationalObject(RdbConnection conn, String name) {
		Map<String, MdRdbTable> tableMap = CoreBeanFactory.getMdRdbTableCacheService().getTables(conn);
		return tableMap.get(name);
	}
	
	/**
	 * 根据数据源ID、对象类型，从缓存中取得对应表的元数据信息
	 * 
	 * @param dsId 数据源ID(为空取系统默认数据源id)
	 * @param objTypes 对象类型数组
	 * @return Map<String, MdRdbTable>
	 * @throws CacheException
     * @since 1.0.0
	 * @author Kevice
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
	 * 根据数据库连接、对象类型，从缓存中取得对应表的元数据信息
	 * 
	 * @param conn 数据库连接
	 * @param objTypes 对象类型可变数组
	 * @return Map<对象名, MdRdbTable>
	 * @since 1.0.0
	 * @author Kevice
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
		Map<String, MdRdbTable> results = new LinkedCaseInsensitiveMap<MdRdbTable>();
		
		for (MdRdbTable table : tableMap.values()) {
			if (objTypeSet.isEmpty() || objTypeSet.contains(table.getType().toLowerCase())) {
				results.put(table.getName(), table);
			}
		}
		return results;
	}
	
}

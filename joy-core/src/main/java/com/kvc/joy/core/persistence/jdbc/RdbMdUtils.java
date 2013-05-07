package com.kvc.joy.core.persistence.jdbc;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import net.sf.ehcache.CacheException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.kvc.joy.commons.lang.string.StringTool;
import com.kvc.joy.core.persistence.jdbc.model.vo.MdRdbColumn;
import com.kvc.joy.core.persistence.jdbc.model.vo.MdRdbPrimaryKey;
import com.kvc.joy.core.persistence.jdbc.model.vo.MdRdbTable;
import com.kvc.joy.core.persistence.jdbc.support.enums.RdbObjectType;
import com.kvc.joy.core.spring.utils.CoreBeanFactory;
import com.kvc.joy.core.support.consts.BaseSysConsts;

/**
 * 获取数据库元数据信息的工具类
 * 
 * @author <b>唐玮琳</b>
 */
public class RdbMdUtils {

	private static Logger logger = LoggerFactory.getLogger(RdbMdUtils.class);

	private RdbMdUtils() {
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
		return datasourceId + "-" + tableName.toUpperCase();
	}

}

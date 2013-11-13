package com.kvc.joy.core.persistence.jdbc.service.impl;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.kvc.joy.core.persistence.jdbc.model.vo.MdRdbColumn;
import com.kvc.joy.core.persistence.jdbc.model.vo.MdRdbPrimaryKey;
import com.kvc.joy.core.persistence.jdbc.service.IMdRdbPrimaryKeyService;
import com.kvc.joy.core.persistence.jdbc.support.utils.JdbcTool;

/**
 * 
 * @author 唐玮琳
 * @time 2013-1-2 下午11:50:15
 */
public class MdRdbPrimaryKeyService  implements IMdRdbPrimaryKeyService {

	private Logger logger = LoggerFactory.getLogger(getClass());
	
	@Override
	public MdRdbPrimaryKey getPrimaryKeyByDatasourceId(String datasourceId, String tableName) {
		logger.info("加载表字段主键的元数据信息，datasourceId: " + datasourceId + ", tableName: " + tableName);
		Connection conn = JdbcTool.getConnection(datasourceId);
		return getPrimaryKey(conn, tableName, null, datasourceId);
	}
	
	@Override
	public MdRdbPrimaryKey getPrimaryKeyByJndi(String jndi, String tableName) {
		logger.info("加载表字段主键的元数据信息，jndi: " + jndi + ", tableName: " + tableName);
		Connection conn = JdbcTool.getConnectionByJndi(jndi);
		return getPrimaryKey(conn, tableName, jndi, null);
	}
	
	protected MdRdbPrimaryKey getPrimaryKey(Connection conn, String tableName, String jndi, String dsId) {
		tableName = tableName.toLowerCase();
		MdRdbPrimaryKey primaryKey = null;
		try {
			DatabaseMetaData metaData = conn.getMetaData();
			List<String> pks = getPrimaryKey(metaData, tableName);
			for (String pk : pks) {
				if (primaryKey == null) {
					primaryKey = new MdRdbPrimaryKey();
				}
				MdRdbColumn column = null;
				if (jndi == null) {
					column = JdbcTool.getColumnByDatasourceId(dsId, tableName, pk);	
				} else {
					column = JdbcTool.getColumnByJndi(jndi, tableName, pk);	
				}
				primaryKey.getColumns().add(column);
			}
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		} finally {
			JdbcTool.closeConnection(conn);
		}
		return primaryKey;
	}
	
	/**
	 * 获取指定数据源的某张表的主键信息
	 * 
	 * @param metaData 
	 * @param tableName 表名
	 * @return 主键字段名数组
	 * @author 唐玮琳
	 * @throws SQLException
	 * @time 2012-11-9 上午10:22:21
	 */
	static List<String> getPrimaryKey(DatabaseMetaData metaData, String tableName) throws SQLException {
		List<String> pks = new ArrayList<String>(1);
		ResultSet primaryKeys = metaData.getPrimaryKeys(null, metaData.getUserName(), tableName);
		while (primaryKeys.next()) {
			pks.add(primaryKeys.getString("COLUMN_NAME").toLowerCase());
		}
		return pks;
	}

}

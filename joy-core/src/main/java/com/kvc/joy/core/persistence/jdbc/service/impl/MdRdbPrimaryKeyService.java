package com.kvc.joy.core.persistence.jdbc.service.impl;

import com.kvc.joy.commons.log.Log;
import com.kvc.joy.commons.log.LogFactory;
import com.kvc.joy.core.persistence.jdbc.model.vo.MdRdbColumn;
import com.kvc.joy.core.persistence.jdbc.model.vo.MdRdbPrimaryKey;
import com.kvc.joy.core.persistence.jdbc.model.vo.RdbConnection;
import com.kvc.joy.core.persistence.jdbc.service.IMdRdbPrimaryKeyService;
import com.kvc.joy.core.persistence.jdbc.support.utils.MdRdbTool;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * 
 * @since 1.0.0
 * @author 唐玮琳
 * @time 2013-1-2 下午11:50:15
 */
public class MdRdbPrimaryKeyService implements IMdRdbPrimaryKeyService {

	protected static final Log logger = LogFactory.getLog(MdRdbPrimaryKeyService.class);

	@Override
	public MdRdbPrimaryKey getPrimaryKey(RdbConnection connection, String tableName) {
		logger.info("加载表字段主键的元数据信息，datasourceId: " + connection.getDsId() + ", tableName: " + tableName);
		tableName = tableName.toLowerCase();
		MdRdbPrimaryKey primaryKey = null;
		Connection conn = connection.getConnection();
		try {
			DatabaseMetaData metaData = conn.getMetaData();
			List<String> pks = getPrimaryKey(metaData, tableName);
			for (String pk : pks) {
				if (primaryKey == null) {
					primaryKey = new MdRdbPrimaryKey();
				}
				MdRdbColumn column = MdRdbTool.getColumn(connection, tableName, pk);
				primaryKey.getColumns().add(column);
			}
		} catch (Exception e) {
			logger.error(e);
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

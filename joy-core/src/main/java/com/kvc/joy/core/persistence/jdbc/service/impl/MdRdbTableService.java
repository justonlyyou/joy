package com.kvc.joy.core.persistence.jdbc.service.impl;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.kvc.joy.core.persistence.jdbc.model.vo.MdRdbTable;
import com.kvc.joy.core.persistence.jdbc.service.IMdRdbTableService;
import com.kvc.joy.core.persistence.jdbc.support.utils.JdbcUtils;
import com.kvc.joy.core.persistence.orm.jpa.JpaUtils;
import com.kvc.joy.core.sysres.datasrc.model.po.TSysDataSrc;
import com.kvc.joy.core.sysres.datasrc.model.po.TSysDataSrc_;

/**
 * 
 * @author 唐玮琳
 * @time 2013-2-3 下午9:39:00
 */
public class MdRdbTableService implements IMdRdbTableService {

	private Logger logger = LoggerFactory.getLogger(getClass());

	public Map<String, MdRdbTable> get() {
		logger.info("加载所有数据库所有表的元数据信息...");
		Map<String, MdRdbTable> tableMap = new HashMap<String, MdRdbTable>();
		List<TSysDataSrc> dsList = JpaUtils.search(TSysDataSrc.class, TSysDataSrc_.deleted, false);
		for (TSysDataSrc ds : dsList) {
			tableMap.putAll(load(ds));
		}
		return tableMap;
	}

	private Map<String, MdRdbTable> load(TSysDataSrc ds) {
		Map<String, MdRdbTable> tableMap = new HashMap<String, MdRdbTable>();
		Connection conn = null;
		try {
			conn = JdbcUtils.getConnectionDirect(ds);
			DatabaseMetaData metaData = conn.getMetaData();
			ResultSet rsTable = metaData.getTables(null, metaData.getUserName(), null, null);
			while (rsTable.next()) {
				String tableName = rsTable.getString("TABLE_NAME").toUpperCase();
				if (!tableName.startsWith("BIN$")) {
					String tableType = rsTable.getString("TABLE_TYPE").toUpperCase();
					String remarks = rsTable.getString("REMARKS");
					MdRdbTable mdDbTable = new MdRdbTable(tableName, remarks, tableType);
					tableMap.put(getDataobjectKey(ds.getDatasourceId(), tableName), mdDbTable);
				}
			}
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		} finally {
			JdbcUtils.closeConnection(conn);
		}
		return tableMap;
	}

	private static String getDataobjectKey(String datasourceId, String tableName) {
		return datasourceId + "-" + tableName;
	}

	public MdRdbTable get(String key) {
		String[] values = key.split("-");
		String datasourceId = values[0];
		String tableName = values[1];
		Connection conn = null;
		try {
			conn = JdbcUtils.getConnectionDirect(datasourceId);
			DatabaseMetaData metaData = conn.getMetaData();
			ResultSet rsTable = metaData.getTables(null, metaData.getUserName(), tableName, null);
			while (rsTable.next()) {
				String tableType = rsTable.getString("TABLE_TYPE").toUpperCase();
				String remarks = rsTable.getString("REMARKS");
				return new MdRdbTable(tableName, remarks, tableType);
			}
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		} finally {
			JdbcUtils.closeConnection(conn);
		}
		return null;
	}

}

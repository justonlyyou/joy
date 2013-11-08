package com.kvc.joy.core.persistence.jdbc.service.impl;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.kvc.joy.core.persistence.jdbc.model.vo.DbMetaData;
import com.kvc.joy.core.persistence.jdbc.model.vo.MdRdbTable;
import com.kvc.joy.core.persistence.jdbc.service.IMdRdbTableService;
import com.kvc.joy.core.persistence.jdbc.support.enums.RdbType;
import com.kvc.joy.core.persistence.jdbc.support.utils.JdbcTool;
import com.kvc.joy.core.persistence.orm.jpa.JpaTool;
import com.kvc.joy.core.spring.utils.CoreBeanFactory;
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
		List<TSysDataSrc> dsList = JpaTool.search(TSysDataSrc.class, TSysDataSrc_.deleted, false);
		for (TSysDataSrc ds : dsList) {
			tableMap.putAll(load(ds));
		}
		return tableMap;
	}

	private Map<String, MdRdbTable> load(TSysDataSrc ds) {
		Map<String, MdRdbTable> tableMap = new HashMap<String, MdRdbTable>();
		Connection conn = null;
		try {
			conn = JdbcTool.getConnectionDirect(ds);
			DatabaseMetaData metaData = conn.getMetaData();
			DbMetaData dbMetaData = new DbMetaData(metaData);
			RdbType databaseType = dbMetaData.getDatabaseType();
			Map<String, String> tableCommentMap = null;
			if (databaseType == RdbType.MYSQL) {
				tableCommentMap = getMySqlTableComments(dbMetaData);
			}
			ResultSet rsTable = metaData.getTables(null, metaData.getUserName(), null, null);
			while (rsTable.next()) {
				String tableName = rsTable.getString("TABLE_NAME").toLowerCase();
				if (!tableName.startsWith("BIN$")) {
					String tableType = rsTable.getString("TABLE_TYPE").toLowerCase();
					String remarks = null;
					if (tableCommentMap == null) {
						remarks = rsTable.getString("REMARKS");	
					} else {
						remarks = tableCommentMap.get(tableName);
					}
					MdRdbTable mdDbTable = new MdRdbTable(tableName, remarks, tableType);
					tableMap.put(getDataobjectKey(ds.getDatasourceId(), tableName), mdDbTable);
				}
			}
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		} finally {
			JdbcTool.closeConnection(conn);
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
			conn = JdbcTool.getConnectionDirect(datasourceId);
			DatabaseMetaData metaData = conn.getMetaData();
			DbMetaData dbMetaData = new DbMetaData(metaData);
			RdbType databaseType = dbMetaData.getDatabaseType();
			Map<String, String> tableCommentMap = null;
			if (databaseType == RdbType.MYSQL) {
				tableCommentMap = getMySqlTableComments(dbMetaData);
			}
			ResultSet rsTable = metaData.getTables(null, metaData.getUserName(), tableName, null);
			while (rsTable.next()) {
				String tableType = rsTable.getString("TABLE_TYPE").toLowerCase();
				String remarks = null;
				if (tableCommentMap == null) {
					remarks = rsTable.getString("REMARKS");	
				} else {
					remarks = tableCommentMap.get(tableName);
				}
				return new MdRdbTable(tableName, remarks, tableType);
			}
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		} finally {
			JdbcTool.closeConnection(conn);
		}
		return null;
	}

	protected Map<String, String> getMySqlTableComments(DbMetaData dbMetaData) {
		String mySqlSchemaName = dbMetaData.getMySqlSchemaName();
		String sql = "Select TABLE_NAME, TABLE_COMMENT from INFORMATION_SCHEMA.TABLES Where table_schema = '"
				+ mySqlSchemaName + "'";
		List<Map<String, Object>> resultList = CoreBeanFactory.getJdbcTemplate().queryForList(sql);
		Map<String, String> resultMap = new HashMap<String, String>(resultList.size());
		for (Map<String, Object> map : resultList) {
			String tableName = ((String) map.get("TABLE_NAME")).toLowerCase();
			String tableComment = (String) map.get("TABLE_COMMENT");
			resultMap.put(tableName, tableComment);
		}
		return resultMap;
	}

}

package com.kvc.joy.core.persistence.jdbc.service.impl;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.kvc.joy.commons.lang.string.StringTool;
import com.kvc.joy.core.persistence.jdbc.model.vo.DbMetaData;
import com.kvc.joy.core.persistence.jdbc.model.vo.MdRdbTable;
import com.kvc.joy.core.persistence.jdbc.service.IMdRdbTableService;
import com.kvc.joy.core.persistence.jdbc.support.enums.RdbType;
import com.kvc.joy.core.persistence.jdbc.support.utils.JdbcTool;
import com.kvc.joy.core.spring.utils.CoreBeanFactory;

/**
 * 
 * @since 1.0.0
 * @author 唐玮琳
 * @time 2013-2-3 下午9:39:00
 */
public class MdRdbTableService implements IMdRdbTableService {

	private Logger logger = LoggerFactory.getLogger(getClass());

//	@Override
//	public Map<String, MdRdbTable> getTablesByJndi(String jndi) {
//		logger.info("加载表元数据信息，jndi: " + jndi);
//		if (StringTool.isBlank(jndi)) {
//			return new HashMap<String, MdRdbTable>(0);
//		} else {
//			Connection conn = JdbcTool.getConnectionByJndi(jndi);
//			return load(conn);
//		}
//	}
	
	@Override
	public Map<String, MdRdbTable> getTables(String dsId) {
		logger.info("加载表元数据信息，datasourceId: " + dsId);
		if (StringTool.isBlank(dsId)) {
			return new HashMap<String, MdRdbTable>(0);
		} else {
			Connection conn = JdbcTool.getConnectionByDsId(dsId);
			return load(conn);
		}
	}
	
	private Map<String, MdRdbTable> load(Connection conn) {
		Map<String, MdRdbTable> tableMap = new LinkedHashMap<String, MdRdbTable>();
		try {
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
					
					tableMap.put(tableName, mdDbTable);
				}
			}
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		} finally {
			JdbcTool.closeConnection(conn);
		}
		return tableMap;
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

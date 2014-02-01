package com.kvc.joy.core.persistence.jdbc.service.impl;

import com.kvc.joy.commons.log.Log;
import com.kvc.joy.commons.log.LogFactory;
import com.kvc.joy.core.persistence.jdbc.model.vo.MdRdbTable;
import com.kvc.joy.core.persistence.jdbc.model.vo.RdbConnection;
import com.kvc.joy.core.persistence.jdbc.service.IMdRdbTableService;
import com.kvc.joy.core.persistence.jdbc.support.db.DbSupportFactory;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * 
 * @since 1.0.0
 * @author 唐玮琳
 * @time 2013-2-3 下午9:39:00
 */
public class MdRdbTableService implements IMdRdbTableService {

	protected static final Log logger = LogFactory.getLog(MdRdbTableService.class);

	@Override
	public Map<String, MdRdbTable> getTables(RdbConnection connection) {
		String dsId = connection.getDsId();
		logger.info("加载表元数据信息，datasourceId: " + dsId);
		Map<String, MdRdbTable> tableMap = new LinkedHashMap<String, MdRdbTable>() {

			@Override
			public Collection<MdRdbTable> values() {
				return new ArrayList<MdRdbTable>(super.values()); // 父类默认返回的集合不是可序列化的
			}
		};
		try {
			Connection conn = connection.getConnection();
			Map<String, String> tableCommentMap = DbSupportFactory.createDbSupport(conn).getTableComments();
			DatabaseMetaData metaData = conn.getMetaData();
			ResultSet rsTable = metaData.getTables(null, metaData.getUserName(), null, null);
			while (rsTable.next()) {
				String tableName = rsTable.getString("TABLE_NAME").toLowerCase();
				if (!tableName.startsWith("bin$")) {
					String tableType = rsTable.getString("TABLE_TYPE").toLowerCase();
					String remarks = tableCommentMap.get(tableName);
					MdRdbTable mdDbTable = new MdRdbTable(dsId, tableName, remarks, tableType);
					tableMap.put(tableName, mdDbTable);
				}
			}
		} catch (Exception e) {
			logger.error(e);
		}
		return tableMap;
	}

}

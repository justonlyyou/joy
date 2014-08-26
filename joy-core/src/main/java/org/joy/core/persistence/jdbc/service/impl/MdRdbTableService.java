package org.joy.core.persistence.jdbc.service.impl;

import org.joy.commons.log.Log;
import org.joy.commons.log.LogFactory;
import org.joy.core.persistence.jdbc.model.vo.MdRdbTable;
import org.joy.core.persistence.jdbc.model.vo.RdbConnection;
import org.joy.core.persistence.jdbc.service.IMdRdbTableService;
import org.joy.core.persistence.jdbc.support.db.DbSupport;
import org.joy.core.persistence.jdbc.support.db.DbSupportFactory;
import org.joy.core.persistence.jdbc.support.db.Schema;
import org.springframework.util.LinkedCaseInsensitiveMap;

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
 * @author Kevice
 * @time 2013-2-3 下午9:39:00
 */
public class MdRdbTableService implements IMdRdbTableService {

	protected static final Log logger = LogFactory.getLog(MdRdbTableService.class);

	@Override
	public Map<String, MdRdbTable> getTables(RdbConnection connection) {
		String dsId = connection.getDsId();
		logger.info("加载表元数据信息，datasourceId: " + dsId);
		Map<String, MdRdbTable> tableMap = new LinkedCaseInsensitiveMap<MdRdbTable>() {

			@Override
			public Collection<MdRdbTable> values() {
				return new ArrayList<MdRdbTable>(super.values()); // 父类默认返回的集合不是可序列化的
			}
		};
		try {
			Connection conn = connection.getConnection();
            DbSupport dbSupport = DbSupportFactory.createDbSupport(conn);
            Map<String, String> tableCommentMap = dbSupport.getTableComments();
			DatabaseMetaData metaData = conn.getMetaData();
            ResultSet rsTable = metaData.getTables(null, dbSupport.getCurrentSchema().getName(), null, null);
			while (rsTable.next()) {
				String tableName = rsTable.getString("TABLE_NAME");
				if (!tableName.startsWith("bin$")) {
					String tableType = rsTable.getString("TABLE_TYPE");
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

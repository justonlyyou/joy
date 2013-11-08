package com.kvc.joy.core.persistence.jdbc.service.impl;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.kvc.joy.core.persistence.jdbc.model.vo.MdRdbColumn;
import com.kvc.joy.core.persistence.jdbc.model.vo.MdRdbColumnComment;
import com.kvc.joy.core.persistence.jdbc.service.IMdRdbColumnService;
import com.kvc.joy.core.persistence.jdbc.support.MdRdbColumnCommentParser;
import com.kvc.joy.core.persistence.jdbc.support.utils.JdbcTool;

/**
 * 
 * @author 唐玮琳
 * @time 2013-1-2 下午10:38:44
 */
public class MdRdbColumnService implements IMdRdbColumnService {

	private Logger logger = LoggerFactory.getLogger(getClass());
	
	public List<MdRdbColumn> getColumns(String datasourceId, String tableName) {
		tableName = tableName.toLowerCase();
		logger.info("加载表字段元数据信息，datasourceId: " + datasourceId + ", table: " + tableName);
		List<MdRdbColumn> columns = null;
		Connection conn = null;
		try {
			conn = JdbcTool.getConnectionDirect(datasourceId);
			DatabaseMetaData metaData = conn.getMetaData();
			Map<String, MdRdbColumn> columnMap = loadColumns(conn, metaData, tableName);
			columns = new ArrayList<MdRdbColumn>(columnMap.values());
			
			// 设置主键标识
			List<String> pks = MdRdbPrimaryKeyService.getPrimaryKey(metaData, tableName);
			for (String pk : pks) {
				MdRdbColumn column = columnMap.get(pk);
				column.setKey(true);
			}
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		} finally {
			JdbcTool.closeConnection(conn);
		}
		return columns;
	}
	
	private Map<String, MdRdbColumn> loadColumns(Connection conn, DatabaseMetaData metaData, String tableName) throws SQLException {
		Map<String, MdRdbColumn> columnMap = new HashMap<String, MdRdbColumn>();
		ResultSet rs = metaData.getColumns(null, metaData.getUserName(), tableName, null);
		while (rs.next()) {
			MdRdbColumn column = createColumn(rs);
			columnMap.put(column.getName().toLowerCase(), column);
		}
		return columnMap;
	}
	
	/**
	 * 创建数据字段对象
	 * 
	 * @param columns 字段结果集
	 * @return 数据字段对象
	 * @throws SQLException
	 * @author 唐玮琳
	 * @time 2012-11-9 上午10:15:07
	 */
	private static MdRdbColumn createColumn(ResultSet columns) throws SQLException {
		MdRdbColumn column = new MdRdbColumn();
		column.setType(columns.getString("TYPE_NAME"));
		column.setName(columns.getString("COLUMN_NAME").toLowerCase());
		String columnDef = columns.getString("COLUMN_DEF");
		if (columnDef != null) {
			columnDef = columnDef.trim();
			if (columnDef.equals("b'1'")) {
				columnDef = "true";
			} else if (columnDef.equals("b'0'")) {
				columnDef = "false";
			}
		}
		column.setDefaultValue(columnDef);
		String remarks = columns.getString("REMARKS");
		MdRdbColumnComment comment = MdRdbColumnCommentParser.parse(remarks);
		column.setComment(comment);
		int nullable = columns.getInt("NULLABLE");
		switch (nullable) {
		case DatabaseMetaData.columnNoNulls:
			column.setNullable(false);
			break;
		case DatabaseMetaData.columnNullable:
			column.setNullable(true);
			break;
		case DatabaseMetaData.columnNullableUnknown:
			column.setNullable(null);
			break;
		}
		column.setLength(columns.getInt("COLUMN_SIZE"));
		column.setPrecision(new BigDecimal(columns.getInt("DECIMAL_DIGITS")));
		return column;
	}
	
	
}

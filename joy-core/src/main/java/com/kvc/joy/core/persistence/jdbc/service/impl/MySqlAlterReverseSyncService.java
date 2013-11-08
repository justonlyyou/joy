/**
 * 
 */
package com.kvc.joy.core.persistence.jdbc.service.impl;

import java.math.BigDecimal;
import java.text.MessageFormat;

import com.kvc.joy.commons.lang.string.StringTool;
import com.kvc.joy.core.persistence.jdbc.model.vo.MdRdbColumn;
import com.kvc.joy.core.persistence.jdbc.model.vo.MdRdbTable;
import com.kvc.joy.core.persistence.jdbc.service.AbstractMdRdbAlterReverseSyncService;

/**
 * <p>
 * 
 * </p>
 * @since 1.0.0
 * @author 唐玮琳
 * @time 2013-11-8 下午8:00:44
 */
public class MySqlAlterReverseSyncService extends AbstractMdRdbAlterReverseSyncService {
	
	private static final String ALTER_COLUMN_COMMENT_SQL = "ALTER TABLE {0} MODIFY COLUMN {1} {2} {3} {4} COMMENT ''{5}''";
	private static final String ALTER_COLUMN_DEFAULT_VALUE_SQL = "ALTER TABLE {0} ALTER {1} SET DEFAULT {2}";

	@Override
	protected String getAlterColumnCommentSql(MdRdbTable table, MdRdbColumn column, MdRdbColumn columnInDb) {
		String columnName = column.getName();
		String type = columnInDb.getType();
		Integer length = columnInDb.getLength();
		if (length != null) {
			type += "(";
			BigDecimal precision = columnInDb.getPrecision();
			if (precision == null || precision.intValue() == 0) {
				type += length;	
			} else {
				type += length + "," + precision.intValue();
			}
			type += ")";
		}
		String defaultValue = columnInDb.getDefaultValue();
		if (StringTool.isNotBlank(defaultValue)) {
			if(type.startsWith("VARCHAR")) {
				defaultValue = "'" + defaultValue + "'";
			}
			defaultValue = "DEFAULT " + defaultValue; 
		} else {
			defaultValue = "";
		}
		boolean nullable = columnInDb.getNullable() == null || columnInDb.getNullable();  
		String nullableStr = nullable ? "null" : "not null";
		String columnComment = column.getComment().toString();
		return MessageFormat.format(ALTER_COLUMN_COMMENT_SQL, table.getName(), columnName, type, defaultValue, nullableStr, columnComment);
	}

	@Override
	protected String getAlterColumnDefaultValueSql(MdRdbTable table, MdRdbColumn column, MdRdbColumn columnInDb) {
		String columnName = column.getName();
		String defaultValue = column.getDefaultValue();
		String type = columnInDb.getType();
		if(type.startsWith("VARCHAR")) {
			defaultValue = "'" + defaultValue + "'";
		}
		return MessageFormat.format(ALTER_COLUMN_DEFAULT_VALUE_SQL, table.getName(), columnName, defaultValue);
	}
	
}

/**
 * 
 */
package com.kvc.joy.core.persistence.jdbc.service.impl;

import java.text.MessageFormat;

import com.kvc.joy.core.persistence.jdbc.model.vo.MdRdbColumn;
import com.kvc.joy.core.persistence.jdbc.model.vo.MdRdbTable;
import com.kvc.joy.core.persistence.jdbc.service.AbstractMdRdbAlterReverseSyncService;

/**
 * <p>
 * 
 * </p>
 * 
 * @since 1.0.0
 * @author 唐玮琳
 * @time 2013-11-8 下午8:00:19
 */
public class OracleAlterReverseSyncService extends AbstractMdRdbAlterReverseSyncService {

	private static final String ALTER_COLUMN_COMMENT_SQL = "ALTER TABLE {0} CHANGE ''{1}'' COMMENT ''{2}'';";
	private static final String ALTER_COLUMN_DEFAULT_VALUE_SQL = "ALTER TABLE {0} MODIFY {1} DEFAULT {2};";

	@Override
	protected String getAlterColumnCommentSql(MdRdbTable table, MdRdbColumn column, MdRdbColumn columnInDb) {
		String columnName = column.getName();
		String columnComment = column.getComment().toString();
		return MessageFormat.format(ALTER_COLUMN_COMMENT_SQL, table.getName(), columnName, columnComment);
	}
	
	@Override
	protected String getAlterColumnDefaultValueSql(MdRdbTable table, MdRdbColumn column, MdRdbColumn columnInDb) {
		String columnName = column.getName();
		String defaultValue = column.getDefaultValue();
		return MessageFormat.format(ALTER_COLUMN_DEFAULT_VALUE_SQL, table.getName(), columnName, defaultValue);
	}

}

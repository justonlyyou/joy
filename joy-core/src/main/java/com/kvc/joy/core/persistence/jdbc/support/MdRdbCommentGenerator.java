package com.kvc.joy.core.persistence.jdbc.support;

import java.text.MessageFormat;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.kvc.joy.commons.collections.CollectionTool;
import com.kvc.joy.commons.lang.string.StringTool;
import com.kvc.joy.core.persistence.jdbc.RdbMdUtils;
import com.kvc.joy.core.persistence.jdbc.model.vo.MdRdbColumn;
import com.kvc.joy.core.persistence.jdbc.model.vo.MdRdbColumnComment;
import com.kvc.joy.core.persistence.jdbc.model.vo.MdRdbTable;
import com.kvc.joy.core.persistence.jdbc.support.utils.JdbcUtils;

/**
 * 
 * @since 1.0.0
 * @author 唐玮琳
 * @time 2013-4-10 下午8:15:48
 */
public class MdRdbCommentGenerator {

//	private static final String TABLE_COMMENT_SQL = "comment on table {0} is ''{1}''";
//	private static final String COLUMN_COMMENT_SQL = "comment on column {0}.{1} is ''{2}''";
	
	private static final String TABLE_COMMENT_SQL = "ALTER TABLE {0} COMMENT ''{1}''";
	private static final String COLUMN_COMMENT_SQL = "ALTER TABLE {0} CHANGE ''{1}'' COMMENT ''{2}''";
//	ALTER TABLE T_SYS_MENU modify TEXT VARCHAR(64) not null COMMENT 'ddd'

	public static void generate() {
		List<MdRdbTable> tables = MdRdbCommentScanner.scan();
		Set<String> sqlSet = genTableCommentSql(tables);
		sqlSet.addAll(genColumnCommentSql(tables));
		executeSql(sqlSet);
	}

	private static Set<String> genTableCommentSql(List<MdRdbTable> tables) {
		Set<String> sqlSet = new HashSet<String>();
		List<MdRdbTable> tablesInDb = RdbMdUtils.getTables("JoyDs");
		Map<Object, MdRdbTable> tableMap = CollectionTool.toEntityMap(tablesInDb, "name");
		for (MdRdbTable table : tables) {
			String tableComment = table.getComment();
			if (StringTool.isNotBlank(tableComment)) {
				String tableName = table.getName();
				String tableCommentInDb = tableMap.get(tableName).getComment();
				if (StringTool.equals(tableComment, tableCommentInDb) == false) {
					sqlSet.add(MessageFormat.format(TABLE_COMMENT_SQL, tableName, tableComment));
				}
			}
		}
		return sqlSet;
	}

	private static Set<String> genColumnCommentSql(List<MdRdbTable> tables) {
		Set<String> sqlSet = new HashSet<String>();
		for (MdRdbTable table : tables) {
			List<MdRdbColumn> columnsInDb = RdbMdUtils.getColumns("JoyDs", table.getName());
			Map<Object, MdRdbColumn> columnMap = CollectionTool.toEntityMap(columnsInDb, "name");

			Collection<MdRdbColumn> columns = table.getColumns();
			for (MdRdbColumn column : columns) {
				String columnComment = column.getComment().toString();
				if (StringTool.isNotBlank(columnComment)) {
					String columnName = column.getName();
					MdRdbColumnComment comment = columnMap.get(columnName).getComment();
					String columnCommentInDb = comment == null ? "" : comment.toString();
					if (StringTool.equals(columnComment, columnCommentInDb) == false) {
						sqlSet.add(MessageFormat.format(COLUMN_COMMENT_SQL, table.getName(), columnName, columnComment));
					}
				}
			}
		}
		return sqlSet;
	}
	
	protected static void executeSql(Set<String> sqlSet) {
		String[] sqls = sqlSet.toArray(new String[0]);
		JdbcUtils.jdbcTemplate().batchUpdate(sqls);
	}

}

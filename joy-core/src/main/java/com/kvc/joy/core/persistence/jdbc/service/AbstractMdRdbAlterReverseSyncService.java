package com.kvc.joy.core.persistence.jdbc.service;

import java.text.MessageFormat;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.annotation.Transactional;

import com.kvc.joy.commons.collections.CollectionTool;
import com.kvc.joy.commons.lang.string.StringTool;
import com.kvc.joy.core.init.support.JoyPropeties;
import com.kvc.joy.core.persistence.jdbc.model.vo.MdRdbColumn;
import com.kvc.joy.core.persistence.jdbc.model.vo.MdRdbColumnComment;
import com.kvc.joy.core.persistence.jdbc.model.vo.MdRdbTable;
import com.kvc.joy.core.persistence.jdbc.support.EntityCommentAndDefaultValueScanner;
import com.kvc.joy.core.persistence.jdbc.support.utils.JdbcTool;

/**
 * 
 * @since 1.0.0
 * @author 唐玮琳
 * @time 2013-4-10 下午8:15:48
 */
public abstract class AbstractMdRdbAlterReverseSyncService implements IMdRdbAlterReverseSyncService {


	private Logger logger = LoggerFactory.getLogger(getClass());
	private static final String TABLE_COMMENT_SQL = "ALTER TABLE {0} COMMENT ''{1}''";

	@Transactional
	public void generate(String... pkgs) {
		List<MdRdbTable> tables = EntityCommentAndDefaultValueScanner.scan(pkgs);
		Set<String> sqlSet = genTableCommentSql(tables);
		sqlSet.addAll(genColumnCommentSql(tables));
		executeSql(sqlSet);
	}

	protected Set<String> genTableCommentSql(List<MdRdbTable> tables) {
		Set<String> sqlSet = new HashSet<String>();
		List<MdRdbTable> tablesInDb = JdbcTool.getTables(JoyPropeties.DB_DATASOURCEID);
		Map<Object, MdRdbTable> tableMap = CollectionTool.toEntityMap(tablesInDb, "name");
		for (MdRdbTable table : tables) {
			String tableComment = table.getComment();
			if (StringTool.isNotBlank(tableComment)) {
				String tableName = table.getName();
				MdRdbTable tableInDb = tableMap.get(tableName);
				String tableCommentInDb = tableInDb.getComment();
				if (StringTool.equals(tableComment, tableCommentInDb) == false) {
					tableInDb.setComment(tableComment);
					sqlSet.add(getAlterTableCommentSql(table));
				}
			}
		}
		return sqlSet;
	}
	
	protected String getAlterTableCommentSql(MdRdbTable table) {
		String tableName = table.getName();
		String tableComment = table.getComment();
		return MessageFormat.format(TABLE_COMMENT_SQL, tableName, tableComment);
	}

	protected Set<String> genColumnCommentSql(List<MdRdbTable> tables) {
		Set<String> sqlSet = new HashSet<String>();
		for (MdRdbTable table : tables) {
			List<MdRdbColumn> columnsInDb = JdbcTool.getColumns(JoyPropeties.DB_DATASOURCEID, table.getName());
			Map<Object, MdRdbColumn> columnMap = CollectionTool.toEntityMap(columnsInDb, "name");

			Collection<MdRdbColumn> columns = table.getColumns();
			for (MdRdbColumn column : columns) {
				String columnName = column.getName();
				MdRdbColumn columnInDb = columnMap.get(columnName);
				if (columnInDb == null) {
					logger.error("列不存在: " + table.getName() + "." + columnName);
					continue;
				}
				
				// comment
				MdRdbColumnComment comment = column.getComment();
				String columnComment = comment.toString();
				MdRdbColumnComment commentInDb = columnInDb.getComment();
				String columnCommentInDb = commentInDb == null ? "" : commentInDb.toString();
				if (StringTool.equals(columnComment, columnCommentInDb) == false) {
					columnInDb.setComment(comment);
					sqlSet.add(getAlterColumnCommentSql(table, column, columnInDb));
				}
				
				// default value
				String defaultValue = column.getDefaultValue();
				String defaultValueInDb = columnInDb.getDefaultValue();
				if (StringTool.equals(defaultValue, defaultValueInDb) == false) {
					columnInDb.setDefaultValue(defaultValueInDb);
					sqlSet.add(getAlterColumnDefaultValueSql(table, column, columnInDb));
				}
			}
		}
		return sqlSet;
	}
	
	protected abstract String getAlterColumnCommentSql(MdRdbTable table, MdRdbColumn column, MdRdbColumn columnInDb);
	
	protected abstract String getAlterColumnDefaultValueSql(MdRdbTable table, MdRdbColumn column, MdRdbColumn columnInDb);
	
	protected void executeSql(Set<String> sqlSet) {
		String[] sqls = sqlSet.toArray(new String[0]);
		if(logger.isDebugEnabled()) {
			for (String sql : sqls) {
				logger.debug("修改表结构: " + sql);
			}	
		}
		if (sqls.length == 0) {
			logger.info("表结构未改变，不用同步数据库。");
		} else {
			JdbcTool.jdbcTemplate().batchUpdate(sqls);	
		}
	}

}

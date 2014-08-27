package org.joy.core.persistence.jdbc.support.db.mysql;

import org.joy.commons.exception.SystemException;
import org.joy.commons.lang.ArrayTool;
import org.joy.commons.lang.string.StringTool;
import org.joy.core.persistence.jdbc.model.vo.MdRdbColumn;
import org.joy.core.persistence.jdbc.model.vo.MdRdbTable;
import org.joy.core.persistence.jdbc.support.db.DbSupport;
import org.joy.core.persistence.jdbc.support.db.Schema;
import org.joy.core.persistence.jdbc.support.db.SqlStatementBuilder;
import org.joy.core.persistence.jdbc.support.enums.RdbType;
import org.joy.core.persistence.jdbc.support.utils.JdbcTool;
import org.springframework.util.LinkedCaseInsensitiveMap;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Types;
import java.text.MessageFormat;
import java.util.List;
import java.util.Map;

/**
 * Mysql-specific support.
 */
public class MySqlDbSupport extends DbSupport {
	
	private static final String TABLE_COMMENT_SQL = "ALTER TABLE {0} COMMENT ''{1}'';";
	private static final String ALTER_COLUMN_COMMENT_SQL = "ALTER TABLE {0} MODIFY COLUMN {1} {2} {3} {4} COMMENT ''{5}'';";
	private static final String ALTER_COLUMN_DEFAULT_VALUE_SQL = "ALTER TABLE {0} ALTER {1} SET DEFAULT {2};";
	
    /**
     * Creates a new instance.
     *
     * @param connection The connection to use.
     */
    public MySqlDbSupport(Connection connection) {
        super(connection);
    }

    public String getScriptLocation() {
        return "com/googlecode/flyway/core/dbsupport/mysql/";
    }

    public String getCurrentUserFunction() {
        return "SUBSTRING_INDEX(USER(),'@',1)";
    }

    @Override
    protected String doGetCurrentSchema() {
        try {
			return connection.getCatalog();
		} catch (SQLException e) {
			throw new SystemException(e);
		}
    }

    @Override
    protected void doSetCurrentSchema(Schema schema)  {
        JdbcTool.execute(connection, "USE " + schema);
    }

    public boolean supportsDdlTransactions() {
        return false;
    }

    public String getBooleanTrue() {
        return "1";
    }

    public String getBooleanFalse() {
        return "0";
    }

    public SqlStatementBuilder createSqlStatementBuilder() {
        return new MySqlSqlStatementBuilder();
    }

    @Override
    public String doQuote(String identifier) {
        return "`" + identifier + "`";
    }

    @Override
    public Schema getSchema(String name) {
        return new MySqlSchema(this, name);
    }

    @Override
    public boolean catalogIsSchema() {
        return true;
    }
    
    @Override
    public String getSysDateFunction() {
    	return "now()";
    }
    
    @Override
    public String getDateToStringFunction(String dbDateFunc, String javaDateFormat) {
    	return new MySqlDateFormatter().format(dbDateFunc, javaDateFormat);
    }
    
    @Override
	public int getNullType() {
		return Types.VARCHAR;
	}
    
    @Override
    public String getAlterTableCommentSql(MdRdbTable table) {
		String tableName = table.getName();
		String tableComment = table.getComment();
		return MessageFormat.format(TABLE_COMMENT_SQL, tableName, tableComment);
	}
    
    @Override
	public String getAlterColumnCommentSql(MdRdbTable table, MdRdbColumn column, MdRdbColumn columnInDb) {
		String columnName = column.getName();
		String type = columnInDb.getType().toLowerCase();
		Integer length = columnInDb.getLength();
		if (length != null && type.startsWith("date") == false) {
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
			if(type.startsWith("varchar")) {
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
	public String getAlterColumnDefaultValueSql(MdRdbTable table, MdRdbColumn column, MdRdbColumn columnInDb) {
		String columnName = column.getName();
		String defaultValue = column.getDefaultValue();
		String type = columnInDb.getType().toLowerCase();
        if(type.contains("char")) {
            defaultValue = "'" + defaultValue + "'";
        }
		return MessageFormat.format(ALTER_COLUMN_DEFAULT_VALUE_SQL, table.getName(), columnName, defaultValue);
	}
	
	@Override
	public Map<String, String> getTableComments(String... tableNames) {
		String tableNameCondition = "";
		if (ArrayTool.isNotEmpty(tableNames)) {
			StringBuilder sb = new StringBuilder(" and TABLE_NAME in(");
			for (String tableName : tableNames) {
				sb.append("'").append(tableName.toLowerCase()).append("'");
			}
			tableNameCondition = sb.toString();
		}
		
		try {
			String sql = "Select TABLE_NAME, TABLE_COMMENT from INFORMATION_SCHEMA.TABLES Where table_schema = '"
					+ getCurrentSchema().getName() + "'" + tableNameCondition;
			List<Map<String, String>> resultList = JdbcTool.queryForList(connection, sql);
			Map<String, String> resultMap = new LinkedCaseInsensitiveMap<String>(resultList.size());
			for (Map<String, String> map : resultList) {
				String tableName = map.get("TABLE_NAME");
				String tableComment = map.get("TABLE_COMMENT");
				resultMap.put(tableName, tableComment);
			}
			return resultMap;
		} catch (Exception e) {
			throw new SystemException(e);
		}
	}

    @Override
    public RdbType getDatabaseType() {
        return RdbType.MYSQL;
    }

}

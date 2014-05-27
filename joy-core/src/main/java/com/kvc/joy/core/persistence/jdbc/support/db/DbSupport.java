package com.kvc.joy.core.persistence.jdbc.support.db;

import com.kvc.joy.commons.exception.SystemException;
import com.kvc.joy.commons.lang.ArrayTool;
import com.kvc.joy.core.persistence.jdbc.model.vo.MdRdbColumn;
import com.kvc.joy.core.persistence.jdbc.model.vo.MdRdbTable;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * Abstraction for database-specific functionality.
 */
public abstract class DbSupport {
	/**
	 * The JDBC template available for use.
	 */
	protected final Connection connection;

	/**
	 */
	public DbSupport(Connection connection) {
		this.connection = connection;
	}

	/**
	 * Retrieves the schema with this name in the database.
	 * 
	 * @param name The name of the schema.
	 * @return The schema.
	 */
	public abstract Schema getSchema(String name);

	/**
	 * Creates a new SqlStatementBuilder for this specific database.
	 * 
	 * @return The new SqlStatementBuilder.
	 */
	public abstract SqlStatementBuilder createSqlStatementBuilder();

	/**
	 * Returns the location on the classpath where the scripts for this database
	 * reside.
	 * 
	 * @return The folder on the classpath, including a trailing slash.
	 */
	public abstract String getScriptLocation();

	/**
	 * Retrieves the current schema.
	 * 
	 * @return The current schema for this connection.
	 */
	public Schema getCurrentSchema() {
		String schemaName = doGetCurrentSchema();
		if (schemaName == null) {
			return null;
		}

		return getSchema(schemaName);
	}

	/**
	 * Retrieves the current schema.
	 * 
	 * @return The current schema for this connection.
	 * @throws SQLException when the current schema could not be retrieved.
	 */
	protected abstract String doGetCurrentSchema();

	/**
	 * Sets the current schema to this schema.
	 * 
	 * @param schema The new current schema for this connection.
	 */
	public void setCurrentSchema(Schema schema) {
		doSetCurrentSchema(schema);
	}

	/**
	 * Sets the current schema to this schema.
	 * 
	 * @param schema The new current schema for this connection.
	 * @throws SQLException when the current schema could not be set.
	 */
	protected abstract void doSetCurrentSchema(Schema schema);

	/**
	 * @return The database function that returns the current user.
	 */
	public abstract String getCurrentUserFunction();

	/**
	 * Checks whether ddl transactions are supported for this database.
	 * 
	 * @return {@code true} if ddl transactions are supported, {@code false} if
	 *         not.
	 */
	public abstract boolean supportsDdlTransactions();

	/**
	 * @return The representation of the value {@code true} in a boolean column.
	 */
	public abstract String getBooleanTrue();

	/**
	 * @return The representation of the value {@code false} in a boolean
	 *         column.
	 */
	public abstract String getBooleanFalse();

	/**
	 * Quote these identifiers for use in sql queries. Multiple identifiers will
	 * be quoted and separated by a dot.
	 * 
	 * @param identifiers The identifiers to quote.
	 * @return The fully qualified quoted identifiers.
	 */
	public String quote(String... identifiers) {
		String result = "";

		boolean first = true;
		for (String identifier : identifiers) {
			if (!first) {
				result += ".";
			}
			first = false;
			result += doQuote(identifier);
		}

		return result;
	}

	/**
	 * Quote this identifier for use in sql queries.
	 * 
	 * @param identifier The identifier to quote.
	 * @return The fully qualified quoted identifier.
	 */
	protected abstract String doQuote(String identifier);

	/**
	 * @return {@code true} if this database use a catalog to represent a
	 *         schema. {@code false} if a schema is simply a schema.
	 */
	public abstract boolean catalogIsSchema();

	/**
	 * 获取数据库当前时间的函数名
	 * 
	 * @return
	 * @since 1.0.0
	 * @author Kevice
	 * @time 2013年11月24日 上午10:58:48
	 */
	public String getSysDateFunction() {
		throw new UnsupportedOperationException();
	}

	/**
	 * 给定数据库日期函数和java日期格式，返回日期转为字符串的函数
	 * 
	 * @param dbDateFunc
	 * @param javaDateFormat
	 * @return
	 * @since 1.0.0
	 * @author Kevice
	 * @time 2013年11月24日 上午11:23:44
	 */
	public String getDateToStringFunction(String dbDateFunc, String javaDateFormat) {
		throw new UnsupportedOperationException();
	}

	public Connection getConnection() {
		return connection;
	}

	/**
	 * 
	 * 
	 * @return
	 * @since 1.0.0
	 * @author Kevice
	 * @time 2013年11月24日 下午3:55:15
	 */
	public abstract int getNullType();

	/**
	 * 
	 * 
	 * @param table
	 * @return
	 * @since 1.0.0
	 * @author Kevice
	 * @time 2013年11月24日 下午9:06:31
	 */
	public String getAlterTableCommentSql(MdRdbTable table) {
		throw new UnsupportedOperationException();
	}
	
	/**
	 * 
	 * 
	 * @param table
	 * @param column
	 * @param columnInDb
	 * @return
	 * @since 1.0.0
	 * @author Kevice
	 * @time 2013年11月24日 下午9:00:09
	 */
	public String getAlterColumnCommentSql(MdRdbTable table, MdRdbColumn column, MdRdbColumn columnInDb) {
		throw new UnsupportedOperationException();
	}
	
	/**
	 * 
	 * 
	 * @param table
	 * @param column
	 * @param columnInDb
	 * @return
	 * @since 1.0.0
	 * @author Kevice
	 * @time 2013年11月24日 下午9:01:33
	 */
	public String getAlterColumnDefaultValueSql(MdRdbTable table, MdRdbColumn column, MdRdbColumn columnInDb) {
		throw new UnsupportedOperationException();
	}
	
	/**
	 * 
	 * 
	 * @param tableNames
	 * @return
	 * @since 1.0.0
	 * @author Kevice
	 * @time 2013年11月24日 下午9:38:57
	 */
	public Map<String, String> getTableComments(String... tableNames) {
		Set<String> tableNameSet = null;
		if (ArrayTool.isNotEmpty(tableNames)) {
			tableNameSet = new HashSet<String>(tableNames.length);
			for (String tableName : tableNames) {
				tableNameSet.add(tableName.toLowerCase());
			}
		}
		Map<String, String> commentMap = new HashMap<String, String>();
		try {
			DatabaseMetaData metaData = connection.getMetaData();
			ResultSet rsTable = metaData.getTables(null, metaData.getUserName(), null, null);
			while (rsTable.next()) {
				String tableName = rsTable.getString("TABLE_NAME").toLowerCase();
				if ((tableNameSet == null || tableNameSet.contains(tableName)) && !tableName.startsWith("bin$")) {
					String remarks = rsTable.getString("REMARKS");
					commentMap.put(tableName, remarks);
				}
			}
		} catch (Exception e) {
			throw new SystemException(e);
		}
		return commentMap;
	}

}

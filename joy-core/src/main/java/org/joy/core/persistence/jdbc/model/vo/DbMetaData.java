/**
 * 
 */
package org.joy.core.persistence.jdbc.model.vo;

import org.joy.core.persistence.jdbc.support.db.DbSupport;
import org.joy.core.persistence.jdbc.support.db.DbSupportFactory;
import org.joy.core.persistence.jdbc.support.enums.RdbType;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.SQLException;

/**
 * 关系型数据库元数据信息
 *
 * @author Kevice
 * @time 2013-11-10 上午10:49:00
 * @since 1.0.0
 */
public class DbMetaData {

	private String databaseProductName; // 数据库产品名称
	private String driverName; // 驱动名称
	private String userName; // 用户名
	private String catalogSeparator; // catalog名称和表名间的分隔符
	private String catalogTerm; // 数据库厂商对catalog的术语
	private String databaseProductVersion; // 数据库产品版本
	private String driverVersion; // 驱动版本
	private String extraNameCharacters; // 除了数字、字母、下划线外，能当作标识符(非引号中)的字符集
	private String identifierQuoteString; // 引号(对sql)字符串，如果不支持返回空格
	private String numericFunctions; // 支持的数学函数列表（以半角逗号分隔）
	private String procedureTerm; // 数据库厂商对存储过程的术语
	private String schemaTerm; // 数据库厂商对schema的术语
	private String searchStringEscape; // 查询时用于转义通配符的字符串
	private String sqlKeywords; // sql关键字列表（以半角逗号分隔）
	private String stringFunctions; // 支持字符串处理函数列表（以半角逗号分隔）
	private String systemFunctions; // 支持系统函数列表（以半角逗号分隔）
	private String timeDateFunctions; // 支持时间函数列表（以半角逗号分隔）
	private String url; // 连接地址
	private String currentSchema; // 当前schema
	private int databaseMajorVersion; // 数据库主版本号
	private int databaseMinorVersion; // 数据库小版本号
	private int defaultTransactionIsolation; // 默认的事务隔离级别
	private int driverMajorVersion; // 驱动的主版本号
	private int driverMinorVersion; // 驱动的小版本号
	private int maxBinaryLiteralLength; // 允许在内嵌二进制字面值中使用的最大十六进制字符数,为零意味着没有限制或限制是未知的
	private int maxCatalogNameLength; // 目录名称的最大长度
	private int maxCharLiteralLength; // 字符文字的最大长度
	private int maxColumnNameLength; // 列名最大长度
	private int maxColumnsInGroupBy; // 分组的最大列数
	private int maxColumnsInIndex; // 索引的最大列数
	private int maxColumnsInOrderBy; // 排序的最大列数
	private int maxColumnsInSelect; // 一次查询返回的最大列数
	private int maxColumnsInTable; // 一个表中的最大列数
	private int maxConnections; // 最大连接数
	private int maxCursorNameLength; // 油标名称最大长度
	private int maxIndexLength; // 索引名称最大长度
	private int maxProcedureNameLength; // 存储过程名称最大长度
	private int maxRowSize; // 最大行大小
	private int maxSchemaNameLength; // schema名称最大长度
	private int maxStatementLength; // 最大的语句长度
	private int maxStatements; // 最大的语句数
	private int maxTableNameLength; // 表名最大长度
	private int maxTablesInSelect; // 一次查询最多的表个数
	private int maxUserNameLength; // 用户名称最大长度
	private int resultSetHoldability; // 结果集持久性
	private int sqlStateType; // sql状态类型

	public DbMetaData() {
	}

	public DbMetaData(Connection conn) throws SQLException {
		DatabaseMetaData databaseMetaData = conn.getMetaData();
		databaseProductName = databaseMetaData.getDatabaseProductName();
		driverName = databaseMetaData.getDriverName();
		userName = databaseMetaData.getUserName();
		catalogSeparator = databaseMetaData.getCatalogSeparator();
		catalogTerm = databaseMetaData.getCatalogTerm();
		databaseMajorVersion = databaseMetaData.getDatabaseMajorVersion();
		databaseMinorVersion = databaseMetaData.getDatabaseMinorVersion();
		databaseProductVersion = databaseMetaData.getDatabaseProductVersion();
		defaultTransactionIsolation = databaseMetaData.getDefaultTransactionIsolation();
		driverMajorVersion = databaseMetaData.getDriverMajorVersion();
		driverMinorVersion = databaseMetaData.getDriverMinorVersion();
		driverVersion = databaseMetaData.getDriverVersion();
		extraNameCharacters = databaseMetaData.getExtraNameCharacters();
		identifierQuoteString = databaseMetaData.getIdentifierQuoteString();
		maxBinaryLiteralLength = databaseMetaData.getMaxBinaryLiteralLength();
		maxCatalogNameLength = databaseMetaData.getMaxCatalogNameLength();
		maxCharLiteralLength = databaseMetaData.getMaxCharLiteralLength();
		maxColumnNameLength = databaseMetaData.getMaxColumnNameLength();
		maxColumnsInGroupBy = databaseMetaData.getMaxColumnsInGroupBy();
		maxColumnsInIndex = databaseMetaData.getMaxColumnsInIndex();
		maxColumnsInOrderBy = databaseMetaData.getMaxColumnsInOrderBy();
		maxColumnsInSelect = databaseMetaData.getMaxColumnsInSelect();
		maxColumnsInTable = databaseMetaData.getMaxColumnsInTable();
		maxConnections = databaseMetaData.getMaxConnections();
		maxCursorNameLength = databaseMetaData.getMaxCursorNameLength();
		maxIndexLength = databaseMetaData.getMaxIndexLength();
		maxProcedureNameLength = databaseMetaData.getMaxProcedureNameLength();
		maxRowSize = databaseMetaData.getMaxRowSize();
		maxSchemaNameLength = databaseMetaData.getMaxSchemaNameLength();
		maxStatementLength = databaseMetaData.getMaxStatementLength();
		maxStatements = databaseMetaData.getMaxStatements();
		maxTableNameLength = databaseMetaData.getMaxTableNameLength();
		maxTablesInSelect = databaseMetaData.getMaxTablesInSelect();
		maxUserNameLength = databaseMetaData.getMaxUserNameLength();
		numericFunctions = databaseMetaData.getNumericFunctions();
		procedureTerm = databaseMetaData.getProcedureTerm();
		resultSetHoldability = databaseMetaData.getResultSetHoldability();
		schemaTerm = databaseMetaData.getSchemaTerm();
		searchStringEscape = databaseMetaData.getSearchStringEscape();
		sqlKeywords = databaseMetaData.getSQLKeywords();
		sqlStateType = databaseMetaData.getSQLStateType();
		stringFunctions = databaseMetaData.getStringFunctions();
		systemFunctions = databaseMetaData.getSystemFunctions();
		timeDateFunctions = databaseMetaData.getTimeDateFunctions();
		url = databaseMetaData.getURL();
		DbSupport db = DbSupportFactory.createDbSupport(conn);
		currentSchema = db.getCurrentSchema().getName();
	}

	public String getDatabaseProductName() {
		return databaseProductName;
	}

	public void setDatabaseProductName(String databaseProductName) {
		this.databaseProductName = databaseProductName;
	}

	public String getDriverName() {
		return driverName;
	}

	public void setDriverName(String driverName) {
		this.driverName = driverName;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getCatalogSeparator() {
		return catalogSeparator;
	}

	public void setCatalogSeparator(String catalogSeparator) {
		this.catalogSeparator = catalogSeparator;
	}

	public String getCatalogTerm() {
		return catalogTerm;
	}

	public void setCatalogTerm(String catalogTerm) {
		this.catalogTerm = catalogTerm;
	}

	public String getDatabaseProductVersion() {
		return databaseProductVersion;
	}

	public void setDatabaseProductVersion(String databaseProductVersion) {
		this.databaseProductVersion = databaseProductVersion;
	}

	public String getDriverVersion() {
		return driverVersion;
	}

	public void setDriverVersion(String driverVersion) {
		this.driverVersion = driverVersion;
	}

	public String getExtraNameCharacters() {
		return extraNameCharacters;
	}

	public void setExtraNameCharacters(String extraNameCharacters) {
		this.extraNameCharacters = extraNameCharacters;
	}

	public String getIdentifierQuoteString() {
		return identifierQuoteString;
	}

	public void setIdentifierQuoteString(String identifierQuoteString) {
		this.identifierQuoteString = identifierQuoteString;
	}

	public String getNumericFunctions() {
		return numericFunctions;
	}

	public void setNumericFunctions(String numericFunctions) {
		this.numericFunctions = numericFunctions;
	}

	public String getProcedureTerm() {
		return procedureTerm;
	}

	public void setProcedureTerm(String procedureTerm) {
		this.procedureTerm = procedureTerm;
	}

	public String getSchemaTerm() {
		return schemaTerm;
	}

	public void setSchemaTerm(String schemaTerm) {
		this.schemaTerm = schemaTerm;
	}

	public String getSearchStringEscape() {
		return searchStringEscape;
	}

	public void setSearchStringEscape(String searchStringEscape) {
		this.searchStringEscape = searchStringEscape;
	}

	public String getSqlKeywords() {
		return sqlKeywords;
	}

	public void setSqlKeywords(String sqlKeywords) {
		this.sqlKeywords = sqlKeywords;
	}

	public String getStringFunctions() {
		return stringFunctions;
	}

	public void setStringFunctions(String stringFunctions) {
		this.stringFunctions = stringFunctions;
	}

	public String getSystemFunctions() {
		return systemFunctions;
	}

	public void setSystemFunctions(String systemFunctions) {
		this.systemFunctions = systemFunctions;
	}

	public String getTimeDateFunctions() {
		return timeDateFunctions;
	}

	public void setTimeDateFunctions(String timeDateFunctions) {
		this.timeDateFunctions = timeDateFunctions;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public int getDatabaseMajorVersion() {
		return databaseMajorVersion;
	}

	public void setDatabaseMajorVersion(int databaseMajorVersion) {
		this.databaseMajorVersion = databaseMajorVersion;
	}

	public int getDatabaseMinorVersion() {
		return databaseMinorVersion;
	}

	public void setDatabaseMinorVersion(int databaseMinorVersion) {
		this.databaseMinorVersion = databaseMinorVersion;
	}

	public int getDefaultTransactionIsolation() {
		return defaultTransactionIsolation;
	}

	public void setDefaultTransactionIsolation(int defaultTransactionIsolation) {
		this.defaultTransactionIsolation = defaultTransactionIsolation;
	}

	public int getDriverMajorVersion() {
		return driverMajorVersion;
	}

	public void setDriverMajorVersion(int driverMajorVersion) {
		this.driverMajorVersion = driverMajorVersion;
	}

	public int getDriverMinorVersion() {
		return driverMinorVersion;
	}

	public void setDriverMinorVersion(int driverMinorVersion) {
		this.driverMinorVersion = driverMinorVersion;
	}

	public int getMaxBinaryLiteralLength() {
		return maxBinaryLiteralLength;
	}

	public void setMaxBinaryLiteralLength(int maxBinaryLiteralLength) {
		this.maxBinaryLiteralLength = maxBinaryLiteralLength;
	}

	public int getMaxCatalogNameLength() {
		return maxCatalogNameLength;
	}

	public void setMaxCatalogNameLength(int maxCatalogNameLength) {
		this.maxCatalogNameLength = maxCatalogNameLength;
	}

	public int getMaxCharLiteralLength() {
		return maxCharLiteralLength;
	}

	public void setMaxCharLiteralLength(int maxCharLiteralLength) {
		this.maxCharLiteralLength = maxCharLiteralLength;
	}

	public int getMaxColumnNameLength() {
		return maxColumnNameLength;
	}

	public void setMaxColumnNameLength(int maxColumnNameLength) {
		this.maxColumnNameLength = maxColumnNameLength;
	}

	public int getMaxColumnsInGroupBy() {
		return maxColumnsInGroupBy;
	}

	public void setMaxColumnsInGroupBy(int maxColumnsInGroupBy) {
		this.maxColumnsInGroupBy = maxColumnsInGroupBy;
	}

	public int getMaxColumnsInIndex() {
		return maxColumnsInIndex;
	}

	public void setMaxColumnsInIndex(int maxColumnsInIndex) {
		this.maxColumnsInIndex = maxColumnsInIndex;
	}

	public int getMaxColumnsInOrderBy() {
		return maxColumnsInOrderBy;
	}

	public void setMaxColumnsInOrderBy(int maxColumnsInOrderBy) {
		this.maxColumnsInOrderBy = maxColumnsInOrderBy;
	}

	public int getMaxColumnsInSelect() {
		return maxColumnsInSelect;
	}

	public void setMaxColumnsInSelect(int maxColumnsInSelect) {
		this.maxColumnsInSelect = maxColumnsInSelect;
	}

	public int getMaxColumnsInTable() {
		return maxColumnsInTable;
	}

	public void setMaxColumnsInTable(int maxColumnsInTable) {
		this.maxColumnsInTable = maxColumnsInTable;
	}

	public int getMaxConnections() {
		return maxConnections;
	}

	public void setMaxConnections(int maxConnections) {
		this.maxConnections = maxConnections;
	}

	public int getMaxCursorNameLength() {
		return maxCursorNameLength;
	}

	public void setMaxCursorNameLength(int maxCursorNameLength) {
		this.maxCursorNameLength = maxCursorNameLength;
	}

	public int getMaxIndexLength() {
		return maxIndexLength;
	}

	public void setMaxIndexLength(int maxIndexLength) {
		this.maxIndexLength = maxIndexLength;
	}

	public int getMaxProcedureNameLength() {
		return maxProcedureNameLength;
	}

	public void setMaxProcedureNameLength(int maxProcedureNameLength) {
		this.maxProcedureNameLength = maxProcedureNameLength;
	}

	public int getMaxRowSize() {
		return maxRowSize;
	}

	public void setMaxRowSize(int maxRowSize) {
		this.maxRowSize = maxRowSize;
	}

	public int getMaxSchemaNameLength() {
		return maxSchemaNameLength;
	}

	public void setMaxSchemaNameLength(int maxSchemaNameLength) {
		this.maxSchemaNameLength = maxSchemaNameLength;
	}

	public int getMaxStatementLength() {
		return maxStatementLength;
	}

	public void setMaxStatementLength(int maxStatementLength) {
		this.maxStatementLength = maxStatementLength;
	}

	public int getMaxStatements() {
		return maxStatements;
	}

	public void setMaxStatements(int maxStatements) {
		this.maxStatements = maxStatements;
	}

	public int getMaxTableNameLength() {
		return maxTableNameLength;
	}

	public void setMaxTableNameLength(int maxTableNameLength) {
		this.maxTableNameLength = maxTableNameLength;
	}

	public int getMaxTablesInSelect() {
		return maxTablesInSelect;
	}

	public void setMaxTablesInSelect(int maxTablesInSelect) {
		this.maxTablesInSelect = maxTablesInSelect;
	}

	public int getMaxUserNameLength() {
		return maxUserNameLength;
	}

	public void setMaxUserNameLength(int maxUserNameLength) {
		this.maxUserNameLength = maxUserNameLength;
	}

	public int getResultSetHoldability() {
		return resultSetHoldability;
	}

	public void setResultSetHoldability(int resultSetHoldability) {
		this.resultSetHoldability = resultSetHoldability;
	}

	public int getSqlStateType() {
		return sqlStateType;
	}

	public void setSqlStateType(int sqlStateType) {
		this.sqlStateType = sqlStateType;
	}
	
	public RdbType getDatabaseType() {
		return RdbType.enumOfName(databaseProductName);
	}
	
	public String getCurrentSchema() {
		return currentSchema;
	}

}

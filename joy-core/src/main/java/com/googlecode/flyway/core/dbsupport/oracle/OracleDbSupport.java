package com.googlecode.flyway.core.dbsupport.oracle;

import com.googlecode.flyway.core.dbsupport.DbSupport;
import com.googlecode.flyway.core.dbsupport.JdbcTemplate;
import com.googlecode.flyway.core.dbsupport.Schema;
import com.googlecode.flyway.core.dbsupport.SqlStatementBuilder;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Types;

/**
 * Oracle-specific support.
 */
public class OracleDbSupport extends DbSupport {
	
	/**
	 * Creates a new instance.
	 * 
	 * @param connection The connection to use.
	 */
	public OracleDbSupport(Connection connection) {
		super(new JdbcTemplate(connection, Types.VARCHAR));
	}

	public String getScriptLocation() {
		return "com/googlecode/flyway/core/dbsupport/oracle/";
	}

	public String getCurrentUserFunction() {
		return "USER";
	}

    @Override
    protected String doGetCurrentSchema() throws SQLException {
        return jdbcTemplate.queryForString("SELECT USER FROM dual");
    }

    @Override
    protected void doSetCurrentSchema(Schema schema) throws SQLException {
        jdbcTemplate.execute("ALTER SESSION SET CURRENT_SCHEMA=" + schema);
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
        return new OracleSqlStatementBuilder();
    }

    @Override
    public String doQuote(String identifier) {
        return identifier;
    }

    @Override
    public Schema getSchema(String name) {
        return new OracleSchema(jdbcTemplate, this, name);
    }

    @Override
    public boolean catalogIsSchema() {
        return false;
    }

}

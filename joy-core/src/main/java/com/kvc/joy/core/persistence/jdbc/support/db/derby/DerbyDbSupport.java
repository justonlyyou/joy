package com.kvc.joy.core.persistence.jdbc.support.db.derby;

import com.kvc.joy.core.persistence.jdbc.support.db.DbSupport;
import com.kvc.joy.core.persistence.jdbc.support.db.Schema;
import com.kvc.joy.core.persistence.jdbc.support.db.SqlStatementBuilder;
import com.kvc.joy.core.persistence.jdbc.support.utils.JdbcTool;

import java.sql.Connection;
import java.sql.Types;

/**
 * Derby database specific support
 */
public class DerbyDbSupport extends DbSupport {
    /**
     * Creates a new instance.
     *
     * @param connection The connection to use.
     */
    public DerbyDbSupport(Connection connection) {
        super(connection);
    }

    public String getScriptLocation() {
        return "com/googlecode/flyway/core/dbsupport/derby/";
    }

    public String getCurrentUserFunction() {
        return "CURRENT_USER";
    }

    @Override
    protected String doGetCurrentSchema() {
        return JdbcTool.queryForString(connection, "SELECT CURRENT SCHEMA FROM SYSIBM.SYSDUMMY1");
    }

    @Override
    protected void doSetCurrentSchema(Schema schema) {
        JdbcTool.execute(connection, "SET SCHEMA " + schema);
    }

    public boolean supportsDdlTransactions() {
        return true;
    }

    public String getBooleanTrue() {
        return "true";
    }

    public String getBooleanFalse() {
        return "false";
    }

    public SqlStatementBuilder createSqlStatementBuilder() {
        return new DerbySqlStatementBuilder();
    }

    @Override
    public String doQuote(String identifier) {
        return "\"" + identifier + "\"";
    }

    @Override
    public Schema getSchema(String name) {
        return new DerbySchema(this, name);
    }

    @Override
    public boolean catalogIsSchema() {
        return false;
    }

	@Override
	public int getNullType() {
		return Types.VARCHAR;
	}
}
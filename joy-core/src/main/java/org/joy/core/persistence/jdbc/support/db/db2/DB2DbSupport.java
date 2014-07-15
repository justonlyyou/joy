package org.joy.core.persistence.jdbc.support.db.db2;

import org.joy.core.persistence.jdbc.support.db.DbSupport;
import org.joy.core.persistence.jdbc.support.db.Schema;
import org.joy.core.persistence.jdbc.support.db.SqlStatementBuilder;
import org.joy.core.persistence.jdbc.support.utils.JdbcTool;

import java.sql.Connection;
import java.sql.Types;

/**
 * DB2 Support.
 */
public class DB2DbSupport extends DbSupport {
    /**
     * Creates a new instance.
     *
     * @param connection The connection to use.
     */
    public DB2DbSupport(Connection connection) {
        super(connection);
    }

    public SqlStatementBuilder createSqlStatementBuilder() {
        return new DB2SqlStatementBuilder();
    }

    public String getScriptLocation() {
        return "com/googlecode/flyway/core/dbsupport/db2/";
    }

    @Override
    protected String doGetCurrentSchema() {
        return JdbcTool.queryForString(connection, "select current_schema from sysibm.sysdummy1");
    }

    @Override
    protected void doSetCurrentSchema(Schema schema) {
    	JdbcTool.execute(connection, "SET SCHEMA " + schema);
    }

    public String getCurrentUserFunction() {
        return "CURRENT_USER";
    }

    public boolean supportsDdlTransactions() {
        return true;
    }

    public String getBooleanTrue() {
        return "1";
    }

    public String getBooleanFalse() {
        return "0";
    }

    @Override
    public String doQuote(String identifier) {
        return "\"" + identifier + "\"";
    }

    @Override
    public Schema getSchema(String name) {
        return new DB2Schema(this, name);
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

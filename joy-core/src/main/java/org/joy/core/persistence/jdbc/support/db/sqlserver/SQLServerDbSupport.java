package org.joy.core.persistence.jdbc.support.db.sqlserver;

import org.joy.commons.lang.string.StringTool;
import org.joy.commons.log.Log;
import org.joy.commons.log.LogFactory;
import org.joy.core.persistence.jdbc.support.db.DbSupport;
import org.joy.core.persistence.jdbc.support.db.Schema;
import org.joy.core.persistence.jdbc.support.db.SqlStatementBuilder;
import org.joy.core.persistence.jdbc.support.enums.RdbType;
import org.joy.core.persistence.jdbc.support.utils.JdbcTool;

import java.sql.Connection;
import java.sql.Types;

/**
 * SQLServer-specific support.
 */
public class SQLServerDbSupport extends DbSupport {

    private static final Log LOG = LogFactory.getLog(SQLServerDbSupport.class);

    /**
     * Creates a new instance.
     *
     * @param connection The connection to use.
     */
    public SQLServerDbSupport(Connection connection) {
        super(connection);
    }

    public String getScriptLocation() {
        return "com/googlecode/flyway/core/dbsupport/sqlserver/";
    }

    public String getCurrentUserFunction() {
        return "SUSER_SNAME()";
    }

    @Override
    protected String doGetCurrentSchema()  {
        return JdbcTool.queryForString(connection, "SELECT SCHEMA_NAME()");
    }

    @Override
    protected void doSetCurrentSchema(Schema schema)  {
        LOG.info("SQLServer does not support setting the schema for the current session. Default schema NOT changed to " + schema);
        // Not currently supported.
        // See http://connect.microsoft.com/SQLServer/feedback/details/390528/t-sql-statement-for-changing-default-schema-context
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

    public SqlStatementBuilder createSqlStatementBuilder() {
        return new SQLServerSqlStatementBuilder();
    }

    /**
     * Escapes this identifier, so it can be safely used in sql queries.
     *
     * @param identifier The identifier to escaped.
     * @return The escaped version.
     */
    private String escapeIdentifier(String identifier) {
        return StringTool.replace(identifier, "]", "]]");
    }

    @Override
    public String doQuote(String identifier) {
        return "[" + escapeIdentifier(identifier) + "]";
    }

    @Override
    public Schema getSchema(String name) {
        return new SQLServerSchema(this, name);
    }

    @Override
    public boolean catalogIsSchema() {
        return false;
    }
    
    @Override
	public int getNullType() {
		return Types.VARCHAR;
	}


    @Override
    public RdbType getDatabaseType() {
        return RdbType.SQLSERVER;
    }
}

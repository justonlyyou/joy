package org.joy.core.persistence.jdbc.support.db.postgresql;

import org.joy.commons.lang.string.StringTool;
import org.joy.core.persistence.jdbc.support.db.DbSupport;
import org.joy.core.persistence.jdbc.support.db.Schema;
import org.joy.core.persistence.jdbc.support.db.SqlStatementBuilder;
import org.joy.core.persistence.jdbc.support.enums.RdbType;
import org.joy.core.persistence.jdbc.support.utils.JdbcTool;

import java.sql.Connection;
import java.sql.Types;

/**
 * PostgreSQL-specific support.
 */
public class PostgreSQLDbSupport extends DbSupport {
    /**
     * Creates a new instance.
     *
     * @param connection The connection to use.
     */
    public PostgreSQLDbSupport(Connection connection) {
        super(connection);
    }


    public String getScriptLocation() {
        return "com/googlecode/flyway/core/dbsupport/postgresql/";
    }

    public String getCurrentUserFunction() {
        return "current_user";
    }

    @Override
    protected String doGetCurrentSchema()  {
        return JdbcTool.queryForString(connection, "SELECT current_schema()");
    }

    @Override
    protected void doSetCurrentSchema(Schema schema)  {
        if (schema == null) {
            JdbcTool.execute(connection, "SELECT set_config('search_path', '', false)");
            return;
        }

        String searchPath = JdbcTool.queryForString(connection, "SHOW search_path");
        if (StringTool.isNotBlank(searchPath)) {
            JdbcTool.execute(connection, "SET search_path = " + schema + "," + searchPath);
        } else {
            JdbcTool.execute(connection, "SET search_path = " + schema);
        }
    }

    public boolean supportsDdlTransactions() {
        return true;
    }

    public String getBooleanTrue() {
        return "TRUE";
    }

    public String getBooleanFalse() {
        return "FALSE";
    }

    public SqlStatementBuilder createSqlStatementBuilder() {
        return new PostgreSQLSqlStatementBuilder();
    }

    @Override
    public String doQuote(String identifier) {
        return "\"" + StringTool.replace(identifier, "\"", "\"\"") + "\"";
    }

    @Override
    public Schema getSchema(String name) {
        return new PostgreSQLSchema(this, name);
    }

    @Override
    public boolean catalogIsSchema() {
        return false;
    }
    
    @Override
	public int getNullType() {
		return Types.NULL;
	}

    @Override
    public RdbType getDatabaseType() {
        return RdbType.POSTGRESQL;
    }
}

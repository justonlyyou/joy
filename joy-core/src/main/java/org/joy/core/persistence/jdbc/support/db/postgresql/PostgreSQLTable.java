package org.joy.core.persistence.jdbc.support.db.postgresql;

import org.joy.core.persistence.jdbc.support.db.DbSupport;
import org.joy.core.persistence.jdbc.support.db.Schema;
import org.joy.core.persistence.jdbc.support.db.Table;
import org.joy.core.persistence.jdbc.support.utils.JdbcTool;

/**
 * PostgreSQL-specific table.
 */
public class PostgreSQLTable extends Table {
    /**
     * Creates a new PostgreSQL table.
     *
     * @param dbSupport    The database-specific support.
     * @param schema       The schema this table lives in.
     * @param name         The name of the table.
     */
    public PostgreSQLTable(DbSupport dbSupport, Schema schema, String name) {
        super(dbSupport, schema, name);
    }

    @Override
    protected void doDrop()  {
        JdbcTool.execute(dbSupport.getConnection(), "DROP TABLE " + dbSupport.quote(schema.getName(), name) + " CASCADE");
    }

    @Override
    protected boolean doExists()  {
        return exists(null, schema, name);
    }

    @Override
    protected boolean doExistsNoQuotes()  {
        return exists(null, dbSupport.getSchema(schema.getName().toLowerCase()), name.toLowerCase(), "TABLE");
    }

    @Override
    protected void doLock()  {
        JdbcTool.execute(dbSupport.getConnection(), "SELECT * FROM " + this + " FOR UPDATE");
    }
}

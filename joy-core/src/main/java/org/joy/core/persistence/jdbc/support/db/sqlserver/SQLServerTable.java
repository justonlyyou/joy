package org.joy.core.persistence.jdbc.support.db.sqlserver;

import org.joy.core.persistence.jdbc.support.db.DbSupport;
import org.joy.core.persistence.jdbc.support.db.Schema;
import org.joy.core.persistence.jdbc.support.db.Table;
import org.joy.core.persistence.jdbc.support.utils.JdbcTool;

/**
 * SQLServer-specific table.
 */
public class SQLServerTable extends Table {
    /**
     * Creates a new SQLServer table.
     *
     * @param dbSupport    The database-specific support.
     * @param schema       The schema this table lives in.
     * @param name         The name of the table.
     */
    public SQLServerTable(DbSupport dbSupport, Schema schema, String name) {
        super(dbSupport, schema, name);
    }

    @Override
    protected void doDrop()  {
        JdbcTool.execute(dbSupport.getConnection(), "DROP TABLE " + dbSupport.quote(schema.getName(), name));
    }

    @Override
    protected boolean doExists()  {
        return exists(null, schema, name);
    }

    @Override
    protected boolean doExistsNoQuotes()  {
        return exists(null, schema, name);
    }

    @Override
    protected void doLock()  {
        JdbcTool.execute(dbSupport.getConnection(), "select * from " + this + " WITH (TABLOCKX)");
    }
}

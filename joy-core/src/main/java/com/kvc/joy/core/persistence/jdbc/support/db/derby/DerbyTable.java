package com.kvc.joy.core.persistence.jdbc.support.db.derby;

import com.kvc.joy.core.persistence.jdbc.support.db.DbSupport;
import com.kvc.joy.core.persistence.jdbc.support.db.Schema;
import com.kvc.joy.core.persistence.jdbc.support.db.Table;
import com.kvc.joy.core.persistence.jdbc.support.utils.JdbcTool;

/**
 * Derby-specific table.
 */
public class DerbyTable extends Table {
    /**
     * Creates a new Derby table.
     *
     * @param dbSupport    The database-specific support.
     * @param schema       The schema this table lives in.
     * @param name         The name of the table.
     */
    public DerbyTable(DbSupport dbSupport, Schema schema, String name) {
        super(dbSupport, schema, name);
    }

    @Override
    protected void doDrop() {
        JdbcTool.execute(dbSupport.getConnection(), "DROP TABLE " + dbSupport.quote(schema.getName(), name));
    }

    @Override
    protected boolean doExists()  {
        return exists(null, schema, name);
    }

    @Override
    protected boolean doExistsNoQuotes()  {
        return exists(null, dbSupport.getSchema(schema.getName().toUpperCase()), name.toUpperCase());
    }

    @Override
    protected void doLock()  {
        JdbcTool.execute(dbSupport.getConnection(), "LOCK TABLE " + this + " IN EXCLUSIVE MODE");
    }
}

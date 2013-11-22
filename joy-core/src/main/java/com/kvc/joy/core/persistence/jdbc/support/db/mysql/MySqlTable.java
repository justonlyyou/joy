package com.kvc.joy.core.persistence.jdbc.support.db.mysql;

import com.kvc.joy.core.persistence.jdbc.support.db.DbSupport;
import com.kvc.joy.core.persistence.jdbc.support.db.Schema;
import com.kvc.joy.core.persistence.jdbc.support.db.Table;
import com.kvc.joy.core.persistence.jdbc.support.utils.JdbcTool;

/**
 * MySQL-specific table.
 */
public class MySqlTable extends Table {
    /**
     * Creates a new MySQL table.
     *
     * @param dbSupport    The database-specific support.
     * @param schema       The schema this table lives in.
     * @param name         The name of the table.
     */
    public MySqlTable(DbSupport dbSupport, Schema schema, String name) {
        super(dbSupport, schema, name);
    }

    @Override
    protected void doDrop() {
        JdbcTool.execute(dbSupport.getConnection(), "DROP TABLE " + dbSupport.quote(schema.getName(), name));
    }

    @Override
    protected boolean doExists()  {
        return exists(schema, null, name);
    }

    @Override
    protected boolean doExistsNoQuotes()  {
        return exists(schema, null, name);
    }

    @Override
    protected void doLock()  {
        JdbcTool.execute(dbSupport.getConnection(), "SELECT * FROM " + this + " FOR UPDATE");
    }
}

package org.joy.core.persistence.jdbc.support.db.db2;

import org.joy.core.persistence.jdbc.support.db.DbSupport;
import org.joy.core.persistence.jdbc.support.db.Schema;
import org.joy.core.persistence.jdbc.support.db.Table;
import org.joy.core.persistence.jdbc.support.utils.JdbcTool;

/**
 * Db2-specific table.
 */
public class DB2Table extends Table {
    /**
     * Creates a new Db2 table.
     *
     * @param dbSupport    The database-specific support.
     * @param schema       The schema this table lives in.
     * @param name         The name of the table.
     */
    public DB2Table( DbSupport dbSupport, Schema schema, String name) {
        super(dbSupport, schema, name);
    }

    @Override
    protected void doDrop() {
        JdbcTool.execute(dbSupport.getConnection(), "DROP TABLE " + dbSupport.quote(schema.getName(), name));
    }

    @Override
    protected boolean doExists() {
        return exists(null, schema, name);
    }

    @Override
    protected boolean doExistsNoQuotes() {
        return exists(null, dbSupport.getSchema(schema.getName().toUpperCase()), name.toUpperCase());
    }

    @Override
    protected void doLock() {
        JdbcTool.update(dbSupport.getConnection(), "lock table " + this + " in exclusive mode");
    }
}

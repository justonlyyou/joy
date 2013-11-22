package com.kvc.joy.core.persistence.jdbc.support.db.db2;

import com.kvc.joy.core.persistence.jdbc.support.db.DbSupport;
import com.kvc.joy.core.persistence.jdbc.support.db.Schema;
import com.kvc.joy.core.persistence.jdbc.support.db.Type;
import com.kvc.joy.core.persistence.jdbc.support.utils.JdbcTool;

/**
 * Db2-specific type.
 */
public class DB2Type extends Type {
    /**
     * Creates a new Db2 type.
     *
     * @param dbSupport    The database-specific support.
     * @param schema       The schema this type lives in.
     * @param name         The name of the type.
     */
    public DB2Type(DbSupport dbSupport, Schema schema, String name) {
        super(dbSupport, schema, name);
    }

    @Override
    protected void doDrop() {
        JdbcTool.execute(dbSupport.getConnection(), "DROP TYPE " + dbSupport.quote(schema.getName(), name));
    }
}

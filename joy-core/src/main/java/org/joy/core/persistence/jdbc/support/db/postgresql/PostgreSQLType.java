package org.joy.core.persistence.jdbc.support.db.postgresql;

import org.joy.core.persistence.jdbc.support.db.DbSupport;
import org.joy.core.persistence.jdbc.support.db.Schema;
import org.joy.core.persistence.jdbc.support.db.Type;
import org.joy.core.persistence.jdbc.support.utils.JdbcTool;

/**
 * PostgreSQL-specific type.
 */
public class PostgreSQLType extends Type {
    /**
     * Creates a new PostgreSQL type.
     *
     * @param dbSupport    The database-specific support.
     * @param schema       The schema this type lives in.
     * @param name         The name of the type.
     */
    public PostgreSQLType(DbSupport dbSupport, Schema schema, String name) {
        super( dbSupport, schema, name);
    }

    @Override
    protected void doDrop()  {
        JdbcTool.execute(dbSupport.getConnection(), "DROP TYPE " + dbSupport.quote(schema.getName(), name));
    }
}

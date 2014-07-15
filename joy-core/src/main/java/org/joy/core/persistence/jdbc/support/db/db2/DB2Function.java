package org.joy.core.persistence.jdbc.support.db.db2;

import org.joy.commons.lang.string.StringTool;
import org.joy.core.persistence.jdbc.support.db.DbSupport;
import org.joy.core.persistence.jdbc.support.db.Function;
import org.joy.core.persistence.jdbc.support.db.Schema;
import org.joy.core.persistence.jdbc.support.utils.JdbcTool;

/**
 * Db2-specific function.
 */
public class DB2Function extends Function {
    /**
     * Creates a new Db2 function.
     *
     * @param dbSupport    The database-specific support.
     * @param schema       The schema this function lives in.
     * @param name         The name of the function.
     * @param args         The arguments of the function.
     */
    public DB2Function(DbSupport dbSupport, Schema schema, String name, String... args) {
        super(dbSupport, schema, name, args);
    }

    @Override
    protected void doDrop() {
        JdbcTool.execute(dbSupport.getConnection(), "DROP FUNCTION "
                + dbSupport.quote(schema.getName(), name)
                + "(" + StringTool.join(args) + ")");
    }

    @Override
    public String toString() {
        return super.toString() + "(" + StringTool.join(args) + ")";
    }
}

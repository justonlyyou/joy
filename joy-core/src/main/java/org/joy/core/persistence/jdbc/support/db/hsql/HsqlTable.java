package org.joy.core.persistence.jdbc.support.db.hsql;

import org.joy.commons.exception.SystemException;
import org.joy.commons.log.Log;
import org.joy.commons.log.LogFactory;
import org.joy.core.persistence.jdbc.support.db.DbSupport;
import org.joy.core.persistence.jdbc.support.db.Schema;
import org.joy.core.persistence.jdbc.support.db.Table;
import org.joy.core.persistence.jdbc.support.utils.JdbcTool;

import java.sql.SQLException;

/**
 * Hsql-specific table.
 */
public class HsqlTable extends Table {
	
    private static final Log LOG = LogFactory.getLog(HsqlDbSupport.class);

    /**
     * Flag indicating whether we are running against the old Hsql 1.8 instead of the newer 2.x.
     */
    private boolean version18;

    /**
     * Creates a new Hsql table.
     *
     * @param dbSupport    The database-specific support.
     * @param schema       The schema this table lives in.
     * @param name         The name of the table.
     */
    public HsqlTable(DbSupport dbSupport, Schema schema, String name) {
        super(dbSupport, schema, name);

        try {
            int majorVersion = dbSupport.getConnection().getMetaData().getDatabaseMajorVersion();
            version18 = majorVersion < 2;
        } catch (SQLException e) {
            throw new SystemException(e, "Unable to determine the Hsql version");
        }
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
        return exists(null, dbSupport.getSchema(schema.getName().toUpperCase()), name.toUpperCase());
    }

    @Override
    protected void doLock()  {
        if (version18) {
            LOG.debug("Unable to lock " + this + " as Hsql 1.8 does not support locking. No concurrent migration supported.");
        } else {
        	JdbcTool.execute(dbSupport.getConnection(), "LOCK TABLE " + this + " WRITE");
        }
    }
}

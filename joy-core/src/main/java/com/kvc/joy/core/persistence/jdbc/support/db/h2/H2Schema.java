package com.kvc.joy.core.persistence.jdbc.support.db.h2;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.kvc.joy.commons.lang.string.StringTool;
import com.kvc.joy.commons.log.Log;
import com.kvc.joy.commons.log.LogFactory;
import com.kvc.joy.core.persistence.jdbc.support.db.DbSupport;
import com.kvc.joy.core.persistence.jdbc.support.db.Schema;
import com.kvc.joy.core.persistence.jdbc.support.db.Table;
import com.kvc.joy.core.persistence.jdbc.support.utils.JdbcTool;

/**
 * H2 implementation of Schema.
 */
public class H2Schema extends Schema {
    private static final Log LOG = LogFactory.getLog(H2Schema.class);

    /**
     * Creates a new H2 schema.
     *
     * @param dbSupport    The database-specific support.
     * @param name         The name of the schema.
     */
    public H2Schema(DbSupport dbSupport, String name) {
        super(dbSupport, name);
    }

    @Override
    protected boolean doExists() {
        return JdbcTool.queryForInt(dbSupport.getConnection(), "SELECT COUNT(*) FROM INFORMATION_SCHEMA.schemata WHERE schema_name=?", name) > 0;
    }

    @Override
    protected boolean doEmpty() throws SQLException {
        return allTables().length == 0;
    }

    @Override
    protected void doCreate()  {
        JdbcTool.execute(dbSupport.getConnection(), "CREATE SCHEMA " + dbSupport.quote(name));
    }

    @Override
    protected void doDrop()  {
        JdbcTool.execute(dbSupport.getConnection(), "DROP SCHEMA " + dbSupport.quote(name));
    }

    @Override
    protected void doClean() throws SQLException {
        for (Table table : allTables()) {
            table.drop();
        }

        List<String> sequenceNames = listObjectNames("SEQUENCE", "IS_GENERATED = false");
        for (String statement : generateDropStatements("SEQUENCE", sequenceNames, "")) {
            JdbcTool.execute(dbSupport.getConnection(), statement);
        }

        List<String> constantNames = listObjectNames("CONSTANT", "");
        for (String statement : generateDropStatements("CONSTANT", constantNames, "")) {
            JdbcTool.execute(dbSupport.getConnection(), statement);
        }

        List<String> domainNames = listObjectNames("DOMAIN", "");
        if (!domainNames.isEmpty()) {
            if (name.equals(dbSupport.getCurrentSchema().getName())) {
                for (String statement : generateDropStatementsForCurrentSchema("DOMAIN", domainNames, "")) {
                    JdbcTool.execute(dbSupport.getConnection(), statement);
                }
            } else {
                LOG.error("Unable to drop DOMAIN objects in schema " + dbSupport.quote(name)
                        + " due to H2 bug! (More info: http://code.google.com/p/h2database/issues/detail?id=306)");
            }
        }
    }

    /**
     * Generate the statements for dropping all the objects of this type in this schema.
     *
     * @param objectType          The type of object to drop (Sequence, constant, ...)
     * @param objectNames         The names of the objects to drop.
     * @param dropStatementSuffix Suffix to append to the statement for dropping the objects.
     * @return The list of statements.
     */
    private List<String> generateDropStatements(String objectType, List<String> objectNames, String dropStatementSuffix) {
        List<String> statements = new ArrayList<String>();
        for (String objectName : objectNames) {
            String dropStatement =
                    "DROP " + objectType + dbSupport.quote(name, objectName) + " " + dropStatementSuffix;

            statements.add(dropStatement);
        }
        return statements;
    }

    /**
     * Generate the statements for dropping all the objects of this type in the current schema.
     *
     * @param objectType          The type of object to drop (Sequence, constant, ...)
     * @param objectNames         The names of the objects to drop.
     * @param dropStatementSuffix Suffix to append to the statement for dropping the objects.
     * @return The list of statements.
     */
    private List<String> generateDropStatementsForCurrentSchema(String objectType, List<String> objectNames, String dropStatementSuffix) {
        List<String> statements = new ArrayList<String>();
        for (String objectName : objectNames) {
            String dropStatement =
                    "DROP " + objectType + dbSupport.quote(objectName) + " " + dropStatementSuffix;

            statements.add(dropStatement);
        }
        return statements;
    }

    @Override
    protected Table[] doAllTables() throws SQLException {
        List<String> tableNames = listObjectNames("TABLE", "TABLE_TYPE = 'TABLE'");

        Table[] tables = new Table[tableNames.size()];
        for (int i = 0; i < tableNames.size(); i++) {
            tables[i] = new H2Table(dbSupport, this, tableNames.get(i));
        }
        return tables;
    }

    /**
     * List the names of the objects of this type in this schema.
     *
     * @param objectType  The type of objects to list (Sequence, constant, ...)
     * @param querySuffix Suffix to append to the query to find the objects to list.
     * @return The names of the objects.
     * @throws java.sql.SQLException when the object names could not be listed.
     */
    private List<String> listObjectNames(String objectType, String querySuffix) throws SQLException {
        String query = "SELECT " + objectType + "_NAME FROM INFORMATION_SCHEMA." + objectType + "s WHERE " + objectType + "_schema = ?";
        if (StringTool.isNotBlank(querySuffix)) {
            query += " AND " + querySuffix;
        }

        return JdbcTool.queryForStringList(dbSupport.getConnection(), query, name);
    }


    @Override
    public Table getTable(String tableName) {
        return new H2Table(dbSupport, this, tableName);
    }
}

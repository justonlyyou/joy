package com.kvc.joy.core.persistence.jdbc.support.db.derby;


import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.kvc.joy.commons.lang.string.StringTool;
import com.kvc.joy.core.persistence.jdbc.support.db.DbSupport;
import com.kvc.joy.core.persistence.jdbc.support.db.Schema;
import com.kvc.joy.core.persistence.jdbc.support.db.Table;
import com.kvc.joy.core.persistence.jdbc.support.utils.JdbcTool;

/**
 * Derby implementation of Schema.
 */
public class DerbySchema extends Schema {
    /**
     * Creates a new Derby schema.
     *
     * @param dbSupport    The database-specific support.
     * @param name         The name of the schema.
     */
    public DerbySchema(DbSupport dbSupport, String name) {
        super(dbSupport, name);
    }

    @Override
    protected boolean doExists() {
        return JdbcTool.queryForInt(dbSupport.getConnection(), "SELECT COUNT (*) FROM sys.sysschemas WHERE schemaname=?", name) > 0;
    }

    @Override
    protected boolean doEmpty() throws SQLException {
        return allTables().length == 0;
    }

    @Override
    protected void doCreate() throws SQLException {
        JdbcTool.execute(dbSupport.getConnection(), "CREATE SCHEMA " + dbSupport.quote(name));
    }

    @Override
    protected void doDrop() throws SQLException {
        clean();
        JdbcTool.execute(dbSupport.getConnection(), "DROP SCHEMA " + dbSupport.quote(name) + " RESTRICT");
    }

    @Override
    protected void doClean() throws SQLException {
        for (String statement : generateDropStatementsForConstraints()) {
            JdbcTool.execute(dbSupport.getConnection(), statement);
        }

        List<String> viewNames = listObjectNames("TABLE", "TABLETYPE='V'");
        for (String statement : generateDropStatements("VIEW", viewNames, "")) {
            JdbcTool.execute(dbSupport.getConnection(), statement);
        }

        for (Table table : allTables()) {
            table.drop();
        }

        List<String> sequenceNames = listObjectNames("SEQUENCE", "");
        for (String statement : generateDropStatements("SEQUENCE", sequenceNames, "RESTRICT")) {
            JdbcTool.execute(dbSupport.getConnection(), statement);
        }
    }

    /**
     * Generate the statements for dropping all the constraints in this schema.
     *
     * @return The list of statements.
     * @throws SQLException when the statements could not be generated.
     */
    private List<String> generateDropStatementsForConstraints() throws SQLException {
        List<Map<String, String>> results = JdbcTool.queryForList(dbSupport.getConnection(), 
        		"SELECT c.constraintname, t.tablename FROM sys.sysconstraints c" +
                " INNER JOIN sys.systables t ON c.tableid = t.tableid" +
                " INNER JOIN sys.sysschemas s ON c.schemaid = s.schemaid" +
                " WHERE c.type = 'F' AND s.schemaname = ?", name);

        List<String> statements = new ArrayList<String>();
        for (Map<String, String> result : results) {
            String dropStatement = "ALTER TABLE " + dbSupport.quote(name, result.get("TABLENAME"))
                    + " DROP CONSTRAINT " + dbSupport.quote(result.get("CONSTRAINTNAME"));

            statements.add(dropStatement);
        }
        return statements;
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
                    "DROP " + objectType + " " + dbSupport.quote(name, objectName) + " " + dropStatementSuffix;

            statements.add(dropStatement);
        }
        return statements;
    }

    @Override
    protected Table[] doAllTables() throws SQLException {
        List<String> tableNames = listObjectNames("TABLE", "TABLETYPE='T'");

        Table[] tables = new Table[tableNames.size()];
        for (int i = 0; i < tableNames.size(); i++) {
            tables[i] = new DerbyTable(dbSupport, this, tableNames.get(i));
        }
        return tables;
    }

    /**
     * List the names of the objects of this type in this schema.
     *
     * @param objectType  The type of objects to list (Sequence, constant, ...)
     * @param querySuffix Suffix to append to the query to find the objects to list.
     * @return The names of the objects.
     * @throws SQLException when the object names could not be listed.
     */
    private List<String> listObjectNames(String objectType, String querySuffix) {
        String query = "SELECT " + objectType + "name FROM sys.sys" + objectType + "s WHERE schemaid in (SELECT schemaid FROM sys.sysschemas where schemaname = ?)";
        if (StringTool.isNotBlank(querySuffix)) {
            query += " AND " + querySuffix;
        }

        return JdbcTool.queryForStringList(dbSupport.getConnection(), query, name);
    }

    @Override
    public Table getTable(String tableName) {
        return new DerbyTable(dbSupport, this, tableName);
    }
}

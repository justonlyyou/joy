package org.joy.core.persistence.jdbc.support.db.sqlserver;

import org.joy.core.persistence.jdbc.support.db.DbSupport;
import org.joy.core.persistence.jdbc.support.db.Schema;
import org.joy.core.persistence.jdbc.support.db.Table;
import org.joy.core.persistence.jdbc.support.utils.JdbcTool;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * SQLServer implementation of Schema.
 */
public class SQLServerSchema extends Schema {
    /**
     * Creates a new SQLServer schema.
     *
     * @param dbSupport    The database-specific support.
     * @param name         The name of the schema.
     */
    public SQLServerSchema(DbSupport dbSupport, String name) {
        super( dbSupport, name);
    }

    @Override
    protected boolean doExists()  {
        return JdbcTool.queryForInt(dbSupport.getConnection(), "SELECT COUNT(*) FROM INFORMATION_SCHEMA.SCHEMATA WHERE SCHEMA_NAME=?", name) > 0;
    }

    @Override
    protected boolean doEmpty()  {
        int objectCount = JdbcTool.queryForInt(dbSupport.getConnection(), "Select count(*) FROM " +
                "( " +
                "Select TABLE_NAME as OBJECT_NAME, TABLE_SCHEMA as OBJECT_SCHEMA from INFORMATION_SCHEMA.TABLES " +
                "Union " +
                "Select TABLE_NAME as OBJECT_NAME, TABLE_SCHEMA as OBJECT_SCHEMA from INFORMATION_SCHEMA.VIEWS " +
                "Union " +
                "Select CONSTRAINT_NAME as OBJECT_NAME, TABLE_SCHEMA as OBJECT_SCHEMA from INFORMATION_SCHEMA.TABLE_CONSTRAINTS " +
                "Union " +
                "Select ROUTINE_NAME as OBJECT_NAME, ROUTINE_SCHEMA as OBJECT_SCHEMA from INFORMATION_SCHEMA.ROUTINES " +
                ") R where OBJECT_SCHEMA = ?", name);
        return objectCount == 0;
    }

    @Override
    protected void doCreate()  {
        JdbcTool.execute(dbSupport.getConnection(), "CREATE SCHEMA " + dbSupport.quote(name));
    }

    @Override
    protected void doDrop()  {
        clean();
        JdbcTool.execute(dbSupport.getConnection(), "DROP SCHEMA " + dbSupport.quote(name));
    }

    @Override
    protected void doClean()  {
        for (String statement : cleanForeignKeys()) {
            JdbcTool.execute(dbSupport.getConnection(), statement);
        }

        for (String statement : cleanDefaultConstraints()) {
            JdbcTool.execute(dbSupport.getConnection(), statement);
        }

        for (String statement : cleanRoutines()) {
            JdbcTool.execute(dbSupport.getConnection(), statement);
        }

        for (String statement : cleanViews()) {
            JdbcTool.execute(dbSupport.getConnection(), statement);
        }

        for (Table table : allTables()) {
            table.drop();
        }

        for (String statement : cleanTypes()) {
            JdbcTool.execute(dbSupport.getConnection(), statement);
        }

        for (String statement : cleanSynonyms()) {
            JdbcTool.execute(dbSupport.getConnection(), statement);
        }
    }

    /**
     * Cleans the foreign keys in this schema.
     *
     * @return The drop statements.
     * @ when the clean statements could not be generated.
     */
    private List<String> cleanForeignKeys()  {
        List<Map<String, String>> constraintNames =
                JdbcTool.queryForList(dbSupport.getConnection(), 
                        "SELECT table_name, constraint_name FROM INFORMATION_SCHEMA.TABLE_CONSTRAINTS" +
                                " WHERE constraint_type in ('FOREIGN KEY','CHECK') and table_schema=?",
                        name);

        List<String> statements = new ArrayList<String>();
        for (Map<String, String> row : constraintNames) {
            String tableName = row.get("table_name");
            String constraintName = row.get("constraint_name");
            statements.add("ALTER TABLE " + dbSupport.quote(name, tableName) + " DROP CONSTRAINT " + dbSupport.quote(constraintName));
        }
        return statements;
    }

    /**
     * Cleans the default constraints in this schema.
     *
     * @return The drop statements.
     * @ when the clean statements could not be generated.
     */
    private List<String> cleanDefaultConstraints()  {
        List<Map<String, String>> constraintNames =
                JdbcTool.queryForList(dbSupport.getConnection(), 
                        "select t.name as table_name, d.name as constraint_name" +
                                " from sys.tables t" +
                                " inner join sys.default_constraints d on d.parent_object_id = t.object_id\n" +
                                " inner join sys.schemas s on s.schema_id = t.schema_id\n" +
                                " where s.name = ?",
                        name);

        List<String> statements = new ArrayList<String>();
        for (Map<String, String> row : constraintNames) {
            String tableName = row.get("table_name");
            String constraintName = row.get("constraint_name");
            statements.add("ALTER TABLE " + dbSupport.quote(name, tableName) + " DROP CONSTRAINT " + dbSupport.quote(constraintName));
        }
        return statements;
    }

    /**
     * Cleans the routines in this schema.
     *
     * @return The drop statements.
     * @ when the clean statements could not be generated.
     */
    private List<String> cleanRoutines()  {
        List<Map<String, String>> routineNames =
                JdbcTool.queryForList(dbSupport.getConnection(), "SELECT routine_name, routine_type FROM INFORMATION_SCHEMA.ROUTINES" +
                        " WHERE routine_schema=?",
                        name);

        List<String> statements = new ArrayList<String>();
        for (Map<String, String> row : routineNames) {
            String routineName = row.get("routine_name");
            String routineType = row.get("routine_type");
            statements.add("DROP " + routineType + " " + dbSupport.quote(name, routineName));
        }
        return statements;
    }

    /**
     * Cleans the views in this schema.
     *
     * @return The drop statements.
     * @ when the clean statements could not be generated.
     */
    private List<String> cleanViews()  {
        List<String> viewNames =
                JdbcTool.queryForStringList(dbSupport.getConnection(), "SELECT table_name FROM INFORMATION_SCHEMA.VIEWS WHERE table_schema=?",
                        name);

        List<String> statements = new ArrayList<String>();
        for (String viewName : viewNames) {
            statements.add("DROP VIEW " + dbSupport.quote(name, viewName));
        }
        return statements;
    }

    /**
     * Cleans the types in this schema.
     *
     * @return The drop statements.
     * @ when the clean statements could not be generated.
     */
    private List<String> cleanTypes()  {
        List<String> typeNames =
                JdbcTool.queryForStringList(dbSupport.getConnection(), 
                        "SELECT t.name FROM sys.types t INNER JOIN sys.schemas s ON t.schema_id = s.schema_id" +
                                " WHERE t.is_user_defined = 1 AND s.name = ?",
                        name);

        List<String> statements = new ArrayList<String>();
        for (String typeName : typeNames) {
            statements.add("DROP TYPE " + dbSupport.quote(name, typeName));
        }
        return statements;
    }

    /**
     * Cleans the synonyms in this schema.
     *
     * @return The drop statements.
     * @ when the clean statements could not be generated.
     */
    private List<String> cleanSynonyms()  {
        List<String> synonymNames =
                JdbcTool.queryForStringList(dbSupport.getConnection(), 
                        "SELECT sn.name FROM sys.synonyms sn INNER JOIN sys.schemas s ON sn.schema_id = s.schema_id" +
                                " WHERE s.name = ?",
                        name);

        List<String> statements = new ArrayList<String>();
        for (String synonymName : synonymNames) {
            statements.add("DROP SYNONYM " + dbSupport.quote(name, synonymName));
        }
        return statements;
    }

    @Override
    protected Table[] doAllTables()  {
        List<String> tableNames = JdbcTool.queryForStringList(dbSupport.getConnection(), 
                "SELECT table_name FROM INFORMATION_SCHEMA.TABLES WHERE table_type='BASE TABLE' and table_schema=?",
                name);


        Table[] tables = new Table[tableNames.size()];
        for (int i = 0; i < tableNames.size(); i++) {
            tables[i] = new SQLServerTable(dbSupport, this, tableNames.get(i));
        }
        return tables;
    }

    @Override
    public Table getTable(String tableName) {
        return new SQLServerTable(dbSupport, this, tableName);
    }
}

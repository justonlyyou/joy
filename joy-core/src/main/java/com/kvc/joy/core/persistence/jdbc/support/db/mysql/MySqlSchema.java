package com.kvc.joy.core.persistence.jdbc.support.db.mysql;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.kvc.joy.core.persistence.jdbc.support.db.DbSupport;
import com.kvc.joy.core.persistence.jdbc.support.db.Schema;
import com.kvc.joy.core.persistence.jdbc.support.db.Table;
import com.kvc.joy.core.persistence.jdbc.support.utils.JdbcTool;

/**
 * MySQL implementation of Schema.
 */
public class MySqlSchema extends Schema {
    /**
     * Creates a new MySQL schema.
     *
     * @param dbSupport    The database-specific support.
     * @param name         The name of the schema.
     */
    public MySqlSchema(DbSupport dbSupport, String name) {
        super(dbSupport, name);
    }

    @Override
    protected boolean doExists()  {
        return JdbcTool.queryForInt(dbSupport.getConnection(), "SELECT COUNT(*) FROM information_schema.schemata WHERE schema_name=?", name) > 0;
    }

    @Override
    protected boolean doEmpty()  {
        int objectCount = JdbcTool.queryForInt(dbSupport.getConnection(), "Select "
                + "(Select count(*) from information_schema.TABLES Where TABLE_SCHEMA=?) + "
                + "(Select count(*) from information_schema.VIEWS Where TABLE_SCHEMA=?) + "
                + "(Select count(*) from information_schema.TABLE_CONSTRAINTS Where TABLE_SCHEMA=?) + "
                + "(Select count(*) from information_schema.ROUTINES Where ROUTINE_SCHEMA=?)",
                name, name, name, name);
        return objectCount == 0;
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
    protected void doClean()  {
        for (String statement : cleanRoutines()) {
        	JdbcTool.execute(dbSupport.getConnection(), statement);
        }

        for (String statement : cleanViews()) {
        	JdbcTool.execute(dbSupport.getConnection(), statement);
        }

        JdbcTool.execute(dbSupport.getConnection(), "SET FOREIGN_KEY_CHECKS = 0");
        for (Table table : allTables()) {
            table.drop();
        }
        JdbcTool.execute(dbSupport.getConnection(), "SET FOREIGN_KEY_CHECKS = 1");
    }

    /**
     * Generate the statements to clean the routines in this schema.
     *
     * @return The list of statements.
     * @ when the clean statements could not be generated.
     */
    private List<String> cleanRoutines()  {
        List<Map<String, String>> routineNames =
        		JdbcTool.queryForList(dbSupport.getConnection(), 
                        "SELECT routine_name, routine_type FROM information_schema.routines WHERE routine_schema=?",
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
     * Generate the statements to clean the views in this schema.
     *
     * @return The list of statements.
     * @ when the clean statements could not be generated.
     */
    private List<String> cleanViews()  {
        List<String> viewNames =
        		JdbcTool.queryForStringList(dbSupport.getConnection(),
                        "SELECT table_name FROM information_schema.views WHERE table_schema=?", name);

        List<String> statements = new ArrayList<String>();
        for (String viewName : viewNames) {
            statements.add("DROP VIEW " + dbSupport.quote(name, viewName));
        }
        return statements;
    }

    @Override
    protected Table[] doAllTables()  {
        List<String> tableNames = JdbcTool.queryForStringList(dbSupport.getConnection(), 
                "SELECT table_name FROM information_schema.tables WHERE table_schema=? AND table_type='BASE TABLE'", name);

        Table[] tables = new Table[tableNames.size()];
        for (int i = 0; i < tableNames.size(); i++) {
            tables[i] = new MySqlTable(dbSupport, this, tableNames.get(i));
        }
        return tables;
    }

    @Override
    public Table getTable(String tableName) {
        return new MySqlTable(dbSupport, this, tableName);
    }
}

package org.joy.core.persistence.jdbc.support.db.postgresql;

import org.joy.core.persistence.jdbc.support.db.DbSupport;
import org.joy.core.persistence.jdbc.support.db.Schema;
import org.joy.core.persistence.jdbc.support.db.Table;
import org.joy.core.persistence.jdbc.support.db.Type;
import org.joy.core.persistence.jdbc.support.utils.JdbcTool;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * PostgreSQL implementation of Schema.
 */
public class PostgreSQLSchema extends Schema {
    /**
     * Creates a new PostgreSQL schema.
     *
     * @param dbSupport    The database-specific support.
     * @param name         The name of the schema.
     */
    public PostgreSQLSchema(DbSupport dbSupport, String name) {
        super(dbSupport, name);
    }

    @Override
    protected boolean doExists()  {
        return JdbcTool.queryForInt(dbSupport.getConnection(), "SELECT COUNT(*) FROM pg_namespace WHERE nspname=?", name) > 0;
    }

    @Override
    protected boolean doEmpty()  {
        int objectCount = JdbcTool.queryForInt(dbSupport.getConnection(), 
                "SELECT count(*) FROM information_schema.tables WHERE table_schema=? AND table_type='BASE TABLE'",
                name);
        return objectCount == 0;
    }

    @Override
    protected void doCreate()  {
        JdbcTool.execute(dbSupport.getConnection(), "CREATE SCHEMA " + dbSupport.quote(name));
    }

    @Override
    protected void doDrop()  {
        JdbcTool.execute(dbSupport.getConnection(), "DROP SCHEMA " + dbSupport.quote(name) + " CASCADE");
    }

    @Override
    protected void doClean()  {
        for (Table table : allTables()) {
            table.drop();
        }

        for (String statement : generateDropStatementsForSequences()) {
            JdbcTool.execute(dbSupport.getConnection(), statement);
        }

        for (String statement : generateDropStatementsForBaseTypes(true)) {
            JdbcTool.execute(dbSupport.getConnection(), statement);
        }

        for (String statement : generateDropStatementsForAggregates()) {
            JdbcTool.execute(dbSupport.getConnection(), statement);
        }

        for (String statement : generateDropStatementsForRoutines()) {
            JdbcTool.execute(dbSupport.getConnection(), statement);
        }

        for (String statement : generateDropStatementsForEnums()) {
            JdbcTool.execute(dbSupport.getConnection(), statement);
        }

        for (String statement : generateDropStatementsForDomains()) {
            JdbcTool.execute(dbSupport.getConnection(), statement);
        }

        for (String statement : generateDropStatementsForBaseTypes(false)) {
            JdbcTool.execute(dbSupport.getConnection(), statement);
        }

        for (Type type : allTypes()) {
            type.drop();
        }
    }

    /**
     * Generates the statements for dropping the sequences in this schema.
     *
     * @return The drop statements.
     * @ when the clean statements could not be generated.
     */
    private List<String> generateDropStatementsForSequences()  {
        List<String> sequenceNames =
                JdbcTool.queryForStringList(dbSupport.getConnection(), 
                        "SELECT sequence_name FROM information_schema.sequences WHERE sequence_schema=?", name);

        List<String> statements = new ArrayList<String>();
        for (String sequenceName : sequenceNames) {
            statements.add("DROP SEQUENCE IF EXISTS " + dbSupport.quote(name, sequenceName));
        }

        return statements;
    }

    /**
     * Generates the statements for dropping the types in this schema.
     *
     * @param recreate Flag indicating whether the types should be recreated. Necessary for type-function chicken and egg problem.
     * @return The drop statements.
     * @ when the clean statements could not be generated.
     */
    private List<String> generateDropStatementsForBaseTypes(boolean recreate)  {
        List<String> typeNames =
                JdbcTool.queryForStringList(dbSupport.getConnection(), 
                        "select typname from pg_catalog.pg_type where typcategory in ('P', 'U') and typnamespace in (select oid from pg_catalog.pg_namespace where nspname = ?)",
                        name);

        List<String> statements = new ArrayList<String>();
        for (String typeName : typeNames) {
            statements.add("DROP TYPE IF EXISTS " + dbSupport.quote(name, typeName) + " CASCADE");
        }

        if (recreate) {
            for (String typeName : typeNames) {
                statements.add("CREATE TYPE " + dbSupport.quote(name, typeName));
            }
        }

        return statements;
    }

    /**
     * Generates the statements for dropping the aggregates in this schema.
     *
     * @return The drop statements.
     * @ when the clean statements could not be generated.
     */
    private List<String> generateDropStatementsForAggregates()  {
        List<Map<String, String>> rows =
                JdbcTool.queryForList(dbSupport.getConnection(), 
                        "SELECT proname, oidvectortypes(proargtypes) AS args "
                                + "FROM pg_proc INNER JOIN pg_namespace ns ON (pg_proc.pronamespace = ns.oid) "
                                + "WHERE pg_proc.proisagg = true AND ns.nspname = ?",
                        name);

        List<String> statements = new ArrayList<String>();
        for (Map<String, String> row : rows) {
            statements.add("DROP AGGREGATE IF EXISTS " + dbSupport.quote(name, row.get("proname")) + "(" + row.get("args") + ") CASCADE");
        }
        return statements;
    }

    /**
     * Generates the statements for dropping the routines in this schema.
     *
     * @return The drop statements.
     * @ when the clean statements could not be generated.
     */
    private List<String> generateDropStatementsForRoutines()  {
        List<Map<String, String>> rows =
                JdbcTool.queryForList(dbSupport.getConnection(), 
                        "SELECT proname, oidvectortypes(proargtypes) AS args "
                                + "FROM pg_proc INNER JOIN pg_namespace ns ON (pg_proc.pronamespace = ns.oid) "
                                + "WHERE pg_proc.proisagg = false AND ns.nspname = ?",
                        name);

        List<String> statements = new ArrayList<String>();
        for (Map<String, String> row : rows) {
            statements.add("DROP FUNCTION IF EXISTS " + dbSupport.quote(name, row.get("proname")) + "(" + row.get("args") + ") CASCADE");
        }
        return statements;
    }

    /**
     * Generates the statements for dropping the enums in this schema.
     *
     * @return The drop statements.
     * @ when the clean statements could not be generated.
     */
    private List<String> generateDropStatementsForEnums()  {
        List<String> enumNames =
                JdbcTool.queryForStringList(dbSupport.getConnection(), 
                        "SELECT t.typname FROM pg_catalog.pg_type t INNER JOIN pg_catalog.pg_namespace n ON n.oid = t.typnamespace WHERE n.nspname = ? and t.typtype = 'e'", name);

        List<String> statements = new ArrayList<String>();
        for (String enumName : enumNames) {
            statements.add("DROP TYPE " + dbSupport.quote(name, enumName));
        }

        return statements;
    }

    /**
     * Generates the statements for dropping the domains in this schema.
     *
     * @return The drop statements.
     * @ when the clean statements could not be generated.
     */
    private List<String> generateDropStatementsForDomains()  {
        List<String> domainNames =
                JdbcTool.queryForStringList(dbSupport.getConnection(), 
                        "SELECT domain_name FROM information_schema.domains WHERE domain_schema=?", name);

        List<String> statements = new ArrayList<String>();
        for (String domainName : domainNames) {
            statements.add("DROP DOMAIN " + dbSupport.quote(name, domainName));
        }

        return statements;
    }

    @Override
    protected Table[] doAllTables()  {
        List<String> tableNames =
                JdbcTool.queryForStringList(dbSupport.getConnection(), 
                        //Search for all the table names
                        "SELECT t.table_name FROM information_schema.tables t" +
                                //in this schema
                                " WHERE table_schema=?" +
                                //that are real tables (as opposed to views)
                                " AND table_type='BASE TABLE'" +
                                //and are not child tables (= do not inherit from another table).
                                " AND NOT (SELECT EXISTS (SELECT inhrelid FROM pg_catalog.pg_inherits" +
                                " WHERE inhrelid = ('\"'||t.table_schema||'\".\"'||t.table_name||'\"')::regclass::oid))",
                        name);
        //Views and child tables are excluded as they are dropped with the parent table when using cascade.

        Table[] tables = new Table[tableNames.size()];
        for (int i = 0; i < tableNames.size(); i++) {
            tables[i] = new PostgreSQLTable(dbSupport, this, tableNames.get(i));
        }
        return tables;
    }

    @Override
    public Table getTable(String tableName) {
        return new PostgreSQLTable(dbSupport, this, tableName);
    }

    @Override
    protected Type getType(String typeName) {
        return new PostgreSQLType(dbSupport, this, typeName);
    }
}

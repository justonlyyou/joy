package org.joy.core.persistence.jdbc.support.db.db2;

import org.joy.commons.lang.string.StringTool;
import org.joy.core.persistence.jdbc.support.db.*;
import org.joy.core.persistence.jdbc.support.utils.JdbcTool;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * DB2 implementation of Schema.
 */
public class DB2Schema extends Schema {
    /**
     * Creates a new DB2 schema.
     *
     * @param dbSupport    The database-specific support.
     * @param name         The name of the schema.
     */
    public DB2Schema(DbSupport dbSupport, String name) {
        super(dbSupport, name);
    }

    @Override
    protected boolean doExists() {
        return JdbcTool.queryForInt(dbSupport.getConnection(), "SELECT COUNT(*) FROM syscat.schemata WHERE schemaname=?", name) > 0;
    }

    @Override
    protected boolean doEmpty() {
    	Connection conn = dbSupport.getConnection();
        int objectCount = JdbcTool.queryForInt(conn, "select count(*) from syscat.tables where tabschema = ?", name);
        objectCount += JdbcTool.queryForInt(conn, "select count(*) from syscat.views where viewschema = ?", name);
        objectCount += JdbcTool.queryForInt(conn, "select count(*) from syscat.sequences where seqschema = ?", name);
        objectCount += JdbcTool.queryForInt(conn, "select count(*) from syscat.indexes where indschema = ?", name);
        objectCount += JdbcTool.queryForInt(conn, "select count(*) from syscat.procedures where procschema = ?", name);
        objectCount += JdbcTool.queryForInt(conn, "select count(*) from syscat.functions where funcschema = ?", name);
        return objectCount == 0;
    }

    @Override
    protected void doCreate() {
    	JdbcTool.execute(dbSupport.getConnection(), "CREATE SCHEMA " + dbSupport.quote(name));
    }

    @Override
    protected void doDrop() {
        clean();
        JdbcTool.execute(dbSupport.getConnection(), "DROP SCHEMA " + dbSupport.quote(name) + " RESTRICT");
    }

    @Override
    protected void doClean() throws SQLException {
        // MQTs are dropped when the backing views or tables are dropped
        // Indexes in DB2 are dropped when the corresponding table is dropped

        // views
        for (String dropStatement : generateDropStatements(name, "V", "VIEW")) {
        	JdbcTool.execute(dbSupport.getConnection(), dropStatement);
        }

        // aliases
        for (String dropStatement : generateDropStatements(name, "A", "ALIAS")) {
            JdbcTool.execute(dbSupport.getConnection(), dropStatement);
        }

        for (Table table : allTables()) {
            table.drop();
        }

        // sequences
        for (String dropStatement : generateDropStatementsForSequences(name)) {
            JdbcTool.execute(dbSupport.getConnection(), dropStatement);
        }

        // procedures
        for (String dropStatement : generateDropStatementsForProcedures(name)) {
            JdbcTool.execute(dbSupport.getConnection(), dropStatement);
        }

        for (Function function : allFunctions()) {
            function.drop();
        }

        for (Type type : allTypes()) {
            type.drop();
        }
    }

    /**
     * Generates DROP statements for the procedures in this schema.
     *
     * @param schema The schema of the objects.
     * @return The drop statements.
     * @throws SQLException when the statements could not be generated.
     */
    private List<String> generateDropStatementsForProcedures(String schema) throws SQLException {
        String dropProcGenQuery = "select rtrim(PROCNAME) from SYSCAT.PROCEDURES where PROCSCHEMA = '" + schema + "'";
        return buildDropStatements("DROP PROCEDURE", dropProcGenQuery, schema);
    }

    /**
     * Generates DROP statements for the sequences in this schema.
     *
     * @param schema The schema of the objects.
     * @return The drop statements.
     * @throws SQLException when the statements could not be generated.
     */
    private List<String> generateDropStatementsForSequences(String schema) throws SQLException {
        String dropSeqGenQuery = "select rtrim(SEQNAME) from SYSCAT.SEQUENCES where SEQSCHEMA = '" + schema
                + "' and SEQTYPE='S'";
        return buildDropStatements("DROP SEQUENCE", dropSeqGenQuery, schema);
    }

    /**
     * Generates DROP statements for this type of table, representing this type of object in this schema.
     *
     * @param schema     The schema of the objects.
     * @param tableType  The type of table (Can be T, V, S, ...).
     * @param objectType The type of object.
     * @return The drop statements.
     * @throws SQLException when the statements could not be generated.
     */
    private List<String> generateDropStatements(String schema, String tableType, String objectType) throws SQLException {
        String dropTablesGenQuery = "select rtrim(TABNAME) from SYSCAT.TABLES where TYPE='" + tableType + "' and TABSCHEMA = '"
                + schema + "'";
        return buildDropStatements("DROP " + objectType, dropTablesGenQuery, schema);
    }

    /**
     * Builds the drop statements for database objects in this schema.
     *
     * @param dropPrefix The drop command for the database object (e.g. 'drop table').
     * @param query      The query to get all present database objects
     * @param schema     The schema for which to build the statements.
     * @return The statements.
     * @throws SQLException when the drop statements could not be built.
     */
    private List<String> buildDropStatements(final String dropPrefix, final String query, String schema) {
        List<String> dropStatements = new ArrayList<String>();
        List<String> dbObjects = JdbcTool.queryForStringList(dbSupport.getConnection(), query);
        for (String dbObject : dbObjects) {
            dropStatements.add(dropPrefix + " " + dbSupport.quote(schema, dbObject));
        }
        return dropStatements;
    }

    @Override
    protected Table[] doAllTables() throws SQLException {
        List<String> tableNames = JdbcTool.queryForStringList(dbSupport.getConnection(), 
                "select rtrim(TABNAME) from SYSCAT.TABLES where TYPE='T' and TABSCHEMA = ?", name);
        Table[] tables = new Table[tableNames.size()];
        for (int i = 0; i < tableNames.size(); i++) {
            tables[i] = new DB2Table(dbSupport, this, tableNames.get(i));
        }
        return tables;
    }

    @Override
    protected Function[] doAllFunctions() {
        List<Map<String, String>> rows = JdbcTool.queryForList(dbSupport.getConnection(), 
                "select p.SPECIFICNAME, p.FUNCNAME," +
                        " substr( xmlserialize( xmlagg( xmltext( concat( ', ', TYPENAME ) ) ) as varchar( 1024 ) ), 3 ) as PARAMS" +
                        " from SYSCAT.FUNCTIONS f inner join SYSCAT.FUNCPARMS p on f.SPECIFICNAME = p.SPECIFICNAME" +
                        " where f.ORIGIN = 'Q' and p.FUNCSCHEMA = ? and p.ROWTYPE = 'P'" +
                        " group by p.SPECIFICNAME, p.FUNCNAME" +
                        " order by p.SPECIFICNAME", name);

        List<Function> functions = new ArrayList<Function>();
        for (Map<String, String> row : rows) {
            functions.add(getFunction(
                    row.get("FUNCNAME"),
                    StringTool.split(row.get("PARAMS"))));
        }

        return functions.toArray(new Function[functions.size()]);
    }

    @Override
    public Table getTable(String tableName) {
        return new DB2Table(dbSupport, this, tableName);
    }

    @Override
    protected Type getType(String typeName) {
        return new DB2Type(dbSupport, this, typeName);
    }

    @Override
    public Function getFunction(String functionName, String... args) {
        return new DB2Function(dbSupport, this, functionName, args);
    }
}

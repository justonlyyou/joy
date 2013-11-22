package com.kvc.joy.core.persistence.jdbc.support.db.hsql;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.kvc.joy.core.persistence.jdbc.support.db.DbSupport;
import com.kvc.joy.core.persistence.jdbc.support.db.Schema;
import com.kvc.joy.core.persistence.jdbc.support.db.Table;
import com.kvc.joy.core.persistence.jdbc.support.utils.JdbcTool;

/**
 * Hsql implementation of Schema.
 */
public class HsqlSchema extends Schema {
    /**
     * Creates a new Hsql schema.
     *
     * @param dbSupport    The database-specific support.
     * @param name         The name of the schema.
     */
    public HsqlSchema(DbSupport dbSupport, String name) {
        super(dbSupport, name);
    }

    @Override
    protected boolean doExists() throws SQLException {
        return JdbcTool.queryForInt(dbSupport.getConnection(), "SELECT COUNT (*) FROM information_schema.system_schemas WHERE table_schem=?", name) > 0;
    }

    @Override
    protected boolean doEmpty() throws SQLException {
        return allTables().length == 0;
    }

    @Override
    protected void doCreate() throws SQLException {
        String user = JdbcTool.queryForString(dbSupport.getConnection(), "SELECT USER() FROM (VALUES(0))");
        JdbcTool.execute(dbSupport.getConnection(), "CREATE SCHEMA " + dbSupport.quote(name) + " AUTHORIZATION " + user);
    }

    @Override
    protected void doDrop() throws SQLException {
    	JdbcTool.execute(dbSupport.getConnection(), "DROP SCHEMA " + dbSupport.quote(name) + " CASCADE");
    }

    @Override
    protected void doClean() throws SQLException {
        for (Table table : allTables()) {
            table.drop();
        }

        for (String statement : generateDropStatementsForSequences()) {
        	JdbcTool.execute(dbSupport.getConnection(), statement);
        }
    }

    /**
     * Generates the statements to drop the sequences in this schema.
     *
     * @return The drop statements.
     * @throws SQLException when the drop statements could not be generated.
     */
    private List<String> generateDropStatementsForSequences() throws SQLException {
        List<String> sequenceNames = JdbcTool.queryForStringList(dbSupport.getConnection(), 
                "SELECT SEQUENCE_NAME FROM INFORMATION_SCHEMA.SYSTEM_SEQUENCES where SEQUENCE_SCHEMA = ?", name);

        List<String> statements = new ArrayList<String>();
        for (String seqName : sequenceNames) {
            statements.add("DROP SEQUENCE " + dbSupport.quote(name, seqName));
        }

        return statements;
    }

    @Override
    protected Table[] doAllTables() throws SQLException {
        List<String> tableNames = JdbcTool.queryForStringList(dbSupport.getConnection(), 
                "SELECT TABLE_NAME FROM INFORMATION_SCHEMA.SYSTEM_TABLES where TABLE_SCHEM = ? AND TABLE_TYPE = 'TABLE'", name);

        Table[] tables = new Table[tableNames.size()];
        for (int i = 0; i < tableNames.size(); i++) {
            tables[i] = new HsqlTable(dbSupport, this, tableNames.get(i));
        }
        return tables;
    }

    @Override
    public Table getTable(String tableName) {
        return new HsqlTable(dbSupport, this, tableName);
    }
}

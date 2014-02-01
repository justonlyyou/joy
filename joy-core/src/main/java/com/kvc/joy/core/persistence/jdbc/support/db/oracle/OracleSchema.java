package com.kvc.joy.core.persistence.jdbc.support.db.oracle;

import com.kvc.joy.commons.exception.SystemException;
import com.kvc.joy.commons.log.Log;
import com.kvc.joy.commons.log.LogFactory;
import com.kvc.joy.core.persistence.jdbc.support.db.DbSupport;
import com.kvc.joy.core.persistence.jdbc.support.db.Schema;
import com.kvc.joy.core.persistence.jdbc.support.db.Table;
import com.kvc.joy.core.persistence.jdbc.support.utils.JdbcTool;

import java.util.ArrayList;
import java.util.List;

/**
 * Oracle implementation of Schema.
 */
public class OracleSchema extends Schema {
    private static final Log LOG = LogFactory.getLog(OracleSchema.class);

    /**
     * Creates a new Oracle schema.
     *
     * @param dbSupport    The database-specific support.
     * @param name         The name of the schema.
     */
    public OracleSchema(DbSupport dbSupport, String name) {
        super(dbSupport, name);
    }

    @Override
    protected boolean doExists()  {
        return JdbcTool.queryForInt(dbSupport.getConnection(), "SELECT COUNT(*) FROM all_users WHERE username=?", name) > 0;
    }

    @Override
    protected boolean doEmpty()  {
        return JdbcTool.queryForInt(dbSupport.getConnection(), "SELECT count(*) FROM all_objects WHERE owner = ?", name) == 0;
    }

    @Override
    protected void doCreate()  {
        JdbcTool.execute(dbSupport.getConnection(), "CREATE USER " + dbSupport.quote(name) + " IDENTIFIED BY flyway");
        JdbcTool.execute(dbSupport.getConnection(), "GRANT RESOURCE TO " + dbSupport.quote(name));
    }

    @Override
    protected void doDrop()  {
        JdbcTool.execute(dbSupport.getConnection(), "DROP USER " + dbSupport.quote(name) + " CASCADE");
    }

    @Override
    protected void doClean()  {
        if ("SYSTEM".equals(name.toUpperCase())) {
            throw new SystemException("Clean not supported on Oracle for user 'SYSTEM'! You should NEVER add your own objects to the SYSTEM schema!");
        }

        JdbcTool.execute(dbSupport.getConnection(), "PURGE RECYCLEBIN");

        for (String statement : generateDropStatementsForSpatialExtensions()) {
        	JdbcTool.execute(dbSupport.getConnection(), statement);
        }

        for (String statement : generateDropStatementsForQueueTables()) {
            //for dropping queue tables, a special grant is required:
            //GRANT EXECUTE ON DBMS_AQADM TO flyway;
        	JdbcTool.execute(dbSupport.getConnection(), statement);
        }

        for (String statement : generateDropStatementsForObjectType("SEQUENCE", "")) {
            JdbcTool.execute(dbSupport.getConnection(), statement);
        }

        for (String statement : generateDropStatementsForObjectType("FUNCTION", "")) {
            JdbcTool.execute(dbSupport.getConnection(), statement);
        }

        for (String statement : generateDropStatementsForObjectType("MATERIALIZED VIEW", "PRESERVE TABLE")) {
            JdbcTool.execute(dbSupport.getConnection(), statement);
        }

        for (String statement : generateDropStatementsForObjectType("PACKAGE", "")) {
            JdbcTool.execute(dbSupport.getConnection(), statement);
        }

        for (String statement : generateDropStatementsForObjectType("PROCEDURE", "")) {
            JdbcTool.execute(dbSupport.getConnection(), statement);
        }

        for (String statement : generateDropStatementsForObjectType("SYNONYM", "")) {
            JdbcTool.execute(dbSupport.getConnection(), statement);
        }

        for (String statement : generateDropStatementsForObjectType("TRIGGER", "")) {
            JdbcTool.execute(dbSupport.getConnection(), statement);
        }

        for (String statement : generateDropStatementsForObjectType("VIEW", "CASCADE CONSTRAINTS")) {
            JdbcTool.execute(dbSupport.getConnection(), statement);
        }

        for (Table table : allTables()) {
            table.drop();
        }

        for (String statement : generateDropStatementsForXmlTables()) {
            JdbcTool.execute(dbSupport.getConnection(), statement);
        }

        for (String statement : generateDropStatementsForObjectType("TYPE", "FORCE")) {
            JdbcTool.execute(dbSupport.getConnection(), statement);
        }
    }

    /**
     * Generates the drop statements for all xml tables.
     *
     * @return The complete drop statements, ready to execute.
     * @ when the drop statements could not be generated.
     */
    private List<String> generateDropStatementsForXmlTables()  {
        List<String> dropStatements = new ArrayList<String>();

        if (!xmlDBExtensionsAvailable()) {
            LOG.debug("Oracle XML DB Extensions are not available. No cleaning of XML tables.");
            return dropStatements;
        }

        List<String> objectNames =
                JdbcTool.queryForStringList(dbSupport.getConnection(), "SELECT table_name FROM all_xml_tables WHERE owner = ?", name);
        for (String objectName : objectNames) {
            dropStatements.add("DROP TABLE " + dbSupport.quote(name, objectName) + " PURGE");
        }
        return dropStatements;
    }

    /**
     * Checks whether Oracle XML DB extensions are available or not.
     *
     * @return {@code true} if they are available, {@code false} if not.
     * @ when checking availability of the extensions failed.
     */
    private boolean xmlDBExtensionsAvailable()  {
        return (JdbcTool.queryForInt(dbSupport.getConnection(), "SELECT COUNT(*) FROM all_users WHERE username = 'XDB'") > 0)
                && (JdbcTool.queryForInt(dbSupport.getConnection(), "SELECT COUNT(*) FROM all_views WHERE view_name = 'RESOURCE_VIEW'") > 0);
    }

    /**
     * Generates the drop statements for all database objects of this type.
     *
     * @param objectType     The type of database object to drop.
     * @param extraArguments The extra arguments to add to the drop statement.
     * @return The complete drop statements, ready to execute.
     * @ when the drop statements could not be generated.
     */
    private List<String> generateDropStatementsForObjectType(String objectType, String extraArguments)  {
        String query = "SELECT object_name FROM all_objects WHERE object_type = ? AND owner = ?"
                // Ignore Spatial Index Sequences as they get dropped automatically when the index gets dropped.
                + " AND object_name NOT LIKE 'MDRS_%$'";

        List<String> objectNames = JdbcTool.queryForStringList(dbSupport.getConnection(), query, objectType, name);
        List<String> dropStatements = new ArrayList<String>();
        for (String objectName : objectNames) {
            dropStatements.add("DROP " + objectType + " " + dbSupport.quote(name, objectName) + " " + extraArguments);
        }
        return dropStatements;
    }

    /**
     * Generates the drop statements for Oracle Spatial Extensions-related database objects.
     *
     * @return The complete drop statements, ready to execute.
     * @ when the drop statements could not be generated.
     */
    private List<String> generateDropStatementsForSpatialExtensions()  {
        List<String> statements = new ArrayList<String>();

        if (!spatialExtensionsAvailable()) {
            LOG.debug("Oracle Spatial Extensions are not available. No cleaning of MDSYS tables and views.");
            return statements;
        }
        if (!dbSupport.getCurrentSchema().getName().equalsIgnoreCase(name)) {
            int count = JdbcTool.queryForInt(dbSupport.getConnection(), "SELECT COUNT (*) FROM all_sdo_geom_metadata WHERE owner=?", name);
            count += JdbcTool.queryForInt(dbSupport.getConnection(), "SELECT COUNT (*) FROM all_sdo_index_info WHERE sdo_index_owner=?", name);
            if (count > 0) {
                LOG.warn("Unable to clean Oracle Spatial objects for schema '" + name + "' as they do not belong to the default schema for this connection!");
            }
            return statements;
        }


        statements.add("DELETE FROM mdsys.user_sdo_geom_metadata");

        List<String> indexNames = JdbcTool.queryForStringList(dbSupport.getConnection(), "select INDEX_NAME from USER_SDO_INDEX_INFO");
        for (String indexName : indexNames) {
            statements.add("DROP INDEX \"" + indexName + "\"");
        }

        return statements;
    }

    /**
     * Generates the drop statements for queue tables.
     *
     * @return The complete drop statements, ready to execute.
     * @ when the drop statements could not be generated.
     */
    private List<String> generateDropStatementsForQueueTables()  {
        List<String> statements = new ArrayList<String>();

        List<String> queueTblNames = JdbcTool.queryForStringList(dbSupport.getConnection(), "select QUEUE_TABLE from USER_QUEUE_TABLES");
        for (String queueTblName : queueTblNames) {
            statements.add("begin DBMS_AQADM.drop_queue_table (queue_table=> '" + queueTblName + "', FORCE => TRUE); end;");
        }

        return statements;
    }

    /**
     * Checks whether Oracle Spatial extensions are available or not.
     *
     * @return {@code true} if they are available, {@code false} if not.
     * @ when checking availability of the spatial extensions failed.
     */
    private boolean spatialExtensionsAvailable()  {
        return JdbcTool.queryForInt(dbSupport.getConnection(), "SELECT COUNT(*) FROM all_views WHERE owner = 'MDSYS' AND view_name = 'USER_SDO_GEOM_METADATA'") > 0;
    }

    @Override
    protected Table[] doAllTables()  {
        List<String> tableNames = JdbcTool.queryForStringList(dbSupport.getConnection(), 
                "SELECT table_name FROM all_tables WHERE owner = ?"
                        // Ignore Recycle bin objects
                        + " AND table_name NOT LIKE 'BIN$%'"
                        // Ignore Spatial Index Tables as they get dropped automatically when the index gets dropped.
                        + " AND table_name NOT LIKE 'MDRT_%$'"
                        // Ignore Materialized View Logs
                        + " AND table_name NOT LIKE 'MLOG$%' AND table_name NOT LIKE 'RUPD$%'"
                        // Ignore Oracle Text Index Tables
                        + " AND table_name NOT LIKE 'DR$%'"
                        // Ignore Index Organized Tables
                        + " AND table_name NOT LIKE 'SYS_IOT_OVER_%'"
                        // Ignore Nested Tables
                        + " AND nested != 'YES'"
                        // Ignore Nested Tables
                        + " AND secondary != 'Y'", name);

        Table[] tables = new Table[tableNames.size()];
        for (int i = 0; i < tableNames.size(); i++) {
            tables[i] = new OracleTable(dbSupport, this, tableNames.get(i));
        }
        return tables;
    }

    @Override
    public Table getTable(String tableName) {
        return new OracleTable( dbSupport, this, tableName);
    }
}

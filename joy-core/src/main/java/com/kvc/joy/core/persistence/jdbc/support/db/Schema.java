package com.kvc.joy.core.persistence.jdbc.support.db;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.kvc.joy.commons.exception.SystemException;
import com.kvc.joy.core.persistence.jdbc.support.utils.JdbcTool;

/**
 * Represents a database schema.
 */
public abstract class Schema {

    /**
     * The database-specific support.
     */
    protected final DbSupport dbSupport;

    /**
     * The name of the schema.
     */
    protected final String name;

    /**
     * Creates a new schema.
     *
     * @param dbSupport    The database-specific support.
     * @param name         The name of the schema.
     */
    public Schema(DbSupport dbSupport, String name) {
        this.dbSupport = dbSupport;
        this.name = name;
    }

    /**
     * @return The name of the schema, quoted for the database it lives in.
     */
    public String getName() {
        return name;
    }

    /**
     * Checks whether this schema exists.
     *
     * @return {@code true} if it does, {@code false} if not.
     */
    public boolean exists() {
        try {
            return doExists();
        } catch (SQLException e) {
            throw new SystemException(e, "Unable to check whether schema " + this + " exists");
        }
    }

    /**
     * Checks whether this schema exists.
     *
     * @return {@code true} if it does, {@code false} if not.
     * @throws SQLException when the check failed.
     */
    protected abstract boolean doExists() throws SQLException;

    /**
     * Checks whether this schema is empty.
     *
     * @return {@code true} if it is, {@code false} if isn't.
     */
    public boolean empty() {
        try {
            return doEmpty();
        } catch (SQLException e) {
            throw new SystemException(e, "Unable to check whether schema " + this + " is empty");
        }
    }

    /**
     * Checks whether this schema is empty.
     *
     * @return {@code true} if it is, {@code false} if isn't.
     * @throws SQLException when the check failed.
     */
    protected abstract boolean doEmpty() throws SQLException;

    /**
     * Creates this schema in the database.
     */
    public void create() {
        try {
            doCreate();
        } catch (SQLException e) {
            throw new SystemException(e, "Unable to create schema " + this);
        }
    }

    /**
     * Creates this schema in the database.
     *
     * @throws SQLException when the creation failed.
     */
    protected abstract void doCreate() throws SQLException;

    /**
     * Drops this schema from the database.
     */
    public void drop() {
        try {
            doDrop();
        } catch (SQLException e) {
            throw new SystemException(e, "Unable to drop schema " + this);
        }
    }

    /**
     * Drops this schema from the database.
     *
     * @throws SQLException when the drop failed.
     */
    protected abstract void doDrop() throws SQLException;

    /**
     * Cleans all the objects in this schema.
     */
    public void clean() {
        try {
            doClean();
        } catch (SQLException e) {
            throw new SystemException(e, "Unable to clean schema " + this);
        }
    }

    /**
     * Cleans all the objects in this schema.
     *
     * @throws SQLException when the clean failed.
     */
    protected abstract void doClean() throws SQLException;

    /**
     * Retrieves all the tables in this schema.
     *
     * @return All tables in the schema.
     */
    public Table[] allTables() {
        try {
            return doAllTables();
        } catch (SQLException e) {
            throw new SystemException(e, "Unable to retrieve all tables in schema " + this);
        }
    }

    /**
     * Retrieves all the tables in this schema.
     *
     * @return All tables in the schema.
     * @throws SQLException when the retrieval failed.
     */
    protected abstract Table[] doAllTables() throws SQLException;

    /**
     * Retrieves all the types in this schema.
     *
     * @return All types in the schema.
     */
    public final Type[] allTypes() {
        ResultSet resultSet = null;
        try {
            resultSet = dbSupport.getConnection().getMetaData().getUDTs(null, name, null, null);

            List<Type> types = new ArrayList<Type>();
            while (resultSet.next()) {
                types.add(getType(resultSet.getString("TYPE_NAME")));
            }

            return types.toArray(new Type[types.size()]);
        } catch (SQLException e) {
            throw new SystemException(e, "Unable to retrieve all types in schema " + this);
        } finally {
            JdbcTool.closeResultSet(resultSet);
        }
    }

    /**
     * Retrieves the type with this name in this schema.
     *
     * @param typeName The name of the type.
     * @return The type.
     */
    protected Type getType(String typeName) {
        return null;
    }

    /**
     * Retrieves the table with this name in this schema.
     *
     * @param tableName The name of the table.
     * @return The table.
     */
    public abstract Table getTable(String tableName);

    /**
     * Retrieves the function with this name in this schema.
     *
     * @param functionName The name of the function.
     * @return The function.
     */
    public Function getFunction(String functionName, String... args) {
        throw new UnsupportedOperationException("getFunction()");
    }

    /**
     * Retrieves all the types in this schema.
     *
     * @return All types in the schema.
     */
    public final Function[] allFunctions() {
        try {
            return doAllFunctions();
        } catch (SQLException e) {
            throw new SystemException(e, "Unable to retrieve all functions in schema " + this);
        }
    }

    /**
     * Retrieves all the functions in this schema.
     *
     * @return All functions in the schema.
     * @throws SQLException when the retrieval failed.
     */
    protected Function[] doAllFunctions() throws SQLException {
        return new Function[0];
    }

    @Override
    public String toString() {
        return dbSupport.quote(name);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Schema schema = (Schema) o;
        return name.equals(schema.name);
    }

    @Override
    public int hashCode() {
        return name.hashCode();
    }
}

package com.kvc.joy.core.persistence.jdbc.support.db;

/**
 * A user defined type within a schema.
 */
public abstract class Function extends SchemaObject {
    /**
     * The arguments of the function.
     */
    protected final String[] args;

    /**
     * Creates a new function with this name within this schema.
     *
     * @param dbSupport    The database-specific support.
     * @param schema       The schema this function lives in.
     * @param name         The name of the function.
     * @param args         The arguments of the function.
     */
    public Function(DbSupport dbSupport, Schema schema, String name, String... args) {
        super(dbSupport, schema, name);
        this.args = args;
    }
}

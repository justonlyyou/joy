package org.joy.core.persistence.jdbc.support.db;

/**
 * A user defined type within a schema.
 */
public abstract class Type extends SchemaObject {
    /**
     * Creates a new type with this name within this schema.
     *
     * @param dbSupport    The database-specific support.
     * @param schema       The schema this type lives in.
     * @param name         The name of the type.
     */
    public Type(DbSupport dbSupport, Schema schema, String name) {
        super(dbSupport, schema, name);
    }
}

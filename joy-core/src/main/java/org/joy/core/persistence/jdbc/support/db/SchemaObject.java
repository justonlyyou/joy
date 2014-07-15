package org.joy.core.persistence.jdbc.support.db;


/**
 * An object within a database schema.
 */
public abstract class SchemaObject {

	/**
	 * The database-specific support.
	 */
	protected final DbSupport dbSupport;

	/**
	 * The schema this table lives in.
	 */
	protected final Schema schema;

	/**
	 * The name of the table.
	 */
	protected final String name;

	/**
	 * Creates a new schema object with this name within this schema.
	 * 
	 * @param dbSupport The database-specific support.
	 * @param schema The schema the object lives in.
	 * @param name The name of the object.
	 */
	public SchemaObject(DbSupport dbSupport, Schema schema, String name) {
		this.name = name;
		this.dbSupport = dbSupport;
		this.schema = schema;
	}

	/**
	 * @return The schema this object lives in.
	 */
	public final Schema getSchema() {
		return schema;
	}

	/**
	 * @return The name of the object.
	 */
	public final String getName() {
		return name;
	}

	/**
	 * Drops this object from the database.
	 */
	public final void drop() {
		doDrop();
	}

	/**
	 * Drops this object from the database.
	 * 
	 * @throws java.sql.SQLException when the drop failed.
	 */
	protected abstract void doDrop();

	@Override
	public String toString() {
		return dbSupport.quote(schema.getName(), name);
	}
}

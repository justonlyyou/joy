package com.kvc.joy.core.persistence.jdbc.support.db;

import com.kvc.joy.commons.exception.SystemException;
import com.kvc.joy.commons.log.Log;
import com.kvc.joy.commons.log.LogFactory;
import com.kvc.joy.core.persistence.jdbc.support.utils.JdbcTool;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Represents a database table within a schema.
 */
public abstract class Table extends SchemaObject {

	private static final Log LOG = LogFactory.getLog(Table.class);

	/**
	 * Creates a new table.
	 * 
	 * @param dbSupport The database-specific support.
	 * @param schema The schema this table lives in.
	 * @param name The name of the table.
	 */
	public Table(DbSupport dbSupport, Schema schema, String name) {
		super(dbSupport, schema, name);
	}

	/**
	 * Checks whether this table exists.
	 * 
	 * @return {@code true} if it does, {@code false} if not.
	 */
	public boolean exists() {
		return doExists();
	}

	/**
	 * Checks whether this table exists.
	 * 
	 * @return {@code true} if it does, {@code false} if not.
	 * @throws SQLException when the check failed.
	 */
	protected abstract boolean doExists();

	/**
	 * Checks whether this table is already present in the database. WITHOUT
	 * quoting either the table or the schema name!
	 * 
	 * @return {@code true} if the table exists, {@code false} if it doesn't.
	 */
	public boolean existsNoQuotes() {
		return doExistsNoQuotes();
	}

	/**
	 * Checks whether this table is already present in the database. WITHOUT
	 * quoting either the table or the schema name!
	 * 
	 * @return {@code true} if the table exists, {@code false} if it doesn't.
	 * @throws SQLException when there was an error checking whether this table
	 *             exists in this schema.
	 */
	protected abstract boolean doExistsNoQuotes();

	/**
	 * Checks whether the database contains a table matching these criteria.
	 * 
	 * @param catalog The catalog where the table resides. (optional)
	 * @param schema The schema where the table resides. (optional)
	 * @param table The name of the table. (optional)
	 * @param tableTypes The types of table to look for (ex.: TABLE). (optional)
	 * @return {@code true} if a matching table has been found, {@code false} if
	 *         not.
	 * @throws SQLException when the check failed.
	 */
	protected boolean exists(Schema catalog, Schema schema, String table, String... tableTypes) {
		String[] types = tableTypes;
		if (types.length == 0) {
			types = null;
		}

		ResultSet resultSet = null;
		boolean found;
		try {
			resultSet = dbSupport
					.getConnection()
					.getMetaData()
					.getTables(catalog == null ? null : catalog.getName(), schema == null ? null : schema.getName(),
							table, types);
			found = resultSet.next();
		} catch (Exception e) {
			throw new SystemException(e);
		} finally {
			JdbcTool.closeResultSet(resultSet);
		}

		return found;
	}

	/**
	 * Checks whether the table has a primary key.
	 * 
	 * @return {@code true} if a primary key has been found, {@code false} if
	 *         not.
	 */
	public boolean hasPrimaryKey() {
		ResultSet resultSet = null;
		boolean found;
		try {
			if (dbSupport.catalogIsSchema()) {
				resultSet = dbSupport.getConnection().getMetaData().getPrimaryKeys(schema.getName(), null, name);
			} else {
				resultSet = dbSupport.getConnection().getMetaData().getPrimaryKeys(null, schema.getName(), name);
			}
			found = resultSet.next();
		} catch (SQLException e) {
			throw new SystemException(e, "Unable to check whether table " + this + " has a primary key");
		} finally {
			JdbcTool.closeResultSet(resultSet);
		}

		return found;
	}

	/**
	 * Checks whether the database contains a column matching these criteria.
	 * 
	 * @param column The column to look for.
	 * @return {@code true} if a matching column has been found, {@code false}
	 *         if not.
	 */
	public boolean hasColumn(String column) {
		ResultSet resultSet = null;
		boolean found;
		try {
			if (dbSupport.catalogIsSchema()) {
				resultSet = dbSupport.getConnection().getMetaData().getColumns(schema.getName(), null, name, column);
			} else {
				resultSet = dbSupport.getConnection().getMetaData().getColumns(null, schema.getName(), name, column);
			}
			found = resultSet.next();
		} catch (SQLException e) {
			throw new SystemException(e, "Unable to check whether table " + this + " has a column named " + column);
		} finally {
			JdbcTool.closeResultSet(resultSet);
		}

		return found;
	}

	/**
	 * Determines the size (in characters) of this column.
	 * 
	 * @param column The column to look for.
	 * @return The size (in characters).
	 */
	public int getColumnSize(String column) {
		ResultSet resultSet = null;
		int columnSize;
		try {
			if (dbSupport.catalogIsSchema()) {
				resultSet = dbSupport.getConnection().getMetaData().getColumns(schema.getName(), null, name, column);
			} else {
				resultSet = dbSupport.getConnection().getMetaData().getColumns(null, schema.getName(), name, column);
			}
			resultSet.next();
			columnSize = resultSet.getInt("COLUMN_SIZE");
		} catch (SQLException e) {
			throw new SystemException(e, "Unable to check the size of column " + column + " in table " + this);
		} finally {
			JdbcTool.closeResultSet(resultSet);
		}

		return columnSize;
	}

	/**
	 * Locks this table in this schema using a read/write pessimistic lock until
	 * the end of the current transaction.
	 */
	public void lock() {
		LOG.debug("Locking table " + this + "...");
		doLock();
		LOG.debug("Lock acquired for table " + this);
	}

	/**
	 * Locks this table in this schema using a read/write pessimistic lock until
	 * the end of the current transaction.
	 * 
	 * @throws SQLException when this table in this schema could not be locked.
	 */
	protected abstract void doLock();
}

package com.kvc.joy.core.persistence.jdbc.support.db;

/**
 * A sql statement from a script that can be executed at once against a
 * database.
 */
public class SqlStatement {
	/**
	 * The original line number where the statement was located in the script it
	 * came from.
	 */
	private int lineNumber;

	/**
	 * The sql to send to the database.
	 */
	private String sql;

	/**
	 * Creates a new sql statement.
	 * 
	 * @param lineNumber The original line number where the statement was
	 *            located in the script it came from.
	 * @param sql The sql to send to the database.
	 */
	public SqlStatement(int lineNumber, String sql) {
		this.lineNumber = lineNumber;
		this.sql = sql;
	}

	/**
	 * @return The original line number where the statement was located in the
	 *         script it came from.
	 */
	public int getLineNumber() {
		return lineNumber;
	}

	/**
	 * @return The sql to send to the database.
	 */
	public String getSql() {
		return sql;
	}
}

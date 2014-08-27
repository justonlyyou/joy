package org.joy.core.persistence.jdbc.support.db.h2;

import org.joy.commons.exception.SystemException;
import org.joy.core.persistence.jdbc.model.vo.MdRdbColumn;
import org.joy.core.persistence.jdbc.model.vo.MdRdbTable;
import org.joy.core.persistence.jdbc.support.db.DbSupport;
import org.joy.core.persistence.jdbc.support.db.Schema;
import org.joy.core.persistence.jdbc.support.db.SqlStatementBuilder;
import org.joy.core.persistence.jdbc.support.enums.RdbType;
import org.joy.core.persistence.jdbc.support.utils.JdbcTool;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Types;
import java.text.MessageFormat;

/**
 * H2 database specific support
 */
public class H2DbSupport extends DbSupport {

    private static final String TABLE_COMMENT_SQL = "COMMENT ON TABLE {0} IS ''{1}''";
    private static final String ALTER_COLUMN_COMMENT_SQL = "COMMENT ON COLUMN {0}.{1} IS ''{2}''";
    private static final String ALTER_COLUMN_DEFAULT_VALUE_SQL = "ALTER TABLE {0} ALTER COLUMN {1} SET DEFAULT {2}";

	/**
	 * Creates a new instance.
	 * 
	 * @param connection The connection to use.
	 */
	public H2DbSupport(Connection connection) {
		super(connection);
	}

	public String getScriptLocation() {
		return "com/googlecode/flyway/core/dbsupport/h2/";
	}

	public String getCurrentUserFunction() {
		return "USER()";
	}

	protected String doGetCurrentSchema() {
        String schema = null;
        try {
            String procedure = "{?=call SCHEMA()}";
            CallableStatement cs = connection.prepareCall(procedure);
            cs.registerOutParameter(1, Types.VARCHAR);
            cs.executeUpdate();
            schema = cs.getString(1);
            cs.close();
        } catch (SQLException e) {
            throw new SystemException(e);
        }

//        ResultSet resultSet = null;
//		String schema = null;
//        try {
//            String catalog = connection.getCatalog();
//
//            resultSet = jdbc.getMetaData().getSchemas();
//			while (resultSet.next()) {
//				if (resultSet.getBoolean("IS_DEFAULT")) {
//					schema = resultSet.getString("TABLE_SCHEM");
//					break;
//				}
//			}
//		} catch (Exception e) {
//			throw new SystemException(e);
//		} finally {
//			JdbcTool.closeResultSet(resultSet);
//		}

		return schema;
	}

	@Override
	protected void doSetCurrentSchema(Schema schema) {
		JdbcTool.execute(connection, "SET SCHEMA " + schema);
	}

	public boolean supportsDdlTransactions() {
		return false;
	}

	public String getBooleanTrue() {
		return "1";
	}

	public String getBooleanFalse() {
		return "0";
	}

	public SqlStatementBuilder createSqlStatementBuilder() {
		return new H2SqlStatementBuilder();
	}

	@Override
	public String doQuote(String identifier) {
		return "\"" + identifier + "\"";
	}

	@Override
	public Schema getSchema(String name) {
		return new H2Schema(this, name);
	}

	@Override
	public boolean catalogIsSchema() {
		return false;
	}

	@Override
	public int getNullType() {
		return Types.VARCHAR;
	}

    @Override
    public String getAlterTableCommentSql(MdRdbTable table) {
        String tableName = table.getName();
        String tableComment = table.getComment();
        return MessageFormat.format(TABLE_COMMENT_SQL, tableName, tableComment);
    }

    @Override
    public String getAlterColumnCommentSql(MdRdbTable table, MdRdbColumn column, MdRdbColumn columnInDb) {
        String columnName = column.getName();
        String columnComment = column.getComment().toString();
        return MessageFormat.format(ALTER_COLUMN_COMMENT_SQL, table.getName(), columnName, columnComment);
    }

    @Override
    public String getAlterColumnDefaultValueSql(MdRdbTable table, MdRdbColumn column, MdRdbColumn columnInDb) {
        String columnName = column.getName();
        String defaultValue = column.getDefaultValue();
        String type = columnInDb.getType().toLowerCase();
        if(type.contains("char")) {
            defaultValue = "'" + defaultValue + "'";
        }
        return MessageFormat.format(ALTER_COLUMN_DEFAULT_VALUE_SQL, table.getName(), columnName, defaultValue);
    }

    @Override
    public RdbType getDatabaseType() {
        return RdbType.H2;
    }

}
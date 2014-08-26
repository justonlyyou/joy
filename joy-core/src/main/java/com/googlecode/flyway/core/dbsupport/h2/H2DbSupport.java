package com.googlecode.flyway.core.dbsupport.h2;

import com.googlecode.flyway.core.dbsupport.DbSupport;
import com.googlecode.flyway.core.dbsupport.JdbcTemplate;
import com.googlecode.flyway.core.dbsupport.Schema;
import com.googlecode.flyway.core.dbsupport.SqlStatementBuilder;
import com.googlecode.flyway.core.util.jdbc.JdbcUtils;

import java.sql.*;
import java.text.MessageFormat;

/**
 * H2 database specific support
 */
public class H2DbSupport extends DbSupport {

	/**
	 * Creates a new instance.
	 * 
	 * @param connection The connection to use.
	 */
	public H2DbSupport(Connection connection) {
		super(new JdbcTemplate(connection, Types.VARCHAR));
	}

	public String getScriptLocation() {
		return "com/googlecode/flyway/core/dbsupport/h2/";
	}

	public String getCurrentUserFunction() {
		return "USER()";
	}

//	protected String doGetCurrentSchema() throws SQLException {
//        ResultSet resultSet = null;
//        String schema = null;
//        try {
//            resultSet = jdbcTemplate.getMetaData().getSchemas();
//            while (resultSet.next()) {
//                if (resultSet.getBoolean("IS_DEFAULT")) {
//                    schema = resultSet.getString("TABLE_SCHEM");
//                    break;
//                }
//            }
//        } finally {
//            JdbcUtils.closeResultSet(resultSet);
//        }
//
//        return schema;
//	}

    protected String doGetCurrentSchema() throws SQLException {
        String procedure = "{?=call SCHEMA()}";
        CallableStatement cs = jdbcTemplate.getConnection().prepareCall(procedure);
        cs.registerOutParameter(1, Types.VARCHAR);
        cs.executeUpdate();
        String schema = cs.getString(1);
        cs.close();
        return schema;
    }

	@Override
    protected void doSetCurrentSchema(Schema schema) throws SQLException {
        jdbcTemplate.execute("SET SCHEMA " + schema);
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
//		return "\"" + identifier + "\"";
        return identifier; // modify
	}

	@Override
    public Schema getSchema(String name) {
        return new H2Schema(jdbcTemplate, this, name);
    }

	@Override
	public boolean catalogIsSchema() {
		return false;
	}

}
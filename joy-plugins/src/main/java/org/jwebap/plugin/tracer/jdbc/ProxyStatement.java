package org.jwebap.plugin.tracer.jdbc;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLWarning;
import java.sql.Statement;

import org.jwebap.core.Trace;

import com.kvc.joy.commons.log.Log;
import com.kvc.joy.commons.log.LogFactory;
import com.kvc.joy.plugin.monitor.jdbc.model.vo.ParamMsg;


public class ProxyStatement extends Trace implements Statement {
	
	protected ProxyConnection _conn;
	protected Statement _stmt = null;
	protected boolean _closed = false;
	private final static Log logger = LogFactory.getLog(ProxyStatement.class); 

	public ProxyStatement(ProxyConnection conn, Statement stmt) {
		super(conn);
		_stmt = stmt;
		_conn = conn;
	}

	public ResultSet executeQuery(String sql) throws SQLException {
		checkOpen();
		ResultSet rs = null;
		Trace child = null;
		try {
			child = new Trace(this);
			child.setContent(sql);
			rs = _stmt.executeQuery(sql);

		} catch (SQLException e) {
			throw e;
		} finally {
			child.inActive();
			//TODO
			logInfoToDb(child);
		}
		return rs;
	}

	public boolean execute(String sql) throws SQLException {
		checkOpen();
		boolean tag = false;
		Trace child = null;
		try {
			child = new Trace(this);
			child.setContent(sql);
			tag = _stmt.execute(sql);

		} catch (SQLException e) {
			throw e;
		} finally {

			child.inActive();
			//TODO
			logInfoToDb(child);
		}
		return tag;
	}

	public int executeUpdate(String sql) throws SQLException {
		checkOpen();
		int tag = 0;
		Trace child = null;
		try {
			child = new Trace(this);
			child.setContent(sql);
			tag = _stmt.executeUpdate(sql);

		} catch (SQLException e) {
			throw e;
		} finally {
			child.inActive();
			//TODO
			logInfoToDb(child);
		}
		return tag;
	}

	public void close() throws SQLException {
		try {
			if (_stmt != null) {
				_stmt.close();
			}
		} finally {
			inActive();
			_stmt = null;
			_conn = null;
			_closed = true;
		}

	}

	// ///////////////////////////////////////////////////////////////////////////////////////
	protected void checkOpen() throws SQLException {
		if (isClosed() || _stmt == null) {
			throw new SQLException(this.getClass().getName() + " is closed.");
		}
	}

	public boolean isClosed() throws SQLException {
		if (_closed) {
			return true;
		}
		return false;
	}

	public ResultSet getResultSet() throws SQLException {
		checkOpen();
		try {
			return _stmt.getResultSet();
		} catch (SQLException e) {
			throw e;
		}
	}

	public int getMaxFieldSize() throws SQLException {
		checkOpen();
		try {
			return _stmt.getMaxFieldSize();
		} catch (SQLException e) {
			throw e;
		}
	}

	public void setMaxFieldSize(int max) throws SQLException {
		checkOpen();
		try {
			_stmt.setMaxFieldSize(max);
		} catch (SQLException e) {
			throw e;
		}
	}

	public int getMaxRows() throws SQLException {
		checkOpen();
		try {
			return _stmt.getMaxRows();
		} catch (SQLException e) {
			throw e;
		}
	}

	public void setMaxRows(int max) throws SQLException {
		checkOpen();
		try {
			_stmt.setMaxRows(max);
		} catch (SQLException e) {
			throw e;
		}
	}

	public void setEscapeProcessing(boolean enable) throws SQLException {
		checkOpen();
		try {
			_stmt.setEscapeProcessing(enable);
		} catch (SQLException e) {
			throw e;
		}
	}

	public int getQueryTimeout() throws SQLException {
		checkOpen();
		try {
			return _stmt.getQueryTimeout();
		} catch (SQLException e) {
			throw e;
		}
	}

	public void setQueryTimeout(int seconds) throws SQLException {
		checkOpen();
		try {
			_stmt.setQueryTimeout(seconds);
		} catch (SQLException e) {
			throw e;
		}
	}

	public void cancel() throws SQLException {
		checkOpen();
		try {
			_stmt.cancel();
		} catch (SQLException e) {
			throw e;
		}
	}

	public SQLWarning getWarnings() throws SQLException {
		checkOpen();
		try {
			return _stmt.getWarnings();
		} catch (SQLException e) {
			throw e;
		}
	}

	public void clearWarnings() throws SQLException {
		checkOpen();
		try {
			_stmt.clearWarnings();
		} catch (SQLException e) {
			throw e;
		}
	}

	public void setCursorName(String name) throws SQLException {
		checkOpen();
		try {
			_stmt.setCursorName(name);
		} catch (SQLException e) {
			throw e;
		}
	}

	public int getUpdateCount() throws SQLException {
		checkOpen();
		try {
			return _stmt.getUpdateCount();
		} catch (SQLException e) {
			throw e;
		}
	}

	public boolean getMoreResults() throws SQLException {
		checkOpen();
		try {
			return _stmt.getMoreResults();
		} catch (SQLException e) {
			throw e;
		}
	}

	public void setFetchDirection(int direction) throws SQLException {
		checkOpen();
		try {
			_stmt.setFetchDirection(direction);
		} catch (SQLException e) {
			throw e;
		}
	}

	public int getFetchDirection() throws SQLException {
		checkOpen();
		try {
			return _stmt.getFetchDirection();
		} catch (SQLException e) {
			throw e;
		}
	}

	public void setFetchSize(int rows) throws SQLException {
		checkOpen();
		try {
			_stmt.setFetchSize(rows);
		} catch (SQLException e) {
			throw e;
		}
	}

	public int getFetchSize() throws SQLException {
		checkOpen();
		try {
			return _stmt.getFetchSize();
		} catch (SQLException e) {
			throw e;
		}
	}

	public int getResultSetConcurrency() throws SQLException {
		checkOpen();
		try {
			return _stmt.getResultSetConcurrency();
		} catch (SQLException e) {
			throw e;
		}
	}

	public int getResultSetType() throws SQLException {
		checkOpen();
		try {
			return _stmt.getResultSetType();
		} catch (SQLException e) {
			throw e;
		}
	}

	public void addBatch(String sql) throws SQLException {
		checkOpen();
		try {
			_stmt.addBatch(sql);
		} catch (SQLException e) {
			throw e;
		}
	}

	public void clearBatch() throws SQLException {
		checkOpen();
		try {
			_stmt.clearBatch();
		} catch (SQLException e) {
			throw e;
		}
	}

	public int[] executeBatch() throws SQLException {
		checkOpen();
		try {
			return _stmt.executeBatch();
		} catch (SQLException e) {
			throw e;
		}
	}

	// ------------------- JDBC 3.0 -----------------------------------------

	public boolean getMoreResults(int current) throws SQLException {
		checkOpen();
		try {
			return _stmt.getMoreResults(current);
		} catch (SQLException e) {
			throw e;
		}
	}

	public ResultSet getGeneratedKeys() throws SQLException {
		checkOpen();
		try {
			return _stmt.getGeneratedKeys();
		} catch (SQLException e) {
			throw e;
		}
	}

	public int executeUpdate(String sql, int autoGeneratedKeys) throws SQLException {
		checkOpen();
		Trace child = null;
		try {
			child = new Trace(this);
			return _stmt.executeUpdate(sql, autoGeneratedKeys);
		} catch (SQLException e) {
			throw e;
		}finally{
			//TODO
			child.inActive();
			logInfoToDb(child);
		}
	}

	public int executeUpdate(String sql, int columnIndexes[]) throws SQLException {
		checkOpen();
		Trace child = null;
		try {
			child = new Trace(this);
			return _stmt.executeUpdate(sql, columnIndexes);
		} catch (SQLException e) {
			throw e;
		}finally{
			//TODO
			child.inActive();
			logInfoToDb(child);
		}
	}

	public int executeUpdate(String sql, String columnNames[]) throws SQLException {
		checkOpen();
		Trace child = null;
		try {
			child = new Trace(this);
			return _stmt.executeUpdate(sql, columnNames);
		} catch (SQLException e) {
			throw e;
		}finally{
			//TODO
			child.inActive();
			logInfoToDb(child);
		}
	}

	public boolean execute(String sql, int autoGeneratedKeys) throws SQLException {
		checkOpen();
		Trace child = null;
		try {
			child = new Trace(this);
			return _stmt.execute(sql, autoGeneratedKeys);
		} catch (SQLException e) {
			throw e;
		}finally{
			//TODO
			child.inActive();
			logInfoToDb(child);
		}
	}

	public boolean execute(String sql, int columnIndexes[]) throws SQLException {
		checkOpen();
		Trace child = null;
		try {
			child = new Trace(this);
			return _stmt.execute(sql, columnIndexes);
		} catch (SQLException e) {
			throw e;
		}finally{
			//TODO
			child.inActive();
			logInfoToDb(child);
		}
	}

	public boolean execute(String sql, String columnNames[]) throws SQLException {
		checkOpen();
		Trace child = null;
		try {
			child = new Trace(this);
			return _stmt.execute(sql, columnNames);
		} catch (SQLException e) {
			throw e;
		}finally{
			//TODO
			child.inActive();
			logInfoToDb(child);
		}
	}

	public int getResultSetHoldability() throws SQLException {
		checkOpen();
		try {
			return _stmt.getResultSetHoldability();
		} catch (SQLException e) {
			throw e;
		}
	}

	public Connection getConnection() throws SQLException {
		checkOpen();
		return _conn;
	}
	
	private void logInfoToDb(Trace trace) {
		try {
			if (!verify(trace)) {
				return;
			}
			ParamMsg message = new ParamMsg(trace.getContent(), trace.getActiveTime());
			logger.debug(message.toString(), message);
		} catch (Exception e) {
			logger.error(e, "sql性能监控处理出错 ！");
		}
	}
	
	/**校验是否需要记录,对于记录日志表的sql语句不持久化*/
	protected boolean verify(Trace trace){
		//日志表
		if(trace.getContent().toUpperCase().indexOf("T_SYS_SQL_LOG") != -1){
			return false;
		}
		return true;
	}

	public boolean isWrapperFor(Class<?> arg0) throws SQLException {
		// TODO Auto-generated method stub
		return false;
	}

	public <T> T unwrap(Class<T> arg0) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	public void closeOnCompletion() throws SQLException {
		// TODO Auto-generated method stub
		
	}

	public boolean isCloseOnCompletion() throws SQLException {
		// TODO Auto-generated method stub
		return false;
	}

	public boolean isPoolable() throws SQLException {
		// TODO Auto-generated method stub
		return false;
	}

	public void setPoolable(boolean arg0) throws SQLException {
		// TODO Auto-generated method stub
		
	}
}

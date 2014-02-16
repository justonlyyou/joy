package org.jwebap.plugin.tracer.jdbc;

import com.kvc.joy.commons.log.Log;
import com.kvc.joy.commons.log.LogFactory;
import com.kvc.joy.plugin.monitor.jdbc.model.vo.ParamMsg;
import org.jwebap.core.Trace;

import java.sql.*;


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
		} finally {
            if (child != null) {
                child.inActive();
            }
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
		} finally {
            if(child != null) {
                child.inActive();
                logInfoToDb(child);
            }
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

		} finally {
            if(child != null) {
                child.inActive();
                logInfoToDb(child);
            }
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
        return _closed;
    }

	public ResultSet getResultSet() throws SQLException {
		checkOpen();
        return _stmt.getResultSet();
    }

	public int getMaxFieldSize() throws SQLException {
		checkOpen();
        return _stmt.getMaxFieldSize();
    }

	public void setMaxFieldSize(int max) throws SQLException {
		checkOpen();
        _stmt.setMaxFieldSize(max);
    }

	public int getMaxRows() throws SQLException {
		checkOpen();
        return _stmt.getMaxRows();
    }

	public void setMaxRows(int max) throws SQLException {
		checkOpen();
        _stmt.setMaxRows(max);
    }

	public void setEscapeProcessing(boolean enable) throws SQLException {
		checkOpen();
        _stmt.setEscapeProcessing(enable);
    }

	public int getQueryTimeout() throws SQLException {
		checkOpen();
        return _stmt.getQueryTimeout();
    }

	public void setQueryTimeout(int seconds) throws SQLException {
		checkOpen();
        _stmt.setQueryTimeout(seconds);
    }

	public void cancel() throws SQLException {
		checkOpen();
        _stmt.cancel();
    }

	public SQLWarning getWarnings() throws SQLException {
		checkOpen();
        return _stmt.getWarnings();
    }

	public void clearWarnings() throws SQLException {
		checkOpen();
        _stmt.clearWarnings();
    }

	public void setCursorName(String name) throws SQLException {
		checkOpen();
        _stmt.setCursorName(name);
    }

	public int getUpdateCount() throws SQLException {
		checkOpen();
        return _stmt.getUpdateCount();
    }

	public boolean getMoreResults() throws SQLException {
		checkOpen();
        return _stmt.getMoreResults();
    }

	public void setFetchDirection(int direction) throws SQLException {
		checkOpen();
        _stmt.setFetchDirection(direction);
    }

	public int getFetchDirection() throws SQLException {
		checkOpen();
        return _stmt.getFetchDirection();
    }

	public void setFetchSize(int rows) throws SQLException {
		checkOpen();
        _stmt.setFetchSize(rows);
    }

	public int getFetchSize() throws SQLException {
		checkOpen();
        return _stmt.getFetchSize();
    }

	public int getResultSetConcurrency() throws SQLException {
		checkOpen();
        return _stmt.getResultSetConcurrency();
    }

	public int getResultSetType() throws SQLException {
		checkOpen();
        return _stmt.getResultSetType();
    }

	public void addBatch(String sql) throws SQLException {
		checkOpen();
        _stmt.addBatch(sql);
    }

	public void clearBatch() throws SQLException {
		checkOpen();
        _stmt.clearBatch();
    }

	public int[] executeBatch() throws SQLException {
		checkOpen();
        return _stmt.executeBatch();
    }

	// ------------------- JDBC 3.0 -----------------------------------------

	public boolean getMoreResults(int current) throws SQLException {
		checkOpen();
        return _stmt.getMoreResults(current);
    }

	public ResultSet getGeneratedKeys() throws SQLException {
		checkOpen();
        return _stmt.getGeneratedKeys();
    }

	public int executeUpdate(String sql, int autoGeneratedKeys) throws SQLException {
		checkOpen();
		Trace child = null;
		try {
			child = new Trace(this);
			return _stmt.executeUpdate(sql, autoGeneratedKeys);
		} finally{
            if(child != null) {
                child.inActive();
                logInfoToDb(child);
            }
		}
	}

	public int executeUpdate(String sql, int columnIndexes[]) throws SQLException {
		checkOpen();
		Trace child = null;
		try {
			child = new Trace(this);
			return _stmt.executeUpdate(sql, columnIndexes);
		} finally{
            if(child != null) {
                child.inActive();
                logInfoToDb(child);
            }
		}
	}

	public int executeUpdate(String sql, String columnNames[]) throws SQLException {
		checkOpen();
		Trace child = null;
		try {
			child = new Trace(this);
			return _stmt.executeUpdate(sql, columnNames);
		} finally{
            if(child != null) {
                child.inActive();
                logInfoToDb(child);
            }
		}
	}

	public boolean execute(String sql, int autoGeneratedKeys) throws SQLException {
		checkOpen();
		Trace child = null;
		try {
			child = new Trace(this);
			return _stmt.execute(sql, autoGeneratedKeys);
		} finally{
            if(child != null) {
                child.inActive();
                logInfoToDb(child);
            }
		}
	}

	public boolean execute(String sql, int columnIndexes[]) throws SQLException {
		checkOpen();
		Trace child = null;
		try {
			child = new Trace(this);
			return _stmt.execute(sql, columnIndexes);
		} finally{
            if(child != null) {
                child.inActive();
                logInfoToDb(child);
            }
		}
	}

	public boolean execute(String sql, String columnNames[]) throws SQLException {
		checkOpen();
		Trace child = null;
		try {
			child = new Trace(this);
			return _stmt.execute(sql, columnNames);
		} finally{
            if(child != null) {
                child.inActive();
                logInfoToDb(child);
            }
		}
	}

	public int getResultSetHoldability() throws SQLException {
		checkOpen();
        return _stmt.getResultSetHoldability();
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
        return trace.getContent().toUpperCase().contains("T_SYS_SQL_LOG");
    }

	public boolean isWrapperFor(Class<?> arg0) throws SQLException {
		// TODO Auto-generated method stub
		return false;
	}

	public <T> T unwrap(Class<T> arg0) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	public void closeOnCompletion() {
		// TODO Auto-generated method stub
		
	}

	public boolean isCloseOnCompletion() {
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

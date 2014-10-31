package org.jwebap.plugin.tracer.jdbc;

import org.joy.commons.lang.string.StringTool;
import org.joy.commons.log.Log;
import org.joy.commons.log.LogFactory;
import org.joy.plugin.monitor.jdbc.jwebap.service.IPreparedSqlConvertor;
import org.joy.plugin.monitor.jdbc.jwebap.service.impl.PreparedSqlConvertor;
import org.joy.plugin.monitor.jdbc.jwebap.model.vo.ParamMsg;
import org.jwebap.core.Trace;

import java.io.InputStream;
import java.io.Reader;
import java.math.BigDecimal;
import java.sql.*;
import java.util.Calendar;


/**
 * 用于检测SQL执行轨迹的PreparedStatement代理类
 * 
 * @author leadyu
 */
public class ProxyPreparedStatement extends ProxyStatement
		implements PreparedStatement {
	
	protected PreparedStatement _stmt = null;
	private String _sql=null;
	private int _batchCount=0;
	private IPreparedSqlConvertor convertor;
	protected static final Log logger = LogFactory.getLog(ProxyPreparedStatement.class); 
	
	public ProxyPreparedStatement(ProxyConnection conn,
			PreparedStatement stmt) {
		super(conn, stmt);
		_stmt = stmt;
	}

	public ProxyPreparedStatement(ProxyConnection conn,
			PreparedStatement stmt, String sql) {
		this(conn, stmt);
		
		_sql=sql;
		if(StringTool.isNotBlank(sql) && sql.contains("?")){
			convertor = new PreparedSqlConvertor(sql);
		}
	}
	private void setParam(int index, Object value){
		if(convertor != null){
			convertor.setParam(index, value);
		}
	}
	private void endSQL() {
		Trace[] ts = getChildTraces();
        for (Trace t : ts) {
            t.inActive();
            logInfoToDb(t);
        }

	}

	public ResultSet executeQuery() throws SQLException {
		checkOpen();
		try {
			//增加SQL轨迹
			Trace child = new Trace(this);
			child.setContent(_sql);
			
			return _stmt.executeQuery();
		} finally {
			endSQL();

		}
	}


	public void addBatch() throws SQLException {
		checkOpen();
		
		try {
			_stmt.addBatch();
		}finally{
			_batchCount++;
			//记录sql内容为批量更新
			_sql="(batch update "+_batchCount+"): " + _sql;
		}
	}
	
	public int[] executeBatch() throws SQLException{
		Trace child = new Trace(this);
		child.setContent(_sql);
		
		int [] nums = super.executeBatch();
		
		child.inActive();
		logInfoToDb(child);
		return nums;
	}
	
	public int executeUpdate() throws SQLException {
		checkOpen();
		try {
			//增加SQL轨迹
			Trace child = new Trace(this);
			child.setContent(_sql);
			
			return _stmt.executeUpdate();
		} finally {
			endSQL();

		}
	}

	public boolean execute() throws SQLException {
		checkOpen();
		try {
			//增加SQL轨迹
			Trace child = new Trace(this);
			child.setContent(_sql);
			
			return _stmt.execute();
		} finally {
			endSQL();
		}
	}

	public void close() throws SQLException {
		super.close();
		this._stmt = null;

	}

	// ////////////////////////////////////////////////////////////////////////////////////////

	public void setNull(int parameterIndex, int sqlType) throws SQLException {
		checkOpen();
        setParam(parameterIndex, "");
        _stmt.setNull(parameterIndex, sqlType);
    }

	public void setBoolean(int parameterIndex, boolean x) throws SQLException {
		checkOpen();
        setParam(parameterIndex, x);
        _stmt.setBoolean(parameterIndex, x);
    }

	public void setByte(int parameterIndex, byte x) throws SQLException {
		checkOpen();
        setParam(parameterIndex, x);
        _stmt.setByte(parameterIndex, x);
    }

	public void setShort(int parameterIndex, short x) throws SQLException {
		checkOpen();
        setParam(parameterIndex, x);
        _stmt.setShort(parameterIndex, x);
    }

	public void setInt(int parameterIndex, int x) throws SQLException {
		checkOpen();
        setParam(parameterIndex, x);
        _stmt.setInt(parameterIndex, x);
    }

	public void setLong(int parameterIndex, long x) throws SQLException {
		checkOpen();
        setParam(parameterIndex, x);
        _stmt.setLong(parameterIndex, x);
    }

	public void setFloat(int parameterIndex, float x) throws SQLException {
		checkOpen();
        setParam(parameterIndex, x);
        _stmt.setFloat(parameterIndex, x);
    }

	public void setDouble(int parameterIndex, double x) throws SQLException {
		checkOpen();
        setParam(parameterIndex, x);
        _stmt.setDouble(parameterIndex, x);
    }

	public void setBigDecimal(int parameterIndex, BigDecimal x)
			throws SQLException {
		checkOpen();
        setParam(parameterIndex, x);
        _stmt.setBigDecimal(parameterIndex, x);
    }

	public void setString(int parameterIndex, String x) throws SQLException {
		checkOpen();
        setParam(parameterIndex, x);
        _stmt.setString(parameterIndex, x);
    }

	public void setBytes(int parameterIndex, byte[] x) throws SQLException {
		checkOpen();
        setParam(parameterIndex, x);
        _stmt.setBytes(parameterIndex, x);
    }

	public void setDate(int parameterIndex, java.sql.Date x)
			throws SQLException {
		checkOpen();
        setParam(parameterIndex, x);
        _stmt.setDate(parameterIndex, x);
    }

	public void setTime(int parameterIndex, java.sql.Time x)
			throws SQLException {
		checkOpen();
        setParam(parameterIndex, x);
        _stmt.setTime(parameterIndex, x);
    }

	public void setTimestamp(int parameterIndex, java.sql.Timestamp x)
			throws SQLException {
		checkOpen();
        setParam(parameterIndex, x);
        _stmt.setTimestamp(parameterIndex, x);
    }

	public void setAsciiStream(int parameterIndex, java.io.InputStream x,
			int length) throws SQLException {
		checkOpen();
        setParam(parameterIndex, "?");
        _stmt.setAsciiStream(parameterIndex, x, length);
    }

	/** @deprecated */
	public void setUnicodeStream(int parameterIndex, java.io.InputStream x,
			int length) throws SQLException {
		checkOpen();
        setParam(parameterIndex, "?");
        _stmt.setUnicodeStream(parameterIndex, x, length);
    }

	public void setBinaryStream(int parameterIndex, java.io.InputStream x,
			int length) throws SQLException {
		checkOpen();
        setParam(parameterIndex, "?");
        _stmt.setBinaryStream(parameterIndex, x, length);
    }

	public void clearParameters() throws SQLException {
		checkOpen();
        _stmt.clearParameters();
    }

	public void setObject(int parameterIndex, Object x, int targetSqlType,
			int scale) throws SQLException {
		checkOpen();
        setParam(parameterIndex, x);
        _stmt.setObject(parameterIndex, x, targetSqlType, scale);
    }

	public void setObject(int parameterIndex, Object x, int targetSqlType)
			throws SQLException {
		checkOpen();
        setParam(parameterIndex, x);
        _stmt.setObject(parameterIndex, x, targetSqlType);
    }

	public void setObject(int parameterIndex, Object x) throws SQLException {
		checkOpen();
        setParam(parameterIndex, x);
        _stmt.setObject(parameterIndex, x);
    }

	public void setCharacterStream(int parameterIndex, java.io.Reader reader,
			int length) throws SQLException {
		checkOpen();
        setParam(parameterIndex, "?");
        _stmt.setCharacterStream(parameterIndex, reader, length);
    }

	public void setRef(int i, Ref x) throws SQLException {
		checkOpen();
        setParam(i, "?");
        _stmt.setRef(i, x);
    }

	public void setBlob(int i, Blob x) throws SQLException {
		checkOpen();
        setParam(i, "?");
        _stmt.setBlob(i, x);
    }

	public void setClob(int i, Clob x) throws SQLException {
		checkOpen();
        setParam(i, "?");
        _stmt.setClob(i, x);
    }

	public void setArray(int i, Array x) throws SQLException {
		checkOpen();
        _stmt.setArray(i, x);
    }

	public ResultSetMetaData getMetaData() throws SQLException {
		checkOpen();
        return _stmt.getMetaData();
    }

	public void setDate(int parameterIndex, java.sql.Date x, Calendar cal)
			throws SQLException {
		checkOpen();
        setParam(parameterIndex, x);
        _stmt.setDate(parameterIndex, x, cal);
    }

	public void setTime(int parameterIndex, java.sql.Time x, Calendar cal)
			throws SQLException {
		checkOpen();
        setParam(parameterIndex, x);
        _stmt.setTime(parameterIndex, x, cal);
    }

	public void setTimestamp(int parameterIndex, java.sql.Timestamp x,
			Calendar cal) throws SQLException {
		checkOpen();
        setParam(parameterIndex, x);
        _stmt.setTimestamp(parameterIndex, x, cal);
    }

	public void setNull(int paramIndex, int sqlType, String typeName)
			throws SQLException {
		checkOpen();
        setParam(paramIndex, "");
        _stmt.setNull(paramIndex, sqlType, typeName);
    }

	// ------------------- JDBC 3.0 -----------------------------------------

	public void setURL(int parameterIndex, java.net.URL x) throws SQLException {
		checkOpen();
        setParam(parameterIndex, "?");
        _stmt.setURL(parameterIndex, x);
    }

	public java.sql.ParameterMetaData getParameterMetaData()
			throws SQLException {
		checkOpen();
        return _stmt.getParameterMetaData();
    }
	
	// 以下代码
	
	/**
	 * 记录sql执行信息到数据库
	 */
	private void logInfoToDb(Trace trace) {
		try {
			if (!verify(trace)) {
				return;
			}
			
			ParamMsg message;
			if (convertor == null) {
				message = new ParamMsg(trace.getContent(), Integer.valueOf(trace.getActiveTime() + ""));
			} else {
				message = new ParamMsg(convertor, Integer.valueOf(trace.getActiveTime() + ""));
			}
			logger.debug(message.toString(), message);
		} catch (Exception e) {
			logger.error(e, "sql性能监控处理出错 ！");
		}
	}

	public void setRowId(int parameterIndex, RowId x) throws SQLException {
		// TODO Auto-generated method stub
		
	}

	public void setNString(int parameterIndex, String value) throws SQLException {
		// TODO Auto-generated method stub
		
	}

	public void setNCharacterStream(int parameterIndex, Reader value, long length) throws SQLException {
		// TODO Auto-generated method stub
		
	}

	public void setNClob(int parameterIndex, NClob value) throws SQLException {
		// TODO Auto-generated method stub
		
	}

	public void setClob(int parameterIndex, Reader reader, long length) throws SQLException {
		// TODO Auto-generated method stub
		
	}

	public void setBlob(int parameterIndex, InputStream inputStream, long length) throws SQLException {
		// TODO Auto-generated method stub
		
	}

	public void setNClob(int parameterIndex, Reader reader, long length) throws SQLException {
		// TODO Auto-generated method stub
		
	}

	public void setSQLXML(int parameterIndex, SQLXML xmlObject) throws SQLException {
		// TODO Auto-generated method stub
		
	}

	public void setAsciiStream(int parameterIndex, InputStream x, long length) throws SQLException {
		// TODO Auto-generated method stub
		
	}

	public void setBinaryStream(int parameterIndex, InputStream x, long length) throws SQLException {
		// TODO Auto-generated method stub
		
	}

	public void setCharacterStream(int parameterIndex, Reader reader, long length) throws SQLException {
		// TODO Auto-generated method stub
		
	}

	public void setAsciiStream(int parameterIndex, InputStream x) throws SQLException {
		// TODO Auto-generated method stub
		
	}

	public void setBinaryStream(int parameterIndex, InputStream x) throws SQLException {
		// TODO Auto-generated method stub
		
	}

	public void setCharacterStream(int parameterIndex, Reader reader) throws SQLException {
		// TODO Auto-generated method stub
		
	}

	public void setNCharacterStream(int parameterIndex, Reader value) throws SQLException {
		// TODO Auto-generated method stub
		
	}

	public void setClob(int parameterIndex, Reader reader) throws SQLException {
		// TODO Auto-generated method stub
		
	}

	public void setBlob(int parameterIndex, InputStream inputStream) throws SQLException {
		// TODO Auto-generated method stub
		
	}

	public void setNClob(int parameterIndex, Reader reader) throws SQLException {
		// TODO Auto-generated method stub
		
	}

}
package org.joy.core.persistence.jdbc.model.vo;

import org.joy.core.persistence.jdbc.support.utils.JdbcTool;

import java.sql.Connection;

/**
 * 关系型数据库连接元数据信息
 *
 * @since 1.0.0
 * @author Kevice
 * @time 2013年11月24日 下午10:04:24
 */
public class RdbConnection {

	private String dsId; // 数据源id
	private Connection connection; // 连接对象

	public RdbConnection(String dsId) {
		this.dsId = dsId;
		this.connection = JdbcTool.getConnectionByDsId(dsId);
	}
	
	public RdbConnection(String dsId, Connection connection) {
		this.dsId = dsId;
		this.connection = connection;
	}

	public String getDsId() {
		return dsId;
	}

	public void setDsId(String dsId) {
		this.dsId = dsId;
	}

	public Connection getConnection() {
		return connection;
	}

	public void setConnection(Connection connection) {
		this.connection = connection;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((dsId == null) ? 0 : dsId.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		RdbConnection other = (RdbConnection) obj;
		if (dsId == null) {
			if (other.dsId != null)
				return false;
		} else if (!dsId.equals(other.dsId))
			return false;
		return true;
	}

}

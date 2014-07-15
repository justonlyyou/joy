package org.joy.core.persistence.jdbc.model.vo;

import java.io.Serializable;

/**
 * 
 * @author Kevice
 * @time 2012-12-28 下午11:25:31
 */
public class MdRdbConn implements Serializable {

	private String url;
	private String username;
	private String password;
	private String datasourceId;

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getDatasourceId() {
		return datasourceId;
	}

	public void setDatasourceId(String datasourceId) {
		this.datasourceId = datasourceId;
	}
	
}

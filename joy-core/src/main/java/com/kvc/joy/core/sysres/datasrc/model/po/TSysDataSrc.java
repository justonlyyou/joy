package com.kvc.joy.core.sysres.datasrc.model.po;

import com.kvc.joy.core.persistence.support.entity.UuidCrudEntity;
import com.kvc.joy.core.persistence.jdbc.model.vo.IMdRdbDataSrc;
import com.kvc.joy.core.persistence.orm.jpa.annotations.Comment;
import com.kvc.joy.core.sysres.code.model.po.TSysCodeTable;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * 数据源
 * 
 * @author Kevice
 * @time 2012-6-8 下午9:26:10
 */
@Entity
@Table(name = "t_sys_data_src")
@Comment("数据源")
public class TSysDataSrc extends UuidCrudEntity implements IMdRdbDataSrc {

	private String name;
	private String dbAlias;
	private String dbType;
	private String dbName;
	private String dbUrl;
	private String jndiName;
	private String username;
	private String password;
	private String parameter;
	private String charset;
	private String ipAddress;
	private String serverPort;
	private Integer maxConnCount;
	private Integer minConnCount;
	private Set<TSysCodeTable> codeDics = new HashSet<TSysCodeTable>(0);

	@Column(length = 32, nullable = false)
	@Comment("名称")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(length = 32)
	@Comment("数据库别名")
	public String getDbAlias() {
		return dbAlias;
	}

	public void setDbAlias(String dbAlias) {
		this.dbAlias = dbAlias;
	}

	@Column(length = 2)
	@Comment("数据库类型")
	public String getDbType() {
		return dbType;
	}

	public void setDbType(String dbType) {
		this.dbType = dbType;
	}

	@Column(length = 32)
	@Comment("数据库名称")
	public String getDbName() {
		return dbName;
	}

	public void setDbName(String dbName) {
		this.dbName = dbName;
	}

	@Column(length = 128)
	@Comment("连接串")
	public String getDbUrl() {
		return dbUrl;
	}

	public void setDbUrl(String dbUrl) {
		this.dbUrl = dbUrl;
	}

	@Column(length = 32)
	@Comment("JNDI名称")
	public String getJndiName() {
		return jndiName;
	}

	public void setJndiName(String jndiName) {
		this.jndiName = jndiName;
	}

	@Column(length = 32)
	@Comment("用户名")
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	@Column(length = 32)
	@Comment("用户密码")
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Column(length = 128)
	@Comment("连接参数")
	public String getParameter() {
		return parameter;
	}

	public void setParameter(String parameter) {
		this.parameter = parameter;
	}

	@Column(length = 16)
	@Comment("字符集")
	public String getCharset() {
		return charset;
	}

	public void setCharset(String charset) {
		this.charset = charset;
	}

	@Column(length = 32)
	@Comment("IP地址")
	public String getIpAddress() {
		return ipAddress;
	}

	public void setIpAddress(String ipAddress) {
		this.ipAddress = ipAddress;
	}

	@Column(length = 5)
	@Comment("端口")
	public String getServerPort() {
		return serverPort;
	}

	public void setServerPort(String serverPort) {
		this.serverPort = serverPort;
	}

	@Column(length = 4)
	@Comment("最大连接数")
	public Integer getMaxConnCount() {
		return maxConnCount;
	}

	public void setMaxConnCount(Integer maxConnCount) {
		this.maxConnCount = maxConnCount;
	}

	@Column(length = 4)
	@Comment("最小连接数")
	public Integer getMinConnCount() {
		return minConnCount;
	}

	public void setMinConnCount(Integer minConnCount) {
		this.minConnCount = minConnCount;
	}

	@OneToMany(mappedBy = "dataSrc", cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
	public Set<TSysCodeTable> getCodeDics() {
		return codeDics;
	}

	public void setCodeDics(Set<TSysCodeTable> codeDics) {
		this.codeDics = codeDics;
	}

	@Transient
	public String getUrl() {
		return dbUrl;
	}

	@Transient
	public String getDatasourceId() {
		return id;
	}

}

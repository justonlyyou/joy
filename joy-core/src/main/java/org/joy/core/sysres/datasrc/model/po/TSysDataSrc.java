package org.joy.core.sysres.datasrc.model.po;

import org.joy.core.persistence.jdbc.model.vo.IMdRdbDataSrc;
import org.joy.core.persistence.orm.jpa.annotations.Comment;
import org.joy.core.persistence.support.entity.UuidCrudEntity;
import org.joy.core.sysres.code.model.po.TSysCodeTable;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * 数据源实体
 *
 * @since 1.0.0
 * @author Kevice
 * @time 2012-6-8 下午9:26:10
 */
@Entity
@Table(name = "t_sys_data_src")
@Comment("数据源")
public class TSysDataSrc extends UuidCrudEntity implements IMdRdbDataSrc {

	private String name; // 名称
	private String dbAlias; // 别名
	private String dbType; // 数据库类型
	private String dbName; // 数据库名称
	private String dbUrl; // 数据库地址
	private String jndiName; // jndi名称
	private String username; // 用户名
	private String password; // 密码
	private String parameter; // 参数
	private String charset; // 字符集
	private String ipAddress; // ip地址
	private String serverPort; // 端口
	private Integer maxConnCount; // 最大连接数
	private Integer minConnCount; // 最小连接数
	private Set<TSysCodeTable> codeDics = new HashSet<>(0); // 代码字典集合

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

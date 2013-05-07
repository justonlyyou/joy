package com.kvc.joy.plugin.monitor.jdbc.model.po;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.kvc.joy.core.persistence.entity.UuidEntity;

/**
 * 
 * @author 唐玮琳
 * @time 2012-12-15 下午7:09:20
 */
@Entity
@Table(name = "T_SYS_SQL_LOG")
public class TSysSqlLog extends UuidEntity {

	private String logTime;
	private String appName;
	private String moduleName;
	private Long costTime;
	private String sqlText;
	private String variables;
	private String fullSql;
	private String className;
	private String methodName;
	private Integer lineNumber;

	@Column(length = 17)
	public String getLogTime() {
		return logTime;
	}

	public void setLogTime(String logTime) {
		this.logTime = logTime;
	}

	@Column(length = 32)
	public String getAppName() {
		return appName;
	}

	public void setAppName(String appName) {
		this.appName = appName;
	}

	@Column(length = 128)
	public String getModuleName() {
		return moduleName;
	}

	public void setModuleName(String moduleName) {
		this.moduleName = moduleName;
	}

	@Column(length = 8)
	public Long getCostTime() {
		return costTime;
	}

	public void setCostTime(Long costTime) {
		this.costTime = costTime;
	}

	@Column(length = 4000)
	public String getSqlText() {
		return sqlText;
	}

	public void setSqlText(String sqlText) {
		this.sqlText = sqlText;
	}

	@Column(length = 1024)
	public String getVariables() {
		return variables;
	}

	public void setVariables(String variables) {
		this.variables = variables;
	}

	@Column(length = 4000)
	public String getFullSql() {
		return fullSql;
	}

	public void setFullSql(String fullSql) {
		this.fullSql = fullSql;
	}

	@Column(length = 128)
	public String getClassName() {
		return className;
	}

	public void setClassName(String className) {
		this.className = className;
	}

	@Column(length = 64)
	public String getMethodName() {
		return methodName;
	}

	public void setMethodName(String methodName) {
		this.methodName = methodName;
	}

	@Column(length = 5)
	public Integer getLineNumber() {
		return lineNumber;
	}

	public void setLineNumber(Integer lineNumber) {
		this.lineNumber = lineNumber;
	}

}

package com.kvc.joy.plugin.monitor.jdbc.model.po;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.kvc.joy.core.persistence.entity.UuidEntity;
import com.kvc.joy.core.persistence.orm.jpa.annotations.Comment;

/**
 * 
 * @author 唐玮琳
 * @time 2012-12-15 下午7:09:20
 */
@Entity
@Table(name = "t_sys_sql_log")
@Comment("SQL日志")
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
	@Comment("日志时间")
	public String getLogTime() {
		return logTime;
	}

	public void setLogTime(String logTime) {
		this.logTime = logTime;
	}

	@Column(length = 32)
	@Comment("应用名称")
	public String getAppName() {
		return appName;
	}

	public void setAppName(String appName) {
		this.appName = appName;
	}

	@Column(length = 128)
	@Comment("模块名")
	public String getModuleName() {
		return moduleName;
	}

	public void setModuleName(String moduleName) {
		this.moduleName = moduleName;
	}

	@Column
	@Comment(value="耗时", detailDesc="单位: 毫秒")
	public Long getCostTime() {
		return costTime;
	}

	public void setCostTime(Long costTime) {
		this.costTime = costTime;
	}

	@Column(length = 4000)
	@Comment(value="sql文本", detailDesc="未参数化")
	public String getSqlText() {
		return sqlText;
	}

	public void setSqlText(String sqlText) {
		this.sqlText = sqlText;
	}

	@Column(length = 1024)
	@Comment(value="变量值", detailDesc="多个用半角逗号隔开")
	public String getVariables() {
		return variables;
	}

	public void setVariables(String variables) {
		this.variables = variables;
	}

	@Column(length = 4000)
	@Comment(value="完整sql文本", detailDesc="已参数化")
	public String getFullSql() {
		return fullSql;
	}

	public void setFullSql(String fullSql) {
		this.fullSql = fullSql;
	}

	@Column(length = 128)
	@Comment("类名")
	public String getClassName() {
		return className;
	}

	public void setClassName(String className) {
		this.className = className;
	}

	@Column(length = 64)
	@Comment("方法名")
	public String getMethodName() {
		return methodName;
	}

	public void setMethodName(String methodName) {
		this.methodName = methodName;
	}

	@Column(length = 5)
	@Comment("行号")
	public Integer getLineNumber() {
		return lineNumber;
	}

	public void setLineNumber(Integer lineNumber) {
		this.lineNumber = lineNumber;
	}

}

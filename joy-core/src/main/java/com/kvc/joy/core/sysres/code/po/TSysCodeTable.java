package com.kvc.joy.core.sysres.code.po;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.kvc.joy.core.persistence.entity.UuidCrudEntity;
import com.kvc.joy.core.sysres.datasrc.model.po.TSysDataSrc;

@Entity
@Table(name = "T_SYS_CODE_TABLE")
public class TSysCodeTable extends UuidCrudEntity {

	// Fields
	private String tableName; // 代码表表名
	private String cnTableName; // 代码表中文名
	private String codeField; // 代码字段
	private String transField; // 译文字段
	private String parentField; // 父代码字段
	private TSysDataSrc dataSrc; // 数据源

	// Constructors

	public TSysCodeTable() {
	}

	// Property accessors

	@Column(length = 30)
	public String getCodeField() {
		return this.codeField;
	}

	public void setCodeField(String codeField) {
		this.codeField = codeField;
	}

	@ManyToOne(cascade = { CascadeType.MERGE, CascadeType.REFRESH }, optional = false)
	@JoinColumn(name = "DATA_SRC_ID")
	public TSysDataSrc getDataSrc() {
		return dataSrc;
	}

	public void setDataSrc(TSysDataSrc dataSrc) {
		this.dataSrc = dataSrc;
	}

	public String getTableName() {
		return tableName;
	}

	public void setTableName(String tableName) {
		this.tableName = tableName;
	}

	public String getCnTableName() {
		return cnTableName;
	}

	public void setCnTableName(String cnTableName) {
		this.cnTableName = cnTableName;
	}

	public String getTransField() {
		return transField;
	}

	public void setTransField(String transField) {
		this.transField = transField;
	}

	public String getParentField() {
		return parentField;
	}

	public void setParentField(String parentField) {
		this.parentField = parentField;
	}

}
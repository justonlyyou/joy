package com.kvc.joy.core.sysres.code.model.po;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.kvc.joy.core.persistence.entity.UuidCrudEntity;
import com.kvc.joy.core.persistence.orm.jpa.annotations.Comment;
import com.kvc.joy.core.persistence.orm.jpa.annotations.DefaultValue;
import com.kvc.joy.core.sysres.datasrc.model.po.TSysDataSrc;

@Entity
@Table(name = "t_sys_code_table")
@Comment("代码表注册")
public class TSysCodeTable extends UuidCrudEntity {

	// Fields
	private String tableName; // 代码表表名
	private String tableComment; // 代码表注释
	private String codeField; // 代码字段
	private String transField; // 译文字段
	private String orderField; // 排序字段
	private String pinyinField; // 拼音字段
	private String segmentRule; // 分段规则
	private String parentField; // 父代码字段
	private String activeField; // 是否启用字段
	private String deletedField; // 是否删除字段
	private String groupingField; // 组字段名(多种代码放同一表时区分用)
	private String groupingCommentField; // 组注释
	private TSysDataSrc dataSrc; // 数据源

	// Constructors

	public TSysCodeTable() {
	}

	// Property accessors

	@Column(length = 30, nullable = false)
	@DefaultValue("code")
	@Comment("代码字段")
	public String getCodeField() {
		return this.codeField;
	}

	public void setCodeField(String codeField) {
		this.codeField = codeField;
	}

	@ManyToOne(cascade = { CascadeType.MERGE, CascadeType.REFRESH }, optional = false)
	@JoinColumn(name = "DATA_SRC_ID")
	@Comment("数据源ID")
	public TSysDataSrc getDataSrc() {
		return dataSrc;
	}

	public void setDataSrc(TSysDataSrc dataSrc) {
		this.dataSrc = dataSrc;
	}

	@Column(length = 30, nullable = false)
	@Comment("代码表名")
	public String getTableName() {
		return tableName;
	}

	public void setTableName(String tableName) {
		this.tableName = tableName;
	}

	@Column(length = 64)
	@Comment("代码表注释")
	public String getTableComment() {
		return tableComment;
	}

	public void setTableComment(String tableComment) {
		this.tableComment = tableComment;
	}

	@Column(length = 32, nullable = false)
	@Comment("翻译字段")
	public String getTransField() {
		return transField;
	}

	public void setTransField(String transField) {
		this.transField = transField;
	}

	@Column(length = 32)
	@Comment("父项代码字段")
	public String getParentField() {
		return parentField;
	}

	public void setParentField(String parentField) {
		this.parentField = parentField;
	}

	@Column(length = 32)
	@Comment("排序字段")
	public String getOrderField() {
		return orderField;
	}

	public void setOrderField(String orderField) {
		this.orderField = orderField;
	}

	@Column(length = 32)
	@Comment("拼音字段")
	public String getPinyinField() {
		return pinyinField;
	}

	public void setPinyinField(String pinyinField) {
		this.pinyinField = pinyinField;
	}

	@Column(length = 32)
	@Comment("分段规则,如果是共用代码表则表示分段规则的字段")
	public String getSegmentRule() {
		return segmentRule;
	}

	public void setSegmentRule(String segmentRule) {
		this.segmentRule = segmentRule;
	}
	
	@Column(length = 32)
	@Comment("启用状态字段")
	public String getActiveField() {
		return activeField;
	}

	public void setActiveField(String activeField) {
		this.activeField = activeField;
	}

	@Column(length = 32)
	@Comment("删除状态字段")
	public String getDeletedField() {
		return deletedField;
	}

	public void setDeletedField(String deletedField) {
		this.deletedField = deletedField;
	}

	@Column(length = 32)
	@Comment("分组字段")
	public String getGroupingField() {
		return groupingField;
	}

	public void setGroupingField(String groupingField) {
		this.groupingField = groupingField;
	}

	@Column(length = 64)
	@Comment("分组注释字段")
	public String getGroupingCommentField() {
		return groupingCommentField;
	}

	public void setGroupingCommentField(String groupingCommentField) {
		this.groupingCommentField = groupingCommentField;
	}

}
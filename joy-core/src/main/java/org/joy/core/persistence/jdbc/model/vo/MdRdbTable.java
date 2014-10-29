package org.joy.core.persistence.jdbc.model.vo;

import org.joy.core.persistence.jdbc.support.enums.RdbObjectType;

import java.io.Serializable;
import java.util.Collection;

/**
 * 关系型数据库表元数据信息
 * 
 * @author Kevice
 * @time 2012-12-28 下午11:25:31
 * @since 1.0.0
 */
public class MdRdbTable implements Comparable<MdRdbTable>, Serializable {

	private static final long serialVersionUID = -8119151332561864994L;
	private String dsId; // 数据源id
	private String name; // 表名
	private String comment; // 表注释
	private String type; // 表类型，如：table，view等，见RdbObjectType.class
//	private MdDbPrimaryKey primaryKey;
	private Collection<MdRdbColumn> columns; // 表中包含的列
	
	public MdRdbTable() {
	}
	
	public MdRdbTable(String dsId, String name, String comment) {
		this(dsId, name, comment, RdbObjectType.TABLE.getCode());
	}
	
	public MdRdbTable(String dsId, String name, String comment, String type) {
		this.dsId = dsId;
		this.name = name;
		this.comment = comment;
		this.type = type;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public int compareTo(MdRdbTable obj) {
		return name.compareTo(obj.getName());
	}

//	public MdDbPrimaryKey getPrimaryKey() {
//		return primaryKey;
//	}
//
//	public void setPrimaryKey(MdDbPrimaryKey primaryKey) {
//		this.primaryKey = primaryKey;
//	}
//
	public Collection<MdRdbColumn> getColumns() {
		return columns;
	}

	public void setColumns(Collection<MdRdbColumn> columns) {
		this.columns = columns;
	}

	public String getDsId() {
		return dsId;
	}

	public void setDsId(String dsId) {
		this.dsId = dsId;
	}
	
}

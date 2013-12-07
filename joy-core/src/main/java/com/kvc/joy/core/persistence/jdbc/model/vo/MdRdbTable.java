package com.kvc.joy.core.persistence.jdbc.model.vo;

import java.io.Serializable;
import java.util.Collection;

import com.kvc.joy.core.persistence.jdbc.support.enums.RdbObjectType;

/**
 * 
 * @author <b>唐玮琳</b>
 */
public class MdRdbTable implements Comparable<MdRdbTable>, Serializable {

	private static final long serialVersionUID = -8119151332561864994L;
	private String dsId;
	private String name;
	private String comment;
	private String type;
//	private MdDbPrimaryKey primaryKey;
	private Collection<MdRdbColumn> columns;
	
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

package org.joy.core.persistence.jdbc.model.vo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * 主键
 * 
 * @author <b>Kevice</b>
 */
public class MdRdbPrimaryKey implements Serializable {

	private String keyName; // 主键名
	private List<MdRdbColumn> columns = new ArrayList<MdRdbColumn>(1); // 组成主键的字段列表

	public MdRdbPrimaryKey() {
	}

	public String getKeyName() {
		return keyName;
	}

	public void setKeyName(String keyName) {
		this.keyName = keyName;
	}

	public List<MdRdbColumn> getColumns() {
		return columns;
	}

	public void setColumns(List<MdRdbColumn> columns) {
		this.columns = columns;
	}

	public boolean isPrimaryKeyField(String fieldName) {
		for (MdRdbColumn field : columns) {
			if (field.getName().trim().equalsIgnoreCase(fieldName.trim())) {
				return true;
			}
		}
		return false;
	}

}

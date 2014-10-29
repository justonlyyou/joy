package org.joy.core.persistence.jdbc.model.vo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * 关系型数据库主键元数据信息
 * 
 * @author Kevice
 * @time 2012-12-28 下午11:25:31
 * @since 1.0.0
 */
public class MdRdbPrimaryKey implements Serializable {

	private String keyName; // 主键名
	private List<MdRdbColumn> columns = new ArrayList<>(1); // 组成主键的字段列表

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

package com.kvc.joy.plugin.mapping.field.po;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.kvc.joy.core.persistence.entity.UuidEntity;
import com.kvc.joy.core.persistence.orm.jpa.annotations.Comment;

/**
 * 字段映射规则
 * @author  唐玮琳
 */
@Entity
@Table(name = "t_sys_field_mapping_rule")
@Comment("字段映射规则")
public class TSysFieldMappingRule extends UuidEntity {

	/**
	 * 对象类型：java对象 
	 */
	public static final String OBJECT_TYPE_JAVA_OBJECT = "1";
	/**
	 * 对象类型：数据库表
	 */
	public static final String OBJECT_TYPE_DB_TABLE = "2";
	/**
	 * 对象类型：数据库表转为Java对象
	 */
	public static final String OBJECT_TYPE_TABLE_TO_JAVA = "3";
	
	private String object1; // 对象1的名称
	private String object2; // 对象2的名称
	private String desc;    // 描述
	private String objectType; // 对象类型
	private Set<TSysFieldMapping> fieldMappingSet = new HashSet<TSysFieldMapping>();

	@Column(length = 64, nullable = false)
	@Comment("对象1的名称")
	public String getObject1() {
		return object1;
	}

	public void setObject1(String object1) {
		this.object1 = object1;
	}

	@Column(length = 64, nullable = false)
	@Comment("对象2的名称")
	public String getObject2() {
		return object2;
	}

	public void setObject2(String object2) {
		this.object2 = object2;
	}

	@Column(name = "DESCRIPTION", length = 128, nullable = false)
	@Comment("描述")
	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	@Column(length = 2, nullable = false)
	@Comment("对象类型")
	public String getObjectType() {
		return objectType;
	}

	public void setObjectType(String objectType) {
		this.objectType = objectType;
	}

	@OneToMany(mappedBy = "rule", cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
	public Set<TSysFieldMapping> getFieldMappingSet() {
		return fieldMappingSet;
	}

	public void setFieldMappingSet(Set<TSysFieldMapping> fieldMappingSet) {
		this.fieldMappingSet = fieldMappingSet;
	}
	
}

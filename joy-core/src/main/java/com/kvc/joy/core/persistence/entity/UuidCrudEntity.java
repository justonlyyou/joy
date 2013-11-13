package com.kvc.joy.core.persistence.entity;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

import com.kvc.joy.core.persistence.orm.jpa.annotations.Comment;
import com.kvc.joy.core.persistence.orm.jpa.annotations.DefaultValue;

/**
 * 
 * @author 唐玮琳
 * @time 2012-12-17 下午11:59:02
 */
@MappedSuperclass
public class UuidCrudEntity extends UuidEntity implements ICrudEntity<String> {

	protected String createTime;
	protected String createUser;
	protected String createDept;
	protected String updateTime;
	protected String updateUser;
	protected String updateDept;
	protected String deleteTime;
	protected String deleteUser;
	protected String deleteDept;
	protected boolean deleted;
	protected boolean active;
	protected boolean builtIn;
	protected String desc;

	@Column(length = 17)
	@Comment("创建时间")
	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	@Column(length = 32)
	@Comment("创建用户ID")
	public String getCreateUser() {
		return createUser;
	}

	public void setCreateUser(String createUser) {
		this.createUser = createUser;
	}

	@Column(length = 32)
	@Comment("创建单位ID")
	public String getCreateDept() {
		return createDept;
	}

	public void setCreateDept(String createDept) {
		this.createDept = createDept;
	}

	@Column(length = 17)
	@Comment("更新时间")
	public String getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(String updateTime) {
		this.updateTime = updateTime;
	}

	@Column(length = 32)
	@Comment("更新用户ID")
	public String getUpdateUser() {
		return updateUser;
	}

	public void setUpdateUser(String updateUser) {
		this.updateUser = updateUser;
	}

	@Column(length = 32)
	@Comment("更新单位ID")
	public String getUpdateDept() {
		return updateDept;
	}

	public void setUpdateDept(String updateDept) {
		this.updateDept = updateDept;
	}

	@Column(length = 17)
	@Comment("删除时间")
	public String getDeleteTime() {
		return deleteTime;
	}

	public void setDeleteTime(String deleteTime) {
		this.deleteTime = deleteTime;
	}

	@Column(length = 32)
	@Comment("删除用户ID")
	public String getDeleteUser() {
		return deleteUser;
	}

	public void setDeleteUser(String deleteUser) {
		this.deleteUser = deleteUser;
	}

	@Column(length = 32)
	@Comment("删除单位ID")
	public String getDeleteDept() {
		return deleteDept;
	}

	public void setDeleteDept(String deleteDept) {
		this.deleteDept = deleteDept;
	}

	@Column(length = 1, nullable = false)
	@DefaultValue("false")
	@Comment("是否已删除")
	public boolean isDeleted() {
		return deleted;
	}

	public void setDeleted(boolean deleted) {
		this.deleted = deleted;
	}
	
	@Column(length = 1, nullable = false)
	@DefaultValue("true")
	@Comment("是否激活")
	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	@Column(length = 1, updatable = false, nullable = false)
	@DefaultValue("false")
	@Comment("是否系统内置")
	public boolean isBuiltIn() {
		return builtIn;
	}

	public void setBuiltIn(boolean builtIn) {
		this.builtIn = builtIn;
	}
	
	@Column(name = "DESCRIPTION")
	@Comment("描述")
	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

}

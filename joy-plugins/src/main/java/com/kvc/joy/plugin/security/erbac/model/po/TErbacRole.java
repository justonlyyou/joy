package com.kvc.joy.plugin.security.erbac.model.po;

import java.util.Collection;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.kvc.joy.commons.support.IListToTreeRestrict;
import com.kvc.joy.core.persistence.entity.UuidCrudEntity;

@Entity
@Table(name = "T_ERBAC_ROLE")
public class TErbacRole extends UuidCrudEntity implements IListToTreeRestrict<String> {

	private String parentId; // 父角色id
	private String name; // 角色名
	private String desc; // 描述
	private Collection<TErbacUser> users; // 用户
	private Collection<TErbacGroup> groups; // 组
	// private Collection<TErbacAuthority> authorities; // 权限
	private Collection<TErbacRoleAuth> roleAuths; // 角色权限关系

	@Column(name = "ROLE_NAME", length = 64, nullable = false)
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "DESCRIPTION")
	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	@ManyToMany(cascade = CascadeType.REFRESH)
	@JoinTable(name = "T_ERBAC_ROLE_USER", // 关联表名
	inverseJoinColumns = @JoinColumn(name = "USER_ID"),// 被维护端外键
	joinColumns = @JoinColumn(name = "ROLE_ID"))
	// 维护端外键
	public Collection<TErbacUser> getUsers() {
		return users;
	}

	public void setUsers(Collection<TErbacUser> tErbacUsers) {
		this.users = tErbacUsers;
	}

	// @ManyToMany(cascade = CascadeType.REFRESH)
	// @JoinTable(name = "T_ERBAC_ROLE_AUTH", // 关联表名
	// inverseJoinColumns = @JoinColumn(name = "AUTHORITY_ID"), // 被维护端外键
	// joinColumns = @JoinColumn(name = "ROLE_ID")) // 维护端外键
	// public Collection<TErbacAuthority> getAuthorities() {
	// return authorities;
	// }
	//
	// public void setAuthorities(Collection<TErbacAuthority> tErbacAuthorities)
	// {
	// this.authorities = tErbacAuthorities;
	// }

	@ManyToMany(cascade = CascadeType.REFRESH)
	@JoinTable(name = "T_ERBAC_ROLE_GROUP", // 关联表名
	inverseJoinColumns = @JoinColumn(name = "GROUP_ID"), // 被维护端外键
	joinColumns = @JoinColumn(name = "ROLE_ID"))
	// 维护端外键
	public Collection<TErbacGroup> getGroups() {
		return groups;
	}

	public void setGroups(Collection<TErbacGroup> groups) {
		this.groups = groups;
	}

	@Column(length = 32)
	public String getParentId() {
		return parentId;
	}

	public void setParentId(String parentId) {
		this.parentId = parentId;
	}

	@OneToMany(mappedBy = "role", cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
	public Collection<TErbacRoleAuth> getRoleAuths() {
		return roleAuths;
	}

	public void setRoleAuths(Collection<TErbacRoleAuth> roleAuths) {
		this.roleAuths = roleAuths;
	}

}

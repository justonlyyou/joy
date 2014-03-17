package com.kvc.joy.plugin.security.erbac.model.po;

import com.kvc.joy.core.persistence.support.entity.UuidCrudEntity;
import com.kvc.joy.core.persistence.orm.jpa.annotations.Comment;
import com.kvc.joy.plugin.security.erbac.support.enums.AuthResourceType;

import javax.persistence.*;
import java.util.Collection;

@Entity
@Table(name = "t_erbac_authority")
@Comment("权限")
public class TErbacAuthority extends UuidCrudEntity {

	private String parentId; // 父权限id
	private String name; // 权限名称
	private String resource; // 资源
	private AuthResourceType resourceType; 
	private String domain; // 资源(某一类资源)
	private String action; // 操作
	private String instance; // 资源实例(某类资源特定的实例)
	private Collection<TErbacUserAuth> userAuths; // 用户权限关系
	private Collection<TErbacRoleAuth> roleAuths; // 角色权限关系
	private Collection<TErbacGroupAuth> groupAuths; // 组权限关系
//	private Collection<TErbacRole> roles; // 角色
//	private Collection<TUserBasic> users; // 用户
//	private Collection<TErbacGroup> groups; // 组

	@Column(name="AUTH_NAME", length=64, nullable=false)
	@Comment("名称")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

//	@ManyToMany(cascade = CascadeType.REFRESH,
//			mappedBy="authorities", 
//			fetch = FetchType.LAZY
//			)
//	public Collection<TErbacRole> getRoles() {
//		return roles;
//	}
//
//	public void setRoles(Collection<TErbacRole> tErbacRoles) {
//		this.roles = tErbacRoles;
//	}

//	@ManyToMany(cascade = CascadeType.REFRESH)
//	@JoinTable(name = "t_erbac_auth_res",
//			inverseJoinColumns = @JoinColumn(name = "RESOURCE_ID"),
//			joinColumns = @JoinColumn(name = "AUTHORITY_ID"))	
//	public Collection<TErbacResource> getResources() {
//		return resources;
//	}
//
//	public void setResources(Collection<TErbacResource> tErbacResources) {
//		this.resources = tErbacResources;
//	}

//	@ManyToMany(cascade = CascadeType.REFRESH, mappedBy = "authorities",// 通过维护端的属性关联
//			fetch = FetchType.LAZY)
//	public Collection<TUserBasic> getUsers() {
//		return users;
//	}
//
//	public void setUsers(Collection<TUserBasic> users) {
//		this.users = users;
//	}
	
//	@ManyToMany(cascade = CascadeType.REFRESH, mappedBy = "authorities",// 通过维护端的属性关联
//			fetch = FetchType.LAZY)
//	public Collection<TErbacGroup> getGroups() {
//		return groups;
//	}
//
//	public void setGroups(Collection<TErbacGroup> groups) {
//		this.groups = groups;
//	}

	@Column(length = 32)
	@Comment("父项ID")
	public String getParentId() {
		return parentId;
	}

	public void setParentId(String parentId) {
		this.parentId = parentId;
	}

	@OneToMany(mappedBy = "authority", cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
	public Collection<TErbacUserAuth> getUserAuths() {
		return userAuths;
	}

	public void setUserAuths(Collection<TErbacUserAuth> userAuths) {
		this.userAuths = userAuths;
	}

	@OneToMany(mappedBy = "authority", cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
	public Collection<TErbacRoleAuth> getRoleAuths() {
		return roleAuths;
	}

	public void setRoleAuths(Collection<TErbacRoleAuth> roleAuths) {
		this.roleAuths = roleAuths;
	}
	
	@OneToMany(mappedBy = "authority", cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
	public Collection<TErbacGroupAuth> getGroupAuths() {
		return groupAuths;
	}

	public void setGroupAuths(Collection<TErbacGroupAuth> groupAuths) {
		this.groupAuths = groupAuths;
	}

	@Comment("资源域")
	public String getDomain() {
		return domain;
	}

	public void setDomain(String domain) {
		this.domain = domain;
	}

	@Column(length = 32)
	@Comment("操作")
	public String getAction() {
		return action;
	}

	public void setAction(String action) {
		this.action = action;
	}

	@Column(length = 32)
	@Comment("资源实例")
	public String getInstance() {
		return instance;
	}

	public void setInstance(String instance) {
		this.instance = instance;
	}
	
	@Column(length = 2, nullable = false)
	@Comment(value="资源类型代码", codeId="auth_res_type")
	public String getResourceTypeCode() {
		return resourceType == null ? null : resourceType.getCode();
	}

	public void setResourceTypeCode(String typeCode) {
		this.resourceType = AuthResourceType.enumOf(typeCode);
	}

	@Transient
	public AuthResourceType getResourceType() {
		return resourceType;
	}

	public void setResourceType(AuthResourceType resourceType) {
		this.resourceType = resourceType;
	}

	@Column(nullable = false)
	@Comment("资源")
	public String getResource() {
		return resource;
	}

	public void setResource(String resource) {
		this.resource = resource;
	}
	
}

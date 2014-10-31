package org.joy.plugin.security.erbac.model.po;

import org.joy.core.persistence.orm.jpa.annotations.Comment;
import org.joy.core.persistence.support.entity.UuidEntity;
import org.joy.plugin.security.erbac.support.enums.AuthorityType;

import javax.persistence.*;

/**
 * 角色-权限关联实体
 *
 * @since 1.0.0
 * @author Kevice
 * @time 2012-6-21 上午12:13:24
 */
@Entity
@Table(name = "t_erbac_role_auth")
@Comment("角色权限")
public class TErbacRoleAuth extends UuidEntity {

	private TErbacRole role;
	private TErbacAuthority authority;
	private AuthorityType authorityType;

	@Transient
	public AuthorityType getAuthorityType() {
		return authorityType;
	}

	public void setAuthorityType(AuthorityType authorityType) {
		this.authorityType = authorityType;
	}

	@Column(length = 1, nullable = false)
	@Comment(value="权限类型代码", codeId="authority_type")
	public String getAuthorityTypeCode() {
		return authorityType.getCode();
	}

	public void setAuthorityTypeCode(String authorityTypeCode) {
		this.authorityType = AuthorityType.enumOf(authorityTypeCode);
	}

	@ManyToOne(cascade = { CascadeType.MERGE, CascadeType.REFRESH }, optional = false)
	@JoinColumn(name = "ROLE_ID")
	@Comment("角色ID")
	public TErbacRole getRole() {
		return role;
	}

	public void setRole(TErbacRole role) {
		this.role = role;
	}

	@ManyToOne(cascade = { CascadeType.MERGE, CascadeType.REFRESH }, optional = false)
	@JoinColumn(name = "AUTHORITY_ID")
	@Comment("权限ID")
	public TErbacAuthority getAuthority() {
		return authority;
	}

	public void setAuthority(TErbacAuthority authority) {
		this.authority = authority;
	}

}

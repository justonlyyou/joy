package com.kvc.joy.plugin.security.erbac.model.po;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.kvc.joy.core.persistence.entity.UuidEntity;
import com.kvc.joy.plugin.security.erbac.support.enums.AuthorityType;

@Entity
@Table(name = "T_ERBAC_ROLE_AUTH")
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
	public String getAuthorityTypeCode() {
		return authorityType.getCode();
	}

	public void setAuthorityTypeCode(String authorityTypeCode) {
		this.authorityType = AuthorityType.enumOf(authorityTypeCode);
	}

	@ManyToOne(cascade = { CascadeType.MERGE, CascadeType.REFRESH }, optional = false)
	@JoinColumn(name = "ROLE_ID")
	public TErbacRole getRole() {
		return role;
	}

	public void setRole(TErbacRole role) {
		this.role = role;
	}

	@ManyToOne(cascade = { CascadeType.MERGE, CascadeType.REFRESH }, optional = false)
	@JoinColumn(name = "AUTHORITY_ID")
	public TErbacAuthority getAuthority() {
		return authority;
	}

	public void setAuthority(TErbacAuthority authority) {
		this.authority = authority;
	}

}

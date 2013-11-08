package com.kvc.joy.plugin.security.erbac.model.po;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.kvc.joy.core.persistence.entity.UuidEntity;
import com.kvc.joy.core.persistence.orm.jpa.annotations.Comment;
import com.kvc.joy.plugin.security.erbac.support.enums.AuthorityType;

@Entity
@Table(name = "t_erbac_user_auth")
@Comment("用户权限")
public class TErbacUserAuth extends UuidEntity {

	private AuthorityType authorityType;
	private TErbacUser user;
	private TErbacAuthority authority;

	@Transient
	public AuthorityType getAuthorityType() {
		return authorityType;
	}

	public void setAuthorityType(AuthorityType authorityType) {
		this.authorityType = authorityType;
	}

	@Column(length = 1, nullable = false)
	@Comment(value="权限类型代码", codeId="joy_code_authority_type")
	public String getAuthorityTypeCode() {
		return authorityType.getCode();
	}

	public void setAuthorityTypeCode(String authorityTypeCode) {
		this.authorityType = AuthorityType.enumOf(authorityTypeCode);
	}

	@ManyToOne(cascade = { CascadeType.MERGE, CascadeType.REFRESH }, optional = false)
	@JoinColumn(name = "USER_ID")
	public TErbacUser getUser() {
		return user;
	}

	public void setUser(TErbacUser user) {
		this.user = user;
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

package org.joy.plugin.security.erbac.model.po;

import org.joy.core.persistence.support.entity.UuidEntity;
import org.joy.core.persistence.orm.jpa.annotations.Comment;
import org.joy.plugin.security.erbac.support.enums.AuthorityType;
import org.joy.plugin.security.user.model.po.TUserBasic;

import javax.persistence.*;

@Entity
@Table(name = "t_erbac_user_auth")
@Comment("用户权限")
public class TErbacUserAuth extends UuidEntity {

	private AuthorityType authorityType;
	private TUserBasic user;
	private TErbacAuthority authority;

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
	@JoinColumn(name = "USER_ID")
	@Comment("用户ID")
	public TUserBasic getUser() {
		return user;
	}

	public void setUser(TUserBasic user) {
		this.user = user;
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

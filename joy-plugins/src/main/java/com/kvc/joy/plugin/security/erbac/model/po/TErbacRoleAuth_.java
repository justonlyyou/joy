package com.kvc.joy.plugin.security.erbac.model.po;

import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@StaticMetamodel(TErbacRoleAuth.class)
public class TErbacRoleAuth_ {

	public static volatile SingularAttribute<TErbacRoleAuth, String> id;
	public static volatile SingularAttribute<TErbacRoleAuth, TErbacRole> role;
	public static volatile SingularAttribute<TErbacRoleAuth, TErbacAuthority> authority;
	public static volatile SingularAttribute<TErbacRoleAuth, String> authorityTypeCode;
	
}

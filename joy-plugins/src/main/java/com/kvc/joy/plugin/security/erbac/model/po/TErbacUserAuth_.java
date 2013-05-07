package com.kvc.joy.plugin.security.erbac.model.po;

import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@StaticMetamodel(TErbacUserAuth.class)
public class TErbacUserAuth_ {

	public static volatile SingularAttribute<TErbacUserAuth, String> id;
	public static volatile SingularAttribute<TErbacUserAuth, TErbacUser> user;
	public static volatile SingularAttribute<TErbacUserAuth, TErbacAuthority> authority;
	public static volatile SingularAttribute<TErbacUserAuth, String> authorityTypeCode;
	
}

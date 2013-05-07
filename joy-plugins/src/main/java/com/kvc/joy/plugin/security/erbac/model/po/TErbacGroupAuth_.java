package com.kvc.joy.plugin.security.erbac.model.po;

import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@StaticMetamodel(TErbacGroupAuth.class)
public class TErbacGroupAuth_ {

	public static volatile SingularAttribute<TErbacGroupAuth, String> id;
	public static volatile SingularAttribute<TErbacGroupAuth, TErbacGroup> group;
	public static volatile SingularAttribute<TErbacGroupAuth, TErbacAuthority> authority;
	public static volatile SingularAttribute<TErbacGroupAuth, String> authorityTypeCode;

}

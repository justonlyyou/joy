package com.kvc.joy.plugin.security.erbac.model.po;

import java.util.Collection;

import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

import com.kvc.joy.core.persistence.entity.UuidCrudEntity_;

@StaticMetamodel(TErbacAuthority.class)
public class TErbacAuthority_ extends UuidCrudEntity_ {

	public static volatile SingularAttribute<TErbacAuthority, String> parentId;
	public static volatile SingularAttribute<TErbacAuthority, String> name;
	public static volatile SingularAttribute<TErbacAuthority, String> desc;
	public static volatile SingularAttribute<TErbacAuthority, String> resource;
	public static volatile SingularAttribute<TErbacAuthority, String> domain;
	public static volatile SingularAttribute<TErbacAuthority, String> action;
	public static volatile SingularAttribute<TErbacAuthority, String> instance;
	public static volatile SingularAttribute<TErbacAuthority, String> resourceTypeCode;
	public static volatile CollectionAttribute<TErbacRole, Collection<TErbacRoleAuth>> roleAuths;
	public static volatile CollectionAttribute<TErbacRole, Collection<TErbacGroupAuth>> groupAuths;
	public static volatile CollectionAttribute<TErbacRole, Collection<TErbacUserAuth>> userAuths;

}

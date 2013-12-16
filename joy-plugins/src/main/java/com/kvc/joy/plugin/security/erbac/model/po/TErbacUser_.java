package com.kvc.joy.plugin.security.erbac.model.po;

import java.util.Collection;

import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

import com.kvc.joy.core.persistence.entity.UuidCrudEntity_;

@StaticMetamodel(TErbacUser.class)
public class TErbacUser_ extends UuidCrudEntity_ {

	public static volatile SingularAttribute<TErbacUser, String> account;
	public static volatile SingularAttribute<TErbacUser, String> name;
	public static volatile SingularAttribute<TErbacUser, String> sexCode;
	public static volatile SingularAttribute<TErbacUser, String> password;
	public static volatile SingularAttribute<TErbacUser, String> stautsCode;
	public static volatile SingularAttribute<TErbacUser, String> online;
	public static volatile SingularAttribute<TErbacUser, Integer> loginCount;
	public static volatile SingularAttribute<TErbacUser, String> lastLoginTime;
	public static volatile CollectionAttribute<TErbacUser, Collection<TErbacUserAuth>> userAuths;
	public static volatile CollectionAttribute<TErbacUser, Collection<TErbacRole>> roles;
	public static volatile CollectionAttribute<TErbacUser, Collection<TErbacGroup>> groups;

}

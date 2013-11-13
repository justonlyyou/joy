package com.kvc.joy.plugin.security.erbac.model.po;

import java.util.Collection;

import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

import com.kvc.joy.core.persistence.entity.UuidCrudEntity_;

@StaticMetamodel(TErbacRole.class)
public class TErbacRole_ extends UuidCrudEntity_ {
	
	public static volatile SingularAttribute<TErbacRole, String> parentId;
	public static volatile SingularAttribute<TErbacRole, String> name;
	public static volatile CollectionAttribute<TErbacRole, Collection<TErbacRoleAuth>> roleAuths;
	public static volatile CollectionAttribute<TErbacRole, Collection<TErbacUser>> users;
	public static volatile CollectionAttribute<TErbacRole, Collection<TErbacGroup>> groups;

}

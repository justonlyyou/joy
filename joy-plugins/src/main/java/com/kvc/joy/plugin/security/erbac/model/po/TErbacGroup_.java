package com.kvc.joy.plugin.security.erbac.model.po;

import java.util.Collection;

import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

import com.kvc.joy.core.persistence.entity.UuidCrudEntity_;

@StaticMetamodel(TErbacGroup.class)
public class TErbacGroup_ extends UuidCrudEntity_ {

	public static volatile SingularAttribute<TErbacGroup, String> parentId;
	public static volatile SingularAttribute<TErbacGroup, String> name;
	public static volatile SingularAttribute<TErbacGroup, String> desc;
	public static volatile CollectionAttribute<TErbacGroup, Collection<TErbacUser>> users;
	public static volatile CollectionAttribute<TErbacGroup, Collection<TErbacRole>> roles;
	public static volatile CollectionAttribute<TErbacGroup, Collection<TErbacGroupAuth>> groupAuths;

}

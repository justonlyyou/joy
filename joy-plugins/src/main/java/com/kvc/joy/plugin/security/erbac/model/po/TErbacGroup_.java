package com.kvc.joy.plugin.security.erbac.model.po;

import com.kvc.joy.core.persistence.entity.UuidCrudEntity_;

import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import java.util.Collection;

@StaticMetamodel(TErbacGroup.class)
public class TErbacGroup_ extends UuidCrudEntity_ {

	public static volatile SingularAttribute<TErbacGroup, String> parentId;
	public static volatile SingularAttribute<TErbacGroup, String> name;
	public static volatile CollectionAttribute<TErbacGroup, Collection<TErbacUser>> users;
	public static volatile CollectionAttribute<TErbacGroup, Collection<TErbacRole>> roles;
	public static volatile CollectionAttribute<TErbacGroup, Collection<TErbacGroupAuth>> groupAuths;

}

package com.kvc.joy.core.sysres.menu.po;

import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@StaticMetamodel(TSysMenu.class)
public class TSysMenu_ {
	
	public static volatile SingularAttribute<TSysMenu, String> id;
	public static volatile SingularAttribute<TSysMenu, String> text;
	public static volatile SingularAttribute<TSysMenu, String> parentId;
	public static volatile SingularAttribute<TSysMenu, String> url;
	public static volatile SingularAttribute<TSysMenu, String> orderNum;
	public static volatile SingularAttribute<TSysMenu, String> icon;
	public static volatile SingularAttribute<TSysMenu, Boolean> active;
	public static volatile SingularAttribute<TSysMenu, Boolean> deleted;

}

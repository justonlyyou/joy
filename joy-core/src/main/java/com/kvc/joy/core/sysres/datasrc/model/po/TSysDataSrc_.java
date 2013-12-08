package com.kvc.joy.core.sysres.datasrc.model.po;

import java.util.Set;

import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

import com.kvc.joy.core.persistence.entity.UuidCrudEntity_;
import com.kvc.joy.core.sysres.code.model.po.TSysCodeTable;

/**
 * 
 * @author 唐玮琳
 * @time 2012-6-8 下午10:00:53
 */
@StaticMetamodel(TSysDataSrc.class)
public class TSysDataSrc_ extends UuidCrudEntity_ {
	
	public static volatile SingularAttribute<TSysDataSrc, String> name;
	public static volatile SingularAttribute<TSysDataSrc, String> dbAlias;
	public static volatile SingularAttribute<TSysDataSrc, String> dbType;
	public static volatile SingularAttribute<TSysDataSrc, String> dbName;
	public static volatile SingularAttribute<TSysDataSrc, String> dbUrl;
	public static volatile SingularAttribute<TSysDataSrc, String> jndiName;
	public static volatile SingularAttribute<TSysDataSrc, String> username;
	public static volatile SingularAttribute<TSysDataSrc, String> password;
	public static volatile SingularAttribute<TSysDataSrc, String> parameter;
	public static volatile SingularAttribute<TSysDataSrc, String> charset;
	public static volatile SingularAttribute<TSysDataSrc, String> ipAddress;
	public static volatile SingularAttribute<TSysDataSrc, String> serverPort;
	public static volatile SingularAttribute<TSysDataSrc, Integer> maxConnCount;
	public static volatile SingularAttribute<TSysDataSrc, Integer> minConnCount;
	public static volatile SetAttribute<TSysDataSrc, Set<TSysCodeTable>> codeDics;
	public static volatile SetAttribute<TSysDataSrc, Set<TSysDataSrc>>dataObjs;

}

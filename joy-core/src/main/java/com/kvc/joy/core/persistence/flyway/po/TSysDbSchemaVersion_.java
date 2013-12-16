package com.kvc.joy.core.persistence.flyway.po;

import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

/**
 * 
 * @since 1.0.0
 * @author 唐玮琳
 * @time 2013年11月15日 上午11:41:41
 */
@StaticMetamodel(TSysDbSchemaVersion.class)
public class TSysDbSchemaVersion_ {
	
	public static volatile SingularAttribute<TSysDbSchemaVersion, String> id;
	public static volatile SingularAttribute<TSysDbSchemaVersion, Integer> versionRank;
	public static volatile SingularAttribute<TSysDbSchemaVersion, Integer> installedRank;
	public static volatile SingularAttribute<TSysDbSchemaVersion, String> versionDomain;
	public static volatile SingularAttribute<TSysDbSchemaVersion, String> version;
	public static volatile SingularAttribute<TSysDbSchemaVersion, String> desc;
	public static volatile SingularAttribute<TSysDbSchemaVersion, String> type;
	public static volatile SingularAttribute<TSysDbSchemaVersion, String> script;
	public static volatile SingularAttribute<TSysDbSchemaVersion, Integer> checksum;
	public static volatile SingularAttribute<TSysDbSchemaVersion, String> installedBy;
	public static volatile SingularAttribute<TSysDbSchemaVersion, String> installedOn;
	public static volatile SingularAttribute<TSysDbSchemaVersion, Integer> executionTime;
	public static volatile SingularAttribute<TSysDbSchemaVersion, String> success;
	
}

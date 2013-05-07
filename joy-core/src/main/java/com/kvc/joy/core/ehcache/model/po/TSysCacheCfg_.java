package com.kvc.joy.core.ehcache.model.po;

import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

import com.kvc.joy.core.persistence.entity.UuidCrudEntity_;

/**
 * 
 * @author 唐玮琳
 * @time 2013-1-2 下午7:23:51
 */
@StaticMetamodel(TSysCacheCfg.class)
public class TSysCacheCfg_ extends UuidCrudEntity_ {

	public static volatile SingularAttribute<TSysCacheCfg, String> cacheName;
	public static volatile SingularAttribute<TSysCacheCfg, Integer> maxElementsInMemory;
	public static volatile SingularAttribute<TSysCacheCfg, Integer> maxElementsOnDisk;
	public static volatile SingularAttribute<TSysCacheCfg, Boolean> eternal;
	public static volatile SingularAttribute<TSysCacheCfg, Integer> timeToIdleSeconds;
	public static volatile SingularAttribute<TSysCacheCfg, Integer> timeToLiveSeconds;
	public static volatile SingularAttribute<TSysCacheCfg, Boolean> overflowToDisk;
	public static volatile SingularAttribute<TSysCacheCfg, String> memoryStoreEvictionPolicy;
	public static volatile SingularAttribute<TSysCacheCfg, Boolean> diskPersistent;
	public static volatile SingularAttribute<TSysCacheCfg, Integer> diskExpiryThreadIntervalSeconds;
	public static volatile SingularAttribute<TSysCacheCfg, Integer> diskSpoolBufferSizeMB;

}

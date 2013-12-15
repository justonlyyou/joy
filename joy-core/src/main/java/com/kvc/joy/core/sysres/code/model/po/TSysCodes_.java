package com.kvc.joy.core.sysres.code.model.po;

import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

import com.kvc.joy.core.persistence.entity.UuidCrudEntity_;

/**
 * 
 * @since 1.0.0
 * @author 唐玮琳
 * @time 2013年12月15日 下午9:44:05
 */
@StaticMetamodel(TSysCodes.class)
public class TSysCodes_ extends UuidCrudEntity_ {
	
	public static volatile SingularAttribute<TSysCodes, String> groupId;
	public static volatile SingularAttribute<TSysCodes, String> groupComment;
	public static volatile SingularAttribute<TSysCodes, String> code;
	public static volatile SingularAttribute<TSysCodes, String> trans;
	public static volatile SingularAttribute<TSysCodes, String> trnasEnUs;
	public static volatile SingularAttribute<TSysCodes, String> ordinal;
	public static volatile SingularAttribute<TSysCodes, String> parentCode;
	public static volatile SingularAttribute<TSysCodes, String> pinYin;
	public static volatile SingularAttribute<TSysCodes, String> segmentRule;
	
}

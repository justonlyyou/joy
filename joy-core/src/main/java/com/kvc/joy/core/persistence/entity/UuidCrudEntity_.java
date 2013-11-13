package com.kvc.joy.core.persistence.entity;

import javax.persistence.metamodel.SingularAttribute;

/**
 * 
 * @author 唐玮琳
 * @time 2012-12-17 下午11:17:29
 */
public class UuidCrudEntity_ extends UuidEntity_ {

	public static volatile SingularAttribute<UuidCrudEntity, String> createTime;
	public static volatile SingularAttribute<UuidCrudEntity, String> createUser;
	public static volatile SingularAttribute<UuidCrudEntity, String> createDept;
	public static volatile SingularAttribute<UuidCrudEntity, String> updateTime;
	public static volatile SingularAttribute<UuidCrudEntity, String> updateUser;
	public static volatile SingularAttribute<UuidCrudEntity, String> updateDept;
	public static volatile SingularAttribute<UuidCrudEntity, String> deleteTime;
	public static volatile SingularAttribute<UuidCrudEntity, String> deleteUser;
	public static volatile SingularAttribute<UuidCrudEntity, String> deleteDept;
	public static volatile SingularAttribute<UuidCrudEntity, String> desc;
	public static volatile SingularAttribute<UuidCrudEntity, Boolean> deleted;
	public static volatile SingularAttribute<UuidCrudEntity, Boolean> active;
	public static volatile SingularAttribute<UuidCrudEntity, Boolean> builtIn;

}

/**
 * 
 */
package com.kvc.joy.core.sysres.param.po;

import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

import com.kvc.joy.core.persistence.entity.UuidCrudEntity_;

/**
 * 系统参数元模型
 * 
 * @author 唐玮琳
 * @time 2012-4-30 下午8:49:11
 */
@StaticMetamodel(TSysParam.class)
public class TSysParam_ extends UuidCrudEntity_ {

	public static volatile SingularAttribute<TSysParam, String> paramName;
	public static volatile SingularAttribute<TSysParam, String> paramValue;
	public static volatile SingularAttribute<TSysParam, String> defaultValue;
	public static volatile SingularAttribute<TSysParam, Boolean> encrypt;

}

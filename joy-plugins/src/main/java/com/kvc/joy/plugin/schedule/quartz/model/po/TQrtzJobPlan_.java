package com.kvc.joy.plugin.schedule.quartz.model.po;

import com.kvc.joy.core.persistence.entity.UuidCrudEntity_;

import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

/**
 * 
 * @author 唐玮琳
 * @time 2013-1-2 下午10:12:02
 */
@StaticMetamodel(TQrtzJobPlan.class)
public class TQrtzJobPlan_ extends UuidCrudEntity_ {

	public static volatile SingularAttribute<TQrtzJobPlan, String> name;
	public static volatile SingularAttribute<TQrtzJobPlan, String> cronExp;
	public static volatile SingularAttribute<TQrtzJobPlan, String> runState;
	public static volatile SingularAttribute<TQrtzJobPlan, String> effectTime;
	public static volatile SingularAttribute<TQrtzJobPlan, String> expireTime;
	public static volatile SingularAttribute<TQrtzJobPlan, String> lastFireTime;
	public static volatile SingularAttribute<TQrtzJobPlan, String> nextFireTime;

}
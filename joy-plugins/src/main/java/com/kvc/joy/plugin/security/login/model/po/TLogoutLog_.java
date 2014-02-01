package com.kvc.joy.plugin.security.login.model.po;

import com.kvc.joy.core.persistence.entity.UuidEntity_;

import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

/**
 * 
 * @since 1.0.0
 * @author 唐玮琳
 * @time 2013年10月15日 上午9:58:02
 */
@StaticMetamodel(TLogoutLog.class)
public class TLogoutLog_ extends UuidEntity_ {
	
	public static volatile SingularAttribute<TLogoutLog, String> userId;
	public static volatile SingularAttribute<TLogoutLog, String> loginLogId;
	public static volatile SingularAttribute<TLogoutLog, String> logoutTime;
	public static volatile SingularAttribute<TLogoutLog, String> logoutMethodCode;

}

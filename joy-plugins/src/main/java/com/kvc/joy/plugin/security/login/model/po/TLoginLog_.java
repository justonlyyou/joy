package com.kvc.joy.plugin.security.login.model.po;

import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

import com.kvc.joy.core.persistence.entity.UuidEntity_;

@StaticMetamodel(TLoginLog.class)
public class TLoginLog_ extends UuidEntity_ {

	public static volatile SingularAttribute<TLoginLog, String> userId;
	public static volatile SingularAttribute<TLoginLog, String> userAccount;
	public static volatile SingularAttribute<TLoginLog, String> userName;
	public static volatile SingularAttribute<TLoginLog, String> userPassword;
	public static volatile SingularAttribute<TLoginLog, String> loginTime;
	public static volatile SingularAttribute<TLoginLog, String> logoutTime;
	public static volatile SingularAttribute<TLoginLog, String> loginIp;
	public static volatile SingularAttribute<TLoginLog, String> broswerType;
	public static volatile SingularAttribute<TLoginLog, String> broswerVersion;
	public static volatile SingularAttribute<TLoginLog, String> osType;
	public static volatile SingularAttribute<TLoginLog, String> osVersion;
	public static volatile SingularAttribute<TLoginLog, String> loginStateCode;
	public static volatile SingularAttribute<TLoginLog, String> rememberMe;

}

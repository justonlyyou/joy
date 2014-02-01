package com.kvc.joy.plugin.monitor.jdbc.model.po;

import com.kvc.joy.core.persistence.entity.UuidEntity_;

import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

/**
 * 
 * @author 唐玮琳
 * @time 2012-12-15 下午7:28:58
 */
@StaticMetamodel(TSysSqlLog.class)
public class TSysSqlLog_ extends UuidEntity_ {

	public static volatile SingularAttribute<TSysSqlLog, String> logTime;
	public static volatile SingularAttribute<TSysSqlLog, String> appName;
	public static volatile SingularAttribute<TSysSqlLog, String> moduleName;
	public static volatile SingularAttribute<TSysSqlLog, Long> costTime;
	public static volatile SingularAttribute<TSysSqlLog, String> sqlText;
	public static volatile SingularAttribute<TSysSqlLog, String> variables;
	public static volatile SingularAttribute<TSysSqlLog, String> fullSql;
	public static volatile SingularAttribute<TSysSqlLog, String> className;
	public static volatile SingularAttribute<TSysSqlLog, String> methodName;
	public static volatile SingularAttribute<TSysSqlLog, Integer> lineNumber;
	
}

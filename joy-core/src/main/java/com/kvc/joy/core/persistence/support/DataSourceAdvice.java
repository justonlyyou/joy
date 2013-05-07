package com.kvc.joy.core.persistence.support;

import java.lang.reflect.Method;
import java.sql.Connection;

import org.springframework.aop.AfterReturningAdvice;

/**
 * 数据源通知，主要为了设置AutoCommit为false
 * 
 * @author 唐玮琳
 * @time 2012-5-29 下午11:25:57
 */
public class DataSourceAdvice implements AfterReturningAdvice {

	private boolean autoCommit = true;

	public void afterReturning(Object returnValue, Method method, Object[] arg2, Object arg3) throws Throwable {
		if (returnValue instanceof Connection && "getConnection".equals(method.getName())) {
			Connection conn = (Connection) returnValue;
			conn.setAutoCommit(autoCommit);
		}
	}

	public boolean isAutoCommit() {
		return autoCommit;
	}

	public void setAutoCommit(boolean autoCommit) {
		this.autoCommit = autoCommit;
	}

}

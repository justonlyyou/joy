package com.kvc.joy.core.persistence.jdbc.model.vo;

/**
 * 
 * @author 唐玮琳
 * @time 2013-1-3 上午12:19:58
 */
public interface IMdRdbConn {

	String getUrl();

	String getUsername();

	String getPassword();

	String getDatasourceId();
	
	String getJndiName();

}

package com.kvc.joy.core.persistence.jdbc.model.vo;


/**
 * 
 * @author Kevice
 * @time 2013-1-3 上午12:19:58
 */
public interface IMdRdbDataSrc {

	String getUrl();

	String getUsername();

	String getPassword();

	String getDatasourceId();
	
	String getJndiName();
	
}

package org.joy.core.persistence.jdbc.model.vo;


/**
 * 关系型数据库数据源元数据接口
 * 
 * @author Kevice
 * @time 2013-1-3 上午12:19:58
 * @since 1.0.0
 */
public interface IMdRdbDataSrc {

	String getUrl();

	String getUsername();

	String getPassword();

	String getDatasourceId();
	
	String getJndiName();
	
}

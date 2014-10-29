package org.joy.core.persistence.jdbc;

import org.joy.core.init.service.IPlugin;
import org.springframework.stereotype.Component;

/**
 * jdbc插件，提供通过jdbc操作关系型数据库的一些工具，并提供获取关系型数据库元数据信息的服务
 * 
 * @since 1.0.0
 * @author Kevice
 * @time 2013年11月16日 下午11:32:29
 */
@Component
public class JdbcPlugin implements IPlugin {

	@Override
	public String getSqlMigrationPrefix() {
		return "JDBC";
	}

	@Override
	public String getPoPackage() {
		return null;
	}

	@Override
	public String getName() {
		return "JDBC";
	}

	@Override
	public int getInitPriority() {
		return 0;
	}

	@Override
	public void startup() {
		
	}

	@Override
	public void destroy() {
		
	}

	@Override
	public boolean isEnabled() {
		return true; // 作为核心组件，必须启用
	}
	
	@Override
	public String getCtxConfLocation() {
		return "classpath*:/conf/comp-appCtx-jdbc.xml";
	}

}

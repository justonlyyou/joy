package com.kvc.joy.core.persistence.jdbc;

import com.kvc.joy.core.init.service.IJoyPlugin;
import org.springframework.stereotype.Component;

/**
 * 
 * @since 1.0.0
 * @author Kevice
 * @time 2013年11月16日 下午11:32:29
 */
@Component
public class JdbcPlugin implements IJoyPlugin {

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
		return true;
	}
	
	@Override
	public String getCtxConfLocation() {
		return "classpath*:/conf/comp-appCtx-jdbc.xml";
	}

}

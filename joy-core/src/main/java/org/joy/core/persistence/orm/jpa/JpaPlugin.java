package org.joy.core.persistence.orm.jpa;

import org.joy.core.init.service.IJoyPlugin;
import org.springframework.stereotype.Component;

/**
 * 
 * @since 1.0.0
 * @author Kevice
 * @time 2013年11月16日 下午11:44:52
 */
@Component
public class JpaPlugin implements IJoyPlugin {

	@Override
	public String getSqlMigrationPrefix() {
		return "JPA";
	}

	@Override
	public String getPoPackage() {
		return null;
	}

	@Override
	public String getName() {
		return "JPA";
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
		return "classpath*:/conf/comp-appCtx-jpa.xml";
	}

}

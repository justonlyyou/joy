package org.joy.core.sysres;

import org.joy.core.init.service.IJoyPlugin;
import org.springframework.stereotype.Component;

/**
 * 
 * @since 1.0.0
 * @author Kevice
 * @time 2013年11月16日 下午11:46:22
 */
@Component
public class SysResPlugin implements IJoyPlugin {

	@Override
	public String getSqlMigrationPrefix() {
		return "SYS_RES";
	}

	@Override
	public String getPoPackage() {
		return "org.joy.core.sysres.**.po";
	}

	@Override
	public String getName() {
		return "平台基础资源";
	}

	@Override
	public int getInitPriority() {
		return -1;
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
		return "classpath*:/conf/comp-appCtx-sysres.xml";
	}

}

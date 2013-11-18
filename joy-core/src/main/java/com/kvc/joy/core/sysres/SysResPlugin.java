package com.kvc.joy.core.sysres;

import org.springframework.stereotype.Component;

import com.kvc.joy.core.init.service.IJoyPlugin;

/**
 * 
 * @since 1.0.0
 * @author 唐玮琳
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
		return "com.kvc.joy.core.sysres.**.po";
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

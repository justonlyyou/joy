package org.joy.core.sysres;

import org.joy.core.init.service.IPlugin;
import org.springframework.stereotype.Component;

/**
 * 系统资源插件，提供应用系统运行的基础资源，如：数据源、代码字典、系统参数、菜单等
 * 
 * @since 1.0.0
 * @author Kevice
 * @time 2013年11月16日 下午11:46:22
 */
@Component
public class SysResPlugin implements IPlugin {

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
		return true; // 作为核心组件，必须启用
	}
	
	@Override
	public String getCtxConfLocation() {
		return "classpath*:/conf/comp-appCtx-sysres.xml";
	}

}

package com.kvc.joy.plugin.persistence.hibernate;

import com.kvc.joy.core.init.service.IJoyPlugin;
import com.kvc.joy.core.init.support.properties.JoyProperties;
import org.springframework.stereotype.Component;

/**
 * 
 * @since 1.0.0
 * @author 唐玮琳
 * @time 2013年10月3日 下午8:36:42
 */
@Component
public class HibernatePlugin implements IJoyPlugin {

	@Override
	public String getName() {
		return "Hibernate";
	}

	@Override
	public int getInitPriority() {
		return Integer.MAX_VALUE;
	}

	@Override
	public void startup() {
	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean isEnabled() {
		return JoyProperties.PLUGIN_PERSISTENCE_HIBERNATE_ENABLED;
	}

	@Override
	public String getSqlMigrationPrefix() {
		return "HIBERNATE";
	}

	@Override
	public String getPoPackage() {
		return null;
	}

	@Override
	public String getCtxConfLocation() {
		return "classpath*:/conf/plugin-appCtx-hibernate.xml";
	}

}

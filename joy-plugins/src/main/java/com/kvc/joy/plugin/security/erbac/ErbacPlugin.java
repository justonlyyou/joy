package com.kvc.joy.plugin.security.erbac;

import org.springframework.stereotype.Component;

import com.kvc.joy.core.init.service.SpringManagedJoyPlugin;
import com.kvc.joy.core.init.support.JoyPropeties;

/**
 * 
 * @author 唐玮琳
 * @time 2013-2-14 上午10:35:38
 */
@Component
public class ErbacPlugin extends SpringManagedJoyPlugin {

	public String getName() {
		return "扩展的基于角色访问控制";
	}

	public void startup() {
//		EhCacheManager shiroCacheManager = PluginBeanFactory.getShiroCacheManager();
//		if (JoyPropeties.ehchcheEnabled) {
//			EhCacheManagerFactoryBean ehCacheManagerFactory = PluginBeanFactory.getEhCacheManagerFactory();
//			shiroCacheManager.setCacheManager(ehCacheManagerFactory.getObject());
//		}
	}

	public void destroy() {
		// TODO Auto-generated method stub

	}

	public boolean isEnabled() {
		return JoyPropeties.PLUGIN_ERBAC_ENABLED;
	}

	public int getInitPriority() {
		return Integer.MAX_VALUE;
	}

	@Override
	public String getXmlPath() {
		return "/conf/component-applicationContext-erbac.xml";
	}

}

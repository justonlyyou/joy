package com.kvc.joy.plugin.monitor.jdbc;

import java.net.URL;

import org.jwebap.startup.Startup;
import org.springframework.stereotype.Component;

import com.kvc.joy.core.init.service.SpringManagedJoyPlugin;
import com.kvc.joy.core.init.support.JoyPropeties;

/**
 * 
 * @author 唐玮琳
 * @time 2013-2-5 上午12:39:07
 */
 @Component
public class JwebapJdbcPlugin extends SpringManagedJoyPlugin {

	public String getName() {
		return "Jwebap JDBC监控";
	}

	public void init() {
	}

	public void startup() {
		
	}
	
	//TODO
	public static void preInit() {
		URL url = JwebapJdbcPlugin.class.getClassLoader().getResource("conf/jwebap.xml");
		Startup.startup(url);
	}

	public void destroy() {
	}

	public boolean isEnabled() {
		return JoyPropeties.PLUGIN_JWEBAP_JDBC_ENABLED;
	}

	public int getInitPriority() {
		return 0;
	}

	@Override
	public String getXmlPath() {
		return "/conf/component-applicationContext-jwebap-jdbc.xml";
	}

}

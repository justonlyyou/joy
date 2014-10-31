package org.joy.plugin.monitor.jdbc.jwebap;

import org.joy.core.init.service.IPlugin;
import org.joy.core.init.support.properties.JoyProperties;
import org.joy.plugin.monitor.jdbc.jwebap.model.po.TSysSqlLog;
import org.jwebap.startup.Startup;
import org.springframework.stereotype.Component;

import java.net.URL;

/**
 * JwebapJdbc插件，使用jwebap项目的jdbc监控部分，用于sql性能监控和参数填充
 *
 * @since 1.0.0
 * @author Kevice
 * @time 2013-2-5 上午12:39:07
 */
 @Component
public class JwebapJdbcPlugin implements IPlugin {

	public String getName() {
		return "Jwebap JDBC监控";
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
		return JoyProperties.PLUGIN_JWEBAP_JDBC_ENABLED;
	}

	public int getInitPriority() {
		return 0;
	}

	@Override
	public String getSqlMigrationPrefix() {
		return "JWEBAP_JDBC";
	}

	@Override
	public String getPoPackage() {
		return TSysSqlLog.class.getPackage().getName();
	}
	
	@Override
	public String getCtxConfLocation() {
		return "classpath*:/conf/plugin-appCtx-jwebap-jdbc.xml";
	}

}

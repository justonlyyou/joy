package org.joy.web.init;

import org.joy.commons.log.Log;
import org.joy.commons.log.LogFactory;
import org.joy.commons.support.ICommand;
import org.joy.core.init.service.IJoyInitializer;
import org.joy.core.init.service.impl.JoyInitializer;
import org.joy.core.init.service.impl.JoyPluginsInitializer;
import org.joy.core.init.support.properties.JoyProperties;
import org.joy.core.sysres.param.service.BaseSystemParameter;
import org.joy.plugin.monitor.jdbc.jwebap.JwebapJdbcPlugin;
import org.springframework.web.context.ConfigurableWebApplicationContext;
import org.springframework.web.context.ContextLoaderListener;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;

/**
 * 
 * @author Kevice
 * @time 2012-12-28 下午11:57:19
 */
public class JoyWebInitializer extends ContextLoaderListener {

	private static final Log logger = LogFactory.getLog(JoyWebInitializer.class);

	private final IJoyInitializer joyInitializer = new JoyInitializer();
	
	@Override
	public void contextInitialized(ServletContextEvent event) {
		preContextInitialized(event);
		initContext(event);
		postContextInitialized(event);
	}

	@SuppressWarnings("serial")
	protected void preContextInitialized(final ServletContextEvent event) {
		joyInitializer.beforeContextInit(new ICommand() {

			@Override
			public void execute() {
				// new WebXmlDecorator(event).decorate();
				initJwebap();

				// 验证是否设置软件许可过滤器
				// if (LicensesCheckWebXml.checkLicensesConfig() == false) {
				// logger.warn("未配置许可证过滤器！");
				// }

				// 设置服务器名称
				BaseSystemParameter.SERVER_INFO = event.getServletContext().getServerInfo();
				logger.debug("服务器信息 :" + BaseSystemParameter.SERVER_INFO);
			}
		});
	}

	@SuppressWarnings("serial")
	protected void initContext(final ServletContextEvent event) {
		joyInitializer.initContext(new ICommand() {

			@Override
			public void execute() {
				JoyWebInitializer.super.contextInitialized(event);
			}
		});
	}
	
	@Override
	protected void customizeContext(final ServletContext sc, ConfigurableWebApplicationContext wac) {
		String contextConfigLocation = JoyPluginsInitializer.getCtxConfLocations();
		wac.setConfigLocation(contextConfigLocation);
        logger.info("准备加载以下spring xml文件：\n" + contextConfigLocation);
		super.customizeContext(sc, wac);
	}

	protected void postContextInitialized(ServletContextEvent event) {
		joyInitializer.afterContextInit(null);
	}

	@Override
	@SuppressWarnings("serial")
	public void contextDestroyed(final ServletContextEvent event) {
		joyInitializer.onContextDestroyed(new ICommand() {

			@Override
			public void execute() {
				JoyWebInitializer.super.contextDestroyed(event);
			}
		});
	}

	@Override
	public void closeWebApplicationContext(ServletContext servletContext) {
		logger.info("closeWebApplicationContext");
		super.closeWebApplicationContext(servletContext);
	}

	protected static void initJwebap() {
		if (JoyProperties.PLUGIN_JWEBAP_JDBC_ENABLED) {
			JwebapJdbcPlugin.preInit();
		}
	}
	
}

package com.kvc.joy.web.init;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.context.ContextLoaderListener;

import com.kvc.joy.commons.support.ICommand;
import com.kvc.joy.core.init.service.IJoyInitializer;
import com.kvc.joy.core.init.service.impl.JoyInitializer;
import com.kvc.joy.core.init.support.JoyPropeties;
import com.kvc.joy.core.sysres.param.service.BaseSystemParameter;
import com.kvc.joy.plugin.monitor.jdbc.JwebapJdbcPlugin;

/**
 * 
 * @author 唐玮琳
 * @time 2012-12-28 下午11:57:19
 */
public class JoyWebInitializer extends ContextLoaderListener {

	private static Logger logger = LoggerFactory.getLogger(JoyWebInitializer.class);

	private IJoyInitializer joyInitializer = new JoyInitializer();

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
		if (JoyPropeties.PLUGIN_JWEBAP_JDBC_ENABLED) {
			JwebapJdbcPlugin.preInit();
		}
	}

}

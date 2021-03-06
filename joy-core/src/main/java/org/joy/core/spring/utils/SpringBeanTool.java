package org.joy.core.spring.utils;

import org.joy.commons.log.Log;
import org.joy.commons.log.LogFactory;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * Spring Bean工具类，同时它负责spring上下文注入
 * 
 * @author Kevice
 * @time 2011-12-7 下午9:06:16
 * @since 1.0.0
 */
@Service
public class SpringBeanTool implements ApplicationContextAware {

	private static ApplicationContext appContext;
	protected static final Log logger = LogFactory.getLog(SpringBeanTool.class);

	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		SpringBeanTool.appContext = applicationContext;
		logger.info("初始化Spring上下文成功。");
	}

	/**
	 * 获取 ApplicationContext
     * @return ApplicationContext
     * @author Kevice
     * @time 2011-12-7 下午9:06:16
     * @since 1.0.0
	 */
	public static ApplicationContext getApplicationContext() {
//		if (null == appContext) {
//			appContext = ContextLoader.getCurrentWebApplicationContext();
//		}
		return appContext;
	}

	/**
	 * 获取 Bean
	 * 
	 * @param beanName bean 名称
	 * @return Bean实例
     * @author Kevice
     * @time 2011-12-7 下午9:06:16
     * @since 1.0.0
	 */
	public static Object getBean(String beanName) {
		checkApplicationContext();
		return getApplicationContext().getBean(beanName);
	}

	/**
	 * 获取 Bean
	 * 
	 * @param clazz bean 类
	 * @return Bean实例
     * @author Kevice
     * @time 2011-12-7 下午9:06:16
     * @since 1.0.0
	 */
	public static <T> T getBean(Class<T> clazz) {
		checkApplicationContext();
		Map<?, ?> map = getApplicationContext().getBeansOfType(clazz);
		return map.isEmpty() ? null : (T) map.values().iterator().next();
	}

	/**
	 * 检查 ApplicationContext 是否注入
	 */
	private static void checkApplicationContext() {
		if (null == getApplicationContext()) {
			throw new IllegalStateException("applicaitonContext未注入");
		}
	}

}

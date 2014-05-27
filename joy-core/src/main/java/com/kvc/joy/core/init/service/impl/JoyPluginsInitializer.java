package com.kvc.joy.core.init.service.impl;

import com.googlecode.flyway.core.Flyway;
import com.kvc.joy.commons.exception.ServiceException;
import com.kvc.joy.commons.lang.PackageTool;
import com.kvc.joy.commons.lang.string.StringTool;
import com.kvc.joy.commons.log.Log;
import com.kvc.joy.commons.log.LogFactory;
import com.kvc.joy.core.init.service.IJoyPlugin;
import com.kvc.joy.core.init.service.ISystemInitService;
import com.kvc.joy.core.init.support.properties.JoyProperties;
import com.kvc.joy.core.spring.utils.CoreBeanFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * 
 * @author Kevice
 * @time 2013-2-3 下午4:13:09
 */
@Service
public class JoyPluginsInitializer implements ISystemInitService, BeanPostProcessor {

	private final List<IJoyPlugin> components = new ArrayList<IJoyPlugin>();
	private static final Log logger = LogFactory.getLog(JoyPluginsInitializer.class);

	public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
		if (bean instanceof IJoyPlugin) {
			register((IJoyPlugin) bean);
		}
		return bean;
	}

	public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
		return bean;
	}

	public void init() {
		// 按初始化优先级从小到大排序
		Collections.sort(components, new Comparator<IJoyPlugin>() {

			public int compare(IJoyPlugin comp1, IJoyPlugin comp2) {
				return Integer.valueOf(comp1.getInitPriority()).compareTo(comp2.getInitPriority());
			}

		});
		
		// 初始化平台各插件
		for (IJoyPlugin component : components) {
			if (component.isEnabled()) {
				logger.info("启动JOY平台插件【" + component.getName() + "】...");
				
				// 实体字段注释和默认值同步到数据库
				String poPkg = component.getPoPackage();
				if(StringTool.isNotBlank(poPkg)) {
					CoreBeanFactory.getMdRdbAlterReverseSyncService().generate(poPkg);
				}
				
				// sql脚本安装
				Flyway flyway = CoreBeanFactory.getRdbObjectsInitService().createFlyway();
				String migrationPrefix = component.getSqlMigrationPrefix();
                if (StringTool.isNotBlank(migrationPrefix)) {
                    flyway.setSqlMigrationPrefix(migrationPrefix);
                    CoreBeanFactory.getRdbObjectsInitService().migrate(flyway);
                }

				try {
					component.startup();
					logger.info("JOY平台插件【{0}】启动成功。", component.getName());
				} catch (Exception e) {
					logger.error(e, "JOY平台插件【{0}】启动失败！", component.getName());
				}
			} else {
				logger.info("JOY平台插件【{0}】未启动。", component.getName());
			}
		}
		
		// 应用sql脚本安装
		Flyway flyway = CoreBeanFactory.getRdbObjectsInitService().createFlyway();
		String migrationPrefix = JoyProperties.FLYWAY_SQLMIGRATIONPREFIX;
        if (StringTool.isNotBlank(migrationPrefix)) {
            flyway.setSqlMigrationPrefix(migrationPrefix);
            CoreBeanFactory.getRdbObjectsInitService().migrate(flyway);
        }
	}

	protected void register(IJoyPlugin component) {
		components.add(component);
	}

	public String getName() {
		return "JOY平台各插件启动管理";
	}
	
	public static String getCtxConfLocations() {
		StringBuilder sb = new StringBuilder();
		String DEFAULT_CFG_LOCATION = "classpath*:/conf/applicationContext*.xml";
		sb.append(DEFAULT_CFG_LOCATION);
//		sb.append(",").append("classpath*:/conf/springMVC.xml");
		Set<Class<?>> classes = PackageTool.getClassesInPackage("com.kvc.joy.core", true);
		classes.addAll(PackageTool.getClassesInPackage("com.kvc.joy.plugin", true));
		for (Class<?> clazz : classes) {
			if (clazz.getName().endsWith("Plugin") && IJoyPlugin.class.isAssignableFrom(clazz) && clazz != IJoyPlugin.class) {
				try {
					IJoyPlugin plugin = (IJoyPlugin) clazz.newInstance();
					if(plugin.isEnabled()) {
						String location = plugin.getCtxConfLocation();
						sb.append(",").append(location);
					}
				} catch (Exception e) {
                    e.printStackTrace();
                    throw new ServiceException(e);
				}
			}
		}
		return sb.toString();
	}

}

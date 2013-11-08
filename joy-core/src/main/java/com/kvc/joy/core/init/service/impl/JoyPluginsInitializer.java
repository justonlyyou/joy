package com.kvc.joy.core.init.service.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Service;

import com.kvc.joy.core.init.service.IJoyPlugin;
import com.kvc.joy.core.init.service.ISystemInitService;
import com.kvc.joy.core.spring.utils.CoreBeanFactory;

/**
 * 
 * @author 唐玮琳
 * @time 2013-2-3 下午4:13:09
 */
@Service
public class JoyPluginsInitializer implements ISystemInitService, BeanPostProcessor {

	private List<IJoyPlugin> components = new ArrayList<IJoyPlugin>();
	private static Logger logger = LoggerFactory.getLogger(JoyPluginsInitializer.class);

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
		
		List<String> xmlList = new ArrayList<String>();
		for (IJoyPlugin component : components) {
			if (component.isEnabled()) {
				xmlList.add(component.getXmlPath());
			}
		}
		String[] xmls = xmlList.toArray(new String[xmlList.size()]);
		CoreBeanFactory.getSpringXmlDynamicLoadService().load(xmls);

		// 初始化平台各插件
		for (IJoyPlugin component : components) {
			if (component.isEnabled()) {
				logger.info("启动JOY平台插件【" + component.getName() + "】...");
				try {
					component.init();
					component.startup();
					logger.info("JOY平台插件【" + component.getName() + "】启动成功。");
				} catch (Exception e) {
					logger.error("JOY平台插件【" + component.getName() + "】启动失败！", e);
				}
			} else {
				logger.info("JOY平台插件【" + component.getName() + "】未启动。");
			}
		}
	}

	protected void register(IJoyPlugin component) {
		components.add(component);
	}

	public String getName() {
		return "JOY平台各插件启动管理";
	}

}

package com.kvc.joy.core.init.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Service;

import com.kvc.joy.commons.log.Log;
import com.kvc.joy.commons.log.LogFactory;
import com.kvc.joy.core.init.service.ISystemInitService;

/**
 * 
 * @author 唐玮琳
 * @time 2012-12-29 上午12:43:32
 */
@Service
public class SystemInitializer implements ISystemInitService, BeanPostProcessor {

	private List<ISystemInitService> initServiceList = new ArrayList<ISystemInitService>();
	protected static final Log logger = LogFactory.getLog(SystemInitializer.class);

	public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
		if (bean instanceof ISystemInitService) {
			register((ISystemInitService) bean);
		}
		return bean;
	}

	public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
		return bean;
	}

	protected void register(ISystemInitService sysInitService) {
		initServiceList.add(sysInitService);
	}

	public void init() {
		for (ISystemInitService initService : initServiceList) {
			logger.info("初始化系统服务：" + initService.getName() + "...");
			initService.init();
			logger.info("系统服务：" + initService.getName() + "初始化成功。");
		}
	}

	public String getName() {
		return "系统初始化服务";
	}

}

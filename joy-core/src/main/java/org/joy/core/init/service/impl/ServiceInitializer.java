package org.joy.core.init.service.impl;

import org.joy.commons.log.Log;
import org.joy.commons.log.LogFactory;
import org.joy.core.init.service.IInitService;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * 服务初始化器
 *
 * @author Kevice
 * @time 2012-12-29 上午12:43:32
 * @since 1.0.0
 */
@Service
public class ServiceInitializer implements IInitService, BeanPostProcessor {

    private List<IInitService> initServiceList = new ArrayList<>();
	private List<IInitService> appInitServiceList = new ArrayList<>();
	protected static final Log logger = LogFactory.getLog(ServiceInitializer.class);

	public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
		if (bean instanceof IInitService) {
			register((IInitService) bean);
		}
		return bean;
	}

	public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
		return bean;
	}

	protected void register(IInitService initService) {
        if (initService.getClass().getName().startsWith("org.joy")) {
            initServiceList.add(initService);
        } else {
            appInitServiceList.add(initService);
        }
	}

	public void init() {
        Comparator<IInitService> comparator = new Comparator<IInitService>() {
            @Override
            public int compare(IInitService o1, IInitService o2) {
                return o1.getInitPriority() - o2.getInitPriority();
            }
        };

        Collections.sort(initServiceList, comparator);
        for (IInitService initService : initServiceList) {
            logger.info("初始化平台系统服务：" + initService.getName() + "...");
            initService.init();
            logger.info("平台系统服务：" + initService.getName() + "初始化成功。");
        }

        Collections.sort(appInitServiceList, comparator);
		for (IInitService initService : appInitServiceList) {
			logger.info("初始化应用系统服务：" + initService.getName() + "...");
			initService.init();
			logger.info("应用系统服务：" + initService.getName() + "初始化成功。");
		}
	}

    @Override
    public int getInitPriority() {
        return 1;
    }

    public String getName() {
		return "系统初始化服务";
	}

}

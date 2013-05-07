package com.kvc.joy.core.init.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ResourceLoader;

import com.kvc.joy.core.spring.SpringXmlDynamicLoadService;

/**
 * 
 * @author 唐玮琳
 * @time 2013-2-14 下午8:30:24
 */
public abstract class SpringManagedJoyPlugin implements IJoyPlugin {

	@Autowired
	private SpringXmlDynamicLoadService springXmlDynamicLoadService;
	protected Logger logger = LoggerFactory.getLogger(getClass());

	public void init() {
//		String location = ResourceLoader.CLASSPATH_URL_PREFIX + getXmlPath();
//		springXmlDynamicLoadService.load(location);
	}

//	protected abstract String getXmlPath();

	public void setSpringXmlDynamicLoadService(SpringXmlDynamicLoadService springXmlDynamicLoadService) {
		this.springXmlDynamicLoadService = springXmlDynamicLoadService;
	}
	
}

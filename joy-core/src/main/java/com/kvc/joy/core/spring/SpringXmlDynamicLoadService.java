package com.kvc.joy.core.spring;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.stereotype.Service;

import com.kvc.joy.commons.lang.ArrayTool;

/**
 * 
 * @author 唐玮琳
 * @time 2013-2-16 下午8:06:55
 */
@Service
public class SpringXmlDynamicLoadService implements BeanFactoryAware {

	private XmlBeanDefinitionReader definitionReader;
	private Logger logger = LoggerFactory.getLogger(getClass());

	@Override
	public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
		definitionReader = new XmlBeanDefinitionReader((BeanDefinitionRegistry) beanFactory);
	}

	public void load(String... locations) {
		if (ArrayTool.isNotEmpty(locations)) {
			StringBuilder sb = new StringBuilder("\n加载以下spring配置文件：\n");
			for (String location : locations) {
				sb.append(location + "\n");
			}
			logger.info(sb.toString());
			definitionReader.loadBeanDefinitions(locations);
		}
	}

}
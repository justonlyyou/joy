package org.joy.core.spring;

import org.joy.commons.lang.ArrayTool;
import org.joy.commons.log.Log;
import org.joy.commons.log.LogFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.stereotype.Service;

/**
 * 
 * @author Kevice
 * @time 2013-2-16 下午8:06:55
 */
@Service
public class SpringXmlDynamicLoadService implements BeanFactoryAware {

	private XmlBeanDefinitionReader definitionReader;
	protected static final Log logger = LogFactory.getLog(SpringXmlDynamicLoadService.class);

	@Override
	public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
		definitionReader = new XmlBeanDefinitionReader((BeanDefinitionRegistry) beanFactory);
	}

	public void load(String... locations) {
		if (ArrayTool.isNotEmpty(locations)) {
			StringBuilder sb = new StringBuilder("\n加载以下spring配置文件：\n");
			for (String location : locations) {
				sb.append(location).append("\n");
			}
			logger.info(sb.toString());
			definitionReader.loadBeanDefinitions(locations);
		}
	}

}
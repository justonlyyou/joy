package com.kvc.joy.junit;

import java.util.ArrayList;
import java.util.List;

import junit.framework.TestCase;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

import com.kvc.joy.commons.support.ICommand;
import com.kvc.joy.core.init.service.IJoyInitializer;
import com.kvc.joy.core.init.service.impl.JoyInitializer;
import com.kvc.joy.core.spring.utils.SpringBeanUtils;

/**
 * <p>
 * core包的测试用例基类 <br>
 * 构造core的一个基本运行环境
 * </p>
 */
public class BaseTestCase {//extends TestCase {
//TODO
//	protected final Logger logger = LoggerFactory.getLogger(getClass());
//	protected ApplicationContext ctx;
//	private IJoyInitializer joyInitializer = new JoyInitializer();
//
//	protected List<String> getSpringConfigFiles() {
//		List<String> list = new ArrayList<String>();
//		list.add("target/classes/conf/test-applicationContext.xml");
//		list.add("target/classes/conf/applicationContext-service-core.xml");
//		list.add("target/classes/conf/applicationContext-service-sysres.xml");
//		return list;
//	}
//
//	@SuppressWarnings("serial")
//	public BaseTestCase() {
//		joyInitializer.beforeContextInit(null);
//		joyInitializer.initContext(new ICommand() {
//
//			@Override
//			public void execute() {
//				initContext();
//			}
//		});
//		joyInitializer.afterContextInit(null);
//	}
//
//	protected void initContext() {
//		ctx = SpringBeanUtils.getApplicationContext();
//		if (ctx == null) {
//			logger.info("Spring初始化...");
//			List<String> list = getSpringConfigFiles();
//			String[] paths = new String[list.size()];
//			for (int i = 0; i < list.size(); i++) {
//				paths[i] = (String) list.get(i);
//			}
//			ctx = new FileSystemXmlApplicationContext(paths);
//			new SpringBeanUtils().setApplicationContext(ctx);
//			logger.info("Spring初始化\t[OK]!");
//		}
//	}
}

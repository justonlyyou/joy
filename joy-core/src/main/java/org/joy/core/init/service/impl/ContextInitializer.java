package org.joy.core.init.service.impl;

import org.joy.commons.enums.Sex;
import org.joy.commons.enums.TimeUnit;
import org.joy.commons.enums.YesNot;
import org.joy.commons.log.Log;
import org.joy.commons.log.LogFactory;
import org.joy.commons.support.ICommand;
import org.joy.core.init.dao.impl.DbPropertiesDao;
import org.joy.core.init.service.IContextInitializer;
import org.joy.core.init.service.IInitService;
import org.joy.core.init.support.properties.JoyProperties;
import org.joy.core.spring.utils.SpringBeanTool;
import org.joy.core.sysres.SysResTool;

import java.util.Properties;

/**
 * 上下文初始化器
 * 
 * @author Kevice
 * @time 2013-4-15 下午11:37:01
 * @since 1.0.0
 */
public class ContextInitializer implements IContextInitializer {

	protected static final Log logger = LogFactory.getLog(ContextInitializer.class);

	@Override
	public void beforeContextInit(ICommand command) {
		logger.info("beforeContextInit...");

        // 初始化表T_JOY_PROPERTIES，并加载该表的properties
        Properties dbProperties = new DbPropertiesDao().initTable();
        JoyProperties.setDbProperties(dbProperties);

		if (command != null) {
			command.execute();
		}
		logger.info("done beforeContextInit");
	}

	@Override
	public void initContext(ICommand command) {
		logger.info("initContext...");
		if (command != null) {
			command.execute();
		}
		logger.info("done initContext");
	}

	@Override
	public void afterContextInit(ICommand command) {
		logger.info("afterContextInit...");
		if (command != null) {
			command.execute();
		}
		
		IInitService pluginsInitializer = (IInitService) SpringBeanTool.getBean("pluginsInitializer");
        pluginsInitializer.init();
		
		IInitService serviceInitializer = (IInitService) SpringBeanTool.getBean("serviceInitializer");
        serviceInitializer.init();
		
		initEnums();

		logger.info("done afterContextInit");
	}

	@Override
	public void onContextDestroyed(ICommand command) {
		logger.info("onContextDestroyed...");
		if (command != null) {
			command.execute();
		}
		logger.info("done onContextDestroyed");
	}
	
	/**
	 * 初始化joy-commons中的几个枚举
	 */
	protected void initEnums() {
		YesNot.initTrans(SysResTool.getAllCodeAndTransStr(YesNot.CODE_TABLE_ID));
		Sex.initTrans(SysResTool.getAllCodeAndTransStr(Sex.CODE_TABLE_ID));
		TimeUnit.initTrans(SysResTool.getAllCodeAndTransStr(TimeUnit.CODE_TABLE_ID));
	}
	
}

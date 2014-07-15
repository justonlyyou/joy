package org.joy.core.init.service.impl;

import org.joy.commons.enums.Sex;
import org.joy.commons.enums.TimeUnit;
import org.joy.commons.enums.YesNot;
import org.joy.commons.log.Log;
import org.joy.commons.log.LogFactory;
import org.joy.commons.support.ICommand;
import org.joy.core.init.dao.impl.JoyPropertiesDao;
import org.joy.core.init.service.IJoyInitializer;
import org.joy.core.init.service.ISystemInitService;
import org.joy.core.init.support.properties.JoyProperties;
import org.joy.core.spring.utils.SpringBeanTool;
import org.joy.core.sysres.SysResTool;

import java.util.Properties;

/**
 * 
 * @since 1.0.0
 * @author Kevice
 * @time 2013-4-15 下午11:37:01
 */
public class JoyInitializer implements IJoyInitializer {

	protected static final Log logger = LogFactory.getLog(JoyInitializer.class);

	@Override
	public void beforeContextInit(ICommand command) {
		logger.info("beforeContextInit...");

        // 初始化表T_JOY_PROPERTIES，并加载该表的properties
        Properties dbProperties = new JoyPropertiesDao().initTable();
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
		
		ISystemInitService compStartupService = (ISystemInitService) SpringBeanTool.getBean("joyPluginsInitializer");
		compStartupService.init();
		
		ISystemInitService sysInitService = (ISystemInitService) SpringBeanTool.getBean("systemInitializer");
		sysInitService.init();
		
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

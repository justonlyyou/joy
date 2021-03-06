package org.joy.plugin.workflow;

import org.activiti.engine.ProcessEngines;
import org.joy.core.init.service.IPlugin;
import org.joy.core.init.support.properties.JoyProperties;
import org.springframework.stereotype.Component;

/**
 * Activiti工作流插件
 *
 * @since 1.0.0
 * @author Kevice
 * @time 2013-3-17 下午9:19:27
 */
@Component
public class ActivitiPlugin implements IPlugin {

	@Override
	public String getName() {
		return "工作流";
	}

	@Override
	public int getInitPriority() {
		return Integer.MAX_VALUE;
	}

	@Override
	public void startup() {
		ProcessEngines.init();
	}

	@Override
	public void destroy() {
		ProcessEngines.destroy();
	}

	@Override
	public boolean isEnabled() {
		return JoyProperties.PLUGIN_WORKFLOW_ENABLED;
	}

	@Override
	public String getSqlMigrationPrefix() {
		return "ACTIVITI";
	}

	@Override
	public String getPoPackage() {
		return null;
	}

	@Override
	public String getCtxConfLocation() {
		return "classpath*:/conf/plugin-appCtx-activiti.xml";
	}

}

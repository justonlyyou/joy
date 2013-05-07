package com.kvc.joy.plugin.workflow;

import org.activiti.engine.ProcessEngines;
import org.springframework.stereotype.Component;

import com.kvc.joy.core.init.service.SpringManagedJoyPlugin;
import com.kvc.joy.core.init.support.JoyPropeties;

/**
 * 
 * @author 唐玮琳
 * @time 2013-3-17 下午9:19:27
 */
@Component
public class ActivitiPlugin extends SpringManagedJoyPlugin {

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
		return JoyPropeties.PLUGIN_WORKFLOW_ENABLED;
	}

	@Override
	public String getXmlPath() {
		return "/conf/component-applicationContext-activiti.xml";
	}

}

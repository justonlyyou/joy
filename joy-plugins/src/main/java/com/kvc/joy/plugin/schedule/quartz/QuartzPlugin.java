package com.kvc.joy.plugin.schedule.quartz;

import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.springframework.stereotype.Component;

import com.kvc.joy.commons.exception.ServiceException;
import com.kvc.joy.core.init.service.SpringManagedJoyPlugin;
import com.kvc.joy.core.init.support.JoyPropeties;
import com.kvc.joy.plugin.schedule.quartz.support.utils.QuartzUtils;
import com.kvc.joy.plugin.support.PluginBeanFactory;

/**
 * 
 * @author 唐玮琳
 * @time 2013-2-5 上午12:47:00
 */
@Component
public class QuartzPlugin extends SpringManagedJoyPlugin {

	public static Scheduler scheduler;

	public String getName() {
		return "Quartz";
	}

	public void startup() {
		scheduler = PluginBeanFactory.getScheduler();
		QuartzUtils.setScheduler(scheduler);
		try {
			scheduler.start();
		} catch (SchedulerException e) {
			throw new ServiceException("quartz调度器启动失败！", e);
		}
		
		PluginBeanFactory.getQuartzTriggersHolder().loadTriggers();
		
	}

	public void destroy() {
		try {
			scheduler.shutdown();
		} catch (SchedulerException e) {
			throw new ServiceException("quartz调度器关闭失败！", e);
		}
//		try {
//			schedulerFactory.destroy();
//		} catch (SchedulerException e) {
//			throw new JoyException("quartz调度器工厂销毁失败！", e);
//		}
	}

	public boolean isEnabled() {
		return JoyPropeties.PLUGIN_QUARTZ_ENABLED;
	}

	public int getInitPriority() {
		return Integer.MAX_VALUE;
	}

	@Override
	public String getXmlPath() {
		return "/conf/component-applicationContext-quartz.xml";
	}

}

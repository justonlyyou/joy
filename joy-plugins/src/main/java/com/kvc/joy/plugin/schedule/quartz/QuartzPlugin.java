package com.kvc.joy.plugin.schedule.quartz;

import com.kvc.joy.commons.exception.SystemException;
import com.kvc.joy.core.init.service.IJoyPlugin;
import com.kvc.joy.core.init.support.properties.JoyProperties;
import com.kvc.joy.plugin.schedule.quartz.model.po.TQrtzJobPlan;
import com.kvc.joy.plugin.schedule.quartz.support.utils.QuartzTool;
import com.kvc.joy.plugin.support.PluginBeanFactory;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.springframework.stereotype.Component;

/**
 * 
 * @author 唐玮琳
 * @time 2013-2-5 上午12:47:00
 */
@Component
public class QuartzPlugin implements IJoyPlugin {

	public static Scheduler scheduler;

	public String getName() {
		return "Quartz";
	}

	public void startup() {
		scheduler = PluginBeanFactory.getScheduler();
		QuartzTool.setScheduler(scheduler);
		try {
			scheduler.start();
		} catch (SchedulerException e) {
			throw new SystemException(e, "quartz调度器启动失败！");
		}

		PluginBeanFactory.getQuartzTriggersHolder().loadTriggers();

	}

	public void destroy() {
		try {
			scheduler.shutdown();
		} catch (SchedulerException e) {
			throw new SystemException(e, "quartz调度器关闭失败！");
		}
		// try {
		// schedulerFactory.destroy();
		// } catch (SchedulerException e) {
		// throw new SystemException(e, "quartz调度器工厂销毁失败！");
		// }
	}

	public boolean isEnabled() {
		return JoyProperties.PLUGIN_QUARTZ_ENABLED;
	}

	public int getInitPriority() {
		return Integer.MAX_VALUE;
	}

	@Override
	public String getSqlMigrationPrefix() {
		return "QUARTZ";
	}

	@Override
	public String getPoPackage() {
		return TQrtzJobPlan.class.getPackage().getName();
	}
	
	@Override
	public String getCtxConfLocation() {
		return "classpath*:/conf/plugin-appCtx-quartz.xml";
	}

}

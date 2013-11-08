package com.kvc.joy.plugin.schedule.quartz.service.impl;

import static org.quartz.JobBuilder.newJob;

import org.quartz.Job;
import org.quartz.JobDetail;
import org.quartz.SchedulerException;
import org.quartz.Trigger;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.kvc.joy.plugin.schedule.quartz.service.IQuartzJobRegistry;
import com.kvc.joy.plugin.schedule.quartz.support.utils.QuartzTool;

/**
 * 
 * @author 唐玮琳
 * @time 2013-2-17 下午8:33:29
 */
public class QuartzJobRegistry implements IQuartzJobRegistry {

	private QuartzTriggersHolder quartzTriggersHolder;
	private Logger logger = LoggerFactory.getLogger(getClass());

	public void registerJob(String planId, String jobId, Class<? extends Job> jobClass) {
		Trigger trigger = quartzTriggersHolder.getTrigger(planId);
		JobDetail jobDetail = newJob(jobClass).withIdentity(jobId).requestRecovery().build();
		try {
			QuartzTool.getScheduler().scheduleJob(jobDetail, trigger);
		} catch (SchedulerException e) {
			logger.error("任务#" + jobId + "调度失败！", e);
		}
	}

	public void setQuartzTriggersHolder(QuartzTriggersHolder quartzTriggersHolder) {
		this.quartzTriggersHolder = quartzTriggersHolder;
	}

	@Override
	public void registerJob(String planId, Class<? extends Job> jobClass) {
		registerJob(planId, planId, jobClass);
	}

}

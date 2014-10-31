package org.joy.plugin.schedule.quartz.service;

import org.quartz.Job;

/**
 * quartz任务注册接口
 *
 * @since 1.0.0
 * @author Kevice
 * @time 2013-2-17 下午8:33:43
 */
public interface IQuartzJobRegistry {
	
	/**
	 * 注册任务
	 * 
	 * @param planId 计划id
	 * @param jobId 任务id
	 * @param jobClass 任务类
     * @since 1.0.0
	 * @author Kevice
	 * @time 2013-2-18 上午12:04:29
	 */
	void registerJob(String planId, String jobId, Class<? extends Job> jobClass);

	/**
	 * 注册任务
	 * 
	 * @param planId 计划id
	 * @param jobClass 任务类
     * @since 1.0.0
	 * @author Kevice
	 * @time 2013-2-18 上午12:13:11
	 */
	void registerJob(String planId, Class<? extends Job> jobClass);

}

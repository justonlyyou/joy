package org.joy.plugin.schedule.quartz.service;

import org.quartz.Job;

/**
 * 
 * @author Kevice
 * @time 2013-2-17 下午8:33:43
 */
public interface IQuartzJobRegistry {
	
	/**
	 * 
	 * 
	 * @param planId
	 * @param jobId
	 * @param jobClass
	 * @author Kevice
	 * @time 2013-2-18 上午12:04:29
	 */
	void registerJob(String planId, String jobId, Class<? extends Job> jobClass);

	/**
	 * 
	 * 
	 * @param planId
	 * @param jobClass
	 * @author Kevice
	 * @time 2013-2-18 上午12:13:11
	 */
	void registerJob(String planId, Class<? extends Job> jobClass);

}

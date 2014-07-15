package org.joy.plugin.schedule.quartz.support.utils;

import org.quartz.Scheduler;

/**
 * 
 * @author Kevice
 * @time 2013-2-17 下午6:46:04
 */
public class QuartzTool {

	private static Scheduler scheduler;

	private QuartzTool() {
	}

	public static void setScheduler(Scheduler scheduler) {
		QuartzTool.scheduler = scheduler;
	}

	public static Scheduler getScheduler() {
		return scheduler;
	}

}

package com.kvc.joy.plugin.schedule.quartz.support.utils;

import org.quartz.Scheduler;

/**
 * 
 * @author 唐玮琳
 * @time 2013-2-17 下午6:46:04
 */
public class QuartzUtils {

	private static Scheduler scheduler;

	private QuartzUtils() {
	}

	public static void setScheduler(Scheduler scheduler) {
		QuartzUtils.scheduler = scheduler;
	}

	public static Scheduler getScheduler() {
		return scheduler;
	}

}

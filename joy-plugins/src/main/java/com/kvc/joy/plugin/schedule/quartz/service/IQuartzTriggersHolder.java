package com.kvc.joy.plugin.schedule.quartz.service;

import java.util.Map;

import org.quartz.Trigger;
import org.quartz.TriggerKey;

/**
 * 
 * @author 唐玮琳
 * @time 2013-2-18 上午12:02:53
 */
public interface IQuartzTriggersHolder {
	
	/**
	 * 
	 * 
	 * @author 唐玮琳
	 * @time 2013-2-18 上午12:04:13
	 */
	void loadTriggers();
	
	/**
	 * 
	 * 
	 * @return
	 * @author 唐玮琳
	 * @time 2013-2-18 上午12:04:16
	 */
	Map<TriggerKey, Trigger> getTriggerMap();
	
	/**
	 * 
	 * 
	 * @param triggerId
	 * @return
	 * @author 唐玮琳
	 * @time 2013-2-18 上午12:04:20
	 */
	Trigger getTrigger(String triggerId);

}

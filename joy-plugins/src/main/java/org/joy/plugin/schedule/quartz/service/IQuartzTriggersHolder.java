package org.joy.plugin.schedule.quartz.service;

import org.quartz.Trigger;
import org.quartz.TriggerKey;

import java.util.Map;

/**
 * 
 * @author Kevice
 * @time 2013-2-18 上午12:02:53
 */
public interface IQuartzTriggersHolder {
	
	/**
	 * 
	 * 
	 * @author Kevice
	 * @time 2013-2-18 上午12:04:13
	 */
	void loadTriggers();
	
	/**
	 * 
	 * 
	 * @return
	 * @author Kevice
	 * @time 2013-2-18 上午12:04:16
	 */
	Map<TriggerKey, Trigger> getTriggerMap();
	
	/**
	 * 
	 * 
	 * @param triggerId
	 * @return
	 * @author Kevice
	 * @time 2013-2-18 上午12:04:20
	 */
	Trigger getTrigger(String triggerId);

}

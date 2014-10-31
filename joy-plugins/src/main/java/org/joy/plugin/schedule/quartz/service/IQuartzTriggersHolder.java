package org.joy.plugin.schedule.quartz.service;

import org.quartz.Trigger;
import org.quartz.TriggerKey;

import java.util.Map;

/**
 * quartz触发器持有者接口
 *
 * @since 1.0.0
 * @author Kevice
 * @time 2013-2-18 上午12:02:53
 */
public interface IQuartzTriggersHolder {
	
	/**
     * 加载所有触发器
	 * 
	 * @since 1.0.0
	 * @author Kevice
	 * @time 2013-2-18 上午12:04:13
	 */
	void loadTriggers();
	
	/**
	 * 返回触发器
	 *
	 * @return Map<触发器key，触发器对象>
     * @since 1.0.0
	 * @author Kevice
	 * @time 2013-2-18 上午12:04:16
	 */
	Map<TriggerKey, Trigger> getTriggerMap();
	
	/**
	 * 根据触发器id获取触发器
	 * 
	 * @param triggerId 触发器id
	 * @return 触发器对象
     * @since 1.0.0
	 * @author Kevice
	 * @time 2013-2-18 上午12:04:20
	 */
	Trigger getTrigger(String triggerId);

}

package com.kvc.joy.core.init.service;

/**
 * 
 * @author 唐玮琳
 * @time 2013-2-3 下午4:10:27
 */
public interface IJoyPlugin {
	
	/**
	 * 
	 * 
	 * @return
	 * @author 唐玮琳
	 * @time 2013-2-3 下午4:11:58
	 */
	String getName();
	
	/**
	 * 获取初始化优先级
	 * 
	 * @return
	 * @author 唐玮琳
	 * @time 2013-2-14 下午8:11:09
	 */
	int getInitPriority();
	
	/**
	 * 
	 * 
	 * @author 唐玮琳
	 * @time 2013-2-3 下午11:13:24
	 */
	void init();
	
	/**
	 * 
	 * 
	 * @author 唐玮琳
	 * @time 2013-2-3 下午4:12:01
	 */
	void startup();
	
	/**
	 * 
	 * 
	 * @author 唐玮琳
	 * @time 2013-2-3 下午11:13:43
	 */
	void destroy();
	
	/**
	 * 
	 * 
	 * @return
	 * @author 唐玮琳
	 * @time 2013-2-4 下午11:34:05
	 */
	boolean isEnabled();
	
	String getXmlPath();

}

package org.joy.core.init.service;

import org.joy.commons.support.ICommand;

/**
 * 上下文初始化器接口
 * 
 * @since 1.0.0
 * @author Kevice
 * @time 2013-4-15 下午11:27:45
 */
public interface IContextInitializer {
	
	/**
	 * 初始化前的操作
	 * 
	 * @param command
	 * @since 1.0.0
	 * @author Kevice
	 * @time 2013-4-15 下午11:35:25
	 */
	void beforeContextInit(ICommand command);
	
	/**
	 * 初始化操作
	 * 
	 * @param command
	 * @since 1.0.0
	 * @author Kevice
	 * @time 2013-4-15 下午11:35:31
	 */
	void initContext(ICommand command);
	
	/**
	 * 初始化后的操作
	 * 
	 * @param command
	 * @since 1.0.0
	 * @author Kevice
	 * @time 2013-4-15 下午11:35:40
	 */
	void afterContextInit(ICommand command);
	
	/**
	 * 上下文销毁时的操作
	 * 
	 * @param command
	 * @since 1.0.0
	 * @author Kevice
	 * @time 2013-4-15 下午11:35:46
	 */
	void onContextDestroyed(ICommand command);

}

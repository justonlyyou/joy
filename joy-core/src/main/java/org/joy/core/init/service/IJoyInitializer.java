package org.joy.core.init.service;

import org.joy.commons.support.ICommand;

/**
 * 
 * @since 1.0.0
 * @author Kevice
 * @time 2013-4-15 下午11:27:45
 */
public interface IJoyInitializer {
	
	/**
	 * 
	 * 
	 * @param command
	 * @since 1.0.0
	 * @author Kevice
	 * @time 2013-4-15 下午11:35:25
	 */
	void beforeContextInit(ICommand command);
	
	/**
	 * 
	 * 
	 * @param command
	 * @since 1.0.0
	 * @author Kevice
	 * @time 2013-4-15 下午11:35:31
	 */
	void initContext(ICommand command);
	
	/**
	 * 
	 * 
	 * @param command
	 * @since 1.0.0
	 * @author Kevice
	 * @time 2013-4-15 下午11:35:40
	 */
	void afterContextInit(ICommand command);
	
	/**
	 * 
	 * 
	 * @param command
	 * @since 1.0.0
	 * @author Kevice
	 * @time 2013-4-15 下午11:35:46
	 */
	void onContextDestroyed(ICommand command);

}

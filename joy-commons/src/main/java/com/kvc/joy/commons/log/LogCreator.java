package com.kvc.joy.commons.log;

/**
 * 
 * 
 * @since 1.0.0
 * @author Kevice
 * @time 2013年11月20日 上午1:19:52
 */
public interface LogCreator {
    
	/**
	 * 
	 * 
	 * @param clazz
	 * @return
	 * @since 1.0.0
	 * @author Kevice
	 * @time 2013年11月20日 上午1:20:03
	 */
    Log createLogger(Class<?> clazz);
}

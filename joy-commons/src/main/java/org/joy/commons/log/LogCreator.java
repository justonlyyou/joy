package org.joy.commons.log;

/**
 * 日志记录器创建者
 * 
 * @since 1.0.0
 * @author Kevice
 * @time 2013年11月20日 上午1:19:52
 */
public interface LogCreator {
    
	/**
	 * 创建日志记录器
	 * 
	 * @param clazz 类
	 * @return 日志记录器
	 * @since 1.0.0
	 * @author Kevice
	 * @time 2013年11月20日 上午1:20:03
	 */
    Log createLogger(Class<?> clazz);
}

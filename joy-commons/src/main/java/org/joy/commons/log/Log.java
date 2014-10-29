package org.joy.commons.log;

/**
 * 日志记录器
 * 
 * @since 1.0.0
 * @author Kevice
 * @time 2013年11月20日 上午1:19:11
 */
public interface Log {
    
	/**
	 * 记录调试信息
	 * 
	 * @param message 信息模板(参考MessageFormat类的说明)
	 * @param args 参数可变数组
	 * @since 1.0.0
	 * @author Kevice
	 * @time 2013年11月20日 上午1:20:17
	 */
    void debug(String message, Object... args);
    
    /**
     * 记录调试信息
     * 
     * @param message 信息模板(参考MessageFormat类的说明)
     * @param param 参数对象
     * @since 1.0.0
     * @author Kevice
     * @time 2013年12月7日 上午10:00:34
     */
    void debug(String message, ILogParam param);
    
    /**
     * 记录提示信息
     * 
     * @param message 信息模板(参考MessageFormat类的说明)
     * @param args  参数可变数组
     * @since 1.0.0
     * @author Kevice
     * @time 2013年11月20日 上午1:20:21
     */
    void info(String message, Object... args);
    
    /**
     * 记录提示信息
     * 
     * @param message 信息模板(参考MessageFormat类的说明)
     * @param param  参数对象
     * @since 1.0.0
     * @author Kevice
     * @time 2013年12月7日 上午10:00:34
     */
    void info(String message, ILogParam param);

    /**
     * 记录警告信息
     * 
     * @param message 信息模板(参考MessageFormat类的说明)
     * @param args  参数可变数组
     * @since 1.0.0
     * @author Kevice
     * @time 2013年11月20日 上午1:20:25
     */
    void warn(String message, Object... args);
    
    /**
     * 记录警告信息
     * 
     * @param message 信息模板(参考MessageFormat类的说明)
     * @param param  参数对象
     * @since 1.0.0
     * @author Kevice
     * @time 2013年12月7日 上午10:00:34
     */
    void warn(String message, ILogParam param);

    /**
     * 记录错误信息
     * 
     * @param message 信息模板(参考MessageFormat类的说明)
     * @param args  参数可变数组
     * @since 1.0.0
     * @author Kevice
     * @time 2013年11月20日 上午1:20:29
     */
    void error(String message, Object... args);
    
    /**
     * 记录错误信息
     * 
     * @param message 信息模板(参考MessageFormat类的说明)
     * @param param  参数对象
     * @since 1.0.0
     * @author Kevice
     * @time 2013年12月7日 上午10:00:34
     */
    void error(String message, ILogParam param);

    /**
     * 记录错误信息
     *
     * @param e
     * @param message 信息模板(参考MessageFormat类的说明)
     * @param args  参数可变数组
     * @since 1.0.0
     * @author Kevice
     * @time 2013年11月20日 上午1:20:32
     */
    void error(Throwable e, String message, Object... args);
    
    /**
     * 记录错误信息
     * 
     * @param e 异常对象
     * @since 1.0.0
     * @author Kevice
     * @time 2013年11月22日 上午12:12:32
     */
    void error(Throwable e);
    
    /**
     * 是否开启调试级别
     * 
     * @return 是否开启调试级别
     * @since 1.0.0
     * @author Kevice
     * @time 2013年11月20日 上午1:47:18
     */
    boolean isDebugEnabled();
    
    /**
     * 是否开提示级别
     * 
     * @return 是否开提示级别
     * @since 1.0.0
     * @author Kevice
     * @time 2013年11月20日 上午1:47:18
     */
    boolean isInfoEnabled();
    
    /**
     * 是否开启警告级别
     * 
     * @return 是否开启警告级别
     * @since 1.0.0
     * @author Kevice
     * @time 2013年11月20日 上午1:47:18
     */
    boolean isWarnEnabled();
    
    /**
     * 是否开启错误级别
     * 
     * @return 是否开启错误级别
     * @since 1.0.0
     * @author Kevice
     * @time 2013年11月20日 上午1:47:18
     */
    boolean isErrorEnabled();
}

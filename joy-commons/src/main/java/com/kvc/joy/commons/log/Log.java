package com.kvc.joy.commons.log;

/**
 * 
 * 
 * @since 1.0.0
 * @author 唐玮琳
 * @time 2013年11月20日 上午1:19:11
 */
public interface Log {
    
	/**
	 * 
	 * 
	 * @param message
	 * @param args
	 * @since 1.0.0
	 * @author 唐玮琳
	 * @time 2013年11月20日 上午1:20:17
	 */
    void debug(String message, Object... args);
    
    /**
     * 
     * 
     * @param message
     * @param param
     * @since 1.0.0
     * @author 唐玮琳
     * @time 2013年12月7日 上午10:00:34
     */
    void debug(String message, ILogParam param);
    
    /**
     * 
     * 
     * @param message
     * @param args
     * @since 1.0.0
     * @author 唐玮琳
     * @time 2013年11月20日 上午1:20:21
     */
    void info(String message, Object... args);
    
    /**
     * 
     * 
     * @param message
     * @param param
     * @since 1.0.0
     * @author 唐玮琳
     * @time 2013年12月7日 上午10:00:34
     */
    void info(String message, ILogParam param);

    /**
     * 
     * 
     * @param message
     * @param args
     * @since 1.0.0
     * @author 唐玮琳
     * @time 2013年11月20日 上午1:20:25
     */
    void warn(String message, Object... args);
    
    /**
     * 
     * 
     * @param message
     * @param param
     * @since 1.0.0
     * @author 唐玮琳
     * @time 2013年12月7日 上午10:00:34
     */
    void warn(String message, ILogParam param);

    /**
     * 
     * 
     * @param message
     * @param args
     * @since 1.0.0
     * @author 唐玮琳
     * @time 2013年11月20日 上午1:20:29
     */
    void error(String message, Object... args);
    
    /**
     * 
     * 
     * @param message
     * @param param
     * @since 1.0.0
     * @author 唐玮琳
     * @time 2013年12月7日 上午10:00:34
     */
    void error(String message, ILogParam param);

    /**
     * 
     *
     * @param e
     * @param message
     * @param args
     * @since 1.0.0
     * @author 唐玮琳
     * @time 2013年11月20日 上午1:20:32
     */
    void error(Throwable e, String message, Object... args);
    
    /**
     * 
     * 
     * @param e
     * @since 1.0.0
     * @author 唐玮琳
     * @time 2013年11月22日 上午12:12:32
     */
    void error(Throwable e);
    
    /**
     * 
     * 
     * @return
     * @since 1.0.0
     * @author 唐玮琳
     * @time 2013年11月20日 上午1:47:18
     */
    boolean isDebugEnabled();
    
    /**
     * 
     * 
     * @return
     * @since 1.0.0
     * @author 唐玮琳
     * @time 2013年11月20日 上午1:47:18
     */
    boolean isInfoEnabled();
    
    /**
     * 
     * 
     * @return
     * @since 1.0.0
     * @author 唐玮琳
     * @time 2013年11月20日 上午1:47:18
     */
    boolean isWarnEnabled();
    
    /**
     * 
     * 
     * @return
     * @since 1.0.0
     * @author 唐玮琳
     * @time 2013年11月20日 上午1:47:18
     */
    boolean isErrorEnabled();
}

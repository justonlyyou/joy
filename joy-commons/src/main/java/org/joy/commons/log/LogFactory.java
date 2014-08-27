package org.joy.commons.log;

import org.joy.commons.log.slf4j.Slf4jLogCreator;

/**
 * 
 * 
 * @since 1.0.0
 * @author Kevice
 * @time 2013年11月20日 上午1:18:38
 */
public class LogFactory {
    
    private static LogCreator logCreator;

    private LogFactory() {
    }

    /**
     * 
     * 
     * @param logCreator
     * @since 1.0.0
     * @author Kevice
     * @time 2013年11月20日 上午1:20:44
     */
    public static void setLogCreator(LogCreator logCreator) {
        LogFactory.logCreator = logCreator;
    }

    /**
     * 
     * 
     * @param clazz
     * @return
     * @since 1.0.0
     * @author Kevice
     * @time 2013年11月20日 上午1:20:47
     */
    public static Log getLog(Class<?> clazz) {
        if (logCreator == null) {
        	logCreator = new Slf4jLogCreator();
        }
        return logCreator.createLogger(clazz);
    }
    
}

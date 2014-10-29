package org.joy.commons.log;

import org.joy.commons.log.slf4j.Slf4jLogCreator;

/**
 * 日志记录器工厂
 * 
 * @since 1.0.0
 * @author Kevice
 * @time 2013年11月20日 上午1:18:38
 */
public class LogFactory {
    
    private static LogCreator logCreator; // 日志记录器创建者

    private LogFactory() {
    }

    /**
     * 设置日志记录器创建者
     * 
     * @param logCreator 日志记录器创建者
     * @since 1.0.0
     * @author Kevice
     * @time 2013年11月20日 上午1:20:44
     */
    public static void setLogCreator(LogCreator logCreator) {
        LogFactory.logCreator = logCreator;
    }

    /**
     * 创建日志记录器
     * 
     * @param clazz 类
     * @return 日志记录器
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

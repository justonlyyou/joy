package org.joy.commons.log.slf4j;

import org.joy.commons.log.Log;
import org.joy.commons.log.LogCreator;
import org.slf4j.LoggerFactory;

/**
 * 
 * @since 1.0.0
 * @author Kevice
 * @time 2013年11月20日 上午1:21:59
 */
public class Slf4jLogCreator implements LogCreator {

	public Log createLogger(Class<?> clazz) {
        return new Slf4jLog(LoggerFactory.getLogger(clazz));
    }
	
}

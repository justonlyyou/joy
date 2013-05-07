package com.kvc.joy.commons.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 非业务的系统异常
 * 
 * @since 1.0.0
 * @author 唐玮琳
 * @time 2013年10月2日 上午12:01:33
 */
public class SystemException extends RuntimeException {
	
	private Logger logger = LoggerFactory.getLogger(getClass());
	
	public SystemException(String message, Object... objs) {
		super(message);
		logger.error(message);
	}

	public SystemException(String message, Throwable cause) {
		super(message, cause);
		logger.error(message, cause);
	}
	
	public SystemException(String message, Throwable cause, boolean log) {
		super(message, cause);
		if(log) {
			logger.error(message, cause);	
		}
	}

	public SystemException(Exception ex) {
		this(ex.getMessage(), ex);
	}

}

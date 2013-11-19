package com.kvc.joy.commons.exception;


/**
 * 非业务的系统异常
 * 
 * @since 1.0.0
 * @author 唐玮琳
 * @time 2013年10月2日 上午12:01:33
 */
public class SystemException extends JoyRuntimeException {

	public SystemException(String message, Object... args) {
		super(message, args);
	}

	public SystemException(Throwable cause, String message, Object... args) {
		super(cause, message, args);
	}
	
	public SystemException(Throwable cause, boolean log, String message, Object... args) {
		super(cause, log, message, args);
	}

	public SystemException(Throwable ex) {
		super(ex);
	}

}

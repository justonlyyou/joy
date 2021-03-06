package org.joy.commons.exception;


/**
 * 非业务的系统异常（面向开发人员）
 * 
 * @since 1.0.0
 * @author Kevice
 * @time 2013年10月2日 上午12:01:33
 */
public class SystemException extends CustomRuntimeException {

    public SystemException(String message, Throwable e) {
        super(e, message);
    }

	public SystemException(String message, Object... args) {
		super(message, args);
	}

	public SystemException(Throwable cause, String message, Object... args) {
		super(cause, message, args);
	}
	
	public SystemException(Throwable cause, boolean log, String message, Object... args) {
		super(cause, log, message, args);
	}

	public SystemException(Throwable e) {
		super(e);
	}

}

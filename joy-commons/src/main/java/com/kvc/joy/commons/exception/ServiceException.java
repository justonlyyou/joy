package com.kvc.joy.commons.exception;

import java.net.ConnectException;

/**
 * 服务层抛出的异常
 * 
 * @since 1.0.0
 * @author <b>唐玮琳</b>
 */
public class ServiceException extends JoyRuntimeException {

	private String url;
	private int type;

	/**
	 * 所有异常类型提示信息数组(请站在用户的角度定义好这些信息)
	 */
	private static final String[] msgs = { "服务方产生未预期的错误！", "连接异常！" };

	/**
	 * 未知异常
	 */
	public static final int UNKNOWN_EXCETION = 0;
	/**
	 * 连接异常
	 */
	public static final int CONNECTION_EXCETION = 1;
	/**
	 * 业务异常
	 */
	public static final int BUSINESS_EXCETION = 2;

	public ServiceException(String message, Object... args) {
		super(message, args);
		type = BUSINESS_EXCETION;
	}

	public ServiceException(Throwable cause, String message, Object... args) {
		super(cause, message, args);
	}
	
	public ServiceException(Throwable cause, boolean log, String message, Object... args) {
		super(cause, log, message, args);
	}

	public ServiceException(Throwable ex) {
		super(ex);
	}

	public String getMessage() {
		String msg = super.getMessage();

		switch (type) {
		case CONNECTION_EXCETION:
			if (url != null) {
				msg = "无法连接到: " + url;
			} else {
				msg = "连接异常！";
			}
			break;
		// case BUSINESS_EXCETION:
		// msg = getCause().getMessage();
		// break;
		// case ...
		default:
			if (msg == null) {
				msg = msgs[type];
			}
			break;
		}

		return msg;
	}

	public ServiceException(Exception ex, String url) {
		super(ex.getMessage(), ex);
		this.url = url;
		processType(ex);
	}

	private void processType(Throwable ex) {
		Throwable cause = ex.getCause();
		if (cause != null) {
			processType(cause);
		}
		
		if (ex instanceof ServiceException) {
			type = BUSINESS_EXCETION;
		} else if (ex instanceof ConnectException) {
			type = CONNECTION_EXCETION;
		} else {
			type = UNKNOWN_EXCETION;
		}
		logger.error(getMessage(), ex);
	}

	/**
	 * 根据异常类型，取得异常信息
	 * 
	 * @param ex
	 *            异常
	 * @return 异常信息
	 */
	public static String getMsg(Exception ex) {
		String msg = msgs[UNKNOWN_EXCETION];
		if (ex instanceof ServiceException) {
			msg = ex.getMessage();
		} else {
			// TODO 精确提示
		}
		logger.error(msg, ex);
		return msg;
	}
	
	public int getExceptionType() {
		return type;
	}
	
}

package com.kvc.joy.commons.exception;

import java.net.ConnectException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 服务层抛出的异常
 * 
 * @since 1.0.0
 * @author <b>唐玮琳</b>
 */
public class JoyException extends RuntimeException {

	private static final long serialVersionUID = -6627301388547321950L;
	private static Logger logger = LoggerFactory.getLogger(JoyException.class);
	private String url;

	public JoyException(String message) {
		super(message);
		type = BUSINESS_EXCETION;
		logger.error(message);
	}

	public JoyException(String message, Throwable cause) {
		super(message, cause);
		logger.error(message, cause);
	}
	
	public JoyException(String message, Throwable cause, boolean log) {
		super(message, cause);
		if(log) {
			logger.error(message, cause);	
		}
	}

	public JoyException(Exception ex) {
		this(ex.getMessage(), ex);
	}

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

	public JoyException(Exception ex, String url) {
		super(ex.getMessage(), ex);
		this.url = url;
		processType(ex);
	}

	private void processType(Throwable ex) {
		Throwable cause = ex.getCause();
		if (cause != null) {
			processType(cause);
		}
		
		if (ex instanceof JoyException) {
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
		if (ex instanceof JoyException) {
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

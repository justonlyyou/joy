package org.joy.commons.exception;

import org.joy.commons.log.Log;
import org.joy.commons.log.LogFactory;
import org.joy.commons.log.Log;

import java.text.MessageFormat;

/**
 * 
 * @since 1.0.0
 * @author Kevice
 * @time 2013年11月20日 下午11:55:25
 */
public class JoyRuntimeException extends RuntimeException {

	protected String detailMessage;
	protected static final Log logger = LogFactory.getLog(JoyRuntimeException.class);

	public JoyRuntimeException(String message, Object... args) {
		fillInStackTrace();
		handleMessageWithoutLog(message, args);
		logger.error(detailMessage);
	}
	
	public JoyRuntimeException(Throwable ex) {
		this(ex, ex.getMessage());
	}

	public JoyRuntimeException(Throwable cause, String message, Object... args) {
		fillInStackTrace();
		handleMessageWithoutLog(message, args);
		logger.error(cause, detailMessage);
	}

	public JoyRuntimeException(Throwable cause, boolean log, String message, Object... args) {
		fillInStackTrace();
		handleMessageWithoutLog(message, args);
		if (log) {
			logger.error(cause, detailMessage);	
		}
	}

	private void handleMessageWithoutLog(String pattern, Object... args) {
		detailMessage = MessageFormat.format(pattern, args);
	}

	@Override
	public String getMessage() {
		return detailMessage;
	}
}

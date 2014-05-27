package com.kvc.joy.commons.log.slf4j;

import com.kvc.joy.commons.log.ILogParam;
import com.kvc.joy.commons.log.Log;
import org.slf4j.Logger;

import java.text.MessageFormat;

/**
 * 
 * @since 1.0.0
 * @author Kevice
 * @time 2013年11月20日 上午1:22:59
 */
public class Slf4jLog implements Log {

	private final Logger log;
	
	public Slf4jLog(Logger log) {
		this.log = log;
	}
	
	@Override
	public void debug(String message, Object... args) {
		log.debug(getMsg(message, args));
	}

	@Override
	public void info(String message, Object... args) {
		log.info(getMsg(message, args));
	}

	@Override
	public void warn(String message, Object... args) {
		log.warn(getMsg(message, args));
	}

	@Override
	public void error(String message, Object... args) {
		log.error(getMsg(message, args));
	}

	@Override
	public void error(Throwable e, String message, Object... args) {
		log.error(getMsg(message, args), e);
	}
	
	@Override
	public void error(Throwable e) {
		log.error(e.getMessage(), e);
	}
	
	private String getMsg(String message, Object... args) {
		if (args != null && args.length != 0) {
			return MessageFormat.format(message, args);	
		}
		return message;
	}

	@Override
	public boolean isDebugEnabled() {
		return log.isDebugEnabled();
	}

	@Override
	public boolean isInfoEnabled() {
		return log.isInfoEnabled();
	}

	@Override
	public boolean isWarnEnabled() {
		return log.isWarnEnabled();
	}

	@Override
	public boolean isErrorEnabled() {
		return log.isErrorEnabled();
	}

	@Override
	public void debug(String message, ILogParam param) {
		log.debug(message, param);
	}

	@Override
	public void info(String message, ILogParam param) {
		log.info(message, param);
	}

	@Override
	public void warn(String message, ILogParam param) {
		log.warn(message, param);
	}

	@Override
	public void error(String message, ILogParam param) {
		log.error(message, param);
	}

}

package com.kvc.joy.plugin.monitor.jdbc;

import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.core.AppenderBase;

import com.kvc.joy.plugin.monitor.jdbc.model.vo.ParamMsg;
import com.kvc.joy.plugin.support.PluginBeanFactory;

/**
 * 系统sql执行性能监控Appender
 * 
 * @since 1.0.0
 * @author 唐玮琳
 * @time 2012-12-15 下午7:04:31
 */
public class SqlMonitorAppender extends AppenderBase<ILoggingEvent> {

	public void append(ILoggingEvent event) {
		Object[] args = event.getArgumentArray();
		if (args != null && args[0] instanceof ParamMsg) {
			PluginBeanFactory.getSysSqlLogService().saveLog((ParamMsg) args[0]);
		}
	}

}

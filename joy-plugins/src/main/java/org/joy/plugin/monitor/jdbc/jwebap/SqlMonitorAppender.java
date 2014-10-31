package org.joy.plugin.monitor.jdbc.jwebap;

import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.core.AppenderBase;
import org.joy.plugin.monitor.jdbc.jwebap.model.vo.ParamMsg;
import org.joy.plugin.support.PluginBeanFactory;

/**
 * 系统sql执行监控Appender
 *
 * @author Kevice
 * @time 2012-12-15 下午7:04:31
 * @since 1.0.0
 */
public class SqlMonitorAppender extends AppenderBase<ILoggingEvent> {

    /**
     * 将执行耗时的sql信息记录到数据库
     *
     * @param event 日志事件
     * @author Kevice
     * @time 2012-12-15 下午7:04:31
     * @since 1.0.0
     */
    public void append(ILoggingEvent event) {
        Object[] args = event.getArgumentArray();
        if (args != null && args[0] instanceof ParamMsg) {
            PluginBeanFactory.getSysSqlLogService().saveLog((ParamMsg) args[0]);
        }
    }

}

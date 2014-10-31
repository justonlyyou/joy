package org.joy.plugin.monitor.jdbc.jwebap;

import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.core.filter.Filter;
import ch.qos.logback.core.spi.FilterReply;
import org.joy.commons.lang.string.StringTool;

/**
 * sql监控过滤器
 *
 * @author Kevice
 * @time 2012-12-15 下午7:53:54
 * @since 1.0.0
 */
public class SqlMonitorFilter extends Filter<ILoggingEvent> {

    private String costTime; // 耗时时间
    private String filterTime; // 过滤时间

    @Override
    public FilterReply decide(ILoggingEvent event) {
        if (StringTool.isBlank(filterTime)) {
            return FilterReply.DENY;
        }
        Long time = Long.valueOf(filterTime);
        if (time > 0) {
            //TODO
        }

        if (event.getMessage() != null && event.getMessage().contains("sample")) {
            return FilterReply.ACCEPT;
        } else {
            return FilterReply.DENY;
        }
    }

    /**
     * 设置过滤时间
     *
     * @param time 过滤时间
     */
    public void setTime(String time) {
        this.filterTime = time;
    }

}

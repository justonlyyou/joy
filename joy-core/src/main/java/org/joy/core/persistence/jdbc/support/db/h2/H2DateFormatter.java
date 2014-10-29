package org.joy.core.persistence.jdbc.support.db.h2;

import org.joy.core.persistence.jdbc.support.db.IDateFormatter;

import java.text.MessageFormat;

/**
 * h2日期格式化器
 *
 * @author Kevice
 * @since 1.0.0
 * @time 2014/10/29 下午2:00:00
 */
public class H2DateFormatter implements IDateFormatter {

    @Override
    public String format(String dbDateFunc, String javaDateFormat) {
        // h2的日期格式化模式和java一样
        String pattern = "FORMATDATETIME({0}, ''{1}'')";
        return MessageFormat.format(pattern, dbDateFunc, javaDateFormat);
    }
}

package com.kvc.joy.core.persistence.jdbc.support.db.mysql;

import com.kvc.joy.core.persistence.jdbc.support.db.IDateFormatter;

import java.text.MessageFormat;

/**
 * 
 * @since 1.0.0
 * @author Kevice
 * @time 2013年11月24日 上午11:40:43
 */
public class MySqlDateFormatter implements IDateFormatter {

	@Override
	public String format(String dbDateFunc, String javaDateFormat) {
//		%S, %s 两位数字形式的秒（ 00,01, . . ., 59）
		javaDateFormat = javaDateFormat.replaceFirst("s+", "%s");
		
//		%i 两位数字形式的分（ 00,01, . . ., 59）
		javaDateFormat = javaDateFormat.replaceFirst("m+", "%i");
		
//		%H 两位数字形式的小时，24 小时（00,01, . . ., 23）
		javaDateFormat = javaDateFormat.replaceFirst("H+", "%H");
		
//		%h, %I 两位数字形式的小时，12 小时（01,02, . . ., 12）
		javaDateFormat = javaDateFormat.replaceFirst("h+", "%h");
		
//		%k 数字形式的小时，24 小时（0,1, . . ., 23）
		javaDateFormat = javaDateFormat.replaceFirst("k+", "%k");
		
//		%l 数字形式的小时，12 小时（1, 2, . . ., 12）
		javaDateFormat = javaDateFormat.replaceFirst("K+", "%l");
		
//		%T 24 小时的时间形式（hh:mm:ss）
//		%r 12 小时的时间形式（hh:mm:ss AM 或hh:mm:ss PM）
		
//		%p AM 或PM
		javaDateFormat = javaDateFormat.replaceFirst("a", "%p");
		
//		%W 一周中每一天的名称（ Sunday, Monday, . . ., Saturday）
		javaDateFormat = javaDateFormat.replaceFirst("[E]{4,}", "%W");
		
//		%a 一周中每一天名称的缩写（ Sun, Mon, . . ., Sat）
		javaDateFormat = javaDateFormat.replaceFirst("[E]{1,3}", "%a");
		
//		%d 两位数字表示月中的天数（ 00, 01, . . ., 31）
//		%e 数字形式表示月中的天数（ 1, 2， . . ., 31）
		javaDateFormat = javaDateFormat.replaceFirst("d+", "%d");
		
//		%D 英文后缀表示月中的天数（ 1st, 2nd, 3rd, . . .）
//		%w 以数字形式表示周中的天数（ 0 = Sunday, 1=Monday, . . ., 6=Saturday）
		
//		%j 以三位数字表示年中的天数（ 001, 002, . . ., 366）
		javaDateFormat = javaDateFormat.replaceFirst("[D]{1,3}", "%j");
		
//		% U 周（0, 1, 52），其中Sunday 为周中的第一天
//		%u 周（0, 1, 52），其中Monday 为周中的第一天
		
//		%M 月名（January, February, . . ., December）
		javaDateFormat = javaDateFormat.replaceFirst("[M]{4,}", "%M");
		
//		%b 缩写的月名（Jan, Feb, . . ., Dec）
		javaDateFormat = javaDateFormat.replaceFirst("MMM", "%b");
		
//		%m 两位数字表示的月份（ 01, 02, . . ., 12）
		javaDateFormat = javaDateFormat.replaceFirst("MM", "%m");
		
//		%c 数字表示的月份（ 1, 2, . . ., 12）
		javaDateFormat = javaDateFormat.replaceFirst("M", "%c");
		
//		%Y 四位数字表示的年份
		javaDateFormat = javaDateFormat.replaceFirst("yyyy", "%Y");
		
//		%y 两位数字表示的年份
		javaDateFormat = javaDateFormat.replaceFirst("yy", "%y");
		
		javaDateFormat = javaDateFormat.replaceFirst("S*$", "");
		
		String pattern = "date_format({0}, ''{1}'')";
		return MessageFormat.format(pattern, dbDateFunc, javaDateFormat);
	}

}

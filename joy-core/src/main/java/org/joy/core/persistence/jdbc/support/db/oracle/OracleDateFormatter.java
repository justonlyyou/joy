package org.joy.core.persistence.jdbc.support.db.oracle;

import org.joy.core.persistence.jdbc.support.db.IDateFormatter;

import java.text.MessageFormat;

/**
 * Oracle日期格式化器
 * 
 * @since 1.0.0
 * @author Kevice
 * @time 2013年11月24日 下午2:47:08
 */
public class OracleDateFormatter implements IDateFormatter {

	@Override
	public String format(String dbDateFunc, String javaDateFormat) {
		// HH 一天的小时数 (01-12)
		// HH12 一天的小时数 (01-12)
		javaDateFormat = javaDateFormat.replaceFirst("h+", "HH12");
		
		// HH24 一天的小时数 (00-23)
		javaDateFormat = javaDateFormat.replaceFirst("H+", "HH24");
		
		// MI 分钟 (00-59)
		javaDateFormat = javaDateFormat.replaceFirst("m+", "MI");
		
		// SS 秒 (00-59)
		javaDateFormat = javaDateFormat.replaceFirst("s+", "SS");
		
		// ff3 毫秒(001-999)
		javaDateFormat = javaDateFormat.replaceFirst("S+", "ff3");
		
		// SSSS 午夜后的秒 (0-86399)
		// AM or A.M. or PM or P.M. 正午标识（大写）
		// am or a.m. or pm or p.m. 正午标识（小写）
		javaDateFormat = javaDateFormat.replaceFirst("a", "AM");
		
		// Y,YYY 带逗号的年（4 和更多位）
		// YYYY 年（4和更多位）
		// YYY 年的后三位
		// YY 年的后两位
		// Y 年的最后一位
		javaDateFormat = javaDateFormat.replace("y", "Y");
		
		// BC or B.C. or AD or A.D. 年标识（大写）
		// bc or b.c. or ad or a.d. 年标识（小写）
		
		// MONTH 全长大写月份名（9字符）
		// Month 全长混合大小写月份名（9字符）
		// month 全长小写月份名（9字符）
		javaDateFormat = javaDateFormat.replaceFirst("[M]{4,}", "MONTH");
		// MON 大写缩写月份名（3字符）
		// Mon 缩写混合大小写月份名（3字符）
		// mon 小写缩写月份名（3字符）
		javaDateFormat = javaDateFormat.replaceFirst("MMM", "MON");
		// MM 月份 (01-12)
		javaDateFormat = javaDateFormat.replaceFirst("MM|M", "MM");
		
		// DAY 全长大写日期名（9字符）
		// Day 全长混合大小写日期名（9字符）
		// day 全长小写日期名（9字符）
		// DY 缩写大写日期名（3字符）
		// Dy 缩写混合大小写日期名（3字符）
		// dy 缩写小写日期名（3字符）
		
		// DDD 一年里的日子(001-366)
		javaDateFormat = javaDateFormat.replaceFirst("[D]{1,3}", "DDD");
		
		// DD 一个月里的日子(01-31)
		javaDateFormat = javaDateFormat.replaceFirst("d+", "DD");
		
		// D 一周里的日子(1-7；SUN=1)
		javaDateFormat = javaDateFormat.replaceFirst("E+", "D");
		
		// W 一个月里的周数
		javaDateFormat = javaDateFormat.replaceFirst("W+", "W");
		
		// WW 一年里的周数
		javaDateFormat = javaDateFormat.replaceFirst("w+", "WW");
		
		// CC 世纪（2 位）
		javaDateFormat = javaDateFormat.replaceFirst("G+", "CC");
		
		// J Julian 日期（自公元前4712年1月1日来的日期）
		// Q 季度
		// RM 罗马数字的月份（I-XII；I=JAN）－大写
		// rm 罗马数字的月份（I-XII；I=JAN）－小写
		
		String pattern = "to_char({0}, ''{1}'')";
		return MessageFormat.format(pattern, dbDateFunc, javaDateFormat);
	}

}

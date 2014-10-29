package org.joy.core.persistence.jdbc.support.db;

/**
 * 日期格式化器
 * 
 * @since 1.0.0
 * @author Kevice
 * @time 2013年11月24日 上午11:37:56
 */
public interface IDateFormatter {
	
	/**
	 * 格式化
	 * 
	 * @param dbDateFunc 数据库日期函数
	 * @param javaDateFormat java日期格式化串
	 * @return 格式化后的日期串
	 * @since 1.0.0
	 * @author Kevice
	 * @time 2013年11月24日 上午11:40:17
	 */
	String format(String dbDateFunc, String javaDateFormat);

}

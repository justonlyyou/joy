package org.joy.core.persistence.jdbc.support.db;

/**
 * 
 * @since 1.0.0
 * @author Kevice
 * @time 2013年11月24日 上午11:37:56
 */
public interface IDateFormatter {
	
	/**
	 * 
	 * 
	 * @param dbDateFunc
	 * @param javaDateFormat
	 * @return
	 * @since 1.0.0
	 * @author Kevice
	 * @time 2013年11月24日 上午11:40:17
	 */
	String format(String dbDateFunc, String javaDateFormat);

}

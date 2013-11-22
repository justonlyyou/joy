package com.kvc.joy.plugin.monitor.jdbc;

import java.util.List;

/**
 * 预编译sql语句转化器
 * 
 */
public interface IPreparedSqlConvertor {
	/**
	 * 设置预编译参数
	 * 
	 * @param i
	 * @param param
	 */
	void setParam(int i, Object param);

	/**
	 * 转化预编译sql
	 * 
	 * @return
	 */
	String convert();

	/**
	 * 获取参数
	 * 
	 * @return
	 */
	List<String> getParams();

	/**
	 * 获取预编译sql
	 * 
	 * @return
	 */
	String getSql();
}

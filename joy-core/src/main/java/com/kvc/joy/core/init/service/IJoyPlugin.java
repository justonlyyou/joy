package com.kvc.joy.core.init.service;

/**
 * 
 * @author 唐玮琳
 * @time 2013-2-3 下午4:10:27
 */
public interface IJoyPlugin {
	
	/**
	 * 获取数据库更新脚本文件前缀
	 * 
	 * @return
	 * @since 1.0.0
	 * @author 唐玮琳
	 * @time 2013年11月16日 下午5:58:34
	 */
	String getSqlMigrationPrefix();
	
	/**
	 * 获取持久对象包名(可包含*号通配符)
	 * 
	 * @return
	 * @since 1.0.0
	 * @author 唐玮琳
	 * @time 2013年11月16日 下午6:02:32
	 */
	String getPoPackage();
	
	/**
	 * 
	 * 
	 * @return
	 * @author 唐玮琳
	 * @time 2013-2-3 下午4:11:58
	 */
	String getName();
	
	/**
	 * 获取初始化优先级
	 * 
	 * @return
	 * @author 唐玮琳
	 * @time 2013-2-14 下午8:11:09
	 */
	int getInitPriority();
	
	/**
	 * 
	 * 
	 * @author 唐玮琳
	 * @time 2013-2-3 下午4:12:01
	 */
	void startup();
	
	/**
	 * 
	 * 
	 * @author 唐玮琳
	 * @time 2013-2-3 下午11:13:43
	 */
	void destroy();
	
	/**
	 * 
	 * 
	 * @return
	 * @author 唐玮琳
	 * @time 2013-2-4 下午11:34:05
	 */
	boolean isEnabled();
	
	/**
	 * 
	 * 
	 * @return
	 * @since 1.0.0
	 * @author 唐玮琳
	 * @time 2013年11月17日 上午12:33:33
	 */
	String getCtxConfLocation();
	
}

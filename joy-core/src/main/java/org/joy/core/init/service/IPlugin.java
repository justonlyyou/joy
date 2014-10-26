package org.joy.core.init.service;

/**
 * 插件接口
 * 
 * @author Kevice
 * @time 2013-2-3 下午4:10:27
 * @since 1.0.0
 */
public interface IPlugin {
	
	/**
	 * 返回数据库更新脚本文件前缀
	 * 
	 * @return
	 * @since 1.0.0
	 * @author Kevice
	 * @time 2013年11月16日 下午5:58:34
	 */
	String getSqlMigrationPrefix();
	
	/**
	 * 返回持久对象包名(可包含*号通配符)
	 * 
	 * @return
	 * @since 1.0.0
	 * @author Kevice
	 * @time 2013年11月16日 下午6:02:32
	 */
	String getPoPackage();
	
	/**
	 * 返回插件名称
	 * 
	 * @return
	 * @author Kevice
	 * @time 2013-2-3 下午4:11:58
	 */
	String getName();
	
	/**
	 * 返回初始化优先级
	 * 
	 * @return
	 * @author Kevice
	 * @time 2013-2-14 下午8:11:09
	 */
	int getInitPriority();
	
	/**
	 * 启动后执行的操作
	 * 
	 * @author Kevice
	 * @time 2013-2-3 下午4:12:01
	 */
	void startup();
	
	/**
	 * 销毁时执行的操作
	 * 
	 * @author Kevice
	 * @time 2013-2-3 下午11:13:43
	 */
	void destroy();
	
	/**
	 * 插件是否启用
	 * 
	 * @return
	 * @author Kevice
	 * @time 2013-2-4 下午11:34:05
	 */
	boolean isEnabled();
	
	/**
	 * 返回插件的spring配置文件的位置
	 * 
	 * @return
	 * @since 1.0.0
	 * @author Kevice
	 * @time 2013年11月17日 上午12:33:33
	 */
	String getCtxConfLocation();
	
}

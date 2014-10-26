package org.joy.core.init.service;

/**
 * 初始化接口
 * 
 * @author Kevice
 * @time 2012-12-29 上午12:38:02
 * @since 1.0.0
 */
public interface IInitService {

	/**
	 * 获取初始化服务的名称
	 * 
	 * @return
	 * @author Kevice
	 * @time 2013-2-3 下午4:19:04
	 */
	String getName();
	
	/**
	 * 执行初始化操作
	 * 
	 * @author Kevice
	 * @time 2012-12-29 上午12:38:20
	 */
	void init();

    /**
     * 获取初始化优先级，joy平台的服务优先级总是比应用系统的高
     *
     * @return
     * @author Kevice
     * @time 2014-10-26 下午23:15:09
     */
    int getInitPriority();
	
}

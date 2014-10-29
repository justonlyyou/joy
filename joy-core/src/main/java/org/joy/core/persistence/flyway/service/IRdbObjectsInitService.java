package org.joy.core.persistence.flyway.service;

import com.googlecode.flyway.core.Flyway;

/**
 * 关系型数据库对象初始化服务接口，利用Flyway对数据库脚本进行初始化或升级
 * 
 * @since 1.0.0
 * @author Kevice
 * @time 2013年11月13日 下午8:32:22
 */
public interface IRdbObjectsInitService {
	
	/**
	 * 创建Flyway对象
	 * 
	 * @return
	 * @since 1.0.0
	 * @author Kevice
	 * @time 2013年11月16日 下午10:21:01
	 */
	Flyway createFlyway();
	
	/**
	 * 利用Flyway对数据库脚本进行初始化或升级
	 * 
	 * @since 1.0.0
	 * @author Kevice
	 * @time 2013年11月13日 下午8:32:39
	 */
	void migrate(Flyway flyway);

}

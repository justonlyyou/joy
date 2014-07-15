package org.joy.core.persistence.flyway.service;

import com.googlecode.flyway.core.Flyway;

/**
 * 
 * @since 1.0.0
 * @author Kevice
 * @time 2013年11月13日 下午8:32:22
 */
public interface IRdbObjectsInitService {
	
	/**
	 * 
	 * 
	 * @return
	 * @since 1.0.0
	 * @author Kevice
	 * @time 2013年11月16日 下午10:21:01
	 */
	Flyway createFlyway();
	
	/**
	 * 
	 * 
	 * @since 1.0.0
	 * @author Kevice
	 * @time 2013年11月13日 下午8:32:39
	 */
	void migrate(Flyway flyway);

}

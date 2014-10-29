package org.joy.commons.support;

import java.io.Serializable;

/**
 * 命令接口
 * 
 * @since 1.0.0
 * @author Kevice
 * @time 2011-12-1 下午8:39:30
 */
public interface ICommand extends Serializable {
	
	/**
	 * 执行命令
     * @since 1.0.0
     * @author Kevice
     * @time 2011-12-1 下午8:39:30
	 */
	void execute();
}

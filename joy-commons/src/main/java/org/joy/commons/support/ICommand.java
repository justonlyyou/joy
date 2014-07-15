package org.joy.commons.support;

import java.io.Serializable;

/**
 * 命令接口
 * 
 * @since 1.0.0
 * @author Kevice
 */
public interface ICommand extends Serializable {
	
	/**
	 * 执行命令
	 */
	void execute();
}

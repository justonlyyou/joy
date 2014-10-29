package org.joy.commons.bean;

import java.io.Serializable;

/**
 * 持久化实体对象接口
 * 
 * @since 1.0.0
 * @author Kevice
 * @time 2011-12-3 下午10:58:36
 */
public interface IEntity<T> extends Serializable {
	
	/**
	 * 取得主键
     * @return 主键值
     * @since 1.0.0
     * @author Kevice
     * @time 2011-12-3 下午10:58:36
	 */
	T getId();
	
	/**
	 * 设置主键
     * @since 1.0.0
     * @author Kevice
     * @time 2011-12-3 下午10:58:36
	 */
	void setId(T id);
	
}

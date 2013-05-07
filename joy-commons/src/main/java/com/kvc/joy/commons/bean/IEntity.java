package com.kvc.joy.commons.bean;

import java.io.Serializable;

/**
 * 持久化实体对象接口
 * 
 * @since 1.0.0
 * @author <b>唐玮琳</b>
 */
public interface IEntity<T> extends Serializable {
	
	/**
	 * 取得主键
	 */
	T getId();
	
	/**
	 * 设置主键
	 */
	void setId(T id);
	
}

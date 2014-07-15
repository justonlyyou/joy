package org.joy.commons.tree;

import java.io.Serializable;

/**
 * 列表转为树结构的约束的接口
 * 
 * @since 1.0.0
 * @author Kevice
 * @time 2012-5-5 下午10:03:48
 */
public interface IListToTreeRestrict<T> extends Serializable {

	/**
	 * 获取当前结点的id
	 * 
	 * @return 当前结点的id
	 * @author Kevice
	 * @time 2012-5-5 下午10:03:48
	 */
	T getId();

	/**
	 * 获取父结点的id
	 * 
	 * @return 父结点的id
	 * @author Kevice
	 * @time 2012-5-5 下午10:03:48
	 */
	T getParentId();
}

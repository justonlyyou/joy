package com.kvc.joy.commons.support;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * 
 * <p>
 * 分组执行器
 * 应用场景如：Hibernate限定超过1000个元素的in查询，这时可以用分组执行器进行分组执行
 * </p>
 * 
 * <pre>
 * 	Collection<Object> elems = ...;
 * 	int groupSize = ...;
 *  new GroupExecutor<Object>(elems, groupSize) {
 *     
 *     protected void groupExecute(List<Object> subList) {
 *        ... // 如分组执行更新
 *     }
 *     
 *  }.execute();
 * </pre>
 * 
 * @since 1.0.0
 * @author <b>唐玮琳</b>
 */
public abstract class GroupExecutor<E> {

	private int groupSize; // 每组大小
	private Collection<E> elems;

	/**
	 * 构造器，分组大小默认为1000
	 * 
	 * @param elems 所有要分组的元素集合
	 */
	public GroupExecutor(Collection<E> elems) {
		this(elems, 1000);
	}

	/**
	 * 构造器
	 * 
	 * @param elems 所有要分组的元素集合
	 * @param groupSize 分组大小
	 */
	public GroupExecutor(Collection<E> elems, int groupSize) {
		this.elems = elems;
		this.groupSize = groupSize;
	}

	/**
	 * 执行操作
	 */
	public void execute() {
		int size = elems.size();
		int groupCount = (int) Math.ceil(size / (double) groupSize);
		List<E> elemList = new ArrayList<E>(elems);
		for (int index = 0; index < groupCount; index++) {
			int from = index * groupSize;
			int end = (index == groupCount - 1 ? elemList.size() : from + groupSize);
			List<E> subList = elemList.subList(from, end);
			groupExecute(subList);
		}
	}

	/**
	 * 分组执行
	 * 
	 * @param subList 分组元素列表
	 */
	protected abstract void groupExecute(List<E> subList);

}

package com.kvc.joy.commons.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * 通用树结点
 * 
 * @since 1.0.0
 * @author Kevice
 * @time 2012-5-1 下午9:24:05
 */
public class TreeNode<T> implements Serializable {

	private static final long serialVersionUID = -7315465823402069017L;

	private T object; // 当前结点对象
	private T parentObject; // 父结点对象
	private List<TreeNode<T>> children = new ArrayList<TreeNode<T>>(0); // 孩子结点集合

	/**
	 * 空构造器
	 */
	public TreeNode() {
	}

	/**
	 * 构造器
	 * 
	 * @param object 当前结点对象
	 */
	public TreeNode(T object) {
		this.object = object;
	}

	/**
	 * 构造器
	 * 
	 * @param object 当前结点对象
	 * @param parentObject 父结点对象
	 */
	public TreeNode(T object, T parentObject) {
		this.object = object;
		this.parentObject = parentObject;
	}

	public T getObject() {
		return object;
	}

	public void setObject(T object) {
		this.object = object;
	}

	public T getParentObject() {
		return parentObject;
	}

	public void setParentObject(T parentObject) {
		this.parentObject = parentObject;
	}

	public List<TreeNode<T>> getChildren() {
		return children;
	}

	public void setChildren(List<TreeNode<T>> children) {
		this.children = children;
	}

	public boolean isLeaf() {
		return children.isEmpty();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((object == null) ? 0 : object.hashCode());
		return result;
	}

	@Override
	@SuppressWarnings("unchecked")
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		TreeNode<T> other = (TreeNode<T>) obj;
		if (object == null) {
			if (other.object != null) {
				return false;
			}
		} else if (!object.equals(other.object)) {
			return false;
		}
		return true;
	}

}

package org.joy.commons.bean;

import java.io.Serializable;
import java.util.Map;

/**
 * 由两个元素组成的对象，适用于包装两个不同类型的元素，用来代替Object[]
 * 
 * @since 1.0.0
 * @author Kevice
 * @time 2011-12-1 下午9:59:30
 */
public class Pair<L, R> implements Map.Entry<L, R>, Serializable {

	private static final long serialVersionUID = 310389695718804619L;

	private L left; // 左边元素(第一个元素)
	private R right; // 右边元素(第二个元素)

	public Pair() {
	}

	public Pair(L left, R right) {
		this.left = left;
		this.right = right;

	}

	public L getLeft() {
		return left;
	}

	public void setLeft(L left) {
		this.left = left;
	}

	public R getRight() {
		return right;
	}

	@Override
	public String toString() {
		return "(" + left + ", " + right + ")";
	}

	public void setRight(R right) {
		this.right = right;
	}

	public void putToMap(Map<L, R> map) {
		map.put(left, right);
	}

	public L getKey() {
		return left;
	}

	public R getValue() {
		return right;
	}

	public R setValue(R value) {
		setRight(value);
		return value;
	}
}

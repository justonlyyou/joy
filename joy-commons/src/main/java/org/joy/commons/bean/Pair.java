package org.joy.commons.bean;

import java.io.Serializable;
import java.util.Map;

/**
 * 由两个元素组成的对象
 * 
 * @since 1.0.0
 * @author <b>Kevice</b>
 */
public class Pair<L, R> implements Map.Entry<L, R>, Serializable {

	private static final long serialVersionUID = 310389695718804619L;

	private L left;
	private R right;

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

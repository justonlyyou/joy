package com.kvc.joy.commons.bean;

import java.io.Serializable;
import java.util.Map;

/**
 * 由两个元素组成的对象
 * 
 * @since 1.0.0
 * @author <b>唐玮琳</b>
 */
public class Pair<F, S> implements Map.Entry<F, S>, Serializable {

	private static final long serialVersionUID = 310389695718804619L;

	private F first;
	private S second;

	public Pair() {
	}

	public Pair(F first, S second) {
		this.first = first;
		this.second = second;

	}

	public F getFirst() {
		return first;
	}

	public void setFirst(F first) {
		this.first = first;
	}

	public S getSecond() {
		return second;
	}

	@Override
	public String toString() {
		return "(" + first + ", " + second + ")";
	}

	public void setSecond(S second) {
		this.second = second;
	}

	public void putToMap(Map<F, S> map) {
		map.put(first, second);
	}

	public F getKey() {
		return first;
	}

	public S getValue() {
		return second;
	}

	public S setValue(S value) {
		setSecond(value);
		return value;
	}
}

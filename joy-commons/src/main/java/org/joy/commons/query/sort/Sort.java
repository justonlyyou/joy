package org.joy.commons.query.sort;

import org.joy.commons.collections.CollectionTool;
import org.joy.commons.exception.SystemException;
import org.joy.commons.collections.CollectionTool;
import org.joy.commons.exception.SystemException;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

/**
 * 
 * @since 1.0.0
 * @author Kevice
 * @time 2013年11月20日 下午11:32:05
 */
public class Sort implements Iterable<Order>, Serializable {

	private static final long serialVersionUID = 5737186511679863905L;

	private final List<Order> orders;

	public Sort(Order... orders) {
		this(Arrays.asList(orders));
	}

	public Sort(List<Order> orders) {
		if (CollectionTool.isEmpty(orders)) {
			throw new SystemException("必须至少提供一个排序规则！");
		}
		this.orders = orders;
	}

	public Sort(String... properties) {
		this(Direction.ASC, properties);
	}

	public Sort(Direction direction, String... properties) {
		this(direction, properties == null ? new ArrayList<String>() : Arrays.asList(properties));
	}

	public Sort(Direction direction, List<String> properties) {
		if (properties == null || properties.isEmpty()) {
			throw new IllegalArgumentException("You have to provide at least one property to sort by!");
		}

		this.orders = new ArrayList<Order>(properties.size());

		for (String property : properties) {
			this.orders.add(new Order(property, direction));
		}
	}

	public Sort and(Sort sort) {
		if (sort == null) {
			return this;
		}

		ArrayList<Order> these = new ArrayList<Order>(this.orders);

		for (Order order : sort) {
			these.add(order);
		}

		return new Sort(these);
	}

	public Order getOrderFor(String property) {
		for (Order order : this) {
			if (order.getProperty().equals(property)) {
				return order;
			}
		}

		return null;
	}

	public Iterator<Order> iterator() {
		return this.orders.iterator();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof Sort)) {
			return false;
		}

		Sort that = (Sort) obj;

		return this.orders.equals(that.orders);
	}

	@Override
	public int hashCode() {
		int result = 17;
		result = 31 * result + orders.hashCode();
		return result;
	}

	@Override
	public String toString() {
		return orders.toString();
	}

}

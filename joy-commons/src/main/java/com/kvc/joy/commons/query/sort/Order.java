package com.kvc.joy.commons.query.sort;

import java.io.Serializable;

/**
 * 
 * @since 1.0.0
 * @author Kevice
 * @time 2013年11月20日 下午11:16:17
 */
public class Order implements Serializable {

	private static final long serialVersionUID = 1522511010900998987L;

	private String property;
	private Direction direction;

	public Order() {
	}

	public Order(String property, Direction direction) {
		this.property = property;
		this.direction = direction == null ? Direction.ASC : direction;
	}

	public Order(String property) {
		this(property, Direction.ASC);
	}
	
	public static Order asc(String property) {
		return new Order(property); 
	}
	
	public static Order desc(String property) {
		return new Order(property, Direction.DESC); 
	}

	public boolean isAscending() {
		return this.direction.equals(Direction.ASC);
	}

	@Override
	public String toString() {
		return String.format("%s: %s", property, direction);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((direction == null) ? 0 : direction.hashCode());
		result = prime * result + ((property == null) ? 0 : property.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Order other = (Order) obj;
		if (direction != other.direction)
			return false;
		if (property == null) {
			if (other.property != null)
				return false;
		} else if (!property.equals(other.property))
			return false;
		return true;
	}

	public String getProperty() {
		return property;
	}

	public void setProperty(String property) {
		this.property = property;
	}

	public Direction getDirection() {
		return direction;
	}

	public void setDirection(Direction direction) {
		this.direction = direction;
	}

}

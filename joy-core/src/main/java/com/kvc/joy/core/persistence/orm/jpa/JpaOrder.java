package com.kvc.joy.core.persistence.orm.jpa;

import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Order;
import javax.persistence.metamodel.SingularAttribute;

import org.springframework.data.domain.Sort;

public class JpaOrder extends Sort.Order implements javax.persistence.criteria.Order {

	private boolean ascending;
	private SingularAttribute<?, ?> attribute;
	private String propertyName;

	public String toString() {
		return propertyName + ' ' + (ascending ? "asc" : "desc");
	}

	protected JpaOrder(SingularAttribute<?, ?> property, boolean ascending) {
		this(property.getName(), ascending);
		this.attribute = property;
	}

	protected JpaOrder(String propertyName, boolean ascending) {
		super(ascending ? Sort.Direction.ASC : Sort.Direction.DESC, propertyName);
		this.propertyName = propertyName;
		this.ascending = ascending;
	}

	public boolean isAscending() {
		return ascending;
	}

	public SingularAttribute<?, ?> getAttribute() {
		return attribute;
	}

	public String getPropertyName() {
		return propertyName;
	}

	public static Sort.Order asc(SingularAttribute<?, ?> property) {
		return new JpaOrder(property, true);
	}

	public static Sort.Order desc(SingularAttribute<?, ?> property) {
		return new JpaOrder(property, false);
	}

	public static Sort.Order asc(String propertyName) {
		return new JpaOrder(propertyName, true);
	}

	public static Sort.Order desc(String propertyName) {
		return new JpaOrder(propertyName, false);
	}

	public Expression<?> getExpression() {
		return (Expression<?>) attribute;
	}

	public Order reverse() {
		return new JpaOrder(propertyName, !ascending);
	}

}

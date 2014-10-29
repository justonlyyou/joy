package org.joy.core.persistence.orm.jpa;

import org.joy.commons.query.sort.Direction;

import javax.persistence.criteria.Expression;
import javax.persistence.metamodel.SingularAttribute;

/**
 * JPA排序对象
 *
 * @author Kevice
 * @time 2012-6-26 下午10:36:28
 * @since 1.0.0
 */
public class JpaOrder extends org.joy.commons.query.sort.Order {

	private final boolean ascending; // 是否升序
	private SingularAttribute<?, ?> attribute; // 要排序的jpa静态元模型属性
	private final String propertyName; // 要排序的属性名

	public String toString() {
		return propertyName + ' ' + (ascending ? "asc" : "desc");
	}

	protected JpaOrder(SingularAttribute<?, ?> property, boolean ascending) {
		this(property.getName(), ascending);
		this.attribute = property;
	}

	protected JpaOrder(String propertyName, boolean ascending) {
		super(propertyName, ascending ? Direction.ASC : Direction.DESC);
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

	public static org.joy.commons.query.sort.Order asc(SingularAttribute<?, ?> property) {
		return new JpaOrder(property, true);
	}

	public static org.joy.commons.query.sort.Order desc(SingularAttribute<?, ?> property) {
		return new JpaOrder(property, false);
	}

	public static org.joy.commons.query.sort.Order asc(String propertyName) {
		return new JpaOrder(propertyName, true);
	}

	public static org.joy.commons.query.sort.Order desc(String propertyName) {
		return new JpaOrder(propertyName, false);
	}

	public Expression<?> getExpression() {
		return (Expression<?>) attribute;
	}
//
//	public Order reverse() {
//		return new JpaOrder(propertyName, !ascending);
//	}

}

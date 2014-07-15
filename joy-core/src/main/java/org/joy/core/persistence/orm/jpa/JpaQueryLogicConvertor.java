package org.joy.core.persistence.orm.jpa;

import org.joy.commons.query.QueryLogic;
import org.joy.commons.query.QueryLogicOperator;
import org.joy.commons.query.QueryLogics;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

/**
 * 
 * @author Kevice
 * @time 2012-6-20 上午12:53:41
 */
public class JpaQueryLogicConvertor {

	public static <T> Predicate[] convert(CriteriaBuilder cb, Root<T> root, QueryLogics queryLogics) {
		if (queryLogics != null) {
			List<QueryLogic> conditions = queryLogics.getConditions();
			List<Predicate> predicates = new ArrayList<Predicate>(conditions.size());
			for (QueryLogic lgc : conditions) {
				Predicate predicate = convert(lgc.getProperty(), lgc.getValue(), lgc.getOperator(), cb, root);
				if (predicate != null) {
					predicates.add(predicate);
				}
			}
			return predicates.toArray(new Predicate[predicates.size()]);
		} else {
			return new Predicate[0];
		}
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	private static Predicate convert(String property, Object value, QueryLogicOperator operator, CriteriaBuilder cb,
			Root root) {
		if (value == null || "".equals(value)) {
			return null;
		}
		if (operator.getCode().contains("LIKE") && value instanceof String) {
			value = ((String) value).replaceAll("%", "\\\\%").replaceAll("_", "\\\\_");
		}
		Path path = root.get(property);
		switch (operator) {
		case EQ:
			return cb.equal(path, value);
		case IEQ:
			if (value instanceof String) {
				return cb.equal(cb.upper(path), ((String) value).toUpperCase());
			}
			return cb.equal(path, value);
		case NE:
		case LG:
			return cb.notEqual(path, value);
		case GE:
			return cb.greaterThanOrEqualTo(path, (Comparable) value);
		case LE:
			return cb.lessThanOrEqualTo(path, (Comparable) value);
		case GT:
			return cb.greaterThan(path, (Comparable) value);
		case LT:
			return cb.lessThan(path, (Comparable) value);
		case EQ_P:
			return cb.equal(path, root.get((String) value));
		case NE_P:
		case LG_P:
			return cb.notEqual(path, root.get((String) value));
		case GE_P:
			return cb.ge(path, root.get((String) value));
		case LE_P:
			return cb.le(path, root.get((String) value));
		case GT_P:
			return cb.gt(path, root.get((String) value));
		case LT_P:
			return cb.lt(path, root.get((String) value));
		case LIKE:
			return cb.like(path, "%" + (String) value + "%");
		case LIKE_S:
			return cb.like(path, (String) value + "%");
		case LIKE_E:
			return cb.like(path, "%" + (String) value);
		case ILIKE:
			return cb.like(cb.upper(path), "%" + ((String) value).toUpperCase() + "%");
		case ILIKE_S:
			return cb.like(cb.upper(path), ((String) value).toUpperCase() + "%");
		case ILIKE_E:
			return cb.like(cb.upper(path), "%" + ((String) value).toUpperCase());
		case IN:
			if (value instanceof String) {
				Object[] values = ((String) value).split(",");
				return cb.in(path.in(values));
			} else {
				return cb.in(path.in(value));
			}
		case IS_NULL:
			return cb.isNull(path);
		case IS_NOT_NULL:
			return cb.isNotNull(path);
		case IS_EMPTY:
			return cb.isEmpty(path);
		case IS_NOT_EMPTY:
			return cb.isNotEmpty(path);
		default:
			return null;
		}
	}
	
}

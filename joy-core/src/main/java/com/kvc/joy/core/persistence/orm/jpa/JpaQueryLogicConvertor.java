package com.kvc.joy.core.persistence.orm.jpa;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import com.kvc.joy.core.persistence.support.QueryLogic;
import com.kvc.joy.core.persistence.support.QueryLogicOperator;
import com.kvc.joy.core.persistence.support.QueryLogics;

/**
 * 
 * @author 唐玮琳
 * @time 2012-6-20 上午12:53:41
 */
public class JpaQueryLogicConvertor {

	public static <T> Predicate[] convert(CriteriaBuilder cb, Root<T> root, QueryLogics queryLogics) {
		if (queryLogics != null) {
			Map<String, QueryLogic> conditions = queryLogics.getConditions();
			List<Predicate> predicates = new ArrayList<Predicate>(conditions.size());
			for (Entry<String, QueryLogic> entry : conditions.entrySet()) {
				String property = entry.getKey();
				QueryLogic queryLogic = entry.getValue();
				QueryLogicOperator operator = queryLogic.getOperator();
				Object fieldValue = queryLogic.getFieldValue();
				Predicate predicate = convert(property, fieldValue, operator, cb, root);
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
		switch (operator) {
		case EQ:
			return cb.equal(root.get(property), value);
		case IEQ:
			if (value instanceof String) {
				return cb.equal(cb.upper(root.get(property)), ((String) value).toUpperCase());
			}
			return cb.equal(root.get(property), value);
		case NE:
		case LG:
			return cb.notEqual(root.get(property), value);
		case GE:
			return cb.ge(root.get(property), (Number) value);
		case LE:
			return cb.le(root.get(property), (Number) value);
		case GT:
			return cb.gt(root.get(property), (Number) value);
		case LT:
			return cb.lt(root.get(property), (Number) value);
		case EQ_P:
			return cb.equal(root.get(property), root.get((String) value));
		case NE_P:
		case LG_P:
			return cb.notEqual(root.get(property), root.get((String) value));
		case GE_P:
			return cb.ge(root.get(property), root.get((String) value));
		case LE_P:
			return cb.le(root.get(property), root.get((String) value));
		case GT_P:
			return cb.gt(root.get(property), root.get((String) value));
		case LT_P:
			return cb.lt(root.get(property), root.get((String) value));
		case LIKE:
			return cb.like(root.get(property), "%" + (String) value + "%");
		case LIKE_S:
			return cb.like(root.get(property), (String) value + "%");
		case LIKE_E:
			return cb.like(root.get(property), "%" + (String) value);
		case ILIKE:
			return cb.like(cb.upper(root.get(property)), "%" + ((String) value).toUpperCase() + "%");
		case ILIKE_S:
			return cb.like(cb.upper(root.get(property)), ((String) value).toUpperCase() + "%");
		case ILIKE_E:
			return cb.like(cb.upper(root.get(property)), "%" + ((String) value).toUpperCase());
		 case IN:
			if(value instanceof String) {
				Object[] values = ((String)value).split(",");
				return cb.in(root.get(property).in(values));
			} else {
				return cb.in(root.get(property).in(value));
			}
		 case IS_NULL:
			 return cb.isNull(root.get(property));
		 case IS_NOT_NULL:
			 return cb.isNotNull(root.get(property));
		 case IS_EMPTY:
			 return cb.isEmpty(root.get(property));
		 case IS_NOT_EMPTY:
			 return cb.isNotEmpty(root.get(property));
		 default:
			 return null;
		}
	}
	
	public static void main(String[] args) {
		System.out.println("a_".replaceAll("_", "\\\\_"));
	}
}

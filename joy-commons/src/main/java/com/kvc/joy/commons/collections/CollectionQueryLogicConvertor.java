package com.kvc.joy.commons.collections;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import com.kvc.joy.commons.lang.ArrayTool;
import com.kvc.joy.commons.lang.string.StringTool;
import com.kvc.joy.commons.query.QueryLogicOperator;

/**
 * 
 * @since 1.0.0
 * @author 唐玮琳
 * @time 2013年11月22日 下午10:18:52
 */
public class CollectionQueryLogicConvertor {
	
	private static final String DEFAULT_LOGIC = "1=1";

	private CollectionQueryLogicConvertor() {
	}

	public static String convert(String property, Object value, QueryLogicOperator operator) {
		if (StringTool.isEmpty(property) || operator == null) {
			return DEFAULT_LOGIC;
		}
		if (operator.getCode().contains("LIKE") && value instanceof String) {
			value = ((String) value).replaceAll("%", "\\\\%").replaceAll("_", "\\\\_");
		}
		switch (operator) {
		case EQ:
			return property + " = " + quoteString(value);
		case IEQ:
			 return property + " $LIKE " + quoteString(value);
		case NE:
		case LG:
			return property + " != " + quoteString(value);
		case GE:
			return property + " >= " + quoteString(value);
		case LE:
			return property + " <= " + quoteString(value);
		case GT:
			return property + " > " + quoteString(value);
		case LT:
			return property + " < " + quoteString(value);
		case EQ_P:
			return property + " < " + value;
		case NE_P:
		case LG_P:
			return property + " != " + value;
		case GE_P:
			return property + " >= " + value;
		case LE_P:
			return property + " <= " + value;
		case GT_P:
			return property + " > " + value;
		case LT_P:
			return property + " < " + value;
		case LIKE:
			return property + " LIKE " + "'%" + value + "%'";
		case LIKE_S:
			return property + " LIKE " + "'" + value + "%'";
		case LIKE_E:
			return property + " LIKE " + "'%" +  value + "'";
		case ILIKE:
			return property + " $LIKE " + "'%" +  value + "%'";
		case ILIKE_S:
			return property + " $LIKE " + "'" +  value + "%'";
		case ILIKE_E:
			return property + " $LIKE " + "'%" + value + "'";
		case IN:
			Collection<?> values = null;
			if (value instanceof Collection<?>) {
				values = quoteStrings((Collection<?>) value);
			} else if (ArrayTool.isArray(value)) {
				values = quoteStrings(value);
			}
			if (values != null) {
				String vals = StringTool.join(values.toArray(), ",");
				return property + " IN(" + vals + ")";
			} else {
				return DEFAULT_LOGIC;
			}
		case IS_NULL:
			return property + " IS NULL";
		case IS_NOT_NULL:
			return property + " IS NOT NULL";
		case IS_EMPTY:
			return property + " = ''";
		case IS_NOT_EMPTY:
			return property + " != ''";
		default:
			return DEFAULT_LOGIC;
		}
	}

	@SuppressWarnings("unchecked")
	public static <T> T quoteString(T value) {
		if (value instanceof String) {
			return (T) ("'" + value + "'");
		}
		return value;
	}

	public static <E> Collection<E> quoteStrings(E... values) {
		if (values == null) {
			return new ArrayList<E>(0);
		}
		List<E> list = Arrays.asList(values);
		return quoteStrings(list);
	}

	public static <E> Collection<E> quoteStrings(Collection<E> values) {
		if (CollectionTool.isEmpty(values) == false) {
			E e = values.iterator().next();
			if (e instanceof String) {
				List<E> list = new ArrayList<E>(values.size());
				for (E value : values) {
					list.add(quoteString(value));
				}
				return list;
			}
		}
		return values;
	}

}

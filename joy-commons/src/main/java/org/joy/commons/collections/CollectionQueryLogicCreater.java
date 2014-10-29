package org.joy.commons.collections;

import org.joy.commons.lang.ArrayTool;
import org.joy.commons.lang.string.StringTool;
import org.joy.commons.query.QueryLogicOperator;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

/**
 * 集合查询逻辑表达式创建器
 * 
 * @since 1.0.0
 * @author Kevice
 * @time 2013年11月22日 下午10:18:52
 */
public class CollectionQueryLogicCreater {
	
	private static final String DEFAULT_LOGIC = "1=1";

	private CollectionQueryLogicCreater() {
	}

    /**
     * 拼接条件表达式
     *
     * @param property 属性名
     * @param value 属性值
     * @param operator 查询逻辑操作符枚举
     * @return 条件表达式
     * @since 1.0.0
     * @author Kevice
     * @time 2013年11月22日 下午10:18:52
     */
	public static String create(String property, Object value, QueryLogicOperator operator) {
		if (StringTool.isEmpty(property) || operator == null) {
			return DEFAULT_LOGIC;
		}
		if (value == null || "".equals(value)) {
			return "";
		}
//		if (operator.getCode().contains("LIKE") && value instanceof String) {
//			value = ((String) value).replaceAll("%", "\\\\%").replaceAll("_", "\\\\_");
//		}
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

    /**
     * 对字符串的值添加单引号
     *
     * @param value 值
     * @param <T> 值类型
     * @return 处理后的值
     * @since 1.0.0
     * @author Kevice
     * @time 2013年11月22日 下午10:18:52
     */
	@SuppressWarnings("unchecked")
	public static <T> T quoteString(T value) {
		if (value instanceof String) {
			return (T) ("'" + value + "'");
		}
		return value;
	}

    /**
     * 对数组里的字符串值添加单引号
     *
     * @param values 值数组
     * @param <E> 值类型
     * @return 处理后的值的集合
     * @since 1.0.0
     * @author Kevice
     * @time 2013年11月22日 下午10:18:52
     */
	public static <E> Collection<E> quoteStrings(E... values) {
		if (values == null) {
			return new ArrayList<E>(0);
		}
		List<E> list = Arrays.asList(values);
		return quoteStrings(list);
	}

    /**
     * 对集合里的字符串值添加单引号
     *
     * @param values 值集合
     * @param <E> 值类型
     * @return 处理后的值的集合
     * @since 1.0.0
     * @author Kevice
     * @time 2013年11月22日 下午10:18:52
     */
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

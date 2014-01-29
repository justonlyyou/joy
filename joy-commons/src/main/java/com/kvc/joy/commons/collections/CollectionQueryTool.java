package com.kvc.joy.commons.collections;

import com.kvc.joy.commons.exception.SystemException;
import com.kvc.joy.commons.lang.ArrayTool;
import com.kvc.joy.commons.lang.string.StringTool;
import com.kvc.joy.commons.query.Paging;
import com.kvc.joy.commons.query.QueryLogic;
import com.kvc.joy.commons.query.QueryLogics;
import com.kvc.joy.commons.query.sort.Order;
import org.josql.Query;
import org.josql.QueryExecutionException;
import org.josql.QueryParseException;

import java.text.MessageFormat;
import java.util.*;
import java.util.Map.Entry;

/**
 * 
 * @since 1.0.0
 * @author 唐玮琳
 * @time 2013年11月22日 上午10:08:11
 */
public class CollectionQueryTool {

	private CollectionQueryTool() {
	}

	/**
	 * 
	 * 
	 * @param beans
	 * @param orders
	 * @return
	 * @since 1.0.0
	 * @author 唐玮琳
	 * @time 2013年11月22日 下午7:51:14
	 */
	public static <T> List<T> order(Collection<T> beans, Order... orders) {
		if (CollectionTool.isEmpty(beans)) {
			return new ArrayList<T>(0);
		}
		if (ArrayTool.isEmpty(orders)) {
			return new ArrayList<T>(beans);
		}

		String sqlPattern = "SELECT * FROM {0} ORDER BY {1}";
		String className = beans.iterator().next().getClass().getName();
		Query q = parse(MessageFormat.format(sqlPattern, className, getOrderStrs(orders)));
		return getResults(q, beans);
	}

	/**
	 * 
	 * 
	 * @param beans
	 * @param property
	 * @param values
	 * @param orders
	 * @return
	 * @since 1.0.0
	 * @author 唐玮琳
	 * @time 2013年11月22日 上午11:48:36
	 */
	public static <T, E> List<T> inQuery(Collection<T> beans, String property, Collection<E> values, Order... orders) {
		if (CollectionTool.isEmpty(beans)) {
			return new ArrayList<T>(0);
		}
		if (StringTool.isBlank(property) || CollectionTool.isEmpty(values)) {
			return order(beans, orders);
		}

		String className = beans.iterator().next().getClass().getName();
		values = CollectionQueryLogicConvertor.quoteStrings(values);
		String valueStrs = StringTool.join(values, ",");
		String sqlPattern = "SELECT * FROM {0} WHERE {1} IN({2})";
		Query q;
		if (ArrayTool.isEmpty(orders)) {
			q = parse(MessageFormat.format(sqlPattern, className, property, valueStrs));
		} else {
			sqlPattern += " ORDER BY {3}";
			q = parse(MessageFormat.format(sqlPattern, className, property, valueStrs, getOrderStrs(orders)));
		}
		return getResults(q, beans);
	}

	/**
	 * 
	 * 
	 * @param beans
	 * @param property
	 * @param value
	 * @param orders
	 * @return
	 * @since 1.0.0
	 * @author 唐玮琳
	 * @time 2013年11月22日 下午7:57:27
	 */
	public static <T> List<T> query(Collection<T> beans, String property, Object value, Order... orders) {
		if (CollectionTool.isEmpty(beans)) {
			return new ArrayList<T>(0);
		}
		if (StringTool.isBlank(property)) {
			return order(beans, orders);
		}
		Map<String, Object> propMap = new HashMap<String, Object>(1);
		propMap.put(property, value);
		return andQuery(beans, propMap, orders);
	}

	/**
	 * 
	 * 
	 * @param beans
	 * @param property
	 * @param orders
	 * @return
	 * @since 1.0.0
	 * @author 唐玮琳
	 * @time 2013年11月29日 下午10:51:02
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static List query(Collection<?> beans, String property, Order... orders) {
		if (CollectionTool.isEmpty(beans)) {
			return new ArrayList(0);
		}
		if (StringTool.isBlank(property)) {
			return order(beans, orders);
		}

		String className = beans.iterator().next().getClass().getName();
		String sqlPattern = "SELECT {0} FROM {1} ";
		Query q;
		if (ArrayTool.isEmpty(orders)) {
			q = parse(MessageFormat.format(sqlPattern, property, className));
		} else {
			sqlPattern += " ORDER BY {2}";
			q = parse(MessageFormat.format(sqlPattern, property, className, getOrderStrs(orders)));
		}
		List<?> results = getResults(q, beans);
		List resultList = new ArrayList(results.size());
		for (Object result : results) {
			resultList.add(((Object[]) result)[0]);
		}
		return resultList;
	}

	/**
	 * 
	 * 
	 * @param beans
	 * @param propertyMap
	 * @param orders
	 * @return
	 * @since 1.0.0
	 * @author 唐玮琳
	 * @time 2013年11月22日 下午7:59:14
	 */
	public static <T> List<T> andQuery(Collection<T> beans, Map<String, Object> propertyMap, Order... orders) {
		return search(true, beans, propertyMap, orders);
	}

	/**
	 * 
	 * 
	 * @param beans
	 * @param propertyMap
	 * @param orders
	 * @return
	 * @since 1.0.0
	 * @author 唐玮琳
	 * @time 2013年11月22日 下午9:18:36
	 */
	public static <T> List<T> orQuery(Collection<T> beans, Map<String, Object> propertyMap, Order... orders) {
		return search(false, beans, propertyMap, orders);
	}

	private static <T> List<T> search(boolean andQuery, Collection<T> beans, Map<String, Object> propertyMap,
			Order... orders) {
		if (CollectionTool.isEmpty(beans)) {
			return new ArrayList<T>(0);
		}
		if (MapTool.isEmpty(propertyMap)) {
			return order(beans, orders);
		}

		String className = beans.iterator().next().getClass().getName();
		String dftConfition = andQuery ? "1=1" : "1=2";
		String sqlPattern = "SELECT * FROM {0} WHERE " + dftConfition + " {1}";
		if (ArrayTool.isNotEmpty(orders)) {
			sqlPattern += " ORDER BY {2}";
		}
		StringBuilder where = new StringBuilder();
		for (Entry<String, Object> entry : propertyMap.entrySet()) {
			String property = entry.getKey();
			if (StringTool.isNotBlank(property)) {
				String logic = andQuery ? " AND " : " OR ";
				where.append(logic).append(property);
				Object value = entry.getValue();
				if (value == null) {
					where.append(" IS NULL");
				} else {
					value = CollectionQueryLogicConvertor.quoteString(value);
					where.append(" = ").append(value);
				}
			}
		}
		Query q = parse(MessageFormat.format(sqlPattern, className, where, getOrderStrs(orders)));
		return getResults(q, beans);
	}

	/**
	 * 
	 * 
	 * @param beans
	 * @param queryLogics
	 * @return
	 * @since 1.0.0
	 * @author 唐玮琳
	 * @time 2013年11月22日 下午11:59:14
	 */
	public static <T> List<T> pagingQuery(Collection<T> beans, QueryLogics queryLogics) {
		if (CollectionTool.isEmpty(beans)) {
			return new ArrayList<T>(0);
		}
		if (queryLogics == null) {
			return order(beans);
		}

		String className = beans.iterator().next().getClass().getName();
		String sqlPattern = "SELECT * FROM {0} WHERE 1=1 {1}";
		List<Order> orders = queryLogics.getOrders();
		if (CollectionTool.isNotEmpty(orders)) {
			sqlPattern += " ORDER BY {2}";
		}
		StringBuilder where = new StringBuilder();
		List<QueryLogic> conditions = queryLogics.getConditions();
		for (QueryLogic lgc : conditions) {
			String logic = CollectionQueryLogicConvertor.convert(lgc.getProperty(), lgc.getValue(), lgc.getOperator());
			if (StringTool.isNotBlank(logic)) {
				where.append(" AND ").append(logic);
			}
		}

		Query q = parse(MessageFormat.format(sqlPattern, className, where, getOrderStrs(orders.toArray(new Order[0]))));
		List<T> resultList = getResults(q, beans);

		Paging paging = queryLogics.getPaging();
		paging.setTotalCount(resultList.size());
		int start = paging.getOffset();
		int pageSize = paging.getPageSize();
		int end = start + pageSize;
		if (end > resultList.size()) {
			end = resultList.size();
		}
		return new ArrayList<T>(resultList.subList(start, end));
	}

	@SuppressWarnings("rawtypes")
	public static <T> List queryBySql(Collection<T> beans, String sql) {
		if (CollectionTool.isEmpty(beans)) {
			return new ArrayList<T>(0);
		}

		Query q = parse(sql);
		try {
			return q.execute(beans).getResults();
		} catch (QueryExecutionException e) {
			throw new SystemException(e);
		}
	}

	private static String getOrderStrs(Order... orders) {
		if (ArrayTool.isEmpty(orders)) {
			return "";
		} else {
			StringBuilder sb = new StringBuilder();
			for (Order order : orders) {
				sb.append(order.getProperty()).append(" ").append(order.getDirection()).append(",");
			}
			return sb.substring(0, sb.length() - 1);
		}
	}

	private static Query parse(String sql) {
		Query q = new Query();
		try {
			q.parse(sql);
		} catch (QueryParseException e) {
			throw new SystemException(e);
		}
		return q;
	}

	@SuppressWarnings("unchecked")
	private static <T> List<T> getResults(Query q, Collection<T> beans) {
		try {
			return q.execute(beans).getResults();
		} catch (QueryExecutionException e) {
			throw new SystemException(e);
		}
	}

}

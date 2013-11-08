package com.kvc.joy.core.persistence.support;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.domain.Sort.Order;

import com.kvc.joy.commons.lang.string.StringTool;

/**
 * 
 * @author 唐玮琳
 * @time 2012-6-19 下午9:45:39
 */
public class QueryLogics {
	
	public static final String KEY_PREFIX_ORDER_BY = "_joy_key__order_by_";
	public static final String KEY_PREFIX_ORDER = "_joy_key__order_value_";
	public static final String KEY_PREFIX_ORDER_DEFAULT = "_joy_key__order_default_";

	private Pageable pageable;
	private Map<String, QueryLogic> conditions = new LinkedHashMap<String, QueryLogic>(0);
	private Map<String, String> orderMap = new HashMap<String, String>(0);
	private Logger logger = LoggerFactory.getLogger(getClass());

	public QueryLogicOperator getOperator(String field) {
		QueryLogic queryLogic = conditions.get(field);
		if (queryLogic != null) {
			return queryLogic.getOperator();
		}
		return null;
	}

	public Object getFieldValue(String field) {
		QueryLogic queryLogic = conditions.get(field);
		if (queryLogic != null) {
			return queryLogic.getFieldValue();
		}
		return null;
	}

	public void addCondition(String fieldName, Object fieldValue, QueryLogicOperator operator) {
		if (StringTool.isBlank(fieldName)) {
			throw new IllegalArgumentException("字段名或属性名不为空！");
		}
		if (operator == null) {
			throw new IllegalArgumentException("查询逻辑操作符不为空！");
		}
		conditions.put(fieldName, new QueryLogic(operator, fieldValue));
	}

	public Pageable getPageable() {
		return pageable;
	}

	public void setPageable(Pageable pageable) {
		this.pageable = pageable;
	}

	public Map<String, QueryLogic> getConditions() {
		return conditions;
	}

	public void setConditions(Map<String, QueryLogic> Conditions) {
		this.conditions = Conditions;
	}

	public List<Order> getOrders() {
		List<Order> orders = new ArrayList<Order>(orderMap.size());
		for (String key : orderMap.keySet()) {
			String order = orderMap.get(key);
			try {
				Direction direction = Direction.fromString(order);
				orders.add(new Order(direction, key));
			} catch (Exception e) {
				logger.error(e.getMessage(), e);
			}
		}
		return orders;
	}

	public Order[] getOrderArray() {
		List<Order> orders = getOrders();
		return orders.toArray(new Order[orders.size()]);
	}

	public Map<String, String> getOrderMap() {
		return orderMap;
	}

	public void setOrderMap(Map<String, String> orderMap) {
		this.orderMap = orderMap;
	}
	
	public static void main(String[] args) {
		System.out.println(Direction.fromString("d"));
	}
	
}

package com.kvc.joy.commons.query;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import com.kvc.joy.commons.lang.string.StringTool;
import com.kvc.joy.commons.log.Log;
import com.kvc.joy.commons.log.LogFactory;
import com.kvc.joy.commons.query.sort.Direction;
import com.kvc.joy.commons.query.sort.Order;

/**
 * 
 * @author 唐玮琳
 * @time 2012-6-19 下午9:45:39
 */
public class QueryLogics implements java.io.Serializable {

	public static final String KEY_PREFIX_ORDER_BY = "_joy_key__order_by_";
	public static final String KEY_PREFIX_ORDER = "_joy_key__order_value_";
	public static final String KEY_PREFIX_ORDER_DEFAULT = "_joy_key__order_default_";

	private Paging paging;
	private Map<String, QueryLogic> conditions = new LinkedHashMap<String, QueryLogic>(0);
	private Map<String, String> orderMap = new HashMap<String, String>(0);
	protected static final Log logger = LogFactory.getLog(QueryLogic.class);

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

	public Paging getPaging() {
		return paging;
	}

	public void setPaging(Paging paging) {
		this.paging = paging;
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
				orders.add(new Order(key, direction));
			} catch (Exception e) {
				logger.error(e, e.getMessage());
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

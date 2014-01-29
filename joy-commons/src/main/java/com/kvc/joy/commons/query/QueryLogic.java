package com.kvc.joy.commons.query;

/**
 * 
 * @author 唐玮琳
 * @time 2012-6-22 上午12:00:28
 */
public class QueryLogic implements java.io.Serializable {

	public static final String OPERATOR_PARAM_PREFIX = "_joy_key__operator_";
	public static final String TIME_START_PROP_PREFIX = "time_start__";
	public static final String TIME_END_PROP_PREFIX = "time_end__";

	private QueryLogicOperator operator;
	private Object value;
	private String property;

	public QueryLogic(String property, QueryLogicOperator operator, Object value) {
		this.property = property;
		this.operator = operator;
		this.value = value;
	}

	public QueryLogicOperator getOperator() {
		return operator;
	}

	public void setOperator(QueryLogicOperator operator) {
		this.operator = operator;
	}

	public String getProperty() {
		return property;
	}

	public void setProperty(String property) {
		this.property = property;
	}

	public Object getValue() {
		return value;
	}

//	public Object getQuoteValue() {
//		if (value instanceof String) {
//			return "'" + value + "'";
//		}
//		return value;
//	}

	public void setValue(Object fieldValue) {
		this.value = fieldValue;
	}

	@Override
	public String toString() {
		String op = operator == null ? "null" : operator.getCode();
		Object val = value == null ? "null" : value;
		return property + " " + op + " " + val;
	}

}

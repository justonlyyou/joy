package org.joy.commons.query;

/**
 * 查询逻辑
 *
 * @since 1.0.0
 * @author Kevice
 * @time 2012-6-22 上午12:00:28
 */
public class QueryLogic implements java.io.Serializable {

    /**
     * 页面提交的查询条件的操作符参数名前缀
     */
	public static final String OPERATOR_PARAM_PREFIX = "_joy_key__operator_";
    /**
     * 页面提交的查询条件的开始时间参数名前缀
     */
	public static final String TIME_START_PROP_PREFIX = "time_start__";
    /**
     * 页面提交的查询条件的结束时间参数名前缀
     */
	public static final String TIME_END_PROP_PREFIX = "time_end__";

	private QueryLogicOperator operator; // 查询逻辑操作符枚举
	private Object value; // 值
	private String property; // 属性名

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

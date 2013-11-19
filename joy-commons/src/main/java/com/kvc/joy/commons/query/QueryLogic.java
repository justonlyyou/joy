package com.kvc.joy.commons.query;


/**
 * 
 * @author 唐玮琳
 * @time 2012-6-22 上午12:00:28
 */
public class QueryLogic implements java.io.Serializable {
	
	public static final String OPERATOR_PARA_NAME_PREFIX = "_joy_key__operator_";

	private QueryLogicOperator operator;
	private Object fieldValue;

	public QueryLogic(QueryLogicOperator operator, Object fieldValue) {
		this.operator = operator;
		this.fieldValue = fieldValue;
	}

	public QueryLogicOperator getOperator() {
		return operator;
	}

	public void setOperator(QueryLogicOperator operator) {
		this.operator = operator;
	}

	public Object getFieldValue() {
		return fieldValue;
	}

	public void setFieldValue(Object fieldValue) {
		this.fieldValue = fieldValue;
	}

}

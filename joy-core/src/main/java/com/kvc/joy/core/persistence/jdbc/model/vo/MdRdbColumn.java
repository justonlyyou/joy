package com.kvc.joy.core.persistence.jdbc.model.vo;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * <pre>
 * <b><font color="blue">MdDbColumn</font></b>
 * </pre>
 * 
 * <pre>
 * <b>&nbsp;--描述说明--</b>
 * </pre>
 * 
 * <pre>
 * 
 * </pre>
 * 
 * <pre>
 * <b>--样例--</b>
 *   MdDbColumn obj = new MdDbColumn();
 *   obj.method();
 * </pre>
 * 
 * @author <b>唐玮琳</b>
 */
public class MdRdbColumn implements Serializable {

	public static final String PROP_NAME__name = "name";
	
	private String name;
	private String type;
	private Boolean nullable;
	private Integer length;
	private BigDecimal precision;
	private String defaultValue;
	private boolean key; 
	private MdRdbColumnComment comment;
	

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Boolean getNullable() {
		return nullable;
	}

	public void setNullable(Boolean nullable) {
		this.nullable = nullable;
	}

	public Integer getLength() {
		return length;
	}

	public void setLength(Integer length) {
		this.length = length;
	}

	public BigDecimal getPrecision() {
		return precision;
	}

	public void setPrecision(BigDecimal precision) {
		this.precision = precision;
	}

	public String getDefaultValue() {
		return defaultValue;
	}

	public void setDefaultValue(String defaultValue) {
		this.defaultValue = defaultValue;
	}
	
	public boolean isKey() {
		return key;
	}

	public void setKey(boolean key) {
		this.key = key;
	}

	public MdRdbColumnComment getComment() {
		return comment;
	}

	public void setComment(MdRdbColumnComment comment) {
		this.comment = comment;
	}
	
}

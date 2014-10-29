package org.joy.core.persistence.jdbc.model.vo;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * 关系型数据库列元数据信息
 *
 * @author Kevice
 * @time 2013-1-3 上午12:19:58
 * @since 1.0.0
 */
public class MdRdbColumn implements Serializable {

	private String name; // 列名
	private String type; // 列类型名称
	private Boolean nullable; // 是否可以为空
	private Integer length; // 最大长度
	private BigDecimal precision; // 精度
	private String defaultValue; // 默认值的字符串表示
	private boolean key; // 是否为主键
	private MdRdbColumnComment comment; // 列注释信息对象
	

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

package com.kvc.joy.core.sysres.param.model.po;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.kvc.joy.core.persistence.entity.UuidCrudEntity;
import com.kvc.joy.core.persistence.orm.jpa.annotations.Comment;
import com.kvc.joy.core.persistence.orm.jpa.annotations.DefaultValue;

@Entity
@Table(name = "t_sys_param")
@Comment("系统参数")
public class TSysParam extends UuidCrudEntity {

	// Fields
	private String paramName; // 参数名
	private String paramValue; // 参数值
	private String defaultValue; // 参数默认值
	private String encrypt; // 是否加密

	// Constructors

	/** default constructor */
	public TSysParam() {
	}

	// Property accessors

	@Column(length = 64, nullable = false)
	@Comment("参数名")
	public String getParamName() {
		return this.paramName;
	}

	public void setParamName(String paramName) {
		this.paramName = paramName;
	}

	@Column(length = 128)
	@Comment("参数值")
	public String getParamValue() {
		return this.paramValue;
	}

	public void setParamValue(String paramValue) {
		this.paramValue = paramValue;
	}

	public int hashCode() {
		final int PRIME = 31;
		int result = 1;
		result = PRIME * result + ((paramName == null) ? 0 : paramName.hashCode());
		return result;
	}

	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		final TSysParam other = (TSysParam) obj;
		if (paramName == null) {
			if (other.paramName != null)
				return false;
		} else if (!paramName.equals(other.paramName))
			return false;
		return true;
	}

	@Column(length = 128)
	@Comment("默认值")
	public String getDefaultValue() {
		return defaultValue;
	}

	public void setDefaultValue(String defaultValue) {
		this.defaultValue = defaultValue;
	}

	@Column(length = 1, nullable = false)
	@DefaultValue("0")
	@Comment("是否加密")
	public String getEncrypt() {
		return encrypt;
	}
	
	public boolean encrypt() {
		return "1".equals(encrypt);
	}

	public void setEncrypt(String encrypt) {
		this.encrypt = encrypt;
	}

}

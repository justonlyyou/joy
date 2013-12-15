package com.kvc.joy.core.sysres.code.model.vo;

import java.io.Serializable;

/**
 * 
 * @since 1.0.0
 * @author 唐玮琳
 * @time 2013年12月7日 下午9:22:01
 */
public class CodeRecord implements Serializable {

	private String code;
	private String trans;
	private String parentCode;
	private String order;
	
	public CodeRecord(String code, String trans, String parentCode, String order) {
		this.code = code;
		this.trans = trans;
		this.parentCode = parentCode;
		this.order = order;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getTrans() {
		return trans;
	}

	public void setTrans(String trans) {
		this.trans = trans;
	}

	public String getParentCode() {
		return parentCode;
	}

	public void setParentCode(String parentCode) {
		this.parentCode = parentCode;
	}

	public String getOrder() {
		return order;
	}

	public void setOrder(String order) {
		this.order = order;
	}
	
	@Override
	public String toString() {
		return "(" + code + " : " + trans + ")";
	}

}

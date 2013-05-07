package com.kvc.joy.plugin.security.erbac.support.enums;

import com.kvc.joy.commons.enums.EnumTool;
import com.kvc.joy.commons.enums.ICodeEnum;

public enum UserStatus implements ICodeEnum {

	FORBIDDEN("00", "禁用"),
	NORMAL("01", "正常");
	
	private String code;
	private String desc;
	
	UserStatus(String code, String desc) {
		this.code = code;
		this.desc = desc;
	}
	
	public String getCode() {
		return code;
	}

	public String getTrans() {
		return desc;
	}
	
	public static UserStatus enumOf(String code) {
		return EnumTool.enumOf(UserStatus.class, code);
	}

}

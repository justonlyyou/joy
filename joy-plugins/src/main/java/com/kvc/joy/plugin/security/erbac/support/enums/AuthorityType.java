package com.kvc.joy.plugin.security.erbac.support.enums;

import com.kvc.joy.commons.enums.EnumTool;
import com.kvc.joy.commons.enums.ICodeEnum;

public enum AuthorityType implements ICodeEnum {

	ACCESSIBLE("1", "可访问"), 
	AUTHORIZABLE("2", "可受权");

	private String code;
	private String desc;

	AuthorityType(String code, String desc) {
		this.code = code;
		this.desc = desc;
	}

	public String getCode() {
		return code;
	}

	public String getTrans() {
		return desc;
	}

	public static AuthorityType enumOf(String code) {
		return EnumTool.enumOf(AuthorityType.class, code);
	}

}

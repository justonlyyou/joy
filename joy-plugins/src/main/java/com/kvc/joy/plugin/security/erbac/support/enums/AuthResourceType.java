package com.kvc.joy.plugin.security.erbac.support.enums;

import com.kvc.joy.commons.enums.EnumTool;
import com.kvc.joy.commons.enums.ICodeEnum;
import com.kvc.joy.core.sysres.SysResTool;

public enum AuthResourceType implements ICodeEnum {

	/** URL */
	URL("01"),
	/** 类的方法 */
	METHOD("02");
	
	private String code;
	private String desc;
	
	public static final String CODE_TABLE_ID = "joy_code_auth_res_type";
	
	AuthResourceType(String code) {
		this.code = code;
		this.desc = SysResTool.translateCode(CODE_TABLE_ID, code).getTrans();
	}
	
	public String getCode() {
		return code;
	}

	public String getTrans() {
		return desc;
	}
	
	public static AuthResourceType enumOf(String code) {
		return EnumTool.enumOf(AuthResourceType.class, code);
	}

}

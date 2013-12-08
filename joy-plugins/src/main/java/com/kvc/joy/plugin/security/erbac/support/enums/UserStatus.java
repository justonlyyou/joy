package com.kvc.joy.plugin.security.erbac.support.enums;

import com.kvc.joy.commons.enums.EnumTool;
import com.kvc.joy.commons.enums.ICodeEnum;
import com.kvc.joy.core.sysres.SysResTool;

public enum UserStatus implements ICodeEnum {

	/** 禁用 */
	FORBIDDEN("00"),
	/** 正常 */
	NORMAL("01");
	
	public static final String CODE_TABLE_ID = "joy_code_user_status";
	
	private String code;
	private String desc;
	
	UserStatus(String code) {
		this.code = code;
		this.desc = SysResTool.translateCode(CODE_TABLE_ID, code).getTrans();
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

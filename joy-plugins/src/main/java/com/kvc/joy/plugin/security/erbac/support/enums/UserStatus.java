package com.kvc.joy.plugin.security.erbac.support.enums;

import com.kvc.joy.commons.enums.EnumTool;
import com.kvc.joy.commons.enums.ICodeEnum;
import com.kvc.joy.core.sysres.SysResTool;

public enum UserStatus implements ICodeEnum {

	/** 禁用 */
	FORBIDDEN("00"),
	/** 正常 */
	NORMAL("01");
	
	public static final String CODE_TABLE_ID = "user_status";
	
	private String code;
	
	UserStatus(String code) {
		this.code = code;
	}
	
	public String getCode() {
		return code;
	}

	public String getTrans() {
		return SysResTool.translateCode(CODE_TABLE_ID, code).getTrans();
	}
	
	public static UserStatus enumOf(String code) {
		return EnumTool.enumOf(UserStatus.class, code);
	}

}

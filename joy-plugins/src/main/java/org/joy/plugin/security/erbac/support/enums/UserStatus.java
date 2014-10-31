package org.joy.plugin.security.erbac.support.enums;

import org.joy.commons.enums.EnumTool;
import org.joy.commons.enums.ICodeEnum;
import org.joy.core.sysres.SysResTool;

/**
 * 用户状态枚举
 *
 * @since 1.0.0
 * @author Kevice
 * @time 2012-6-20 下午8:01:46
 */
public enum UserStatus implements ICodeEnum {

	/** 禁用 */
	FORBIDDEN("00"),
	/** 正常 */
	NORMAL("01");
	
	public static final String CODE_TABLE_ID = "user_status";
	
	private final String code;
	
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

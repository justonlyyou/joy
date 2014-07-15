package org.joy.plugin.security.erbac.support.enums;

import org.joy.commons.enums.EnumTool;
import org.joy.commons.enums.ICodeEnum;
import org.joy.core.sysres.SysResTool;

public enum AuthResourceType implements ICodeEnum {

	/** URL */
	URL("01"),
	/** 类的方法 */
	METHOD("02");
	
	private final String code;
	
	public static final String CODE_TABLE_ID = "auth_res_type";
	
	AuthResourceType(String code) {
		this.code = code;
	}
	
	public String getCode() {
		return code;
	}

	public String getTrans() {
		return SysResTool.translateCode(CODE_TABLE_ID, code).getTrans();
	}
	
	public static AuthResourceType enumOf(String code) {
		return EnumTool.enumOf(AuthResourceType.class, code);
	}

}

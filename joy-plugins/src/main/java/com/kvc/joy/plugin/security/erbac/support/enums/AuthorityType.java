package com.kvc.joy.plugin.security.erbac.support.enums;

import com.kvc.joy.commons.enums.EnumTool;
import com.kvc.joy.commons.enums.ICodeEnum;
import com.kvc.joy.core.sysres.SysResTool;

public enum AuthorityType implements ICodeEnum {

	/** 可访问 */
	ACCESSIBLE("1"),
	/** 可受权 */
	AUTHORIZABLE("2");
	
	public static final String CODE_TABLE_ID = "joy_code_authority_type";

	private String code;
	private String desc;

	AuthorityType(String code) {
		this.code = code;
		this.desc = SysResTool.translateCode(CODE_TABLE_ID, code).getTrans();
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

package org.joy.plugin.security.erbac.support.enums;

import org.joy.commons.enums.EnumTool;
import org.joy.commons.enums.ICodeEnum;
import org.joy.core.sysres.SysResTool;

public enum AuthorityType implements ICodeEnum {

	/** 可访问 */
	ACCESSIBLE("1"),
	/** 可受权 */
	AUTHORIZABLE("2");
	
	public static final String CODE_TABLE_ID = "authority_type";

	private final String code;

	AuthorityType(String code) {
		this.code = code;
	}

	public String getCode() {
		return code;
	}

	public String getTrans() {
		return SysResTool.translateCode(CODE_TABLE_ID, code).getTrans();
	}

	public static AuthorityType enumOf(String code) {
		return EnumTool.enumOf(AuthorityType.class, code);
	}

}

package org.joy.plugin.security.user.support.enums;

import org.joy.commons.enums.EnumTool;
import org.joy.commons.enums.ICodeEnum;
import org.joy.core.sysres.SysResTool;

/**
 * 
 * @since 1.0.0
 * @author Kevice
 * @time 2013年10月15日 上午10:21:45
 */
public enum LogoutMethod implements ICodeEnum {
	
	/** 点击退出按钮 */
	CLICK_BUTTON("11"),
	/** 直接关闭浏览器 */
	CLOSE_BROSWER("12"),
	/** 超时 */
	TIMEOUT("21"),
	/** 其它 */
	OTHERS("99");
	
	public static final String CODE_TABLE_ID = "logout_method";
	
	private final String code;
	
	LogoutMethod(String code) {
		this.code = code;
	}

	@Override
	public String getCode() {
		return code;
	}

	@Override
	public String getTrans() {
		return SysResTool.translateCode(CODE_TABLE_ID, code).getTrans();
	}
	
	public static LogoutMethod enumOf(String code) {
		return EnumTool.enumOf(LogoutMethod.class, code);
	}

	@Override
	public String toString() {
		return getTrans();
	}

}

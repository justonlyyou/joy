package com.kvc.joy.plugin.security.login.support.enums;

import com.kvc.joy.commons.enums.EnumTool;
import com.kvc.joy.commons.enums.ICodeEnum;

/**
 * 
 * @since 1.0.0
 * @author 唐玮琳
 * @time 2013年10月15日 上午10:21:45
 */
public enum LogoutMethod implements ICodeEnum {
	
	CLICK_BUTTON("11", "点击退出按钮"),
	CLOSE_BROSWER("12", "直接关闭浏览器"),
	TIMEOUT("21", "超时"),
	OTHERS("99", "其它");
	
	private String code;
	private String trans;
	
	LogoutMethod(String code, String trans) {
		this.code = code;
		this.trans = trans;
	}

	@Override
	public String getCode() {
		return code;
	}

	@Override
	public String getTrans() {
		return trans;
	}
	
	public static LogoutMethod enumOf(String code) {
		return EnumTool.enumOf(LogoutMethod.class, code);
	}

	@Override
	public String toString() {
		return trans;
	}

}

package com.kvc.joy.plugin.security.login.support.enums;

import com.kvc.joy.commons.enums.EnumTool;
import com.kvc.joy.commons.enums.ICodeEnum;

/**
 * 登陆失败原因枚举
 * 
 * @since 1.0.0
 * @author 唐玮琳
 * @time 2013年9月29日 下午11:41:03
 */
public enum LoginFailReason implements ICodeEnum {

	PASSWORD_ERR("11", "密码错误"),
	CAPTCHA_ERR("12", "验证码错误"), 
	ACCOUNT_LOCK("21", "帐号被锁定");

	private String code;
	private String desc;

	LoginFailReason(String code, String desc) {
		this.code = code;
		this.desc = desc;
	}

	@Override
	public String getCode() {
		return code;
	}

	@Override
	public String getTrans() {
		return desc;
	}

	public static LoginFailReason enumOf(String code) {
		return EnumTool.enumOf(LoginFailReason.class, code);
	}

	@Override
	public String toString() {
		return desc;
	}

}

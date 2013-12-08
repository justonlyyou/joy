package com.kvc.joy.plugin.security.login.support.enums;

import com.kvc.joy.commons.enums.EnumTool;
import com.kvc.joy.commons.enums.ICodeEnum;
import com.kvc.joy.core.sysres.SysResTool;

/**
 * 登陆状态枚举
 * 
 * @since 1.0.0
 * @author 唐玮琳
 * @time 2013年9月29日 下午11:41:03
 */
public enum LoginState implements ICodeEnum {
	
	/** 登陆成功 */
	SUCCESS("00"),
	/** 密码错误 */
	PASSWORD_ERR("11"),
	/** 验证码错误 */
	CAPTCHA_ERR("12"),
	/** 帐号被锁定 */
	ACCOUNT_LOCK("21");
	
	public static final String CODE_TABLE_ID = "joy_code_login_state";

	private String code;
	private String desc;

	LoginState(String code) {
		this.code = code;
		this.desc = SysResTool.translateCode(CODE_TABLE_ID, code).getTrans();
	}

	@Override
	public String getCode() {
		return code;
	}

	@Override
	public String getTrans() {
		return desc;
	}

	public static LoginState enumOf(String code) {
		return EnumTool.enumOf(LoginState.class, code);
	}

	@Override
	public String toString() {
		return desc;
	}

}

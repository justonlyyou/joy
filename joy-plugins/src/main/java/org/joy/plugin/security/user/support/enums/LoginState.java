package org.joy.plugin.security.user.support.enums;

import org.joy.commons.enums.EnumTool;
import org.joy.commons.enums.ICodeEnum;
import org.joy.core.sysres.SysResTool;

/**
 * 登陆状态枚举
 * 
 * @since 1.0.0
 * @author Kevice
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
	
	public static final String CODE_TABLE_ID = "login_state";

	private final String code;

	LoginState(String code) {
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

	public static LoginState enumOf(String code) {
		return EnumTool.enumOf(LoginState.class, code);
	}

	@Override
	public String toString() {
		return getTrans();
	}

}

package com.kvc.joy.commons.enums;

import com.kvc.joy.core.sysres.SysResTool;

/**
 * <p>
 * 性别枚举 
 * </p>
 * 
 * @since 1.0.0
 * @author 唐玮琳
 * @time 2013-5-1 下午11:20:11
 */
public enum Sex implements ICodeEnum {
	
	/** 未知的性别 */
	UNKNOWN("0"),
	/** 男 */
	MALE("1"),
	/** 女 */
	FEMALE("2"),
	/** 未说明的性别 */
	UNSPECIFIED("9");
	
	public static final String CODE_TABLE_ID = "joy_code_sex";
	
	private String code;
	private String desc;
	
	Sex(String code) {
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
	
	public static Sex enumOf(String code) {
		return EnumTool.enumOf(Sex.class, code);
	}
	
	@Override
	public String toString() {
		return desc;
	}
	
}

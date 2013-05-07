package com.kvc.joy.commons.enums;

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
	
	UNKNOWN("0", "未知的性别"),
	MALE("1", "男"),
	FEMALE("2", "女"),
	UNSPECIFIED("9", "未说明的性别");
	
	private String code;
	private String desc;
	
	Sex(String code, String desc) {
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
	
	public static Sex enumOf(String code) {
		return EnumTool.enumOf(Sex.class, code);
	}
	
	@Override
	public String toString() {
		return desc;
	}
	
}

package com.kvc.joy.commons.enums;


/**
 * 时间单位枚举
 * 
 * @since 1.0.0
 * @author <b>唐玮琳</b>
 */
public enum TimeUnit implements ICodeEnum {
	
	YEAR("1", "年"),
	MONTH("2", "月"),
	WEEK("3", "周"),
	DAY("4", "日"),
	HOUR("5", "小时"),
	MINUTE("6", "分钟"),
	SECOND("7", "秒"),
	MILLISECOND("8", "毫秒"),
	MICROSECOND("9", "微秒");
	
	private String code;
	private String desc;
	
	TimeUnit(String code, String desc) {
		this.code = code;
		this.desc = desc;
	}
	
	/* (non-Javadoc)
	 * @see com.kvc.common.sys.support.ICodeEnum#getCode()
	 */
	public String getCode() {
		return code;
	}
	
	/* (non-Javadoc)
	 * @see com.kvc.common.sys.support.ICodeEnum#getDesc()
	 */
	public String getTrans() {
		return desc;
	}
	
	public int intValue() {
		return Integer.valueOf(getCode());
	}
	
	public static TimeUnit enumOf(String code) {
		return EnumTool.enumOf(TimeUnit.class, code);
	}

}

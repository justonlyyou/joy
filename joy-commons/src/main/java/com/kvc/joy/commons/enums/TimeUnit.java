package com.kvc.joy.commons.enums;

import java.util.Map;


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
	
	public static final String CODE_TABLE_ID = "time_unit";
	
	private final String code;
	private String desc;
	
	TimeUnit(String code, String desc) {
		this.code = code;
		this.desc = desc;
	}
	
	public static void initTrans(Map<String, String> map) {
		TimeUnit[] values = TimeUnit.values();
		for (TimeUnit timeUnit : values) {
			timeUnit.desc = map.get(timeUnit.getCode());
		}
	}
	
	public String getCode() {
		return code;
	}
	
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

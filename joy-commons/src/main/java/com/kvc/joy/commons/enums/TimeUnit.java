package com.kvc.joy.commons.enums;

import com.kvc.joy.core.sysres.SysResTool;


/**
 * 时间单位枚举
 * 
 * @since 1.0.0
 * @author <b>唐玮琳</b>
 */
public enum TimeUnit implements ICodeEnum {
	
	/** 年 */
	YEAR("1"),
	/** 月 */
	MONTH("2"),
	/** 周 */
	WEEK("3"),
	/** 日 */
	DAY("4"),
	/** 小时 */
	HOUR("5"),
	/** 分钟 */
	MINUTE("6"),
	/** 秒 */
	SECOND("7"),
	/** 毫秒 */
	MILLISECOND("8"),
	/** 微秒 */
	MICROSECOND("9");
	
	public static final String CODE_TABLE_ID = "joy_code_time_unit";
	
	private String code;
	private String desc;
	
	TimeUnit(String code) {
		this.code = code;
		this.desc = SysResTool.translateCode(CODE_TABLE_ID, code).getTrans();
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

package com.kvc.joy.plugin.schedule.quartz.support.enums;

import com.kvc.joy.commons.enums.EnumTool;
import com.kvc.joy.commons.enums.ICodeEnum;

/**
 * 
 * @author 唐玮琳
 * @time 2013-1-2 下午10:23:45
 */
public enum JobRunState implements ICodeEnum {
	
	NO_START("00", "未启动"),
	RUNNING("11", "运行中"),
	EXCEPTION("21", "异常终止"),
	PAUSE("22", "用户挂起"),
	FINISH("80", "已完成");

	private final String code;
	private final String desc;

	JobRunState(String code, String desc) {
		this.code = code;
		this.desc = desc;
	}

	public String getCode() {
		return code;
	}
	
	public String getTrans() {
		return desc;
	}
	
	public static JobRunState enumOf(String code) {
		return EnumTool.enumOf(JobRunState.class, code);
	}

}

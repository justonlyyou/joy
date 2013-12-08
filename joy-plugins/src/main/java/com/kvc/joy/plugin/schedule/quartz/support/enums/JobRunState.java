package com.kvc.joy.plugin.schedule.quartz.support.enums;

import com.kvc.joy.commons.enums.EnumTool;
import com.kvc.joy.commons.enums.ICodeEnum;
import com.kvc.joy.core.sysres.SysResTool;

/**
 * 
 * @author 唐玮琳
 * @time 2013-1-2 下午10:23:45
 */
public enum JobRunState implements ICodeEnum {
	
	/** 未启动 */
	NO_START("00"),
	/** 运行中 */
	RUNNING("11"),
	/** 异常终止 */
	EXCEPTION("21"),
	/** 用户挂起 */
	PAUSE("22"),
	/** 已完成 */
	FINISH("80");
	
	public static final String CODE_TABLE_ID = "joy_code_job_run_state";

	private final String code;
	private final String desc;

	JobRunState(String code) {
		this.code = code;
		this.desc = SysResTool.translateCode(CODE_TABLE_ID, code).getTrans();
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

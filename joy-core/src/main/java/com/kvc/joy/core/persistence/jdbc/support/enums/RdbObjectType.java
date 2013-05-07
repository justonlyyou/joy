package com.kvc.joy.core.persistence.jdbc.support.enums;

import com.kvc.joy.commons.enums.EnumTool;
import com.kvc.joy.commons.enums.ICodeEnum;

/**
 * 
 * @author 唐玮琳
 * @time 2012-12-28 下午11:32:54
 */
public enum RdbObjectType implements ICodeEnum {

	TABLE("TABLE", "表"),
	SYSTEM_TABLE ("SYSTEM TABLE", "系统内置表"),
	GLOBAL_TEMPORARY ("GLOBAL TEMPORARY", "全局临时表"),
	LOCAL_TEMPORARY("LOCAL TEMPORARY", "本地临时表"),
	VIEW("VIEW", "视图"),
	SYNONYM("SYNONYM", "同义词"),
	ALIAS("ALIAS", "别名");
	
	private String code;
	private String desc;

	RdbObjectType(String code, String desc) {
		this.code = code;
		this.desc = desc;
	}

	public String getCode() {
		return code;
	}

	public String getTrans() {
		return desc;
	}

	public static RdbObjectType enumOf(String code) {
		return EnumTool.enumOf(RdbObjectType.class, code);
	}
	
}

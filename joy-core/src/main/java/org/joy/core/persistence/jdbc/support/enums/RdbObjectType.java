package org.joy.core.persistence.jdbc.support.enums;

import org.joy.commons.enums.EnumTool;
import org.joy.commons.enums.ICodeEnum;

/**
 * 关系型数据库对象类型枚举
 * 
 * @author Kevice
 * @time 2012-12-28 下午11:32:54
 * @since 1.0.0
 */
public enum RdbObjectType implements ICodeEnum {

	TABLE("table", "表"),
	SYSTEM_TABLE ("system table", "系统内置表"),
	GLOBAL_TEMPORARY ("global temporary", "全局临时表"),
	LOCAL_TEMPORARY("local temporary", "本地临时表"),
	VIEW("view", "视图"),
	SYNONYM("synonym", "同义词"),
	ALIAS("alias", "别名");
	
	private final String code;
	private String desc;

	RdbObjectType(String code, String desc) {
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

    public static RdbObjectType enumOf(String code) {
		return EnumTool.enumOf(RdbObjectType.class, code);
	}
	
}

package org.joy.commons.enums;

import org.joy.commons.lang.BooleanTool;

import java.util.Map;


/**
 * 逻辑真假的枚举
 * 
 * @since 1.0.0
 * @author Kevice
 * @time 2013-5-1 下午11:20:11
 */
public enum YesNot implements ICodeEnum {
	
	YES(true, "1", "是"),
	NOT(false, "0", "否");
	
	public static final String CODE_TABLE_ID = "yes_not";
	
	private final boolean bool; // 布尔值
	private final String binary; // 二进制对应的字符串值
	private String trans; // 译文
	
	private YesNot(boolean bool, String binary, String trans) {
		this.bool = bool;
		this.binary = binary;
		this.trans = trans;
	}
	
	public static void initTrans(Map<String, String> map) {
		YesNot[] values = YesNot.values();
		for (YesNot yesNot : values) {
			yesNot.trans = map.get(yesNot.getCode());
		}
	}

	public boolean getBool() {
		return bool;
	}
	
	public String getBinary() {
		return binary;
	}

	public String getCode() {
		return binary;
	}

	public String getTrans() {
		return trans;
	}
	
	public static YesNot enumOf(String code) {
		boolean bool = BooleanTool.toBoolean(code);
		return enumOfBool(bool);
	}
	
	public static YesNot enumOfBool(boolean bool) {
		return bool ? YES : NOT;
	}
	
}

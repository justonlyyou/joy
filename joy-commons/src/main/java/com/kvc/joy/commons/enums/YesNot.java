package com.kvc.joy.commons.enums;


/**
 * 逻辑真假的枚举
 * 
 * @since 1.0.0
 * @author <b>唐玮琳</b>
 */
public enum YesNot implements ICodeEnum {
	
	YES(true, "1", "是"),
	NOT(false, "0", "否");
	
	private boolean bool;
	private String binary;
	private String cnName;
	
	private YesNot(boolean bool, String binary, String cnName) {
		this.bool = bool;
		this.binary = binary;
		this.cnName = cnName;
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
		return cnName;
	}
	
	public static YesNot enumOf(String code) {
		return EnumTool.enumOf(YesNot.class, code);
	}
	
	public static YesNot enumOfBool(boolean bool) {
		if (bool) {
			return YesNot.YES;
		}
		return YesNot.NOT;
	}
	
}

/**
 * 
 */
package com.kvc.joy.core.persistence.jdbc.support.enums;

import com.kvc.joy.commons.enums.EnumTool;
import com.kvc.joy.commons.enums.ICodeEnum;
import com.kvc.joy.commons.lang.string.StringTool;

/**
 * <p>
 * 
 * </p>
 * 
 * @since 1.0.0
 * @author 唐玮琳
 * @time 2013-11-10 上午11:51:27
 */
public enum RdbType implements ICodeEnum {

	MYSQL("01", "MySQL"), 
	ORACLE("02", "Oracle"),
	POSTGRESQL("03", "PostgreSQL"),
	SQLSERVER("04", "SQLServer"),
	DB2("05", "DB2"),
	DERBY("11", "Derby"),
	H2("12", "H2"),
	HSQL("13", "HSQL");

	private String code;
	private String name;

	RdbType(String code, String name) {
		this.code = code;
		this.name = name;
	}

	@Override
	public String getCode() {
		return code;
	}

	public String getName() {
		return name;
	}

	@Override
	public String getTrans() {
		return name;
	}

	@Override
	public String toString() {
		return name;
	}

	public static RdbType enumOf(String code) {
		return EnumTool.enumOf(RdbType.class, code);
	}

	public static RdbType enumOfName(String name) {
		if (StringTool.isNotBlank(name)) {
			RdbType[] values = RdbType.values();
			for (RdbType rdbType : values) {
				if (rdbType.getName().equals(name)) {
					return rdbType;
				}
			}
		}
		return null;
	}

}

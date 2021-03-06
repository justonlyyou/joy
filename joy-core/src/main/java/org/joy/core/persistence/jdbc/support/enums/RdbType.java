package org.joy.core.persistence.jdbc.support.enums;

import org.joy.commons.enums.EnumTool;
import org.joy.commons.enums.ICodeEnum;
import org.joy.commons.lang.string.StringTool;

/**
 * joy支持的关系型数据库类型最大集枚举 <br>
 * 目前只支持MYSQL，ORACLE，H2
 * 
 * @since 1.0.0
 * @author Kevice
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

	private final String code;
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
				if (rdbType.getName().equalsIgnoreCase(name)) {
					return rdbType;
				}
			}
		}
		return null;
	}

}

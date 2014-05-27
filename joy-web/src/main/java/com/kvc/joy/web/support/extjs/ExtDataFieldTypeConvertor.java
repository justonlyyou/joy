package com.kvc.joy.web.support.extjs;

import com.kvc.joy.commons.lang.ClassTool;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Extjs数据字段类型转换器，支持将Java类型(基础类及其包装类)转换为Extjs的数据字段类型
 * @author Kevice
 */
public class ExtDataFieldTypeConvertor {

	private static final Map<Class<?>, String> typeMap = new HashMap<Class<?>, String>();

	static {
		typeMap.put(Character.class, "string");
		typeMap.put(String.class, "string");
		typeMap.put(Byte.class, "int");
		typeMap.put(Short.class, "int");
		typeMap.put(Integer.class, "int");
		typeMap.put(Long.class, "int");
		typeMap.put(Float.class, "float");
		typeMap.put(Double.class, "float");
		typeMap.put(Boolean.class, "boolean");
		typeMap.put(Date.class, "date");
	}

	private ExtDataFieldTypeConvertor() {
	}

	public static String getExtType(Class<?> javaType) {
		if (typeMap.containsKey(javaType) == false) {
			javaType = ClassTool.primitiveToWrapper(javaType);
		}
		String extType = typeMap.get(javaType);
		if (extType == null) {
			extType = "auto";
		}
		return extType;
	}

}

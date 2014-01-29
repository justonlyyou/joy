package com.kvc.joy.commons.query;

import com.kvc.joy.commons.enums.EnumTool;
import com.kvc.joy.commons.enums.ICodeEnum;
import com.kvc.joy.commons.lang.string.StringTool;

import java.io.Serializable;

/**
 * 
 * @author 唐玮琳
 * @time 2012-6-19 下午9:51:19
 */
public enum QueryLogicOperator implements ICodeEnum, Serializable{

	EQ("=", "等于"),
	IEQ("I=", "忽略大小写等于"),
	NE("!=", "不等于"),
	LG("<>", "小于大于(不等于)"),
	GE(">=", "大于等于"),
	LE("<=", "小于等于"),
	GT(">", "大于"),
	LT("<", "小于"),
	EQ_P("P=", "等于属性"),
	NE_P("P!=", "不等于属性"),
	LG_P("P<>", "小于大于(不等于)属性"),
	GE_P("P>=", "大于等于属性"),
	LE_P("P<=", "小于等于属性"),
	GT_P("P>", "大于属性"),
	LT_P("P<", "小于属性"),
	LIKE("LIKE", "任意位置匹配"),
	LIKE_S("LIKE_S", "匹配前面"),
	LIKE_E("LIKE_E", "匹配后面"),
	ILIKE("ILIKE", "忽略大小写任意位置匹配"),
	ILIKE_S("ILIKE_S", "忽略大小写匹配前面"),
	ILIKE_E("ILIKE_E", "忽略大小写匹配后面"),
	IN("IN", "in查询"),
	IS_NULL("IS NULL", "判空"),
	IS_NOT_NULL("IS NOT NULL", "非空"),
	IS_EMPTY("=''", "等于空串"),
	IS_NOT_EMPTY("!=''", "不等于空串");
	
	private QueryLogicOperator(String code, String desc) {
		this.code = code;
		this.desc = desc;
	}

	private String code;
	private String desc;
	
	public String getCode() {
		return code;
	}

	public String getTrans() {
		return desc;
	}
	
	public static QueryLogicOperator enumOf(String code) {
		if (StringTool.isNotBlank(code)) {
			code = code.toUpperCase();
		}
		return EnumTool.enumOf(QueryLogicOperator.class, code);
	}

}

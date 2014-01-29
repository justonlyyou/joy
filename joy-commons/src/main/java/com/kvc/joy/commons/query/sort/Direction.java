package com.kvc.joy.commons.query.sort;

import com.kvc.joy.commons.exception.SystemException;

import java.util.Locale;


/**
 * 
 * @since 1.0.0
 * @author 唐玮琳
 * @time 2013年11月20日 下午11:18:30
 */
public enum Direction {

	ASC, DESC;

	public static Direction fromString(String value) {
		try {
			return Direction.valueOf(value.toUpperCase(Locale.US));
		} catch (Exception e) {
			String msg = "非法排序值{0}！取值必须o 'desc' 或 'asc' (大小写不敏感)。";
			throw new SystemException(e, msg, value);
		}
	}

}

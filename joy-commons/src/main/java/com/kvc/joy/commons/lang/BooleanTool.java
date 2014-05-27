package com.kvc.joy.commons.lang;

/**
 * 布尔处理工具类
 * 
 * @since 1.0.0
 * @author Kevice
 * @time 2013-4-9 下午8:08:34
 */
public class BooleanTool {

	// vvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvv
	// 封装org.apache.commons.lang3.BooleanUtils
	// vvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvv

	/**
	 * <p>
	 * 对指定的布尔值取反
	 * </p>
	 * 
	 * <p>
	 * 
	 * 如果传入{@code null}, 将返回{@code null}.
	 * </p>
	 * 
	 * <p>
	 * 注意: 返回null的结果如果自动拆箱为boolean, 将产生NullPointerException异常
	 * </p>
	 * 
	 * <pre>
	 * BooleanUtils.negate(Boolean.TRUE) = Boolean.FALSE;
	 * BooleanUtils.negate(Boolean.FALSE) = Boolean.TRUE;
	 * BooleanUtils.negate(null) = null;
	 * </pre>
	 * 
	 * @param bool 要取反的布尔值, 可以为null
	 * @return 指定布尔值的非运算结果, 输入{@code null}将返回{@code null}
	 * @since 1.0.0
	 * @author Kevice
	 * @time 2013-4-30 下午3:32:49
	 */
	public static Boolean negate(Boolean bool) {
		return org.apache.commons.lang3.BooleanUtils.negate(bool);
	}

	// boolean Boolean methods
	// -----------------------------------------------------------------------
	/**
	 * <p>
	 * 检查指定的{@code Boolean} 值是否为{@code true}, {@code null}将返回{@code false}
	 * </p>
	 * 
	 * <pre>
	 *   BooleanUtils.isTrue(Boolean.TRUE)  = true
	 *   BooleanUtils.isTrue(Boolean.FALSE) = false
	 *   BooleanUtils.isTrue(null)          = false
	 * </pre>
	 * 
	 * @param bool 要检查的布尔值, null 将返回 {@code false}
	 * @return {@code true} 仅当输入的参数不为null并且为true时
	 * @since 1.0.0
	 * @author Kevice
	 * @time 2013-4-30 下午3:34:57
	 */
	public static boolean isTrue(Boolean bool) {
		return org.apache.commons.lang3.BooleanUtils.isTrue(bool);
	}

	/**
	 * <p>
	 * 检查指定的{@code Boolean} 值是否不为{@code true}, {@code null}将返回{@code true}
	 * </p>
	 * 
	 * <pre>
	 *   BooleanUtils.isNotTrue(Boolean.TRUE)  = false
	 *   BooleanUtils.isNotTrue(Boolean.FALSE) = true
	 *   BooleanUtils.isNotTrue(null)          = true
	 * </pre>
	 * 
	 * @param bool 要检查的布尔值, null 将返回 {@code true}
	 * @return {@code true} 如果输入的参数为null或false时
	 * @since 1.0.0
	 * @author Kevice
	 * @time 2013-4-30 下午3:36:26
	 */
	public static boolean isNotTrue(Boolean bool) {
		return org.apache.commons.lang3.BooleanUtils.isNotTrue(bool);
	}

	/**
	 * <p>
	 * 检查指定的{@code Boolean} 值是否为{@code false}, {@code null}将返回{@code false}
	 * </p>
	 * 
	 * <pre>
	 *   BooleanUtils.isFalse(Boolean.TRUE)  = false
	 *   BooleanUtils.isFalse(Boolean.FALSE) = true
	 *   BooleanUtils.isFalse(null)          = false
	 * </pre>
	 * 
	 * @param bool 要检查的布尔值, null 将返回 {@code false}
	 * @return {@code true} 仅当输入的参数不为null并且为false时
	 * @since 1.0.0
	 * @author Kevice
	 * @time 2013-4-30 下午3:37:45
	 */
	public static boolean isFalse(Boolean bool) {
		return org.apache.commons.lang3.BooleanUtils.isFalse(bool);
	}

	/**
	 * <p>
	 * 检查指定的{@code Boolean} 值是否不为{@code false}, {@code null}将返回{@code true}
	 * </p>
	 * 
	 * <pre>
	 *   BooleanUtils.isNotFalse(Boolean.TRUE)  = true
	 *   BooleanUtils.isNotFalse(Boolean.FALSE) = false
	 *   BooleanUtils.isNotFalse(null)          = true
	 * </pre>
	 * 
	 * @param bool 要检查的布尔值, null 将返回 {@code true}
	 * @return {@code true} 如果输入的参数为null或true时
	 * @since 1.0.0
	 * @author Kevice
	 * @time 2013-4-30 下午3:38:53
	 */
	public static boolean isNotFalse(Boolean bool) {
		return org.apache.commons.lang3.BooleanUtils.isNotFalse(bool);
	}

	// -----------------------------------------------------------------------
	/**
	 * <p>
	 * 将Boolean转化为boolean, {@code null} 将返回 {@code false}.
	 * </p>
	 * 
	 * <pre>
	 *   BooleanUtils.toBoolean(Boolean.TRUE)  = true
	 *   BooleanUtils.toBoolean(Boolean.FALSE) = false
	 *   BooleanUtils.toBoolean(null)          = false
	 * </pre>
	 * 
	 * @param bool 要转化的Boolean值
	 * @return {@code true} 或 {@code false}, {@code null} 将返回 {@code false}
	 * @since 1.0.0
	 * @author Kevice
	 * @time 2013-4-30 下午3:42:59
	 */
	public static boolean toBoolean(Boolean bool) {
		return org.apache.commons.lang3.BooleanUtils.toBoolean(bool);
	}

	/**
	 * <p>
	 * 将Boolean转化为boolean, 输入{@code null}时将返回指定的默认值
	 * </p>
	 * 
	 * <pre>
	 *   BooleanUtils.toBooleanDefaultIfNull(Boolean.TRUE, false) = true
	 *   BooleanUtils.toBooleanDefaultIfNull(Boolean.FALSE, true) = false
	 *   BooleanUtils.toBooleanDefaultIfNull(null, true)          = true
	 * </pre>
	 * 
	 * @param bool 要转化的Boolean值
	 * @param valueIfNull 布尔值为{@code null}时返回的值
	 * @return {@code true} 或 {@code false}
	 * @since 1.0.0
	 * @author Kevice
	 * @time 2013-4-30 下午3:44:39
	 */
	public static boolean toBooleanDefaultIfNull(Boolean bool, boolean valueIfNull) {
		return org.apache.commons.lang3.BooleanUtils.toBooleanDefaultIfNull(bool, valueIfNull);
	}

	// Integer to Boolean methods
	// -----------------------------------------------------------------------
	/**
	 * <p>
	 * 将int转化为boolean, 0将当作false
	 * </p>
	 * 
	 * <pre>
	 *   BooleanUtils.toBoolean(0) = false
	 *   BooleanUtils.toBoolean(1) = true
	 *   BooleanUtils.toBoolean(2) = true
	 * </pre>
	 * 
	 * @param value 要转化的int值
	 * @return {@code true} 如果非0, {@code false} 如果是0
	 * @since 1.0.0
	 * @author Kevice
	 * @time 2013-4-30 下午3:48:52
	 */
	public static boolean toBoolean(int value) {
		return org.apache.commons.lang3.BooleanUtils.toBoolean(value);
	}

	/**
	 * <p>
	 * 将int转化为Boolean, 0将当作false
	 * </p>
	 * 
	 * <pre>
	 *   BooleanUtils.toBoolean(0) = Boolean.FALSE
	 *   BooleanUtils.toBoolean(1) = Boolean.TRUE
	 *   BooleanUtils.toBoolean(2) = Boolean.TRUE
	 * </pre>
	 * 
	 * @param value 要转化的int值
	 * @return Boolean.TRUE 如果非0, Boolean.FALSE 如果是0
	 * @since 1.0.0
	 * @author Kevice
	 * @time 2013-4-30 下午3:50:37
	 */
	public static Boolean toBooleanObject(int value) {
		return org.apache.commons.lang3.BooleanUtils.toBooleanObject(value);
	}

	/**
	 * <p>
	 * 将Integer转化为Boolean, 0将当作false
	 * </p>
	 * 
	 * <p>
	 * {@code null} 将返回 {@code null}.
	 * </p>
	 * 
	 * <p>
	 * 注意: 返回null的结果如果自动拆箱为boolean, 将产生NullPointerException异常
	 * </p>
	 * 
	 * <pre>
	 *   BooleanUtils.toBoolean(Integer.valueOf(0))    = Boolean.FALSE
	 *   BooleanUtils.toBoolean(Integer.valueOf(1))    = Boolean.TRUE
	 *   BooleanUtils.toBoolean(Integer.valueOf(null)) = null
	 * </pre>
	 * 
	 * @param value 要转化的Integer值
	 * @return Boolean.TRUE 如果非0, Boolean.FALSE 如果是0, {@code null}将返回{@code null} input
	 * @since 1.0.0
	 * @author Kevice
	 * @time 2013-4-30 下午3:58:48
	 */
	public static Boolean toBooleanObject(Integer value) {
		return org.apache.commons.lang3.BooleanUtils.toBooleanObject(value);
	}

	/**
	 * <p>
	 * 将int转化为boolean, 使用指定的转换规则值
	 * </p>
	 * 
	 * <pre>
	 *   BooleanUtils.toBoolean(0, 1, 0) = false
	 *   BooleanUtils.toBoolean(1, 1, 0) = true
	 *   BooleanUtils.toBoolean(2, 1, 2) = false
	 *   BooleanUtils.toBoolean(2, 2, 0) = true
	 * </pre>
	 * 
	 * @param value 要转化的int值
	 * @param trueValue 代表 {@code true}的值
	 * @param falseValue 代表 {@code false}的值
	 * @return {@code true} 或 {@code false}
	 * @throws IllegalArgumentException 如果不匹配
	 * @since 1.0.0
	 * @author Kevice
	 * @time 2013-4-30 下午4:02:28
	 */
	public static boolean toBoolean(int value, int trueValue, int falseValue) {
		return org.apache.commons.lang3.BooleanUtils.toBoolean(value, trueValue, falseValue);
	}

	/**
	 * <p>
	 * 将Integer转化为boolean, 使用指定的转换规则值
	 * </p>
	 * 
	 * <pre>
	 *   BooleanUtils.toBoolean(Integer.valueOf(0), Integer.valueOf(1), Integer.valueOf(0)) = false
	 *   BooleanUtils.toBoolean(Integer.valueOf(1), Integer.valueOf(1), Integer.valueOf(0)) = true
	 *   BooleanUtils.toBoolean(Integer.valueOf(2), Integer.valueOf(1), Integer.valueOf(2)) = false
	 *   BooleanUtils.toBoolean(Integer.valueOf(2), Integer.valueOf(2), Integer.valueOf(0)) = true
	 *   BooleanUtils.toBoolean(null, null, Integer.valueOf(0))                     = true
	 * </pre>
	 * 
	 * @param value 要转化的Integer值
	 * @param trueValue 代表 {@code true}的值, 可以为 {@code null}
	 * @param falseValue 代表 {@code false}的值, 可以为 {@code null}
	 * @return {@code true} 或 {@code false}
	 * @throws IllegalArgumentException 如果没有匹配
	 * @since 1.0.0
	 * @author Kevice
	 * @time 2013-4-30 下午4:04:22
	 */
	public static boolean toBoolean(Integer value, Integer trueValue, Integer falseValue) {
		return org.apache.commons.lang3.BooleanUtils.toBoolean(value, trueValue, falseValue);
	}

	/**
	 * <p>
	 * 将int转化为Boolean, 使用指定的转换规则值
	 * </p>
	 * 
	 * <p>
	 * 注意: 返回null的结果如果自动拆箱为boolean, 将产生NullPointerException异常
	 * </p>
	 * 
	 * <pre>
	 *   BooleanUtils.toBooleanObject(0, 0, 2, 3) = Boolean.TRUE
	 *   BooleanUtils.toBooleanObject(2, 1, 2, 3) = Boolean.FALSE
	 *   BooleanUtils.toBooleanObject(3, 1, 2, 3) = null
	 * </pre>
	 * 
	 * @param value 要转化的int值
	 * @param trueValue 代表 {@code true}的值
	 * @param falseValue 代表 {@code false}的值
	 * @param nullValue 代表 {@code null}的值
	 * @return Boolean.TRUE, Boolean.FALSE, 或 {@code null}
	 * @throws IllegalArgumentException 如果没有匹配
	 * @since 1.0.0
	 * @author Kevice
	 * @time 2013-4-30 下午4:07:47
	 */
	public static Boolean toBooleanObject(int value, int trueValue, int falseValue, int nullValue) {
		return org.apache.commons.lang3.BooleanUtils.toBooleanObject(value, trueValue, falseValue, nullValue);
	}

	/**
	 * <p>
	 * 将Integer转化为Boolean, 使用指定的转换规则值
	 * </p>
	 * 
	 * <p>
	 * 注意: 返回null的结果如果自动拆箱为boolean, 将产生NullPointerException异常
	 * </p>
	 * 
	 * <pre>
	 *   BooleanUtils.toBooleanObject(Integer.valueOf(0), Integer.valueOf(0), Integer.valueOf(2), Integer.valueOf(3)) = Boolean.TRUE
	 *   BooleanUtils.toBooleanObject(Integer.valueOf(2), Integer.valueOf(1), Integer.valueOf(2), Integer.valueOf(3)) = Boolean.FALSE
	 *   BooleanUtils.toBooleanObject(Integer.valueOf(3), Integer.valueOf(1), Integer.valueOf(2), Integer.valueOf(3)) = null
	 * </pre>
	 * 
	 * @param value 要转化的Integer值
	 * @param trueValue 代表 {@code true}的值, 可以为 {@code null}
	 * @param falseValue 代表 {@code false}的值, 可以为 {@code null}
	 * @param nullValue 代表 {@code null}的值, 可以为 {@code null}
	 * @return Boolean.TRUE, Boolean.FALSE, 或 {@code null}
	 * @throws IllegalArgumentException 如果没有匹配
	 * @since 1.0.0
	 * @author Kevice
	 * @time 2013-4-30 下午4:09:57
	 */
	public static Boolean toBooleanObject(Integer value, Integer trueValue, Integer falseValue, Integer nullValue) {
		return org.apache.commons.lang3.BooleanUtils.toBooleanObject(value, trueValue, falseValue, nullValue);
	}

	// Boolean to Integer methods
	// -----------------------------------------------------------------------
	/**
	 * <p>
	 * 将boolean转化为int, 0当作false
	 * </p>
	 * 
	 * <pre>
	 *   BooleanUtils.toInteger(true)  = 1
	 *   BooleanUtils.toInteger(false) = 0
	 * </pre>
	 * 
	 * @param bool 要转化的boolean值
	 * @return {@code true}返回1, {@code false}返回0
	 * @since 1.0.0
	 * @author Kevice
	 * @time 2013-4-30 下午4:24:20
	 */
	public static int toInteger(boolean bool) {
		return org.apache.commons.lang3.BooleanUtils.toInteger(bool);
	}

	/**
	 * <p>
	 * 将boolean转化为Integer, 0当作false
	 * </p>
	 * 
	 * <pre>
	 *   BooleanUtils.toIntegerObject(true)  = Integer.valueOf(1)
	 *   BooleanUtils.toIntegerObject(false) = Integer.valueOf(0)
	 * </pre>
	 * 
	 * @param bool 要转化的boolean值
	 * @return {@code true}返回1, {@code false}返回0
	 * @since 1.0.0
	 * @author Kevice
	 * @time 2013-4-30 下午4:26:35
	 */
	public static Integer toIntegerObject(boolean bool) {
		return org.apache.commons.lang3.BooleanUtils.toIntegerObject(bool);
	}

	/**
	 * <p>
	 * 将Boolean转化为Integer, 0当作false
	 * </p>
	 * 
	 * <p>
	 * {@code null} 将返回 {@code null}.
	 * </p>
	 * 
	 * <pre>
	 *   BooleanUtils.toIntegerObject(Boolean.TRUE)  = Integer.valueOf(1)
	 *   BooleanUtils.toIntegerObject(Boolean.FALSE) = Integer.valueOf(0)
	 * </pre>
	 * 
	 * @param bool 要转化的Boolean值
	 * @return {@code true}返回1, {@code false}返回0, {@code null}返回{@code null}
	 * @since 1.0.0
	 * @author Kevice
	 * @time 2013-4-30 下午4:27:57
	 */
	public static Integer toIntegerObject(Boolean bool) {
		return org.apache.commons.lang3.BooleanUtils.toIntegerObject(bool);
	}

	/**
	 * <p>
	 * 将boolean转化为int, 使用指定的转换规则值
	 * </p>
	 * 
	 * <pre>
	 *   BooleanUtils.toInteger(true, 1, 0)  = 1
	 *   BooleanUtils.toInteger(false, 1, 0) = 0
	 * </pre>
	 * 
	 * @param bool 要转化的boolean值
	 * @param trueValue 代表 {@code true}的值
	 * @param falseValue 代表 {@code false}的值
	 * @return int值
	 * @since 1.0.0
	 * @author Kevice
	 * @time 2013-4-30 下午4:33:33
	 */
	public static int toInteger(boolean bool, int trueValue, int falseValue) {
		return org.apache.commons.lang3.BooleanUtils.toInteger(bool, trueValue, falseValue);
	}

	/**
	 * <p>
	 * 将Boolean转化为int, 使用指定的转换规则值
	 * </p>
	 * 
	 * <pre>
	 *   BooleanUtils.toInteger(Boolean.TRUE, 1, 0, 2)  = 1
	 *   BooleanUtils.toInteger(Boolean.FALSE, 1, 0, 2) = 0
	 *   BooleanUtils.toInteger(null, 1, 0, 2)          = 2
	 * </pre>
	 * 
	 * @param bool 要转化的Boolean值
	 * @param trueValue 代表 {@code true}的值
	 * @param falseValue 代表 {@code false}的值
	 * @param nullValue 代表 {@code null}的值
	 * @return int值
	 * @since 1.0.0
	 * @author Kevice
	 * @time 2013-4-30 下午4:35:16
	 */
	public static int toInteger(Boolean bool, int trueValue, int falseValue, int nullValue) {
		return org.apache.commons.lang3.BooleanUtils.toIntegerObject(bool, trueValue, falseValue, nullValue);
	}

	/**
	 * <p>
	 * 将boolean转化为Integer, 使用指定的转换规则值
	 * </p>
	 * 
	 * <pre>
	 *   BooleanUtils.toIntegerObject(true, Integer.valueOf(1), Integer.valueOf(0))  = Integer.valueOf(1)
	 *   BooleanUtils.toIntegerObject(false, Integer.valueOf(1), Integer.valueOf(0)) = Integer.valueOf(0)
	 * </pre>
	 * 
	 * @param bool 要转化的Boolean值
	 * @param trueValue 代表 {@code true}的值, 可以为 {@code null}
	 * @param falseValue 代表 {@code false}的值, 可以为 {@code null}
	 * @return int值
	 * @since 1.0.0
	 * @author Kevice
	 * @time 2013-4-30 下午4:37:08
	 */
	public static Integer toIntegerObject(boolean bool, Integer trueValue, Integer falseValue) {
		return org.apache.commons.lang3.BooleanUtils.toIntegerObject(bool, trueValue, falseValue);
	}

	/**
	 * <p>
	 * 将Boolean转化为Integer, 使用指定的转换规则值
	 * </p>
	 * 
	 * <pre>
	 *   BooleanUtils.toIntegerObject(Boolean.TRUE, Integer.valueOf(1), Integer.valueOf(0), Integer.valueOf(2))  = Integer.valueOf(1)
	 *   BooleanUtils.toIntegerObject(Boolean.FALSE, Integer.valueOf(1), Integer.valueOf(0), Integer.valueOf(2)) = Integer.valueOf(0)
	 *   BooleanUtils.toIntegerObject(null, Integer.valueOf(1), Integer.valueOf(0), Integer.valueOf(2))          = Integer.valueOf(2)
	 * </pre>
	 * 
	 * @param bool 要转化的Boolean值
	 * @param trueValue 代表 {@code true}的值, 可以为 {@code null}
	 * @param falseValue 代表 {@code false}的值, 可以为 {@code null}
	 * @param nullValue 代表 {@code null}的值, 可以为 {@code null}
	 * @return Integer值
	 * @since 1.0.0
	 * @author Kevice
	 * @time 2013-4-30 下午4:45:52
	 */
	public static Integer toIntegerObject(Boolean bool, Integer trueValue, Integer falseValue, Integer nullValue) {
		return org.apache.commons.lang3.BooleanUtils.toIntegerObject(bool, trueValue, falseValue, nullValue);
	}

	// String to Boolean methods
	// -----------------------------------------------------------------------
	/**
	 * <p>
	 * 将String转化为Boolean
	 * </p>
	 * 
	 * <p>
	 * {@code 'true'}, {@code 'on'} 或 {@code 'yes'} (大小写不敏感) 将返回 {@code true}. {@code 'false'}, {@code 'off'} 或
	 * {@code 'no'} (大小写不敏感) 将返回 {@code false}. 否则, 返回{@code null}.
	 * </p>
	 * 
	 * <p>
	 * 注意: 返回null的结果如果自动拆箱为boolean, 将产生NullPointerException异常
	 * </p>
	 * 
	 * <pre>
	 *   BooleanUtils.toBooleanObject(null)    = null
	 *   BooleanUtils.toBooleanObject("true")  = Boolean.TRUE
	 *   BooleanUtils.toBooleanObject("false") = Boolean.FALSE
	 *   BooleanUtils.toBooleanObject("on")    = Boolean.TRUE
	 *   BooleanUtils.toBooleanObject("ON")    = Boolean.TRUE
	 *   BooleanUtils.toBooleanObject("off")   = Boolean.FALSE
	 *   BooleanUtils.toBooleanObject("oFf")   = Boolean.FALSE
	 *   BooleanUtils.toBooleanObject("1")   = Boolean.FALSE
	 *   BooleanUtils.toBooleanObject("0")   = Boolean.FALSE
	 *   BooleanUtils.toBooleanObject("blue")  = null
	 * </pre>
	 * 
	 * @param str 要转化的字符串
	 * @return 字符串的布尔值, 不匹配或输入null将返回null
	 * @since 1.0.0
	 * @author Kevice
	 * @time 2013-4-30 下午4:51:44
	 */
	public static Boolean toBooleanObject(String str) {
		if ("1".equals(str)) {
			return Boolean.TRUE;
		}
		if ("0".equals(str)) {
			return Boolean.FALSE;
		}
		return org.apache.commons.lang3.BooleanUtils.toBooleanObject(str);
	}

	/**
	 * <p>
	 * 将String转化为Boolean, 如果没有匹配将抛出异常
	 * </p>
	 * 
	 * <p>
	 * 注意: 返回null的结果如果自动拆箱为boolean, 将产生NullPointerException异常
	 * </p>
	 * 
	 * <pre>
	 *   BooleanUtils.toBooleanObject("true", "true", "false", "null")  = Boolean.TRUE
	 *   BooleanUtils.toBooleanObject("false", "true", "false", "null") = Boolean.FALSE
	 *   BooleanUtils.toBooleanObject("null", "true", "false", "null")  = null
	 * </pre>
	 * 
	 * @param str 要转化的字符串
	 * @param trueString 代表 {@code true}的值(大小写敏感), 可以为 {@code null}
	 * @param falseString 代表 {@code false}的值(大小写敏感), 可以为 {@code null}
	 * @param nullString 代表 {@code null}的值(大小写敏感), 可以为 {@code null}
	 * @return 字符串的布尔值, 如果字符串匹配{@code nullString}或为{@code null}且 {@code nullString}也为{@code null}将返回{@code null},
	 * @throws IllegalArgumentException 如果没有匹配
	 * @since 1.0.0
	 * @author Kevice
	 * @time 2013-4-30 下午5:05:35
	 */
	public static Boolean toBooleanObject(String str, String trueString, String falseString, String nullString) {
		return org.apache.commons.lang3.BooleanUtils.toBooleanObject(str, trueString, falseString, nullString);
	}

	// String to boolean methods
	// -----------------------------------------------------------------------
	/**
	 * <p>
	 * 将String转化为boolean(性能优化)
	 * </p>
	 * 
	 * <p>
	 * {@code 'true'}, {@code 'on'} 或 {@code 'yes'} (大小写不敏感) 将返回 {@code true}. 否则, 返回{@code false}.
	 * </p>
	 * 
	 * <p>
	 * 这个方法的性能比较jdk1.4的{@code Boolean.valueOf(String)}快4倍. 而且, 该方法接受'1'、'on' 和 'yes' 当作true.
	 * </p>
	 * 
	 * <pre>
	 *   BooleanUtils.toBoolean(null)    = false
	 *   BooleanUtils.toBoolean("true")  = true
	 *   BooleanUtils.toBoolean("TRUE")  = true
	 *   BooleanUtils.toBoolean("tRUe")  = true
	 *   BooleanUtils.toBoolean("on")    = true
	 *   BooleanUtils.toBoolean("yes")   = true
	 *   BooleanUtils.toBoolean("1")    = true
	 *   BooleanUtils.toBoolean("0")   = false
	 *   BooleanUtils.toBoolean("false") = false
	 *   BooleanUtils.toBoolean("x gti") = false
	 * </pre>
	 * 
	 * @param str 要转化的字符串
	 * @return 字符串的布尔值, 如果没有匹配或字符串为null将返回{@code false}
	 * @since 1.0.0
	 * @author Kevice
	 * @time 2013-4-30 下午5:14:15
	 */
	public static boolean toBoolean(String str) {
		if ("1".equals(str)) {
			return true;
		}
		if ("0".equals(str)) {
			return false;
		}
		return org.apache.commons.lang3.BooleanUtils.toBoolean(str);
	}
	
	/**
	 * <p>
	 * 将String转化为boolean, 如果没有匹配将抛出异常
	 * </p>
	 * 
	 * <pre>
	 *   BooleanUtils.toBoolean("true", "true", "false")  = true
	 *   BooleanUtils.toBoolean("false", "true", "false") = false
	 * </pre>
	 * 
	 * @param str 要转化的字符串
	 * @param trueString 代表 {@code true}的值(大小写敏感), 可以为 {@code null}
	 * @param falseString 代表 {@code false}的值(大小写敏感), 可以为 {@code null}
	 * @return 字符串的布尔值
	 * @throws IllegalArgumentException 如果没有匹配
	 * @since 1.0.0
	 * @author Kevice
	 * @time 2013-4-30 下午5:16:17
	 */
	public static boolean toBoolean(String str, String trueString, String falseString) {
		return org.apache.commons.lang3.BooleanUtils.toBoolean(str, trueString, falseString);
	}

	// Boolean to String methods
	// -----------------------------------------------------------------------
	/**
	 * <p>
	 * 将Boolean转化为String, 返回{@code 'true'}, {@code 'false'},或 {@code null}.
	 * </p>
	 * 
	 * <pre>
	 *   BooleanUtils.toStringTrueFalse(Boolean.TRUE)  = "true"
	 *   BooleanUtils.toStringTrueFalse(Boolean.FALSE) = "false"
	 *   BooleanUtils.toStringTrueFalse(null)          = null;
	 * </pre>
	 * 
	 * @param bool 要转化的Boolean
	 * @return {@code 'true'}, {@code 'false'}, 或 {@code null}
	 * @since 1.0.0
	 * @author Kevice
	 * @time 2013-4-30 下午5:17:59
	 */
	public static String toStringTrueFalse(Boolean bool) {
		return org.apache.commons.lang3.BooleanUtils.toStringTrueFalse(bool);
	}

	/**
	 * <p>
	 * 将Boolean转化为String, 返回{@code 'on'}, {@code 'off'},或 {@code null}.
	 * </p>
	 * 
	 * <pre>
	 *   BooleanUtils.toStringOnOff(Boolean.TRUE)  = "on"
	 *   BooleanUtils.toStringOnOff(Boolean.FALSE) = "off"
	 *   BooleanUtils.toStringOnOff(null)          = null;
	 * </pre>
	 * 
	 * @param bool 要转化的Boolean
	 * @return {@code 'on'}, {@code 'off'}, 或 {@code null}
	 * @since 1.0.0
	 * @author Kevice
	 * @time 2013-4-30 下午5:18:46
	 */
	public static String toStringOnOff(Boolean bool) {
		return org.apache.commons.lang3.BooleanUtils.toStringOnOff(bool);
	}

	/**
	 * <p>
	 * 将Boolean转化为String, 返回{@code 'yes'}, {@code 'no'},或 {@code null}.
	 * </p>
	 * 
	 * <pre>
	 *   BooleanUtils.toStringYesNo(Boolean.TRUE)  = "yes"
	 *   BooleanUtils.toStringYesNo(Boolean.FALSE) = "no"
	 *   BooleanUtils.toStringYesNo(null)          = null;
	 * </pre>
	 * 
	 * @param bool 要转化的Boolean
	 * @return {@code 'yes'}, {@code 'no'}, 或 {@code null}
	 * @since 1.0.0
	 * @author Kevice
	 * @time 2013-4-30 下午5:19:21
	 */
	public static String toStringYesNo(Boolean bool) {
		return org.apache.commons.lang3.BooleanUtils.toStringYesNo(bool);
	}

	/**
	 * <p>
	 * 将Boolean转化为String, 返回输入的某个匹配的字符串
	 * </p>
	 * 
	 * <pre>
	 *   BooleanUtils.toString(Boolean.TRUE, "true", "false", null)   = "true"
	 *   BooleanUtils.toString(Boolean.FALSE, "true", "false", null)  = "false"
	 *   BooleanUtils.toString(null, "true", "false", null)           = null;
	 * </pre>
	 * 
	 * @param bool 要转化的Boolean
	 * @param trueString 代表 {@code true}的值(大小写敏感), 可以为 {@code null}
	 * @param falseString 代表 {@code false}的值(大小写敏感), 可以为 {@code null}
	 * @param nullString 代表 {@code null}的值(大小写敏感), 可以为 {@code null}
	 * @return 输入的某个匹配的字符串
	 * @since 1.0.0
	 * @author Kevice
	 * @time 2013-4-30 下午5:23:04
	 */
	public static String toString(Boolean bool, String trueString, String falseString, String nullString) {
		return org.apache.commons.lang3.BooleanUtils.toString(bool, trueString, falseString, nullString);
	}

	// boolean to String methods
	// -----------------------------------------------------------------------
	/**
	 * <p>
	 * 将boolean转化为String, 返回 {@code 'true'} or {@code 'false'}.
	 * </p>
	 * 
	 * <pre>
	 *   BooleanUtils.toStringTrueFalse(true)   = "true"
	 *   BooleanUtils.toStringTrueFalse(false)  = "false"
	 * </pre>
	 * 
	 * @param bool 要转化的Boolean
	 * @return {@code 'true'}, {@code 'false'}, 或 {@code null}
	 * @since 1.0.0
	 * @author Kevice
	 * @time 2013-4-30 下午5:24:04
	 */
	public static String toStringTrueFalse(boolean bool) {
		return org.apache.commons.lang3.BooleanUtils.toStringTrueFalse(bool);
	}

	/**
	 * <p>
	 * 将boolean转化为String, 返回 {@code 'on'} or {@code 'off'}.
	 * </p>
	 * 
	 * <pre>
	 *   BooleanUtils.toStringOnOff(true)   = "on"
	 *   BooleanUtils.toStringOnOff(false)  = "off"
	 * </pre>
	 * 
	 * @param bool 要转化的Boolean
	 * @return {@code 'on'}, {@code 'off'}, 或 {@code null}
	 * @since 1.0.0
	 * @author Kevice
	 * @time 2013-4-30 下午5:24:49
	 */
	public static String toStringOnOff(boolean bool) {
		return org.apache.commons.lang3.BooleanUtils.toStringOnOff(bool);
	}

	/**
	 * <p>
	 * 将boolean转化为String, 返回 {@code 'yes'} or {@code 'no'}.
	 * </p>
	 * 
	 * <pre>
	 *   BooleanUtils.toStringYesNo(true)   = "yes"
	 *   BooleanUtils.toStringYesNo(false)  = "no"
	 * </pre>
	 * 
	 * @param bool 要转化的Boolean
	 * @return {@code 'yes'}, {@code 'no'}, 或 {@code null}
	 * @since 1.0.0
	 * @author Kevice
	 * @time 2013-4-30 下午5:25:32
	 */
	public static String toStringYesNo(boolean bool) {
		return org.apache.commons.lang3.BooleanUtils.toStringYesNo(bool);
	}

	/**
	 * <p>
	 * 将boolean转化为String, 返回输入的某个匹配的字符串
	 * </p>
	 * 
	 * <pre>
	 *   BooleanUtils.toString(true, "true", "false")   = "true"
	 *   BooleanUtils.toString(false, "true", "false")  = "false"
	 * </pre>
	 * 
	 * @param bool 要转化的Boolean
	 * @param trueString 代表 {@code true}的值(大小写敏感), 可以为 {@code null}
	 * @param falseString 代表 {@code false}的值(大小写敏感), 可以为 {@code null}
	 * @return 输入的某个匹配的字符串
	 * @since 1.0.0
	 * @author Kevice
	 * @time 2013-4-30 下午5:26:32
	 */
	public static String toString(boolean bool, String trueString, String falseString) {
		return org.apache.commons.lang3.BooleanUtils.toString(bool, trueString, falseString);
	}

	// logical operations
	// ----------------------------------------------------------------------
	/**
	 * <p>
	 *	对一组boolean进行逻辑与操作 
	 * </p>
	 * 
	 * <pre>
	 *   BooleanUtils.and(true, true)         = true
	 *   BooleanUtils.and(false, false)       = false
	 *   BooleanUtils.and(true, false)        = false
	 *   BooleanUtils.and(true, true, false)  = false
	 *   BooleanUtils.and(true, true, true)   = true
	 * </pre>
	 * 
	 * @param array {@code boolean}数组
	 * @return 逻辑与操作的结果
	 * @throws IllegalArgumentException 如果 {@code array} 为 {@code null}
	 * @throws IllegalArgumentException 如果 {@code array} 为空.
	 * @since 1.0.0
	 * @author Kevice
	 * @time 2013-4-30 下午5:29:37
	 */
	public static boolean and(boolean... array) {
		return org.apache.commons.lang3.BooleanUtils.and(array);
	}

	/**
	 * <p>
	 * 对一组Boolean进行逻辑与操作 
	 * </p>
	 * 
	 * <pre>
	 *   BooleanUtils.and(Boolean.TRUE, Boolean.TRUE)                 = Boolean.TRUE
	 *   BooleanUtils.and(Boolean.FALSE, Boolean.FALSE)               = Boolean.FALSE
	 *   BooleanUtils.and(Boolean.TRUE, Boolean.FALSE)                = Boolean.FALSE
	 *   BooleanUtils.and(Boolean.TRUE, Boolean.TRUE, Boolean.TRUE)   = Boolean.TRUE
	 *   BooleanUtils.and(Boolean.FALSE, Boolean.FALSE, Boolean.TRUE) = Boolean.FALSE
	 *   BooleanUtils.and(Boolean.TRUE, Boolean.FALSE, Boolean.TRUE)  = Boolean.FALSE
	 * </pre>
	 * 
	 * @param array {@code Boolean}数组
	 * @return 逻辑与操作的结果
	 * @throws IllegalArgumentException 如果 {@code array} 为 {@code null}
	 * @throws IllegalArgumentException 如果 {@code array} 为空.
	 * @throws IllegalArgumentException 如果 {@code array} 包含 {@code null}
	 * @since 1.0.0
	 * @author Kevice
	 * @time 2013-4-30 下午5:30:59
	 */
	public static Boolean and(Boolean... array) {
		return org.apache.commons.lang3.BooleanUtils.and(array);
	}

	/**
	 * <p>
	 * 对一组boolean进行逻辑或操作 
	 * </p>
	 * 
	 * <pre>
	 *   BooleanUtils.or(true, true)          = true
	 *   BooleanUtils.or(false, false)        = false
	 *   BooleanUtils.or(true, false)         = true
	 *   BooleanUtils.or(true, true, false)   = true
	 *   BooleanUtils.or(true, true, true)    = true
	 *   BooleanUtils.or(false, false, false) = false
	 * </pre>
	 * 
	 * @param array {@code boolean}数组
	 * @return 逻辑或操作的结果
	 * @throws IllegalArgumentException 如果 {@code array} 为 {@code null}
	 * @throws IllegalArgumentException 如果 {@code array} 为空.
	 * @since 1.0.0
	 * @author Kevice
	 * @time 2013-4-30 下午5:32:17
	 */
	public static boolean or(boolean... array) {
		return org.apache.commons.lang3.BooleanUtils.or(array);
	}

	/**
	 * <p>
	 * 对一组Boolean进行逻辑或操作 
	 * </p>
	 * 
	 * <pre>
	 *   BooleanUtils.or(Boolean.TRUE, Boolean.TRUE)                  = Boolean.TRUE
	 *   BooleanUtils.or(Boolean.FALSE, Boolean.FALSE)                = Boolean.FALSE
	 *   BooleanUtils.or(Boolean.TRUE, Boolean.FALSE)                 = Boolean.TRUE
	 *   BooleanUtils.or(Boolean.TRUE, Boolean.TRUE, Boolean.TRUE)    = Boolean.TRUE
	 *   BooleanUtils.or(Boolean.FALSE, Boolean.FALSE, Boolean.TRUE)  = Boolean.TRUE
	 *   BooleanUtils.or(Boolean.TRUE, Boolean.FALSE, Boolean.TRUE)   = Boolean.TRUE
	 *   BooleanUtils.or(Boolean.FALSE, Boolean.FALSE, Boolean.FALSE) = Boolean.FALSE
	 * </pre>
	 * 
	 * @param array {@code Boolean}数组
	 * @return 逻辑或操作的结果
	 * @throws IllegalArgumentException 如果 {@code array} 为 {@code null}
	 * @throws IllegalArgumentException 如果 {@code array} 为空.
	 * @throws IllegalArgumentException 如果 {@code array} 包含 {@code null}
	 * @since 1.0.0
	 * @author Kevice
	 * @time 2013-4-30 下午5:33:05
	 */
	public static Boolean or(Boolean... array) {
		return org.apache.commons.lang3.BooleanUtils.or(array);
	}

	/**
	 * <p>
	 * 对一组boolean进行逻辑异或操作 
	 * </p>
	 * 
	 * <pre>
	 *   BooleanUtils.xor(true, true)   = false
	 *   BooleanUtils.xor(false, false) = false
	 *   BooleanUtils.xor(true, false)  = true
	 *   BooleanUtils.xor(true, true)   = false
	 *   BooleanUtils.xor(false, false) = false
	 *   BooleanUtils.xor(true, false)  = true
	 * </pre>
	 * 
	 * @param array {@code boolean}数组
	 * @return 逻辑异或操作的结果
	 * @throws IllegalArgumentException 如果 {@code array} 为 {@code null}
	 * @throws IllegalArgumentException 如果 {@code array} 为空.
	 * @since 1.0.0
	 * @author Kevice
	 * @time 2013-4-30 下午5:34:09
	 */
	public static boolean xor(boolean... array) {
		return org.apache.commons.lang3.BooleanUtils.xor(array);
	}

	/**
	 * <p>
	 * 对一组boolean进行逻辑异或操作
	 * </p>
	 * 
	 * <pre>
	 *   BooleanUtils.xor(new Boolean[] { Boolean.TRUE, Boolean.TRUE })   = Boolean.FALSE
	 *   BooleanUtils.xor(new Boolean[] { Boolean.FALSE, Boolean.FALSE }) = Boolean.FALSE
	 *   BooleanUtils.xor(new Boolean[] { Boolean.TRUE, Boolean.FALSE })  = Boolean.TRUE
	 * </pre>
	 * 
	 * @param array {@code boolean}数组
	 * @return 逻辑异或操作的结果
	 * @throws IllegalArgumentException 如果 {@code array} 为 {@code null}
	 * @throws IllegalArgumentException 如果 {@code array} 为空.
	 * @throws IllegalArgumentException 如果 {@code array} 包含 {@code null}
	 * @since 1.0.0
	 * @author Kevice
	 * @time 2013-4-30 下午5:34:41
	 */
	public static Boolean xor(Boolean... array) {
		return org.apache.commons.lang3.BooleanUtils.xor(array);
	}

	// ^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^
	// 封装org.apache.commons.lang3.BooleanUtils
	// ^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^
	
	public static void main(String[] args) {
		System.out.println(toBoolean("1"));
	}

}

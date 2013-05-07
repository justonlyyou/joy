package com.kvc.joy.commons.math;

import java.math.BigDecimal;
import java.math.BigInteger;

import org.apache.commons.lang3.math.NumberUtils;

/**
 * 数值工具类
 * 
 * @since 1.0.0
 * @author 唐玮琳
 * @time 2013-4-9 下午7:28:33
 */
public class NumberTool {

	private NumberTool() {
	}

	// vvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvv
	// 封装org.apache.commons.lang3.math.NumberUtils
	// vvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvv

	// -----------------------------------------------------------------------
	/**
	 * <p>
	 * 将一个<code>String</code>转换为<code>int</code>， 如果转换失败返回0
	 * </p>
	 * 
	 * <p>
	 * 如果字符串为<code>null</code>，返回0
	 * </p>
	 * 
	 * <pre>
	 *   NumberUtils.toInt(null) = 0
	 *   NumberUtils.toInt("")   = 0
	 *   NumberUtils.toInt("1")  = 1
	 * </pre>
	 * 
	 * @param str 要转换的字符串，可以为null，为null将返回0
	 * @return 字符串的int表示，字符串为null或转换失败时将返回0
	 * @since 1.0.0
	 * @author 唐玮琳
	 * @time 2013-5-11 下午10:46:21
	 */
	public static int toInt(String str) {
		return NumberUtils.toInt(str);
	}

	/**
	 * <p>
	 * 将一个<code>String</code>转换为<code>int</code>， 如果转换失败返回指定的默认值
	 * </p>
	 * 
	 * <p>
	 * 如果字符串为<code>null</code>，返回指定的默认值
	 * </p>
	 * 
	 * <pre>
	 *   NumberUtils.toInt(null, 1) = 1
	 *   NumberUtils.toInt("", 1)   = 1
	 *   NumberUtils.toInt("1", 0)  = 1
	 * </pre>
	 * 
	 * @param str 要转换的字符串，可以为null，如果为null将返回指定的默认值
	 * @param defaultValue 字符串为null时返回的默认值
	 * @return 字符串的int表示，字符串为null或转换失败时将返回指定的默认值
	 * @since 1.0.0
	 * @author 唐玮琳
	 * @time 2013-5-11 下午10:48:30
	 */
	public static int toInt(String str, int defaultValue) {
		return NumberUtils.toInt(str, defaultValue);
	}

	/**
	 * <p>
	 * 将一个<code>String</code>转换为<code>long</code>， 如果转换失败返回0
	 * </p>
	 * 
	 * <p>
	 * 如果字符串为<code>null</code>，返回0
	 * </p>
	 * 
	 * <pre>
	 *   NumberUtils.toLong(null) = 0L
	 *   NumberUtils.toLong("")   = 0L
	 *   NumberUtils.toLong("1")  = 1L
	 * </pre>
	 * 
	 * @param str 要转换的字符串，可以为null，为null将返回0
	 * @return 字符串的long表示，字符串为null或转换失败时将返回0
	 * @since 1.0.0
	 * @author 唐玮琳
	 * @time 2013-5-11 下午10:49:52
	 */
	public static long toLong(String str) {
		return NumberUtils.toLong(str);
	}

	/**
	 * <p>
	 * 将一个<code>String</code>转换为<code>long</code>， 如果转换失败返回指定的默认值
	 * </p>
	 * 
	 * <p>
	 * 如果字符串为<code>null</code>，返回指定的默认值
	 * </p>
	 * 
	 * <pre>
	 *   NumberUtils.toLong(null, 1L) = 1L
	 *   NumberUtils.toLong("", 1L)   = 1L
	 *   NumberUtils.toLong("1", 0L)  = 1L
	 * </pre>
	 * 
	 * @param str 要转换的字符串，可以为null，如果为null将返回指定的默认值
	 * @param defaultValue 字符串为null时返回的默认值
	 * @return 字符串的long表示，字符串为null或转换失败时将返回指定的默认值
	 * @since 1.0.0
	 * @author 唐玮琳
	 * @time 2013-5-11 下午10:51:12
	 */
	public static long toLong(String str, long defaultValue) {
		return NumberUtils.toLong(str, defaultValue);
	}

	/**
	 * <p>
	 * 将一个<code>String</code>转换为<code>float</code>， 如果转换失败返回0.0f
	 * </p>
	 * 
	 * <p>
	 * 如果字符串为<code>null</code>，返回0.0f
	 * </p>
	 * 
	 * <pre>
	 *   NumberUtils.toFloat(null)   = 0.0f
	 *   NumberUtils.toFloat("")     = 0.0f
	 *   NumberUtils.toFloat("1.5")  = 1.5f
	 * </pre>
	 * 
	 * @param str 要转换的字符串，可以为null，为null将返回0.0f
	 * @return 字符串的float表示，字符串为null或转换失败时将返回0.0f
	 * @since 1.0.0
	 * @author 唐玮琳
	 * @time 2013-5-11 下午10:52:27
	 */
	public static float toFloat(String str) {
		return NumberUtils.toFloat(str);
	}

	/**
	 * <p>
	 * 将一个<code>String</code>转换为<code>float</code>， 如果转换失败返回指定的默认值
	 * </p>
	 * 
	 * <p>
	 * 如果字符串为<code>null</code>，返回指定的默认值
	 * </p>
	 * 
	 * <pre>
	 *   NumberUtils.toFloat(null, 1.1f)   = 1.0f
	 *   NumberUtils.toFloat("", 1.1f)     = 1.1f
	 *   NumberUtils.toFloat("1.5", 0.0f)  = 1.5f
	 * </pre>
	 * 
	 * @param str 要转换的字符串，可以为null，如果为null将返回指定的默认值
	 * @param defaultValue 字符串为null时返回的默认值
	 * @return 字符串的float表示，字符串为null或转换失败时将返回指定的默认值
	 * @since 1.0.0
	 * @author 唐玮琳
	 * @time 2013-5-11 下午10:53:58
	 */
	public static float toFloat(String str, float defaultValue) {
		return NumberUtils.toFloat(str, defaultValue);
	}

	/**
	 * <p>
	 * 将一个<code>String</code>转换为<code>double</code>， 如果转换失败返回0.0d
	 * </p>
	 * 
	 * <p>
	 * 如果字符串为<code>null</code>，返回0.0d
	 * </p>
	 * 
	 * <pre>
	 *   NumberUtils.toDouble(null)   = 0.0d
	 *   NumberUtils.toDouble("")     = 0.0d
	 *   NumberUtils.toDouble("1.5")  = 1.5d
	 * </pre>
	 * 
	 * @param str 要转换的字符串，可以为null，为null将返回0.0d
	 * @return 字符串的double表示，字符串为null或转换失败时将返回0.0d
	 * @since 1.0.0
	 * @author 唐玮琳
	 * @time 2013-5-11 下午10:55:09
	 */
	public static double toDouble(String str) {
		return NumberUtils.toDouble(str);
	}

	/**
	 * <p>
	 * 将一个<code>String</code>转换为<code>double</code>， 如果转换失败返回指定的默认值
	 * </p>
	 * 
	 * <p>
	 * 如果字符串为<code>null</code>，返回指定的默认值
	 * </p>
	 * 
	 * <pre>
	 *   NumberUtils.toDouble(null, 1.1d)   = 1.1d
	 *   NumberUtils.toDouble("", 1.1d)     = 1.1d
	 *   NumberUtils.toDouble("1.5", 0.0d)  = 1.5d
	 * </pre>
	 * 
	 * @param str 要转换的字符串，可以为null，如果为null将返回指定的默认值
	 * @param defaultValue 字符串为null时返回的默认值
	 * @return 字符串的double表示，字符串为null或转换失败时将返回指定的默认值
	 * @since 1.0.0
	 * @author 唐玮琳
	 * @time 2013-5-11 下午10:56:34
	 */
	public static double toDouble(String str, double defaultValue) {
		return NumberUtils.toDouble(str, defaultValue);
	}

	// -----------------------------------------------------------------------
	/**
	 * <p>
	 * 将一个<code>String</code>转换为<code>byte</code>， 如果转换失败返回0
	 * </p>
	 * 
	 * <p>
	 * 如果字符串为<code>null</code>，返回0
	 * </p>
	 * 
	 * <pre>
	 *   NumberUtils.toByte(null) = 0
	 *   NumberUtils.toByte("")   = 0
	 *   NumberUtils.toByte("1")  = 1
	 * </pre>
	 * 
	 * @param str 要转换的字符串，可以为null，为null将返回0
	 * @return 字符串的byte表示，字符串为null或转换失败时将返回0
	 * @since 1.0.0
	 * @author 唐玮琳
	 * @time 2013-5-11 下午10:59:00
	 */
	public static byte toByte(String str) {
		return NumberUtils.toByte(str);
	}

	/**
	 * <p>
	 * 将一个<code>String</code>转换为<code>byte</code>， 如果转换失败返回指定的默认值
	 * </p>
	 * 
	 * <p>
	 * 如果字符串为<code>null</code>，返回指定的默认值
	 * </p>
	 * 
	 * <pre>
	 *   NumberUtils.toByte(null, 1) = 1
	 *   NumberUtils.toByte("", 1)   = 1
	 *   NumberUtils.toByte("1", 0)  = 1
	 * </pre>
	 * 
	 * @param str 要转换的字符串，可以为null，如果为null将返回指定的默认值
	 * @param defaultValue 字符串为null时返回的默认值
	 * @return 字符串的byte表示，字符串为null或转换失败时将返回指定的默认值
	 * @since 1.0.0
	 * @author 唐玮琳
	 * @time 2013-5-11 下午11:00:04
	 */
	public static byte toByte(String str, byte defaultValue) {
		return NumberUtils.toByte(str, defaultValue);
	}

	/**
	 * <p>
	 * 将一个<code>String</code>转换为<code>short</code>， 如果转换失败返回0
	 * </p>
	 * 
	 * <p>
	 * 如果字符串为<code>null</code>，返回0
	 * </p>
	 * 
	 * <pre>
	 *   NumberUtils.toShort(null) = 0
	 *   NumberUtils.toShort("")   = 0
	 *   NumberUtils.toShort("1")  = 1
	 * </pre>
	 * 
	 * @param str 要转换的字符串，可以为null，为null将返回0
	 * @return 字符串的short表示，字符串为null或转换失败时将返回0
	 * @since 1.0.0
	 * @author 唐玮琳
	 * @time 2013-5-11 下午11:02:07
	 */
	public static short toShort(String str) {
		return NumberUtils.toShort(str);
	}

	/**
	 * <p>
	 * 将一个<code>String</code>转换为<code>short</code>， 如果转换失败返回指定的默认值
	 * </p>
	 * 
	 * <p>
	 * 如果字符串为<code>null</code>，返回指定的默认值
	 * </p>
	 * 
	 * <pre>
	 *   NumberUtils.toShort(null, 1) = 1
	 *   NumberUtils.toShort("", 1)   = 1
	 *   NumberUtils.toShort("1", 0)  = 1
	 * </pre>
	 * 
	 * @param str 要转换的字符串，可以为null，为null将返回0
	 * @param 字符串为null时返回的默认值
	 * @return 字符串的short表示，字符串为null或转换失败时将返回指定的默认值
	 * @since 1.0.0
	 * @author 唐玮琳
	 * @time 2013-5-11 下午11:08:39
	 */
	public static short toShort(String str, short defaultValue) {
		return NumberUtils.toShort(str, defaultValue);
	}

	// -----------------------------------------------------------------------

	/**
	 * <p>
	 * 将字符串转换为java.lang.Number
	 * </p>
	 * 
	 * <p>
	 * 首先，将检查给定值的结尾类型限定符<code>'f','F','d','D','l','L'</code>。
	 * 如果找到，开始尝试从指定的类型逐个创建更大的类型，直到找到一个能表示该值的类型。
	 * </p>
	 * 
	 * <p>
	 * 如果一个类型说明符也没有找到，它会检查小数点，然后从小到大地尝试类型，
	 * 从Integer到BigInteger，从Float的BigDecimal
	 * </p>
	 * 
	 * <p>
	 * 一个字符串以<code>0x</code> 或 <code>-0x</code>(大写或小写)开头，它将被解释为十六进制整数。
	 * 以<code>0</code>开头的则被解释为八进制。
	 * </p>
	 * 
	 * <p>
	 * 如果参数为 <code>null</code> 将返回 <code>null</code>.
	 * </p>
	 * 
	 * <p>
	 * 该方法不会对输入的字符串作trim操作。
	 * 如：字符串含有前导或后导空格将抛出NumberFormatException异常.
	 * </p>
	 * 
	 * @param str 数值的字符串形式, 可以为null
	 * @return 字符串所代表的数值，为 <code>null</code> 将返回 <code>null</code>
	 * @throws NumberFormatException 如果字符串不能被转换
	 * @since 1.0.0
	 * @author 唐玮琳
	 * @time 2013-5-12 下午11:13:17
	 */
	public static Number createNumber(String str) throws NumberFormatException {
		return NumberUtils.createNumber(str);
	}

	// -----------------------------------------------------------------------
	/**
	 * <p>
	 * 将一个<code>String</code>转换为<code>Float</code>
	 * </p>
	 * 
	 * <p>
	 * 如果参数为 <code>null</code> 将返回 <code>null</code>.
	 * </p>
	 * 
	 * @param str 待转换的字符串, 可以为null，为null将返回null
	 * @return 转换后的 <code>Float</code> 值，参数为 <code>null</code> 将返回 <code>null</code>
	 * @throws NumberFormatException 如果字符串不能被转换
	 * @since 1.0.0
	 * @author 唐玮琳
	 * @time 2013-5-12 下午11:15:22
	 */
	public static Float createFloat(String str) {
		return NumberUtils.createFloat(str);
	}

	/**
	 * <p>
	 * 将一个<code>String</code>转换为<code>Double</code>
	 * </p>
	 * 
	 * <p>
	 * 如果参数为 <code>null</code> 将返回 <code>null</code>.
	 * </p>
	 * 
	 * @param str 待转换的字符串, 可以为null，为null将返回null
	 * @return 转换后的 <code>Double</code> 值，参数为 <code>null</code> 将返回 <code>null</code>
	 * @throws NumberFormatException 如果字符串不能被转换
	 * @since 1.0.0
	 * @author 唐玮琳
	 * @time 2013-5-12 下午11:15:22
	 */
	public static Double createDouble(String str) {
		return NumberUtils.createDouble(str);
	}

	/**
	 * <p>
	 * 将一个<code>String</code>转换为<code>Integer</code>
	 * </p>
	 * 
	 * <p>
	 * 如果参数为 <code>null</code> 将返回 <code>null</code>.
	 * </p>
	 * 
	 * @param str 待转换的字符串, 可以为null，为null将返回null
	 * @return 转换后的 <code>Integer</code> 值，参数为 <code>null</code> 将返回 <code>null</code>
	 * @throws NumberFormatException 如果字符串不能被转换
	 * @since 1.0.0
	 * @author 唐玮琳
	 * @time 2013-5-12 下午11:15:22
	 */
	public static Integer createInteger(String str) {
		return NumberUtils.createInteger(str);
	}

	/**
	 * <p>
	 * 将一个<code>String</code>转换为<code>Long</code>
	 * </p>
	 * 
	 * <p>
	 * 如果参数为 <code>null</code> 将返回 <code>null</code>.
	 * </p>
	 * 
	 * @param str 待转换的字符串, 可以为null，为null将返回null
	 * @return 转换后的 <code>Long</code> 值，参数为 <code>null</code> 将返回 <code>null</code>
	 * @throws NumberFormatException 如果字符串不能被转换
	 * @since 1.0.0
	 * @author 唐玮琳
	 * @time 2013-5-12 下午11:15:22
	 */
	public static Long createLong(String str) {
		return NumberUtils.createLong(str);
	}

	/**
	 * <p>
	 * 将一个<code>String</code>转换为<code>BigInteger</code>
	 * </p>
	 * 
	 * <p>
	 * 如果参数为 <code>null</code> 将返回 <code>null</code>.
	 * </p>
	 * 
	 * @param str 待转换的字符串, 可以为null，为null将返回null
	 * @return 转换后的 <code>BigInteger</code> 值，参数为 <code>null</code> 将返回 <code>null</code>
	 * @throws NumberFormatException 如果字符串不能被转换
	 * @since 1.0.0
	 * @author 唐玮琳
	 * @time 2013-5-12 下午11:15:22
	 */
	public static BigInteger createBigInteger(String str) {
		return NumberUtils.createBigInteger(str);
	}

	/**
	 * <p>
	 * 将一个<code>String</code>转换为<code>BigDecimal</code>
	 * </p>
	 * 
	 * <p>
	 * 如果参数为 <code>null</code> 将返回 <code>null</code>.
	 * </p>
	 * 
	 * @param str 待转换的字符串, 可以为null，为null将返回null
	 * @return 转换后的 <code>BigDecimal</code> 值，参数为 <code>null</code> 将返回 <code>null</code>
	 * @throws NumberFormatException 如果字符串不能被转换
	 * @since 1.0.0
	 * @author 唐玮琳
	 * @time 2013-5-12 下午11:15:22
	 */
	public static BigDecimal createBigDecimal(String str) {
		return NumberUtils.createBigDecimal(str);
	}

	// Min in array
	// --------------------------------------------------------------------
	/**
	 * <p>
	 * 返回数组中的最小元素
	 * </p>
	 * 
	 * @param array 待检测的数组, 不能为null或空
	 * @return 数组中的最小元素
	 * @throws IllegalArgumentException 如果数组为null或空
	 * @since 1.0.0
	 * @author 唐玮琳
	 * @time 2013-5-12 下午11:18:15
	 */
	public static long min(long[] array) {
		return NumberUtils.min(array);
	}

	/**
	 * <p>
	 * 返回数组中的最小元素
	 * </p>
	 * 
	 * @param array 待检测的数组, 不能为null或空
	 * @return 数组中的最小元素
	 * @throws IllegalArgumentException 如果数组为null或空
	 * @since 1.0.0
	 * @author 唐玮琳
	 * @time 2013-5-12 下午11:18:15
	 */
	public static int min(int[] array) {
		return NumberUtils.min(array);
	}

	/**
	 * <p>
	 * 返回数组中的最小元素
	 * </p>
	 * 
	 * @param array 待检测的数组, 不能为null或空
	 * @return 数组中的最小元素
	 * @throws IllegalArgumentException 如果数组为null或空
	 * @since 1.0.0
	 * @author 唐玮琳
	 * @time 2013-5-12 下午11:18:15
	 */
	public static short min(short[] array) {
		return NumberUtils.min(array);
	}

	/**
	 * <p>
	 * 返回数组中的最小元素
	 * </p>
	 * 
	 * @param array 待检测的数组, 不能为null或空
	 * @return 数组中的最小元素
	 * @throws IllegalArgumentException 如果数组为null或空
	 * @since 1.0.0
	 * @author 唐玮琳
	 * @time 2013-5-12 下午11:18:15
	 */
	public static byte min(byte[] array) {
		return NumberUtils.min(array);
	}

	/**
	 * <p>
	 * 返回数组中的最小元素
	 * </p>
	 * 
	 * @param array 待检测的数组, 不能为null或空
	 * @return 数组中的最小元素
	 * @throws IllegalArgumentException 如果数组为null或空
	 * @since 1.0.0
	 * @author 唐玮琳
	 * @time 2013-5-12 下午11:18:15
	 */
	public static double min(double[] array) {
		return NumberUtils.min(array);
	}

	/**
	 * <p>
	 * 返回数组中的最小元素
	 * </p>
	 * 
	 * @param array 待检测的数组, 不能为null或空
	 * @return 数组中的最小元素
	 * @throws IllegalArgumentException 如果数组为null或空
	 * @since 1.0.0
	 * @author 唐玮琳
	 * @time 2013-5-12 下午11:18:15
	 */
	public static float min(float[] array) {
		return NumberUtils.min(array);
	}

	// Max in array
	// --------------------------------------------------------------------
	/**
	 * <p>
	 * 返回数组中的最大元素
	 * </p>
	 * 
	 * @param array 待检测的数组, 不能为null或空
	 * @return 数组中的最大小元素
	 * @throws IllegalArgumentException 如果数组为null或空
	 * @since 1.0.0
	 * @author 唐玮琳
	 * @time 2013-5-12 下午11:18:15
	 */
	public static long max(long[] array) {
		return NumberUtils.max(array);
	}

	/**
	 * <p>
	 * 返回数组中的最大元素
	 * </p>
	 * 
	 * @param array 待检测的数组, 不能为null或空
	 * @return 数组中的最大小元素
	 * @throws IllegalArgumentException 如果数组为null或空
	 * @since 1.0.0
	 * @author 唐玮琳
	 * @time 2013-5-12 下午11:18:15
	 */
	public static int max(int[] array) {
		return NumberUtils.max(array);
	}

	/**
	 * <p>
	 * 返回数组中的最大元素
	 * </p>
	 * 
	 * @param array 待检测的数组, 不能为null或空
	 * @return 数组中的最大小元素
	 * @throws IllegalArgumentException 如果数组为null或空
	 * @since 1.0.0
	 * @author 唐玮琳
	 * @time 2013-5-12 下午11:18:15
	 */
	public static short max(short[] array) {
		return NumberUtils.max(array);
	}

	/**
	 * <p>
	 * 返回数组中的最大元素
	 * </p>
	 * 
	 * @param array 待检测的数组, 不能为null或空
	 * @return 数组中的最大小元素
	 * @throws IllegalArgumentException 如果数组为null或空
	 * @since 1.0.0
	 * @author 唐玮琳
	 * @time 2013-5-12 下午11:18:15
	 */
	public static byte max(byte[] array) {
		return NumberUtils.max(array);
	}

	/**
	 * <p>
	 * 返回数组中的最大元素
	 * </p>
	 * 
	 * @param array 待检测的数组, 不能为null或空
	 * @return 数组中的最大小元素
	 * @throws IllegalArgumentException 如果数组为null或空
	 * @since 1.0.0
	 * @author 唐玮琳
	 * @time 2013-5-12 下午11:18:15
	 */
	public static double max(double[] array) {
		return NumberUtils.max(array);
	}

	/**
	 * <p>
	 * 返回数组中的最大元素
	 * </p>
	 * 
	 * @param array 待检测的数组, 不能为null或空
	 * @return 数组中的最大小元素
	 * @throws IllegalArgumentException 如果数组为null或空
	 * @since 1.0.0
	 * @author 唐玮琳
	 * @time 2013-5-12 下午11:18:15
	 */
	public static float max(float[] array) {
		return NumberUtils.max(array);
	}

	// 3 param min
	// -----------------------------------------------------------------------
	/**
	 * <p>
	 * 返回三个值中的最小值
	 * </p>
	 * 
	 * @param a 第一个值
	 * @param b 第二个值
	 * @param c 第三个值
	 * @return 最小值
	 * @since 1.0.0
	 * @author 唐玮琳
	 * @time 2013-5-12 下午11:20:05
	 */
	public static long min(long a, long b, long c) {
		return NumberUtils.min(a, b, c);
	}

	/**
	 * <p>
	 * 返回三个值中的最小值
	 * </p>
	 * 
	 * @param a 第一个值
	 * @param b 第二个值
	 * @param c 第三个值
	 * @return 最小值
	 * @since 1.0.0
	 * @author 唐玮琳
	 * @time 2013-5-12 下午11:20:05
	 */
	public static int min(int a, int b, int c) {
		return NumberUtils.min(a, b, c);
	}

	/**
	 * <p>
	 * 返回三个值中的最小值
	 * </p>
	 * 
	 * @param a 第一个值
	 * @param b 第二个值
	 * @param c 第三个值
	 * @return 最小值
	 * @since 1.0.0
	 * @author 唐玮琳
	 * @time 2013-5-12 下午11:20:05
	 */
	public static short min(short a, short b, short c) {
		return NumberUtils.min(a, b, c);
	}

	/**
	 * <p>
	 * 返回三个值中的最小值
	 * </p>
	 * 
	 * @param a 第一个值
	 * @param b 第二个值
	 * @param c 第三个值
	 * @return 最小值
	 * @since 1.0.0
	 * @author 唐玮琳
	 * @time 2013-5-12 下午11:20:05
	 */
	public static byte min(byte a, byte b, byte c) {
		return NumberUtils.min(a, b, c);
	}

	/**
	 * <p>
	 * 返回三个值中的最小值
	 * </p>
	 * 
	 * <p>
	 * 如果任何参数为<code>NaN</code>，将返回<code>NaN</code>。支持无穷大/小
	 * </p>
	 * 
	 * @param a 第一个值
	 * @param b 第二个值
	 * @param c 第三个值
	 * @return 最小值
	 * @see IEEE754rUtils#min(double, double, double) 
	 * @since 1.0.0
	 * @author 唐玮琳
	 * @time 2013-5-12 下午11:20:05
	 */
	public static double min(double a, double b, double c) {
		return NumberUtils.min(a, b, c);
	}

	/**
	 * <p>
	 * 返回三个值中的最小值
	 * </p>
	 * 
	 * <p>
	 * 如果任何参数为<code>NaN</code>，将返回<code>NaN</code>。支持无穷大/小
	 * </p>
	 * 
	 * @param a 第一个值
	 * @param b 第二个值
	 * @param c 第三个值
	 * @return 最小值
	 * @see IEEE754rUtils#min(float, float, double) 
	 * @since 1.0.0
	 * @author 唐玮琳
	 * @time 2013-5-12 下午11:20:05
	 */
	public static float min(float a, float b, float c) {
		return NumberUtils.min(a, b, c);
	}

	// 3 param max
	// -----------------------------------------------------------------------
	/**
	 * <p>
	 * 返回三个值中的最大值
	 * </p>
	 * 
	 * @param a 第一个值
	 * @param b 第二个值
	 * @param c 第三个值
	 * @return 最大值
	 * @since 1.0.0
	 * @author 唐玮琳
	 * @time 2013-5-12 下午11:20:05
	 */
	public static long max(long a, long b, long c) {
		return NumberUtils.min(a, b, c);
	}

	/**
	 * <p>
	 * 返回三个值中的最大值
	 * </p>
	 * 
	 * @param a 第一个值
	 * @param b 第二个值
	 * @param c 第三个值
	 * @return 最大值
	 * @since 1.0.0
	 * @author 唐玮琳
	 * @time 2013-5-12 下午11:20:05
	 */
	public static int max(int a, int b, int c) {
		return NumberUtils.min(a, b, c);
	}

	/**
	 * <p>
	 * 返回三个值中的最大值
	 * </p>
	 * 
	 * @param a 第一个值
	 * @param b 第二个值
	 * @param c 第三个值
	 * @return 最大值
	 * @since 1.0.0
	 * @author 唐玮琳
	 * @time 2013-5-12 下午11:20:05
	 */
	public static short max(short a, short b, short c) {
		return NumberUtils.min(a, b, c);
	}

	/**
	 * <p>
	 * 返回三个值中的最大值
	 * </p>
	 * 
	 * @param a 第一个值
	 * @param b 第二个值
	 * @param c 第三个值
	 * @return 最大值
	 * @since 1.0.0
	 * @author 唐玮琳
	 * @time 2013-5-12 下午11:20:05
	 */
	public static byte max(byte a, byte b, byte c) {
		return NumberUtils.min(a, b, c);
	}

	/**
	 * <p>
	 * 返回三个值中的最大值
	 * </p>
	 * 
	 * <p>
	 * 如果任何参数为<code>NaN</code>，将返回<code>NaN</code>。支持无穷大/小
	 * </p>
	 * 
	 * @param a 第一个值
	 * @param b 第二个值
	 * @param c 第三个值
	 * @return 最大值
	 * @see IEEE754rUtils#max(double, double, double) 
	 * @since 1.0.0
	 * @author 唐玮琳
	 * @time 2013-5-12 下午11:20:05
	 */
	public static double max(double a, double b, double c) {
		return NumberUtils.max(a, b, c);
	}

	/**
	 * <p>
	 * 返回三个值中的最大值
	 * </p>
	 * 
	 * <p>
	 * 如果任何参数为<code>NaN</code>，将返回<code>NaN</code>。支持无穷大/小
	 * </p>
	 * 
	 * @param a 第一个值
	 * @param b 第二个值
	 * @param c 第三个值
	 * @return 最大值
	 * @see IEEE754rUtils#max(float, float, float) 
	 * @since 1.0.0
	 * @author 唐玮琳
	 * @time 2013-5-12 下午11:20:05
	 */
	public static float max(float a, float b, float c) {
		return NumberUtils.max(a, b, c);
	}

	// -----------------------------------------------------------------------
	/**
	 * <p>
	 * 检查指定的字符串是否只包含数字字符
	 * </p>
	 * 
	 * <p>
	 * <code>Null</code> 或 空串将返回 <code>false</code>.
	 * </p>
	 * 
	 * @param str 待检查的字符串
	 * @return <code>true</code> 指定的字符串只包含Unicode的数字字符
	 * @since 1.0.0
	 * @author 唐玮琳
	 * @time 2013-5-12 下午11:25:11
	 */
	public static boolean isDigits(String str) {
		return NumberUtils.isDigits(str);
	}

	/**
	 * <p>
	 * 检查指定的字符串是否只为java的数值
	 * </p>
	 * 
	 * <p>
	 * 有效的数值包括以限定符<code>0x</code>开头的十六进制数，科学记数法和
	 * 以类型限定符结尾的数值（如：123L）
	 * </p>
	 * 
	 * <p>
	 * <code>Null</code> 或 空串将返回 <code>false</code>.
	 * </p>
	 * 
	 * @param str 待检查的字符串
	 * @return <code>true</code> 如果指定的字符串为一个正确格式的数值
	 * @since 1.0.0
	 * @author 唐玮琳
	 * @time 2013-5-12 下午11:28:14
	 */
	public static boolean isNumber(String str) {
		return NumberUtils.isNumber(str);
	}
	
	// ^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^
	// 封装org.apache.commons.lang3.math.NumberUtils
	// ^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^

}

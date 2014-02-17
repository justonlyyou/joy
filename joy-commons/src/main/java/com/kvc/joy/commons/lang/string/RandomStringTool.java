package com.kvc.joy.commons.lang.string;

import java.security.SecureRandom;
import java.util.Random;
import java.util.UUID;

/**
 * 随机字符串工具类
 * 
 * @since 1.0.0
 * @author 唐玮琳
 * @time 2013-4-9 下午8:49:38
 */
public class RandomStringTool {

	private RandomStringTool() {
	}

	private static final SecureRandom random = new SecureRandom();

	/**
	 * <p>
	 * 封装JDK自带的UUID, 中间无"-"分割.
	 * </p>
	 * 
	 * @return 中间无"-"分割的UUID
	 * @since 1.0.0
	 * @author calvin
	 * @author 唐玮琳
	 * @time 2013-5-18 下午5:09:52
	 */
	public static String uuid() {
		return UUID.randomUUID().toString().replaceAll("-", "");
	}

	/**
	 * <p>
	 * 使用SecureRandom随机生成Long.
	 * </p>
	 * 
	 * @return 随机Long
	 * @since 1.0.0
	 * @author calvin
	 * @author 唐玮琳
	 * @time 2013-5-18 下午5:11:16
	 */
	public static long randomLong() {
		return Math.abs(random.nextLong());
	}

	/**
	 * <p>
	 * 基于Base62编码的SecureRandom随机生成bytes.
	 * </p>
	 * 
	 * @param length 长度
	 * @return Base62编码的字符串
	 * @since 1.0.0
	 * @author calvin
	 * @author 唐玮琳
	 * @time 2013-5-18 下午5:12:18
	 */
	public static String randomBase62(int length) {
		byte[] randomBytes = new byte[length];
		random.nextBytes(randomBytes);
		return EncodeTool.encodeBase62(randomBytes);
	}

	// vvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvv
	// 封装org.apache.commons.lang3.RandomStringUtils
	// vvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvv

	/**
	 * <p>
	 * 创建一个指定长度的随机串
	 * </p>
	 * 
	 * <p>
	 * 字符将从字符集中选择
	 * </p>
	 * 
	 * @param count 要创建的随机串的长度
	 * @return 随机串
	 * @since 1.0.0
	 * @author 唐玮琳
	 * @time 2013-5-1 下午8:23:52
	 */
	public static String random(int count) {
		return org.apache.commons.lang3.RandomStringUtils.random(count);
	}

	/**
	 * <p>
	 * 创建一个指定长度的随机串
	 * </p>
	 * 
	 * <p>
	 * 字符将从ASCII码的{@code 32} 到 {@code 126}中选择
	 * </p>
	 * 
	 * @param count 要创建的随机串的长度
	 * @return 随机串
	 * @since 1.0.0
	 * @author 唐玮琳
	 * @time 2013-5-1 下午8:28:43
	 */
	public static String randomAscii(int count) {
		return org.apache.commons.lang3.RandomStringUtils.randomAscii(count);
	}

	/**
	 * <p>
	 * 创建一个指定长度的随机串
	 * </p>
	 * 
	 * <p>
	 * 字符将从字母中选择
	 * </p>
	 * 
	 * @param count 要创建的随机串的长度
	 * @return 随机串
	 * @since 1.0.0
	 * @author 唐玮琳
	 * @time 2013-5-1 下午8:31:28
	 */
	public static String randomAlphabetic(int count) {
		return org.apache.commons.lang3.RandomStringUtils.randomAlphabetic(count);
	}

	/**
	 * <p>
	 * 创建一个指定长度的随机串
	 * </p>
	 * 
	 * <p>
	 * 字符将从字母或数字中选择
	 * </p>
	 * 
	 * @param count 要创建的随机串的长度
	 * @return 随机串
	 * @since 1.0.0
	 * @author 唐玮琳
	 * @time 2013-5-1 下午8:32:38
	 */
	public static String randomAlphanumeric(int count) {
		return org.apache.commons.lang3.RandomStringUtils.randomAlphanumeric(count);
	}

	/**
	 * <p>
	 * 创建一个指定长度的随机串
	 * </p>
	 * 
	 * <p>
	 * 字符将从数字中选择
	 * </p>
	 * 
	 * @param count 要创建的随机串的长度
	 * @return 随机串
	 * @since 1.0.0
	 * @author 唐玮琳
	 * @time 2013-5-1 下午8:33:01
	 */
	public static String randomNumeric(int count) {
		return org.apache.commons.lang3.RandomStringUtils.randomNumeric(count);
	}

	/**
	 * <p>
	 * 创建一个指定长度的随机串
	 * </p>
	 * 
	 * <p>
	 * 字符将从字母或数字中选择
	 * </p>
	 * 
	 * @param count 要创建的随机串的长度
	 * @param letters 如果为 {@code true}, 生成的字符将从字母中选择
	 * @param numbers 如果为 {@code true}, 生成的字符将从数字中选择
	 * @return 随机串
	 * @since 1.0.0
	 * @author 唐玮琳
	 * @time 2013-5-1 下午8:35:31
	 */
	public static String random(int count, boolean letters, boolean numbers) {
		return org.apache.commons.lang3.RandomStringUtils.random(count, letters, numbers);
	}

	/**
	 * <p>
	 * 创建一个指定长度的随机串
	 * </p>
	 * 
	 * <p>
	 * 字符将从字母或数字中选择
	 * </p>
	 * 
	 * @param count 要创建的随机串的长度
	 * @param start 字符集范围的开始位置
	 * @param end 字符集范围的结束位置
	 * @param letters 如果为 {@code true}, 生成的字符将从字母中选择
	 * @param numbers 如果为 {@code true}, 生成的字符将从数字中选择
	 * @return 随机串
	 * @since 1.0.0
	 * @author 唐玮琳
	 * @time 2013-5-1 下午8:38:51
	 */
	public static String random(int count, int start, int end, boolean letters, boolean numbers) {
		return org.apache.commons.lang3.RandomStringUtils.random(count, start, end, letters, numbers);
	}

	/**
	 * <p>
	 * 创建一个随机串, 使用默认的随机源
	 * </p>
	 * 
	 * <p>
	 * 该方法与{@link #random(int,int,int,boolean,boolean,char[],Random)}具有相同的语义,
	 * 但是不使用外部提供的随机源, 它使用内部静态的{@link Random}实例.
	 * </p>
	 * 
	 * @param count 要创建的随机串的长度
	 * @param start 字符集范围的开始位置
	 * @param end 字符集范围的结束位置
	 * @param letters 只允许字母?
	 * @param numbers 只允许数字?
	 * @param 字符集. 如果为 {@code null}, 将使用所有字符
	 * @return 随机串
	 * @throws ArrayIndexOutOfBoundsException 如果指定字符集中的元素不足
	 *             {@code (end - start) + 1} 个
	 * @since 1.0.0
	 * @author 唐玮琳
	 * @time 2013-5-1 下午8:47:32
	 */
	public static String random(int count, int start, int end, boolean letters, boolean numbers, char... chars) {
		return org.apache.commons.lang3.RandomStringUtils.random(count, start, end, letters, numbers, chars);
	}

	/**
	 * <p>
	 * 创建一个随机串, 使用提供的随机源
	 * </p>
	 * 
	 * <p>
	 * 如果{@code start} 和 {@code end}都为{@code 0}, {@code start} 和 {@code end}
	 * 将分别被设置为 {@code ' '} 和 {@code 'z'}, ASCII码可打印字段将被使用, 除非{@code letters} 和
	 * {@code numbers}都为{@code false}, 这样{@code start} 和 {@code end}将 分别被设置为
	 * {@code 0} 和 {@code Integer.MAX_VALUE}. 如果{@code chars}不为{@code null},
	 * {@code start} 到 {@code end} 间的字符将被选择
	 * <p>
	 * 
	 * <p>
	 * 该方法接受一个用户提供的随机源{@link Random}实例. 每次调用时使用同一个随机源和固定的种子, 相同的随机串将重复产生并且是可预见的.
	 * </p>
	 * 
	 * @param count 要创建的随机串的长度
	 * @param start 字符集范围的开始位置
	 * @param end 字符集范围的结束位置
	 * @param letters 只允许字母?
	 * @param numbers 只允许数字?
	 * @param 字符集. 如果为 {@code null}, 将使用所有字符
	 * @param 随机源
	 * @return 随机串
	 * @throws ArrayIndexOutOfBoundsException 如果指定字符集中的元素不足
	 *             {@code (end - start) + 1} 个
	 * @throws IllegalArgumentException 如果 {@code count} &lt; 0.
	 * @since 1.0.0
	 * @author 唐玮琳
	 * @time 2013-5-1 下午8:49:09
	 */
	public static String random(int count, int start, int end, boolean letters, boolean numbers, char[] chars,
			Random random) {
		return org.apache.commons.lang3.RandomStringUtils.random(count, start, end, letters, numbers, chars, random);
	}

	/**
	 * <p>
	 * 创建一个指定长度的随机串
	 * </p>
	 * 
	 * <p>
	 * 字符将从指定的字符串中选择
	 * </p>
	 * 
	 * @param count 要创建的随机串的长度
	 * @param chars 提供字符的字符串, 可以为 null
	 * @return 随机串
	 * @throws IllegalArgumentException 如果 {@code count} &lt; 0.
	 * @since 1.0.0
	 * @author 唐玮琳
	 * @time 2013-5-1 下午9:01:34
	 */
	public static String random(int count, String chars) {
		return org.apache.commons.lang3.RandomStringUtils.random(count, chars);
	}

	/**
	 * <p>
	 * 创建一个指定长度的随机串
	 * </p>
	 * 
	 * <p>
	 * 字符将从指定的字符数组中选择
	 * </p>
	 * 
	 * @param count 要创建的随机串的长度
	 * @param chars 提供字符的字符数组, 可以为 null
	 * @return 随机串
	 * @throws IllegalArgumentException 如果 {@code count} &lt; 0.
	 * @since 1.0.0
	 * @author 唐玮琳
	 * @time 2013-5-1 下午9:01:42
	 */
	public static String random(int count, char... chars) {
		return org.apache.commons.lang3.RandomStringUtils.random(count, chars);
	}

	// ^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^
	// 封装org.apache.commons.lang3.RandomStringUtils
	// ^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^

}

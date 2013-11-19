package com.kvc.joy.commons.security;

import java.io.IOException;
import java.io.InputStream;
import java.security.GeneralSecurityException;
import java.security.MessageDigest;
import java.security.SecureRandom;

import org.apache.commons.lang3.Validate;

import com.kvc.joy.commons.exception.SystemException;

/**
 * <p>
 * 支持SHA-1/MD5消息摘要的工具类
 * </p>
 * 
 * <p>
 * 返回ByteSource，可进一步被编码为Hex, Base64或UrlSafeBase64
 * </p>
 * 
 * @since 1.0.0
 * @author 唐玮琳
 * @time 2013-5-18 下午4:25:48
 */
public class DigestTool {

	public static final String SHA1 = "SHA-1";
	public static final String MD5 = "MD5";

	private static SecureRandom random = new SecureRandom();

	/**
	 * <p>
	 * 对输入字符串字节数组进行sha1散列.
	 * </p>
	 * 
	 * @param input 字符串字节数组
	 * @return 进行sha1散列后的字节数组
	 * @since 1.0.0
	 * @author calvin
	 * @author 唐玮琳
	 * @time 2013-5-18 下午4:29:08
	 */
	public static byte[] sha1(byte[] input) {
		return digest(input, SHA1, null, 1);
	}

	/**
	 * <p>
	 * 对输入字符串字节数组进行sha1散列.
	 * </p>
	 * 
	 * @param input 字符串字节数组
	 * @param salt 加盐值字节数组
	 * @return 进行sha1散列后的字节数组
	 * @since 1.0.0
	 * @author calvin
	 * @author 唐玮琳
	 * @time 2013-5-18 下午4:30:27
	 */
	public static byte[] sha1(byte[] input, byte[] salt) {
		return digest(input, SHA1, salt, 1);
	}

	/**
	 * <p>
	 * 对输入字符串字节数组进行sha1散列.
	 * </p>
	 * 
	 * @param input 字符串字节数组
	 * @param salt 加盐值字节数组
	 * @param iterations 迭代次数
	 * @return 进行sha1散列后的字节数组
	 * @since 1.0.0
	 * @author calvin
	 * @author 唐玮琳
	 * @time 2013-5-18 下午4:33:07
	 */
	public static byte[] sha1(byte[] input, byte[] salt, int iterations) {
		return digest(input, SHA1, salt, iterations);
	}

	/**
	 * <p>
	 * 对字符串进行散列, 支持md5与sha1算法.
	 * </p>
	 * 
	 * @param input 字符串字节数组
	 * @param algorithm 算法名称
	 * @param salt 加盐值字节数组
	 * @param iterations 迭代次数
	 * @return 进行散列后的字节数组
	 * @since 1.0.0
	 * @author calvin
	 * @author 唐玮琳
	 * @time 2013-5-18 下午4:36:35
	 */
	public static byte[] digest(byte[] input, String algorithm, byte[] salt, int iterations) {
		try {
			MessageDigest digest = MessageDigest.getInstance(algorithm);

			if (salt != null) {
				digest.update(salt);
			}

			byte[] result = digest.digest(input);

			for (int i = 1; i < iterations; i++) {
				digest.reset();
				result = digest.digest(result);
			}
			return result;
		} catch (GeneralSecurityException e) {
			throw new SystemException(e);
		}
	}

	/**
	 * <p>
	 * 生成随机的Byte[]作为salt.
	 * </p>
	 * 
	 * @param numBytes byte数组的大小
	 * @return 加盐值字节数组
	 * @since 1.0.0
	 * @author calvin
	 * @author 唐玮琳
	 * @time 2013-5-18 下午4:37:54
	 */
	public static byte[] generateSalt(int numBytes) {
		Validate.isTrue(numBytes > 0, "numBytes argument must be a positive integer (1 or larger)", numBytes);

		byte[] bytes = new byte[numBytes];
		random.nextBytes(bytes);
		return bytes;
	}

	/**
	 * <p>
	 * 对文件进行md5散列.
	 * </p>
	 * 
	 * @param input 文件输入流
	 * @return 散列后的文件字节数组
	 * @since 1.0.0
	 * @author calvin
	 * @author 唐玮琳
	 * @time 2013-5-18 下午4:41:04
	 */
	public static byte[] md5(InputStream input) {
		try {
			return digest(input, MD5);
		} catch (IOException e) {
			throw new SystemException(e);
		}
	}

	/**
	 * <p>
	 * 对文件进行sha1散列.
	 * </p>
	 * 
	 * @param input 文件输入流
	 * @return 散列后的文件字节数组
	 * @since 1.0.0
	 * @author calvin
	 * @author 唐玮琳
	 * @time 2013-5-18 下午4:44:57
	 */
	public static byte[] sha1(InputStream input) {
		try {
			return digest(input, SHA1);
		} catch (IOException e) {
			throw new SystemException(e);
		}
	}

	private static byte[] digest(InputStream input, String algorithm) throws IOException {
		try {
			MessageDigest messageDigest = MessageDigest.getInstance(algorithm);
			int bufferLength = 8 * 1024;
			byte[] buffer = new byte[bufferLength];
			int read = input.read(buffer, 0, bufferLength);

			while (read > -1) {
				messageDigest.update(buffer, 0, read);
				read = input.read(buffer, 0, bufferLength);
			}

			return messageDigest.digest();
		} catch (GeneralSecurityException e) {
			throw new SystemException(e);
		}
	}
	
}

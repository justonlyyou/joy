package org.joy.commons.lang.string;

import org.joy.commons.exception.SystemException;
import org.apache.commons.codec.DecoderException;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.binary.Hex;
import org.joy.commons.exception.SystemException;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;

/**
 * <p>
 * 封装各种格式的编码解码工具类.
 * </p>
 * 
 * <p>
 * 1.Commons-Codec的 hex/base64 编码
 * 2.自制的base62 编码
 * 3.JDK提供的URLEncoder
 * </p>
 * 
 * @since 1.0.0
 * @author Kevice
 * @time 2013-5-18 下午4:46:45
 */
public class EncodeTool {

	private static final String DEFAULT_URL_ENCODING = "UTF-8";
	private static final char[] BASE62 = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz".toCharArray();

	/**
	 * <p>
	 * Hex编码
	 * </p>
	 * 
	 * @param input 待Hex编码的字节数组
	 * @return 编码后的字符串
	 * @since 1.0.0
	 * @author calvin
	 * @author Kevice
	 * @time 2013-5-18 下午4:47:25
	 */
	public static String encodeHex(byte[] input) {
		return Hex.encodeHexString(input);
	}

	/**
	 * <p>
	 * Hex解码
	 * </p>
	 * 
	 * @param input 待Hex解码的字符串
	 * @return 解码后的字节数组
	 * @since 1.0.0
	 * @author calvin
	 * @author Kevice
	 * @time 2013-5-18 下午4:48:19
	 */
	public static byte[] decodeHex(String input) {
		try {
			return Hex.decodeHex(input.toCharArray());
		} catch (DecoderException e) {
			throw new SystemException(e);
		}
	}

	/**
	 * <p>
	 * Base64编码
	 * </p>
	 * 
	 * @param input 待Base64编码的字节数组
	 * @return 编码后的字符串
	 * @since 1.0.0
	 * @author calvin
	 * @author Kevice
	 * @time 2013-5-18 下午4:49:44
	 */
	public static String encodeBase64(byte[] input) {
		return Base64.encodeBase64String(input);
	}

	/**
	 * <p>
	 * Base64编码, URL安全(将Base64中的URL非法字符'+'和'/'转为'-'和'_', 见RFC3548).
	 * </p>
	 * 
	 * @param input 待Base64编码的字节数组
	 * @return 编码后的字符串
	 * @since 1.0.0
	 * @author calvin
	 * @author Kevice
	 * @time 2013-5-18 下午4:50:29
	 */
	public static String encodeUrlSafeBase64(byte[] input) {
		return Base64.encodeBase64URLSafeString(input);
	}

	/**
	 * <p>
	 * Base64解码
	 * </p>
	 * 
	 * @param input 待Hex解码的字符串
	 * @return 解码后的字节数组
	 * @since 1.0.0
	 * @author calvin
	 * @author Kevice
	 * @time 2013-5-18 下午4:50:58
	 */
	public static byte[] decodeBase64(String input) {
		return Base64.decodeBase64(input);
	}

	/**
	 * <p>
	 * Base62编码
	 * </p>
	 * 
	 * @param input 待Base62编码的字节数组
	 * @return 编码后的字符串
	 * @since 1.0.0
	 * @author calvin
	 * @author Kevice
	 * @time 2013-5-18 下午4:51:50
	 */
	public static String encodeBase62(byte[] input) {
		char[] chars = new char[input.length];
		for (int i = 0; i < input.length; i++) {
			chars[i] = BASE62[((input[i] & 0xFF) % BASE62.length)];
		}
		return new String(chars);
	}

	/**
	 * <p>
	 * URL 编码, Encode默认为UTF-8. 
	 * </p>
	 * 
	 * @param part 待编码的部分
	 * @return 编码完的字符串
	 * @since 1.0.0
	 * @author calvin
	 * @author Kevice
	 * @time 2013-5-18 下午4:53:44
	 */
	public static String urlEncode(String part) {
		try {
			return URLEncoder.encode(part, DEFAULT_URL_ENCODING);
		} catch (UnsupportedEncodingException e) {
			throw new SystemException(e);
		}
	}

	/**
	 * <p>
	 * URL 解码, Encode默认为UTF-8. 
	 * </p>
	 * 
	 * @param part 待解码的部分
	 * @return 解码后的字符串
	 * @since 1.0.0
	 * @author calvin
	 * @author Kevice
	 * @time 2013-5-18 下午4:54:43
	 */
	public static String urlDecode(String part) {
		try {
			return URLDecoder.decode(part, DEFAULT_URL_ENCODING);
		} catch (UnsupportedEncodingException e) {
			throw new SystemException(e);
		}
	}
}

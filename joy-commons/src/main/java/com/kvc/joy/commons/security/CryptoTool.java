package com.kvc.joy.commons.security;

import com.kvc.joy.commons.exception.SystemException;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.Mac;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.security.GeneralSecurityException;
import java.security.SecureRandom;
import java.util.Arrays;

/**
 * <p>
 * 加密工具类
 * </p>
 * 
 * <p>
 * 支持HMAC-SHA1消息签名 及 DES/AES对称加密.
 * 支持Hex与Base64两种编码方式.
 * </p>
 * 
 * @since 1.0.0
 * @author 唐玮琳
 * @time 2013-5-18 下午4:02:07
 */
public class CryptoTool {

	private static final String AES = "AES";
	private static final String AES_CBC = "AES/CBC/PKCS5Padding";
	private static final String HMACSHA1 = "HmacSHA1";

	private static final int DEFAULT_HMACSHA1_KEYSIZE = 160; //RFC2401
	private static final int DEFAULT_AES_KEYSIZE = 128;
	private static final int DEFAULT_IVSIZE = 16;
	
	private static final char[] DIGITS = {
	        '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'
	};

	private static final SecureRandom random = new SecureRandom();

	//-- HMAC-SHA1 funciton --//
	/**
	 * <p>
	 * 使用HMAC-SHA1进行消息签名, 返回字节数组,长度为20字节
	 * </p>
	 * 
	 * @param input 原始输入字符数组
	 * @param key HMAC-SHA1密钥
	 * @return 签名后的字节数组
	 * @since 1.0.0
	 * @author calvin
	 * @author 唐玮琳
	 * @time 2013-5-18 下午4:04:31
	 */
	public static byte[] hmacSha1(byte[] input, byte[] key) {
		try {
			SecretKey secretKey = new SecretKeySpec(key, HMACSHA1);
			Mac mac = Mac.getInstance(HMACSHA1);
			mac.init(secretKey);
			return mac.doFinal(input);
		} catch (GeneralSecurityException e) {
			throw new SystemException(e);
		}
	}

	/**
	 * <p>
	 * 校验HMAC-SHA1签名是否正确.
	 * </p>
	 * 
	 * @param expected 已存在的签名
	 * @param input 原始输入字符串
	 * @param key 密钥
	 * @return true: HMAC-SHA1签名正确
	 * @since 1.0.0
	 * @author calvin
	 * @author 唐玮琳
	 * @time 2013-5-18 下午4:07:45
	 */
	public static boolean isMacValid(byte[] expected, byte[] input, byte[] key) {
		byte[] actual = hmacSha1(input, key);
		return Arrays.equals(expected, actual);
	}

	/**
	 * <p>
	 * 生成HMAC-SHA1密钥,返回字节数组,长度为160位(20字节).
	 * HMAC-SHA1算法对密钥无特殊要求, RFC2401建议最少长度为160位(20字节).
	 * </p>
	 * 
	 * @return HMAC-SHA1密钥
	 * @since 1.0.0
	 * @author calvin
	 * @author 唐玮琳
	 * @time 2013-5-18 下午4:08:32
	 */
	public static byte[] generateHmacSha1Key() {
		try {
			KeyGenerator keyGenerator = KeyGenerator.getInstance(HMACSHA1);
			keyGenerator.init(DEFAULT_HMACSHA1_KEYSIZE);
			SecretKey secretKey = keyGenerator.generateKey();
			return secretKey.getEncoded();
		} catch (GeneralSecurityException e) {
			throw new SystemException(e);
		}
	}

	//-- AES funciton --//
	/**
	 * <p>
	 * 使用AES加密原始字符串.
	 * </p>
	 * 
	 * @param input 原始输入字符数组
	 * @param key 符合AES要求的密钥
	 * @return 加密后的字符串字节数组表示
	 * @since 1.0.0
	 * @author calvin
	 * @author 唐玮琳
	 * @time 2013-5-18 下午4:09:18
	 */
	public static byte[] aesEncrypt(byte[] input, byte[] key) {
		return aes(input, key, Cipher.ENCRYPT_MODE);
	}

	/**
	 * <p>
	 * 使用AES加密原始字符串.
	 * </p>
	 * 
	 * @param input 原始输入字符数组
	 * @param key 符合AES要求的密钥
	 * @param iv 初始向量
	 * @return 加密后的字符串字节数组表示
	 * @since 1.0.0
	 * @author calvin
	 * @author 唐玮琳
	 * @time 2013-5-18 下午4:10:15
	 */
	public static byte[] aesEncrypt(byte[] input, byte[] key, byte[] iv) {
		return aes(input, key, iv, Cipher.ENCRYPT_MODE);
	}

	/**
	 * <p>
	 * 使用AES解密字符串, 返回原始字符串.
	 * </p>
	 * 
	 * @param input Hex编码的加密字符串
	 * @param key 符合AES要求的密钥
	 * @return 原始字符串
	 * @since 1.0.0
	 * @author calvin
	 * @author 唐玮琳
	 * @time 2013-5-18 下午4:11:09
	 */
	public static String aesDecrypt(byte[] input, byte[] key) {
		byte[] decryptResult = aes(input, key, Cipher.DECRYPT_MODE);
		return new String(decryptResult);
	}

	/**
	 * <p>
	 * 使用AES解密字符串, 返回原始字符串.
	 * </p>
	 * 
	 * @param input Hex编码的加密字符串
	 * @param key 符合AES要求的密钥
	 * @param iv 初始向量
	 * @return 原始字符串
	 * @since 1.0.0
	 * @author calvin
	 * @author 唐玮琳
	 * @time 2013-5-18 下午4:12:08
	 */
	public static String aesDecrypt(byte[] input, byte[] key, byte[] iv) {
		byte[] decryptResult = aes(input, key, iv, Cipher.DECRYPT_MODE);
		return new String(decryptResult);
	}

	/**
	 * <p>
	 * 使用AES加密或解密无编码的原始字节数组, 返回无编码的字节数组结果.
	 * </p>
	 * 
	 * @param input 原始字节数组
	 * @param key 符合AES要求的密钥
	 * @param mode Cipher.ENCRYPT_MODE 或 Cipher.DECRYPT_MODE
	 * @return 无编码的字节数组
	 * @since 1.0.0
	 * @author calvin
	 * @author 唐玮琳
	 * @time 2013-5-18 下午4:12:49
	 */
	private static byte[] aes(byte[] input, byte[] key, int mode) {
		try {
			SecretKey secretKey = new SecretKeySpec(key, AES);
			Cipher cipher = Cipher.getInstance(AES);
			cipher.init(mode, secretKey);
			return cipher.doFinal(input);
		} catch (GeneralSecurityException e) {
			throw new SystemException(e);
		}
	}

	/**
	 * <p>
	 * 使用AES加密或解密无编码的原始字节数组, 返回无编码的字节数组结果.
	 * </p>
	 * 
	 * @param input 原始字节数组
	 * @param key 符合AES要求的密钥
	 * @param iv 初始向量
	 * @param mode Cipher.ENCRYPT_MODE 或 Cipher.DECRYPT_MODE
	 * @return 无编码的字节数组
	 * @since 1.0.0
	 * @author calvin
	 * @author 唐玮琳
	 * @time 2013-5-18 下午4:13:53
	 */
	private static byte[] aes(byte[] input, byte[] key, byte[] iv, int mode) {
		try {
			SecretKey secretKey = new SecretKeySpec(key, AES);
			IvParameterSpec ivSpec = new IvParameterSpec(iv);
			Cipher cipher = Cipher.getInstance(AES_CBC);
			cipher.init(mode, secretKey, ivSpec);
			return cipher.doFinal(input);
		} catch (GeneralSecurityException e) {
			throw new SystemException(e);
		}
	}

	/**
	 * <p>
	 * 生成AES密钥,返回字节数组, 默认长度为128位(16字节).
	 * </p>
	 * 
	 * @return AES密钥
	 * @since 1.0.0
	 * @author calvin
	 * @author 唐玮琳
	 * @time 2013-5-18 下午4:14:38
	 */
	public static byte[] generateAesKey() {
		return generateAesKey(DEFAULT_AES_KEYSIZE);
	}

	/**
	 * <p>
	 * 生成AES密钥,可选长度为128,192,256位.
	 * </p>
	 * 
	 * @param keysize 密钥长度，只能为128、192或256
	 * @return AES密钥
	 * @since 1.0.0
	 * @author calvin
	 * @author 唐玮琳
	 * @time 2013-5-18 下午4:15:16
	 */
	public static byte[] generateAesKey(int keysize) {
		try {
			KeyGenerator keyGenerator = KeyGenerator.getInstance(AES);
			keyGenerator.init(keysize);
			SecretKey secretKey = keyGenerator.generateKey();
			return secretKey.getEncoded();
		} catch (GeneralSecurityException e) {
			throw new SystemException(e);
		}
	}

	/**
	 * <p>
	 * 生成随机向量,默认大小为cipher.getBlockSize(), 16字节.
	 * </p>
	 * 
	 * @return 向量的字节数组
	 * @since 1.0.0
	 * @author calvin
	 * @author 唐玮琳
	 * @time 2013-5-18 下午4:16:26
	 */
	public static byte[] generateIV() {
		byte[] bytes = new byte[DEFAULT_IVSIZE];
		random.nextBytes(bytes);
		return bytes;
	}
	
	/**
	 * 将字节数组编码为十六进制表示的字符数组
	 * 
	 * @param data 字节数组
	 * @return 十六进制表示的字符数组
	 * @since 1.0.0
	 * @author 唐玮琳
	 * @time 2013年10月1日 下午9:41:41
	 */
	public static char[] encodeHex(byte[] data) {
		int l = data.length;
        char[] out = new char[l << 1];

        // two characters form the hex value.
        for (int i = 0, j = 0; i < l; i++) {
            out[j++] = DIGITS[(0xF0 & data[i]) >>> 4];
            out[j++] = DIGITS[0x0F & data[i]];
        }

        return out;
	}
	
	/**
	 * 将十六进制编码的字节数组解码
	 * 
	 * @param bytes 十六进制编码的字节数组
	 * @return 解码后的字节数组
	 * @since 1.0.0
	 * @author 唐玮琳
	 * @time 2013年10月1日 下午9:46:31
	 */
	public static byte[] decodeHex(byte[] bytes) {
		int iLen = bytes.length;
		// 两个字符表示一个字节，所以字节数组长度是字符串长度除以2
		byte[] arrOut = new byte[iLen / 2];
		for (int i = 0; i < iLen; i = i + 2) {
			String strTmp = new String(bytes, i, 2);
			arrOut[i / 2] = (byte) Integer.parseInt(strTmp, 16);
		}
		return arrOut;
	}
	
}
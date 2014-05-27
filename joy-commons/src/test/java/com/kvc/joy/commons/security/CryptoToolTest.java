package com.kvc.joy.commons.security;

import com.kvc.joy.commons.lang.string.EncodeTool;
import junit.framework.Assert;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * 
 * @since 1.0.0
 * @author Kevice
 * @time 2013-5-18 下午4:20:08
 */
public class CryptoToolTest {

	@Test
	public void mac() {
		String input = "foo message";

		//key可为任意字符串
		//byte[] key = "a foo key".getBytes();
		byte[] key = CryptoTool.generateHmacSha1Key();
		Assert.assertEquals(20, key.length);

		byte[] macResult = CryptoTool.hmacSha1(input.getBytes(), key);
		System.out.println("hmac-sha1 key in hex      :" + EncodeTool.encodeHex(key));
		System.out.println("hmac-sha1 in hex result   :" + EncodeTool.encodeHex(macResult));

		assertTrue(CryptoTool.isMacValid(macResult, input.getBytes(), key));
	}

	@Test
	public void aes() {
		byte[] key = CryptoTool.generateAesKey();
		assertEquals(16, key.length);
		String input = "foo message";

		byte[] encryptResult = CryptoTool.aesEncrypt(input.getBytes(), key);
		String descryptResult = CryptoTool.aesDecrypt(encryptResult, key);

		System.out.println("aes key in hex            :" + EncodeTool.encodeHex(key));
		System.out.println("aes encrypt in hex result :" + EncodeTool.encodeHex(encryptResult));
		assertEquals(input, descryptResult);
	}

	@Test
	public void aesWithIV() {
		byte[] key = CryptoTool.generateAesKey();
		byte[] iv = CryptoTool.generateIV();
		assertEquals(16, key.length);
		assertEquals(16, iv.length);
		String input = "foo message";

		byte[] encryptResult = CryptoTool.aesEncrypt(input.getBytes(), key, iv);
		String descryptResult = CryptoTool.aesDecrypt(encryptResult, key, iv);

		System.out.println("aes key in hex            :" + EncodeTool.encodeHex(key));
		System.out.println("iv in hex                 :" + EncodeTool.encodeHex(iv));
		System.out.println("aes encrypt in hex result :" + EncodeTool.encodeHex(encryptResult));
		assertEquals(input, descryptResult);
	}
	
}

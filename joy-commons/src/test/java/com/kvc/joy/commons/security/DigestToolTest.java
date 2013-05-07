package com.kvc.joy.commons.security;

import java.io.IOException;

import org.junit.Test;

import com.kvc.joy.commons.lang.string.EncodeTool;

/**
 * 
 * @since 1.0.0
 * @author 唐玮琳
 * @time 2013-5-18 下午4:24:12
 */
public class DigestToolTest {

	@Test
	public void DigestTooltring() {
		String input = "user";
		byte[] sha1Result = DigestTool.sha1(input.getBytes());
		System.out.println("sha1 in hex result                               :" + EncodeTool.encodeHex(sha1Result));

		byte[] salt = DigestTool.generateSalt(8);
		System.out.println("salt in hex                                      :" + EncodeTool.encodeHex(salt));
		sha1Result = DigestTool.sha1(input.getBytes(), salt);
		System.out.println("sha1 in hex result with salt                     :" + EncodeTool.encodeHex(sha1Result));

		sha1Result = DigestTool.sha1(input.getBytes(), salt, 1024);
		System.out.println("sha1 in hex result with salt and 1024 interations:" + EncodeTool.encodeHex(sha1Result));

	}

	@Test
	public void digestFile() throws IOException {
//		Resource resource = new ClassPathResource("/log4j.properties");
//		byte[] md5result = DigestTool.md5(resource.getInputStream());
//		byte[] sha1result = DigestTool.sha1(resource.getInputStream());
//		System.out.println("md5: " + EncodeTool.encodeHex(md5result));
//		System.out.println("sha1:" + EncodeTool.encodeHex(sha1result));
	}
	
}

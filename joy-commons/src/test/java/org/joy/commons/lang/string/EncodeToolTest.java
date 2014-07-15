package org.joy.commons.lang.string;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * 
 * @since 1.0.0
 * @author Kevice
 * @time 2013-5-18 下午5:01:11
 */
public class EncodeToolTest {

	@Test
	public void hexEncode() {
		String input = "haha,i am a very long message";
		String result = EncodeTool.encodeHex(input.getBytes());
		assertEquals(input, new String(EncodeTool.decodeHex(result)));
	}

	@Test
	public void base64Encode() {
		String input = "haha,i am a very long message";
		String result = EncodeTool.encodeBase64(input.getBytes());
		assertEquals(input, new String(EncodeTool.decodeBase64(result)));
	}

	@Test
	public void base64UrlSafeEncode() {
		String input = "haha,i am a very long message";
		String result = EncodeTool.encodeUrlSafeBase64(input.getBytes());
		assertEquals(input, new String(EncodeTool.decodeBase64(result)));
	}

	@Test
	public void urlEncode() {
		String input = "http://locahost/?q=中文&t=1";
		String result = EncodeTool.urlEncode(input);
		System.out.println(result);

		assertEquals(input, EncodeTool.urlDecode(result));
	}

}

package com.kvc.joy.commons.math;

import junit.framework.Assert;
import org.junit.Test;

/**
 * 
 * @since 1.0.0
 * @author Kevice
 * @time 2013-4-9 下午8:06:41
 */
public class NumberToolTest {
	
	@Test
	public void testtoInt() {
		Assert.assertEquals(1, NumberTool.toInt(null, 1));
		Assert.assertEquals(1, NumberTool.toInt("", 1));
		Assert.assertEquals(1, NumberTool.toInt(" ", 1));
		Assert.assertEquals(1, NumberTool.toInt("str", 1));
		Assert.assertEquals(1, NumberTool.toInt("1234567899999999999", 1));
		Assert.assertEquals(1, NumberTool.toInt("1.23", 1));
		Assert.assertEquals(123, NumberTool.toInt("123", 1));
		Assert.assertEquals(-123, NumberTool.toInt("-123", 1));
	}

	@Test
	public void testToLong() {
		Assert.assertEquals(1L, NumberTool.toLong(null, 1L));
		Assert.assertEquals(1L, NumberTool.toLong("", 1L));
		Assert.assertEquals(1L, NumberTool.toLong(" ", 1L));
		Assert.assertEquals(1L, NumberTool.toLong("str", 1L));
		Assert.assertEquals(1L, NumberTool.toLong("12345678999999999999999999", 1L));
		Assert.assertEquals(1234567899999999999L, NumberTool.toLong("1234567899999999999", 1L));
		Assert.assertEquals(1L, NumberTool.toLong("1.23", 1L));
		Assert.assertEquals(123L, NumberTool.toLong("123", 1L));
		Assert.assertEquals(-123L, NumberTool.toLong("-123", 1L));
	}

	@Test
	public void testToDouble() {
		Assert.assertEquals(1.0, NumberTool.toDouble(null, 1.0));
		Assert.assertEquals(1.0, NumberTool.toDouble("", 1.0));
		Assert.assertEquals(1.0, NumberTool.toDouble(" ", 1.0));
		Assert.assertEquals(1.0, NumberTool.toDouble("str", 1.0));
		Assert.assertEquals(1.2345679E25, NumberTool.toDouble("12345678999999999999999999", 1.0));
		Assert.assertEquals(1.23, NumberTool.toDouble("1.23", 1.0));
		Assert.assertEquals(123.0, NumberTool.toDouble("123", 1.0));
		Assert.assertEquals(-123.0, NumberTool.toDouble("-123", 1.0));
	}

}

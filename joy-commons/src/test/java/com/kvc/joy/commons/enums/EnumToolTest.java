package com.kvc.joy.commons.enums;

import java.util.Map;

import junit.framework.Assert;

import org.junit.Test;

/**
 * 
 * @since 1.0.0
 * @author 唐玮琳
 * @time 2013-5-13 上午12:35:36
 */
public class EnumToolTest {
	
	private Class<TimeUnit> enumClass = TimeUnit.class;
	private String enumClassStr = TimeUnit.class.getName();
	
	@Test
	public void enumOf() {
		String code = "9";
		Assert.assertEquals(TimeUnit.MICROSECOND, EnumTool.enumOf(enumClass, code));
		
		Assert.assertNull(EnumTool.enumOf(YesNot.class, code));
		
		code = "would not find";
		Assert.assertNull(EnumTool.enumOf(enumClass, code));
		
		Assert.assertNull(EnumTool.enumOf(enumClass, null));
		
		try {
			Assert.assertNull(EnumTool.enumOf((Class<TimeUnit>)null, code));	
			Assert.assertTrue(false);
		} catch (IllegalArgumentException e) {
			Assert.assertTrue(true);
		}
	}
	
	@Test
	public void enumOfStr() {
		String code = "1";
		Assert.assertEquals(TimeUnit.YEAR, EnumTool.enumOf(enumClassStr, code));
		
		try {
			EnumTool.enumOf("err.class", code);
			Assert.assertTrue(false);
		} catch (IllegalArgumentException e) {
			Assert.assertTrue(true);
		}
		
		code = "would not find";
		Assert.assertNull(EnumTool.enumOf(enumClassStr, code));
		
		Assert.assertNull(EnumTool.enumOf(YesNot.class.getName(), code));
		
		Assert.assertNull(EnumTool.enumOf(enumClassStr, null));
		
		try {
			EnumTool.enumOf((String)null, code);	
			Assert.assertTrue(false);
		} catch (IllegalArgumentException e) {
			Assert.assertTrue(true);
		}
	}
	
	@Test
	public void getCodeMap() {
		Map<String, String> codeMap = EnumTool.getCodeMap(enumClass);
		Assert.assertTrue(codeMap.size() >= 9);
		Assert.assertEquals(TimeUnit.YEAR.getTrans(), codeMap.get("1"));
		Assert.assertEquals(TimeUnit.MICROSECOND.getTrans(), codeMap.get("9"));
		
		try {
			EnumTool.getCodeMap((Class<TimeUnit>)null);
			Assert.assertTrue(false);
		} catch (IllegalArgumentException e) {
			Assert.assertTrue(true);
		}
	}
	
	@Test
	public void getCodeMapStr() {
		Map<String, String> codeMap = EnumTool.getCodeMap(enumClassStr);
//		Assert.assertTrue(codeMap.size() >= 9);
		Assert.assertEquals(TimeUnit.YEAR.getTrans(), codeMap.get("1"));
		Assert.assertEquals(TimeUnit.MICROSECOND.getTrans(), codeMap.get("9"));
		
		try {
			EnumTool.getCodeMap((String)null);
			Assert.assertTrue(false);
		} catch (IllegalArgumentException e) {
			Assert.assertTrue(true);
		}
		
		try {
			EnumTool.getCodeMap("");
			Assert.assertTrue(false);
		} catch (IllegalArgumentException e) {
			Assert.assertTrue(true);
		}
		
		try {
			EnumTool.getCodeMap("err.class");
			Assert.assertTrue(false);
		} catch (IllegalArgumentException e) {
			Assert.assertTrue(true);
		}
		
		try {
			EnumTool.getCodeMap(getClass().getName());
			Assert.assertTrue(false);
		} catch (IllegalArgumentException e) {
			Assert.assertTrue(true);
		}
	}
	
	@Test
	public void getCodeEnumClass() {
		Class<? extends Enum<? extends Enum<?>>> codeEnumClass = EnumTool.getCodeEnumClass(enumClassStr);
		Assert.assertTrue(codeEnumClass == enumClass);
		
		try {
			EnumTool.getCodeEnumClass(null);
			Assert.assertTrue(false);
		} catch (IllegalArgumentException e) {
			Assert.assertTrue(true);
		}
		
		try {
			EnumTool.getCodeEnumClass("");
			Assert.assertTrue(false);
		} catch (IllegalArgumentException e) {
			Assert.assertTrue(true);
		}
		
		try {
			EnumTool.getCodeEnumClass("err.class");
			Assert.assertTrue(false);
		} catch (IllegalArgumentException e) {
			Assert.assertTrue(true);
		}
		
		try {
			EnumTool.getCodeEnumClass(getClass().getName());
			Assert.assertTrue(false);
		} catch (IllegalArgumentException e) {
			Assert.assertTrue(true);
		}
	}

}

package com.kvc.joy.commons.cn;

import junit.framework.Assert;

import org.junit.Test;

import com.kvc.joy.commons.enums.Sex;

/**
 * 
 * @since 1.0.0
 * @author 唐玮琳
 * @time 2013-5-14 下午10:04:15
 */
public class IdCardNoToolTest {
	
	private final String MAIN_F_18 = "210502198412020944"; // 大陆，辽宁，女, 18位
	private final String MAIN_F_15 = "210502841202094"; // 大陆，辽宁，女, 15位
	
	private final String HK = "R6728757";
	
	private final String MACAU = "5215299(8)";
	
	private final String TW_M = "J109830254";
	private final String TW_F = "T240298298";
	
	@Test
	public void conver15CardTo18() {
		String idCard18 = IdCardNoTool.convert15To18(MAIN_F_15);
		Assert.assertEquals(MAIN_F_18, idCard18);
		
		Assert.assertNull(IdCardNoTool.convert15To18(null));
		Assert.assertNull(IdCardNoTool.convert15To18(""));
		
		Assert.assertNull(IdCardNoTool.convert15To18(MAIN_F_18));
	}
	
	@Test
	public void isIdCardNo() {
		Assert.assertTrue(IdCardNoTool.isIdCardNo(MAIN_F_15));
		Assert.assertTrue(IdCardNoTool.isIdCardNo(MAIN_F_18));
		Assert.assertTrue(IdCardNoTool.isIdCardNo(HK));
		Assert.assertTrue(IdCardNoTool.isIdCardNo(MACAU));
		Assert.assertTrue(IdCardNoTool.isIdCardNo(TW_M));
		Assert.assertTrue(IdCardNoTool.isIdCardNo(TW_F));
		
		Assert.assertFalse(IdCardNoTool.isIdCardNo(null));
		Assert.assertFalse(IdCardNoTool.isIdCardNo(""));
		Assert.assertFalse(IdCardNoTool.isIdCardNo("1234556"));
	}
	
	@Test
	public void isIdCardNo18() {
		Assert.assertTrue(IdCardNoTool.isIdCardNo18(MAIN_F_18));

		Assert.assertFalse(IdCardNoTool.isIdCardNo18(MAIN_F_15));
		Assert.assertFalse(IdCardNoTool.isIdCardNo18(HK));
		Assert.assertFalse(IdCardNoTool.isIdCardNo18(MACAU));
		Assert.assertFalse(IdCardNoTool.isIdCardNo18(TW_M));
		Assert.assertFalse(IdCardNoTool.isIdCardNo18(TW_F));
		Assert.assertFalse(IdCardNoTool.isIdCardNo18(null));
		Assert.assertFalse(IdCardNoTool.isIdCardNo18(""));
		Assert.assertFalse(IdCardNoTool.isIdCardNo18("1234556"));
	}
	
	@Test
	public void isIdCardNo15() {
		Assert.assertTrue(IdCardNoTool.isIdCardNo15(MAIN_F_15));
		
		Assert.assertFalse(IdCardNoTool.isIdCardNo15(MAIN_F_18));
		Assert.assertFalse(IdCardNoTool.isIdCardNo15(HK));
		Assert.assertFalse(IdCardNoTool.isIdCardNo15(MACAU));
		Assert.assertFalse(IdCardNoTool.isIdCardNo15(TW_M));
		Assert.assertFalse(IdCardNoTool.isIdCardNo15(TW_F));
		Assert.assertFalse(IdCardNoTool.isIdCardNo15(null));
		Assert.assertFalse(IdCardNoTool.isIdCardNo15(""));
		Assert.assertFalse(IdCardNoTool.isIdCardNo15("1234556"));
	}
	
	@Test
	public void isTwIdCardNo() {
		Assert.assertTrue(IdCardNoTool.isTwIdCardNo(TW_M));
		Assert.assertTrue(IdCardNoTool.isTwIdCardNo(TW_F));
		
		Assert.assertFalse(IdCardNoTool.isTwIdCardNo(MAIN_F_15));
		Assert.assertFalse(IdCardNoTool.isTwIdCardNo(MAIN_F_18));
		Assert.assertFalse(IdCardNoTool.isTwIdCardNo(HK));
		Assert.assertFalse(IdCardNoTool.isTwIdCardNo(MACAU));
		Assert.assertFalse(IdCardNoTool.isTwIdCardNo(null));
		Assert.assertFalse(IdCardNoTool.isTwIdCardNo(""));
		Assert.assertFalse(IdCardNoTool.isTwIdCardNo("1234556"));
	}
	
	@Test
	public void isHkIdCardNo() {
		Assert.assertTrue(IdCardNoTool.isHkIdCardNo(HK));

		Assert.assertFalse(IdCardNoTool.isHkIdCardNo(MACAU));
		Assert.assertFalse(IdCardNoTool.isHkIdCardNo(TW_M));
		Assert.assertFalse(IdCardNoTool.isHkIdCardNo(TW_F));
		Assert.assertFalse(IdCardNoTool.isHkIdCardNo(MAIN_F_15));
		Assert.assertFalse(IdCardNoTool.isHkIdCardNo(MAIN_F_18));
		Assert.assertFalse(IdCardNoTool.isHkIdCardNo(null));
		Assert.assertFalse(IdCardNoTool.isHkIdCardNo(""));
		Assert.assertFalse(IdCardNoTool.isHkIdCardNo("1234556"));
	}
	
	@Test
	public void isMacauIdCardNo() {
		Assert.assertTrue(IdCardNoTool.isMacauIdCardNo(MACAU));
		
		Assert.assertFalse(IdCardNoTool.isMacauIdCardNo(HK));
		Assert.assertFalse(IdCardNoTool.isMacauIdCardNo(TW_M));
		Assert.assertFalse(IdCardNoTool.isMacauIdCardNo(TW_F));
		Assert.assertFalse(IdCardNoTool.isMacauIdCardNo(MAIN_F_15));
		Assert.assertFalse(IdCardNoTool.isMacauIdCardNo(MAIN_F_18));
		Assert.assertFalse(IdCardNoTool.isMacauIdCardNo(null));
		Assert.assertFalse(IdCardNoTool.isMacauIdCardNo(""));
		Assert.assertFalse(IdCardNoTool.isMacauIdCardNo("1234556"));
	}
	
	@Test
	public void getBirthday() {
		Assert.assertEquals("19841202", IdCardNoTool.getBirthday(MAIN_F_15));
		Assert.assertEquals("19841202", IdCardNoTool.getBirthday(MAIN_F_18));
		
		Assert.assertNull(IdCardNoTool.getBirthday(HK));
		Assert.assertNull(IdCardNoTool.getBirthday(MACAU));
		Assert.assertNull(IdCardNoTool.getBirthday(TW_M));
		Assert.assertNull(IdCardNoTool.getBirthday(TW_F));
		Assert.assertNull(IdCardNoTool.getBirthday(null));
		Assert.assertNull(IdCardNoTool.getBirthday(""));
		Assert.assertNull(IdCardNoTool.getBirthday("1234556"));
	}
	
	@Test
	public void getSex() {
		Assert.assertEquals(Sex.FEMALE, IdCardNoTool.getSex(MAIN_F_15));
		Assert.assertEquals(Sex.FEMALE, IdCardNoTool.getSex(MAIN_F_18));
		
		Assert.assertEquals(Sex.UNKNOWN, IdCardNoTool.getSex(HK));
		Assert.assertEquals(Sex.MALE, IdCardNoTool.getSex(TW_M));
		Assert.assertEquals(Sex.FEMALE, IdCardNoTool.getSex(TW_F));
		Assert.assertEquals(Sex.UNKNOWN, IdCardNoTool.getSex(null));
		Assert.assertEquals(Sex.UNKNOWN, IdCardNoTool.getSex(""));
		Assert.assertEquals(Sex.UNKNOWN, IdCardNoTool.getSex("1234556"));
	}
	
	@Test
	public void getProvince() {
		Assert.assertEquals(Province.LIAO_NING, IdCardNoTool.getProvince(MAIN_F_15));
		Assert.assertEquals(Province.LIAO_NING, IdCardNoTool.getProvince(MAIN_F_18));
		
		Assert.assertEquals(Province.XIANG_GANG, IdCardNoTool.getProvince(HK));
		Assert.assertEquals(Province.AO_MEN, IdCardNoTool.getProvince(MACAU));
		Assert.assertEquals(Province.TAI_WAN, IdCardNoTool.getProvince(TW_M));
		Assert.assertEquals(Province.TAI_WAN, IdCardNoTool.getProvince(TW_F));
		Assert.assertEquals(null, IdCardNoTool.getProvince(null));
		Assert.assertEquals(null, IdCardNoTool.getProvince(""));
		Assert.assertEquals(null, IdCardNoTool.getProvince("1234556"));
	}
	
}


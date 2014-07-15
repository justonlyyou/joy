package org.joy.commons.cn;

import junit.framework.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * 
 * @author Kevice
 * @time 2013-4-4 下午6:05:46
 */
public class PinYinToolTest {
	
	private String cnStr;

	/**
	 * 
	 * @throws java.lang.Exception
	 * @author Kevice
	 * @time 2013-4-4 下午6:05:46
	 */
	@Before
	public void setUp() throws Exception {
		cnStr = "JOY是一个易用、灵活的javaEE快速开发平台！它采用微内核、插件、分层的架构。";
	}

	@Test
	public void getPinYin() {
		String pinYin = PinYinTool.getPinYin(cnStr);
		
		String expected = "JOYshiyigeyiyong、linghuodejavaEEkuaisukaifapingtai！" +
				"tacaiyongweineihe、chajian、fencengdejiagou。";
		Assert.assertEquals(expected, pinYin);
	}

	@Test
	public void getPinYinHeadChars() {
		String pinYinHeadChars = PinYinTool.getPinYinHeadChars(cnStr);
		
		String expected = "JOYsygyy、lhdjavaEEkskfpt！tcywnh、cj、fcdjg。";
		Assert.assertEquals(expected, pinYinHeadChars);
	}

	@Test
	public void getCnASCII() {
	}

}

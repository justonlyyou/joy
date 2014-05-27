/**
 * 
 */
package com.kvc.joy.commons.lang.string;

import junit.framework.Assert;
import org.junit.Test;

import java.util.Locale;
import java.util.ResourceBundle;

/**
 * <p>
 * 
 * </p>
 * @since 1.0.0
 * @author Kevice
 * @time 2013-5-1 下午4:45:29
 */
public class I18nToolTest {
	
	@Test
	public void testBundle() {
		String baseName = "conf/i18n/language";
		Locale locale = Locale.ENGLISH;
		
		ResourceBundle bundle = I18nTool.bundle(baseName, locale);
		
		Assert.assertNotNull(bundle);
	}
	
	@Test
	public void testChangeLocale() {
		I18nTool.changeLocale(Locale.ENGLISH);
		Assert.assertEquals(Locale.ENGLISH, Locale.getDefault());
	}
	
	@Test
	public void testChangeLocaleOfStrStr() {
		I18nTool.changeLocale("zh", "CN");
		Assert.assertEquals("zh", Locale.getDefault().getLanguage());
		Assert.assertEquals("CN", Locale.getDefault().getCountry());
	}
	
	
	@Test
	public void testGetLocalStr() {
		String i18nKey = "OK";
		I18nTool.changeLocale(Locale.CHINA);
		String localStr = I18nTool.getLocalStr(i18nKey);
		Assert.assertEquals("确定", localStr);
		
		I18nTool.changeLocale(Locale.ENGLISH);
		localStr = I18nTool.getLocalStr(i18nKey);
		Assert.assertEquals("OK", localStr);
	}

}

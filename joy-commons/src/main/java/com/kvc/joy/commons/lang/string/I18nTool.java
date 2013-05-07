package com.kvc.joy.commons.lang.string;

import java.util.Locale;
import java.util.ResourceBundle;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 国际化工具
 * 
 * @since 1.0.0
 * @author 唐玮琳
 * @time 2013-3-31 上午12:16:33
 */
public class I18nTool {

	private static ResourceBundle bundle;
	private static final String BASE_NAME = "conf/i18n/language";
	private static Logger logger = LoggerFactory.getLogger(StringTool.class);

	static {
		bundle(BASE_NAME, Locale.getDefault());
	}

	/**
	 * <p>
	 * 绑定本地运行环境和资源文件
	 * </p>
	 * 
	 * @param baseName 资源文件的基本名称(扣掉后缀)
	 * @param locale 本地运行环境
	 * @since 1.0.0
	 * @author 唐玮琳
	 * @time 2013-5-1 下午5:05:12
	 */
	public static ResourceBundle bundle(String baseName, Locale locale) {
		Locale.setDefault(locale);
		try {
			bundle = ResourceBundle.getBundle(baseName, locale);
		} catch (Exception e) {
			logger.error("绑定本地运行环境和资源文件时出错!", e);
			bundle = null;
		}
		return bundle;
	}

	/**
	 * <p>
	 * 根据多国语言Key, 取得本地环境的字符串
	 * </p>
	 * 
	 * @param i18nKey 多国语言Key
	 * @return 返回本地环境的字符串,如果传NULL或trim后为空串则返回空串
	 * @since 1.0.0
	 * @author 唐玮琳
	 * @time 2013-5-1 下午5:00:03
	 */
	public static String getLocalStr(String i18nKey) {
		if (StringTool.isBlank(i18nKey)) {
			return "";
		}
		if (bundle == null) {
			return i18nKey;
		}
		return bundle.getString(i18nKey);
	}

	/**
	 * <p>
	 * 更改系统运行的环境
	 * </p>
	 * 
	 * @param locale {@link java.util.Locale}
	 * @since 1.0.0
	 * @author 唐玮琳
	 * @time 2013-5-1 下午5:00:30
	 */
	public static void changeLocale(Locale locale) {
		bundle(BASE_NAME, locale);
		javax.swing.JComponent.setDefaultLocale(locale);
	}

	/**
	 * <p>
	 * 更改系统运行的环境
	 * </p>
	 * 
	 * @param languageName 语言的名字(小写的两字母 ISO-639 代码)
	 * @param countryName 国家的名字(大写的两字母 ISO-3166 代码)
	 * @since 1.0.0
	 * @author 唐玮琳
	 * @time 2013-5-1 下午5:01:11
	 */
	public static void changeLocale(String languageName, String countryName) {
		changeLocale(new Locale(languageName, countryName));
	}

}

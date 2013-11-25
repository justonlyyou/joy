package com.kvc.joy.commons.lang.string;

import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.Locale;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.Set;

import com.kvc.joy.commons.log.Log;
import com.kvc.joy.commons.log.LogFactory;
import com.kvc.joy.commons.scanner.classpath.ClassPathScanner;
import com.kvc.joy.commons.scanner.support.Resource;

/**
 * 国际化工具
 * 
 * @since 1.0.0
 * @author 唐玮琳
 * @time 2013-3-31 上午12:16:33
 */
public class I18nTool {

	private static final String DEFAULT_BASE_PATH = "conf/i18n/";
	private static Map<String, ResourceBundle> bundleMap = new LinkedHashMap<String, ResourceBundle>();
	private static final Log logger = LogFactory.getLog(I18nTool.class);

	static {
		Resource[] resources = ClassPathScanner.scanForResources(DEFAULT_BASE_PATH, "", ".properties");
		Set<String> baseNames = new LinkedHashSet<String>();
		for (Resource resource : resources) {
			String filename = resource.getFilename();
			String baseName = filename.replaceFirst("\\.properties$", "").replaceFirst("_[a-z]{2}_[A-Z]{2}$", "");
			baseNames.add(DEFAULT_BASE_PATH + baseName);
		}
		for (String baseName : baseNames) {
			bundle(baseName);
		}
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
	public static synchronized ResourceBundle bundle(String baseName, Locale locale) {
		ResourceBundle bundle = null;
		try {
			bundle = ResourceBundle.getBundle(baseName, locale);
			bundleMap.put(baseName, bundle);
		} catch (Exception e) {
			logger.error(e, "绑定本地运行环境和资源文件时出错!");
		}
		return bundle;
	}

	/**
	 * 绑定本地运行环境和资源文件
	 * 
	 * @param baseName 资源文件的基本名称(扣掉后缀)
	 * @return
	 * @since 1.0.0
	 * @author 唐玮琳
	 * @time 2013年11月25日 下午10:17:27
	 */
	public static ResourceBundle bundle(String baseName) {
		return bundle(baseName, Locale.getDefault());
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
		if (StringTool.isNotBlank(i18nKey)) {
			for (ResourceBundle bundle : bundleMap.values()) {
				if (bundle.containsKey(i18nKey)) {
					return bundle.getString(i18nKey);
				}
			}
		}
		return "";
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
		Set<String> baseNameSet = bundleMap.keySet();
		bundleMap.clear();
		for (String baseName : baseNameSet) {
			bundle(baseName, locale);
		}
		Locale.setDefault(locale);
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

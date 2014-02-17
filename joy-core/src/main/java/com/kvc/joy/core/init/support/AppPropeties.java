package com.kvc.joy.core.init.support;


/**
 * 
 * @author 唐玮琳
 * @time 2012-12-29 上午12:05:15
 */
public class AppPropeties extends BaseProperties {

	private static final AppPropeties self = new AppPropeties();
	public static String VERSION;
	public static String NAME;
	public static String NAME_ABBR;
	public static String COPY_RIGHT_YEAR;
	public static String COPY_RIGHT_AUTHOR;
	public static String CLASS_PREFIX;

	private AppPropeties() {
	}

	public static AppPropeties getInstance() {
		return self;
	}

	static {
		self.init();
	}

	protected void init() {
		VERSION = getStr("app.version");
		NAME = getStr("app.name");
		NAME_ABBR = getStr("app.name.abbr");
		COPY_RIGHT_YEAR = getStr("app.copyRight.year");
		COPY_RIGHT_AUTHOR = getStr("app.copyRight.author");
		CLASS_PREFIX = getStr("app.classPrefix");
	}

	@Override
	protected String getPropertiesFile() {
		return "conf/app.properties";
	}
	
	public static String property(String property) {
		return self.getStr(property);
	}

}

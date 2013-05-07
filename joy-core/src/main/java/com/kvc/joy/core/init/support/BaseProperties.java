package com.kvc.joy.core.init.support;

import java.io.IOException;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.support.PropertiesLoaderUtils;

/**
 * 
 * @since 1.0.0
 * @author 唐玮琳
 * @time 2013年10月3日 下午11:53:39
 */
public abstract class BaseProperties {

	protected static Logger logger = LoggerFactory.getLogger(BaseProperties.class);
	protected Properties properties;

	protected Properties getProperties() {
		if (properties == null) {
			try {
				properties = PropertiesLoaderUtils.loadAllProperties(getPropertiesFile());
			} catch (IOException e) {
				logger.error("加载" + getPropertiesFile() + "文件出错！", e);
			}
		}
		return properties;
	}

	protected abstract String getPropertiesFile();

	protected final Object get(String property) {
		boolean containsKey = getProperties().containsKey(property);
		if (containsKey == false) {
			logger.error(getPropertiesFile() + "文件中不存在" + property + "的key！");
			return null;
		} else {
			return getProperties().get(property);
		}
	}

	public String getStr(String property) {
		return get(property) + "";
	}

	public boolean getBool(String property) {
		return Boolean.valueOf(getStr(property));
	}

	public Integer getInt(String property) {
		Object obj = get(property);
		if (obj == null || obj.equals("")) {
			return null;
		}
		return Integer.valueOf(obj + "");
	}

}

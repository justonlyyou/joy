package com.kvc.joy.core.init.support.properties;

import com.kvc.joy.commons.log.Log;
import com.kvc.joy.commons.log.LogFactory;
import org.springframework.core.io.support.PropertiesLoaderUtils;

import java.io.IOException;
import java.util.Properties;

/**
 * 
 * @since 1.0.0
 * @author 唐玮琳
 * @time 2013年10月3日 下午11:53:39
 */
public abstract class BaseProperties {

	protected static final Log logger = LogFactory.getLog(BaseProperties.class);
	protected Properties properties;

	protected Properties getProperties() {
		if (properties == null) {
			try {
				properties = PropertiesLoaderUtils.loadAllProperties(getPropertiesFile());
			} catch (IOException e) {
				logger.error(e, "加载" + getPropertiesFile() + "文件出错！");
			}
		}
		return properties;
	}

	protected abstract String getPropertiesFile();

	protected Object get(String property) {
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

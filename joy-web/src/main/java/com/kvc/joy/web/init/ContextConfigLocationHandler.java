package com.kvc.joy.web.init;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import com.kvc.joy.core.init.support.JoyPropeties;

/**
 * 
 * @author 唐玮琳
 * @time 2013-2-4 下午10:53:58
 */
public class ContextConfigLocationHandler {

	public static final String DEFAULT_CFG_LOCATION = "classpath*:conf/applicationContext*.xml";
	public static final String EHCACHE_CFG_LOCATION = "classpath*:conf/component-applicationContext-ehcache.xml";
	public static final String SEQGEN_CFG_LOCATION = "classpath*:conf/component-applicationContext-seqgen.xml";
	public static final String QUARTZ_CFG_LOCATION = "classpath*:conf/component-applicationContext-quartz.xml";
	public static final String ERBAC_CFG_LOCATION = "classpath*:conf/component-applicationContext-erbac.xml";
	public static final String ACTIVITI_CFG_LOCATION = "classpath*:conf/component-applicationContext-activiti.xml";
	public static final String CAPTCHA_CFG_LOCATION = "classpath*:conf/component-applicationContext-captcha.xml";
	public static final String HIBERNATE_CFG_LOCATION = "classpath*:conf/component-applicationContext-hibernate.xml";
	public static final String LOGIN_CFG_LOCATION = "classpath*:conf/component-applicationContext-login.xml";
	public static final String JASPERREPORTS_CFG_LOCATION = "classpath*:conf/component-applicationContext-jasperreports.xml";
	public static final String JWEBAP_JDBC_CFG_LOCATION = "classpath*:conf/component-applicationContext-jwebap-jdbc.xml";
	public static final String SPRING_DATA_JPA_CFG_LOCATION = "classpath*:conf/spring-data-jpa.xml";
	public static final String SPRING_MVC_CFG_LOCATION = "classpath*:conf/springMVC.xml";
	
	
	private Map<String, Boolean> locationMap;

	public ContextConfigLocationHandler() {
		locationMap = new HashMap<String, Boolean>();
		locationMap.put(EHCACHE_CFG_LOCATION, JoyPropeties.PLUGIN_EHCACHE_ENABLED);
		locationMap.put(SEQGEN_CFG_LOCATION, JoyPropeties.PLUGIN_SEQGEN_ENABLED);
//		locationMap.put(QUARTZ_CFG_LOCATION, JoyPropeties.PLUGIN_QUARTZ_ENABLED);
//		locationMap.put(ERBAC_CFG_LOCATION, JoyPropeties.PLUGIN_ERBAC_ENABLED);
		locationMap.put(ACTIVITI_CFG_LOCATION, JoyPropeties.PLUGIN_WORKFLOW_ENABLED);
		locationMap.put(CAPTCHA_CFG_LOCATION, JoyPropeties.PLUGIN_CAPTCHA_ENABLED);
		locationMap.put(HIBERNATE_CFG_LOCATION, JoyPropeties.PLUGIN_PERSISTENCE_HIBERNATE_ENABLED);
		locationMap.put(LOGIN_CFG_LOCATION, JoyPropeties.PLUGIN_LOGIN_ENABLED);
		locationMap.put(JASPERREPORTS_CFG_LOCATION, JoyPropeties.PLUGIN_REPORT_JASPERREPORTS_ENABLED);
		locationMap.put(JWEBAP_JDBC_CFG_LOCATION, JoyPropeties.PLUGIN_JWEBAP_JDBC_ENABLED);
		locationMap.put(SPRING_DATA_JPA_CFG_LOCATION, JoyPropeties.PLUGIN_SPRING_DATA_JPA_ENABLED);
//		locationMap.put(SPRING_MVC_CFG_LOCATION, JoyPropeties.PLUGIN_SPRING_MVC_ENABLED);
	}

	public String getContextConfigLocation() {
		StringBuilder result = new StringBuilder();
		result.append(DEFAULT_CFG_LOCATION);
		for (Entry<String, Boolean> entry : locationMap.entrySet()) {
			Boolean enabled = entry.getValue();
			if (enabled) {
				String loaction = entry.getKey();
				result.append("," + loaction);
			}
		}
		return result.toString();
	}

}

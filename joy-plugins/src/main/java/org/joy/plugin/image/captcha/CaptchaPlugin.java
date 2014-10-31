package org.joy.plugin.image.captcha;

import org.joy.core.init.service.IPlugin;
import org.joy.core.init.support.properties.JoyProperties;
import org.springframework.stereotype.Component;

/**
 * 验证码插件
 * 
 * @since 1.0.0
 * @author Kevice
 * @time 2013年9月21日 下午5:28:21
 */
@Component
public class CaptchaPlugin implements IPlugin {

	@Override
	public String getName() {
		return "验证码生成";
	}

	@Override
	public int getInitPriority() {
		return Integer.MAX_VALUE;
	}

	@Override
	public void startup() {
	}

	@Override
	public void destroy() {
	}

	@Override
	public boolean isEnabled() {
		return JoyProperties.PLUGIN_CAPTCHA_ENABLED;
	}

	@Override
	public String getSqlMigrationPrefix() {
		return "CAPTCHA";
	}

	@Override
	public String getPoPackage() {
		return null;
	}

	@Override
	public String getCtxConfLocation() {
		return "classpath*:/conf/plugin-appCtx-captcha.xml";
	}

	
}

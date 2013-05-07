package com.kvc.joy.plugin.image.captcha;

import org.springframework.stereotype.Component;

import com.kvc.joy.core.init.service.SpringManagedJoyPlugin;
import com.kvc.joy.core.init.support.JoyPropeties;

/**
 * 
 * @since 1.0.0
 * @author 唐玮琳
 * @time 2013年9月21日 下午5:28:21
 */
@Component
public class CaptchaPlugin extends SpringManagedJoyPlugin {

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
		return JoyPropeties.PLUGIN_CAPTCHA_ENABLED;
	}

	@Override
	public String getXmlPath() {
		return "/conf/component-applicationContext-captcha.xml";
	}

}

package com.kvc.joy.plugin.security.login;

import org.springframework.stereotype.Component;

import com.kvc.joy.core.init.service.SpringManagedJoyPlugin;
import com.kvc.joy.core.init.support.JoyPropeties;

/**
 * 
 * @since 1.0.0
 * @author 唐玮琳
 * @time 2013年9月29日 上午1:28:49
 */
@Component
public class LoginPlugin extends SpringManagedJoyPlugin {

	@Override
	public String getName() {
		return "用户登陆";
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
		return JoyPropeties.PLUGIN_LOGIN_ENABLED;
	}

	@Override
	public String getXmlPath() {
		return "/conf/component-applicationContext-login.xml";
	}

}

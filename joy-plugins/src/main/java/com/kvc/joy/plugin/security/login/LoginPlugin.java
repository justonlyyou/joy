package com.kvc.joy.plugin.security.login;

import org.springframework.stereotype.Component;

import com.kvc.joy.core.init.service.IJoyPlugin;
import com.kvc.joy.core.init.support.JoyPropeties;
import com.kvc.joy.plugin.security.login.model.po.TLoginLog;

/**
 * 
 * @since 1.0.0
 * @author 唐玮琳
 * @time 2013年9月29日 上午1:28:49
 */
@Component
public class LoginPlugin implements IJoyPlugin {

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
	public String getSqlMigrationPrefix() {
		return "LOGIN";
	}

	@Override
	public String getPoPackage() {
		return TLoginLog.class.getPackage().getName();
	}
	
	@Override
	public String getCtxConfLocation() {
		return "classpath*:/conf/plugin-appCtx-login.xml";
	}

}

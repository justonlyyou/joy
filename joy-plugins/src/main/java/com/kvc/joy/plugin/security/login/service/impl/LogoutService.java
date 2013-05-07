package com.kvc.joy.plugin.security.login.service.impl;

import java.util.List;

import org.apache.shiro.SecurityUtils;
import org.springframework.transaction.annotation.Transactional;

import com.kvc.joy.core.persistence.orm.jpa.JpaUtils;
import com.kvc.joy.plugin.security.login.model.po.TLoginLog;
import com.kvc.joy.plugin.security.login.model.po.TLogoutLog;
import com.kvc.joy.plugin.security.login.model.po.TLogoutLog_;
import com.kvc.joy.plugin.security.login.service.ILoginLogService;
import com.kvc.joy.plugin.security.login.service.ILogoutLogService;
import com.kvc.joy.plugin.security.login.service.ILogoutService;
import com.kvc.joy.plugin.security.login.support.enums.LogoutMethod;

/**
 * 
 * @since 1.0.0
 * @author 唐玮琳
 * @time 2013年10月15日 上午10:37:54
 */
@Transactional
public class LogoutService implements ILogoutService {
	
	private ILogoutLogService logoutLogService;
	private ILoginLogService loginLogService;

	public void logout(String loginLogId, LogoutMethod logoutMethod, String logoutTime) {
		logoutLogService.logout(loginLogId, logoutMethod, logoutTime);
		SecurityUtils.getSubject().logout();
	}
	
	public void mendLogoutLogOnLogin(TLoginLog logOnLogin) {
		TLoginLog preLoginSuccessLog = loginLogService.getPreLoginSuccessLog(logOnLogin.getId());
		if (preLoginSuccessLog != null) {
			String loginLogId = preLoginSuccessLog.getId();
			List<TLogoutLog> logoutLogs = JpaUtils.search(TLogoutLog.class, TLogoutLog_.loginLogId, loginLogId);
			if (logoutLogs.isEmpty()) {
				String logoutTime = preLoginSuccessLog.getLoginTime();
				logoutLogService.logout(loginLogId, LogoutMethod.OTHERS, logoutTime);
			}
		}
	}

	public void setLogoutLogService(ILogoutLogService logoutLogService) {
		this.logoutLogService = logoutLogService;
	}
	
	public void setLoginLogService(ILoginLogService loginLogService) {
		this.loginLogService = loginLogService;
	}
	
}

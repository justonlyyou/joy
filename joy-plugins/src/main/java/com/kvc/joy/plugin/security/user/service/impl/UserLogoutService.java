package com.kvc.joy.plugin.security.user.service.impl;

import com.kvc.joy.core.persistence.orm.jpa.JpaTool;
import com.kvc.joy.plugin.security.user.model.po.TUserLoginLog;
import com.kvc.joy.plugin.security.user.model.po.TUserLogoutLog;
import com.kvc.joy.plugin.security.user.model.po.TLogoutLog_;
import com.kvc.joy.plugin.security.user.service.IUserLoginLogService;
import com.kvc.joy.plugin.security.user.service.IUserLogoutLogService;
import com.kvc.joy.plugin.security.user.service.IUserLogoutService;
import com.kvc.joy.plugin.security.user.support.enums.LogoutMethod;
import org.apache.shiro.SecurityUtils;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 
 * @since 1.0.0
 * @author 唐玮琳
 * @time 2013年10月15日 上午10:37:54
 */
@Transactional
public class UserLogoutService implements IUserLogoutService {
	
	private IUserLogoutLogService logoutLogService;
	private IUserLoginLogService loginLogService;

	public void logout(String loginLogId, LogoutMethod logoutMethod, String logoutTime) {
		logoutLogService.logout(loginLogId, logoutMethod, logoutTime);
		SecurityUtils.getSubject().logout();
	}
	
	public void mendLogoutLogOnLogin(TUserLoginLog logOnLogin) {
		TUserLoginLog preLoginSuccessLog = loginLogService.getPreLoginSuccessLog(logOnLogin.getId());
		if (preLoginSuccessLog != null) {
			String loginLogId = preLoginSuccessLog.getId();
			List<TUserLogoutLog> logoutLogs = JpaTool.search(TUserLogoutLog.class, TLogoutLog_.loginLogId, loginLogId);
			if (logoutLogs.isEmpty()) {
				String logoutTime = preLoginSuccessLog.getLoginTime();
				logoutLogService.logout(loginLogId, LogoutMethod.OTHERS, logoutTime);
			}
		}
	}

	public void setLogoutLogService(IUserLogoutLogService logoutLogService) {
		this.logoutLogService = logoutLogService;
	}
	
	public void setLoginLogService(IUserLoginLogService loginLogService) {
		this.loginLogService = loginLogService;
	}
	
}

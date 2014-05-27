package com.kvc.joy.plugin.security.user.service.impl;

import com.kvc.joy.core.persistence.orm.jpa.JpaTool;
import com.kvc.joy.plugin.security.user.model.po.TUserLoginLog;
import com.kvc.joy.plugin.security.user.model.po.TUserLogoutLog;
import com.kvc.joy.plugin.security.user.model.po.TUserLogoutLog_;
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
 * @author Kevice
 * @time 2013年10月15日 上午10:37:54
 */
@Transactional
public class UserLogoutService implements IUserLogoutService {
	
	private IUserLogoutLogService userLogoutLogService;
	private IUserLoginLogService userLoginLogService;

	public void logout(String loginLogId, LogoutMethod logoutMethod, String logoutTime) {
		userLogoutLogService.logout(loginLogId, logoutMethod, logoutTime);
		SecurityUtils.getSubject().logout();
	}
	
	public void mendLogoutLogOnLogin(TUserLoginLog logOnLogin) {
		TUserLoginLog preLoginSuccessLog = userLoginLogService.getPreLoginSuccessLog(logOnLogin.getId());
		if (preLoginSuccessLog != null) {
			String loginLogId = preLoginSuccessLog.getId();
			List<TUserLogoutLog> logoutLogs = JpaTool.search(TUserLogoutLog.class, TUserLogoutLog_.loginLogId, loginLogId);
			if (logoutLogs.isEmpty()) {
				String logoutTime = preLoginSuccessLog.getLoginTime();
				userLogoutLogService.logout(loginLogId, LogoutMethod.OTHERS, logoutTime);
			}
		}
	}

	public void setUserLogoutLogService(IUserLogoutLogService userLogoutLogService) {
		this.userLogoutLogService = userLogoutLogService;
	}
	
	public void setUserLoginLogService(IUserLoginLogService userLoginLogService) {
		this.userLoginLogService = userLoginLogService;
	}
	
}

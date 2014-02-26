package com.kvc.joy.plugin.security.user.service.impl;

import com.kvc.joy.commons.lang.DateTool;
import com.kvc.joy.core.persistence.orm.jpa.JpaTool;
import com.kvc.joy.plugin.security.erbac.support.utils.UserTool;
import com.kvc.joy.plugin.security.user.model.po.TUserLoginLog;
import com.kvc.joy.plugin.security.user.model.po.TUserLogoutLog;
import com.kvc.joy.plugin.security.user.service.IUserLoginLogService;
import com.kvc.joy.plugin.security.user.service.IUserLogoutLogService;
import com.kvc.joy.plugin.security.user.support.enums.LogoutMethod;

/**
 * 
 * @since 1.0.0
 * @author 唐玮琳
 * @time 2013年10月15日 下午2:32:05
 */
public class UserLogoutLogService implements IUserLogoutLogService {

	private IUserLoginLogService userLoginLogService;

	@Override
	public void logout(String loginLogId, LogoutMethod logoutMethod, String logoutTime) {
		TUserLogoutLog logoutLog = new TUserLogoutLog();
		if (logoutTime == null) {
			logoutTime = DateTool.currentDate(DateTool.UNFMT_yyyyMMddHHmmss);
		}
		logoutLog.setLogoutTime(logoutTime);
		String userId = UserTool.getCurrentUser().getId();
		logoutLog.setUserId(userId);
		if (loginLogId == null) {
			TUserLoginLog loginLog = userLoginLogService.onLogout(logoutTime);
			if (loginLog != null) {
				loginLogId = loginLog.getId();
			}
		}
		logoutLog.setLoginLogId(loginLogId);
		logoutLog.setLogoutMethod(logoutMethod);
		JpaTool.persist(logoutLog);
	}

	public void setUserLoginLogService(IUserLoginLogService userLoginLogService) {
		this.userLoginLogService = userLoginLogService;
	}

}

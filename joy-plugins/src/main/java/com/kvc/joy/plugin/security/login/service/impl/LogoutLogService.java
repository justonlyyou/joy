package com.kvc.joy.plugin.security.login.service.impl;

import com.kvc.joy.commons.lang.DateTool;
import com.kvc.joy.core.persistence.orm.jpa.JpaTool;
import com.kvc.joy.plugin.security.erbac.support.utils.UserTool;
import com.kvc.joy.plugin.security.login.dao.LoginLogDao;
import com.kvc.joy.plugin.security.login.model.po.TLoginLog;
import com.kvc.joy.plugin.security.login.model.po.TLogoutLog;
import com.kvc.joy.plugin.security.login.service.ILogoutLogService;
import com.kvc.joy.plugin.security.login.support.enums.LogoutMethod;

/**
 * 
 * @since 1.0.0
 * @author 唐玮琳
 * @time 2013年10月15日 下午2:32:05
 */
public class LogoutLogService implements ILogoutLogService {

	private LoginLogDao loginLogDao;

	@Override
	public void logout(String loginLogId, LogoutMethod logoutMethod, String logoutTime) {
		TLogoutLog logoutLog = new TLogoutLog();
		String userId = UserTool.getCurrentUser().getId();
		logoutLog.setUserId(userId);
		if (loginLogId == null) {
			TLoginLog loginLog = loginLogDao.getPreLoginSuccessLog(null, userId);
			loginLogId = loginLog.getId();
		}
		logoutLog.setLoginLogId(loginLogId);
		logoutLog.setLogoutMethod(logoutMethod);
		if (logoutTime == null) {
			logoutTime = DateTool.currentDate(DateTool.UNFMT_yyyyMMddHHmmss);
		}
		logoutLog.setLogoutTime(logoutTime);
		JpaTool.persist(logoutLog);
	}

	public void setLoginLogDao(LoginLogDao loginLogDao) {
		this.loginLogDao = loginLogDao;
	}

}

package com.kvc.joy.plugin.security.user.service.impl;

import com.kvc.joy.commons.bean.BeanTool;
import com.kvc.joy.commons.lang.DateTool;
import com.kvc.joy.commons.lang.string.StringTool;
import com.kvc.joy.core.init.support.properties.JoyProperties;
import com.kvc.joy.core.persistence.orm.jpa.JpaTool;
import com.kvc.joy.plugin.security.user.dao.UserLoginLogDao;
import com.kvc.joy.plugin.security.user.model.po.TUserBasic;
import com.kvc.joy.plugin.security.erbac.support.utils.UserTool;
import com.kvc.joy.plugin.security.user.model.po.TUserLoginLog;
import com.kvc.joy.plugin.security.user.service.IUserLoginLogService;
import com.kvc.joy.plugin.security.user.support.vo.UserLoginVo;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

/**
 * 登陆日志服务
 * 
 * @author Kevice
 * @time 2012-6-17 下午9:03:02
 */
public class UserLoginLogService implements IUserLoginLogService {

	private UserLoginLogDao userLoginLogDao;

	@Transactional
	public TUserLoginLog logOnLogin(UserLoginVo loginVo) {
		TUserLoginLog loginLog = new TUserLoginLog();
		BeanTool.copyPropertiesExcludeId(loginVo, loginLog);
		if (loginLog.getLoginTime() == null) {
			loginLog.setLoginTime(DateTool.currentDate(DateTool.UNFMT_yyyyMMddHHmmss));
		}
		loginLog.setUserPassword(StringTool.toMd5HexStr(loginLog.getUserPassword()));
		TUserBasic currentUser = UserTool.getCurrentUser();
		if (currentUser != null) {
			loginLog.setUserId(currentUser.getId());
		}
		JpaTool.persist(loginLog);
		return loginLog;
	}

	@Override
	public long statPasswordErrorCount(String account, String fromTime, String toTime) {
		return userLoginLogDao.statPasswordErrorCount(account, fromTime, toTime);
	}

	/**
	 * 最近几个小时内最近密码连续错误超过预定的次数需要提供验证码
	 */
	@Override
	public boolean shouldCaptchaRequire(String account) {
		int hour = JoyProperties.PLUGIN_USER_PASSWORD_ERROR_PERIOD_HOUR;
		Date now = new Date();
		String fromTime = DateTool.formatDate(DateTool.addHours(now, -hour), DateTool.UNFMT_yyyyMMddHHmmss);
		String toTime = DateTool.formatDate(now, DateTool.UNFMT_yyyyMMddHHmmss);
		int maxErrorCount = JoyProperties.PLUGIN_USER_PASSWORD_ERROR_ALLOW_COUNT - 1;
		return userLoginLogDao.isPasswordErrorFrequently(account, fromTime, toTime, maxErrorCount);
	}

	public void setUserLoginLogDao(UserLoginLogDao userLoginLogDao) {
		this.userLoginLogDao = userLoginLogDao;
	}

	@Override
	public TUserLoginLog getPreLoginSuccessLog(String curLogId) {
		String userId = UserTool.getCurrentUser().getId();
		return userLoginLogDao.getPreLoginSuccessLog(curLogId, userId);
	}

	@Override
	public TUserLoginLog onLogout(String logoutTime) {
		String userId = UserTool.getCurrentUser().getId();
		TUserLoginLog loginLog = userLoginLogDao.getPreLoginSuccessLog(null, userId);
		if (loginLog != null) {
			loginLog.setLogoutTime(logoutTime);
			JpaTool.persist(loginLog);
		}
		return loginLog;
	}

}

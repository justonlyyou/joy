package com.kvc.joy.plugin.security.login.service.impl;

import java.util.Date;

import org.springframework.transaction.annotation.Transactional;

import com.kvc.joy.commons.bean.BeanTool;
import com.kvc.joy.commons.lang.DateTool;
import com.kvc.joy.commons.lang.string.StringTool;
import com.kvc.joy.core.init.support.JoyPropeties;
import com.kvc.joy.core.persistence.orm.jpa.JpaTool;
import com.kvc.joy.plugin.security.erbac.model.po.TErbacUser;
import com.kvc.joy.plugin.security.erbac.support.utils.UserTool;
import com.kvc.joy.plugin.security.login.dao.LoginLogDao;
import com.kvc.joy.plugin.security.login.model.po.TLoginLog;
import com.kvc.joy.plugin.security.login.service.ILoginLogService;
import com.kvc.joy.plugin.security.login.support.vo.LoginVo;

/**
 * 登陆日志服务
 * 
 * @author 唐玮琳
 * @time 2012-6-17 下午9:03:02
 */
public class LoginLogService implements ILoginLogService {
	
	private LoginLogDao loginLogDao;

	@Transactional
	public TLoginLog logOnLogin(LoginVo loginVo) {
		TLoginLog loginLog = new TLoginLog();
		BeanTool.copyPropertiesExcludeId(loginVo, loginLog);
		if (loginLog.getLoginTime() == null) {
			loginLog.setLoginTime(DateTool.currentDate(DateTool.UNFMT_yyyyMMddHHmmss));
		}
		loginLog.setUserPassword(StringTool.toMd5HexStr(loginLog.getUserPassword()));
		TErbacUser currentUser = UserTool.getCurrentUser();
		if (currentUser != null) {
			loginLog.setUserId(currentUser.getId());	
		}
		JpaTool.persist(loginLog);
		return loginLog;
	}
	
	@Override
	public long statPasswordErrorCount(String account, String fromTime, String toTime) {
		return loginLogDao.statPasswordErrorCount(account, fromTime, toTime);
	}

	/**
	 * 最近几个小时内最近密码连续错误超过预定的次数需要提供验证码
	 */
	@Override
	public boolean shouldCaptchaRequire(String account) {
		int hour = JoyPropeties.PLUGIN_LOGIN_PASSWORD_ERROR_PERIOD_HOUR;
		Date now = new Date();
		String fromTime = DateTool.formatDate(DateTool.addHours(now, -hour), DateTool.UNFMT_yyyyMMddHHmmss);
		String toTime = DateTool.formatDate(now, DateTool.UNFMT_yyyyMMddHHmmss);
		int maxErrorCount = JoyPropeties.PLUGIN_LOGIN_PASSWORD_ERROR_ALLOW_COUNT - 1;
		return loginLogDao.isPasswordErrorFrequently(account, fromTime, toTime, maxErrorCount);
	}
	
	public void setLoginLogDao(LoginLogDao loginLogDao) {
		this.loginLogDao = loginLogDao;
	}

	@Override
	public TLoginLog getPreLoginSuccessLog(String curLogId) {
		String userId = UserTool.getCurrentUser().getId();
		return loginLogDao.getPreLoginSuccessLog(curLogId, userId);
	}

}

package com.kvc.joy.plugin.security.login.service;

import com.kvc.joy.plugin.security.login.model.po.TLoginLog;
import com.kvc.joy.plugin.security.login.support.enums.LogoutMethod;

/**
 * 
 * @since 1.0.0
 * @author 唐玮琳
 * @time 2013年10月15日 上午10:37:45
 */
public interface ILogoutService {

	/**
	 * 登出
	 * 
	 * @param loginLogId 登陆日志id
	 * @param logoutMethod 登出方法
	 * @param logoutTime 登出时间
	 * @return 登出日志
	 * @since 1.0.0
	 * @author 唐玮琳
	 * @time 2013年10月15日 下午2:24:59
	 */
	void logout(String loginLogId, LogoutMethod logoutMethod, String logoutTime);
	
	/**
	 * 登录时补登出日志(可能因断电、出错等原因登出时未能记录日志)
	 * 
	 * @param logOnLogin 登录日志
	 * @since 1.0.0
	 * @author 唐玮琳
	 * @time 2013年10月15日 下午2:47:48
	 */
	void mendLogoutLogOnLogin(TLoginLog logOnLogin);
	
}

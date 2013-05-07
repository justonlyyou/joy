package com.kvc.joy.plugin.security.login.service;

import com.kvc.joy.plugin.security.login.support.enums.LogoutMethod;

/**
 * 
 * @since 1.0.0
 * @author 唐玮琳
 * @time 2013年10月15日 下午2:29:56
 */
public interface ILogoutLogService {
	
	/**
	 * 登出时记录日志
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

}

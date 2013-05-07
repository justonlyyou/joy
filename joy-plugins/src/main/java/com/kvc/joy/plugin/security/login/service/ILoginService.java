package com.kvc.joy.plugin.security.login.service;

import com.kvc.joy.plugin.security.login.support.vo.LoginVo;

/**
 * 用户登陆接口
 * 
 * @since 1.0.0
 * @author 唐玮琳
 * @time 2013年9月29日 上午1:18:29
 */
public interface ILoginService {
	
	/**
	 * 用户登陆
	 * 
	 * @param loginVo 登陆VO
	 * @return 错误消息，成功是返回null
	 * @since 1.0.0
	 * @author 唐玮琳
	 * @time 2013年9月29日 上午1:24:27
	 */
	String login(LoginVo loginVo);
	
	/**
	 * 是否已登陆
	 * 
	 * @return true: 已登陆, false: 未登陆
	 * @since 1.0.0
	 * @author 唐玮琳
	 * @time 2013年9月29日 上午1:25:31
	 */
	boolean hasLogin();

}

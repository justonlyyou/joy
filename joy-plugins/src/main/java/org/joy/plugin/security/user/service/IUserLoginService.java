package org.joy.plugin.security.user.service;

import org.joy.plugin.security.user.support.vo.UserLoginVo;

/**
 * 用户登陆接口
 * 
 * @since 1.0.0
 * @author Kevice
 * @time 2013年9月29日 上午1:18:29
 */
public interface IUserLoginService {
	
	/**
	 * 用户登陆
	 * 
	 * @param loginVo 登陆VO
	 * @return 错误消息，成功是返回null
	 * @since 1.0.0
	 * @author Kevice
	 * @time 2013年9月29日 上午1:24:27
	 */
	String login(UserLoginVo loginVo);
	
	/**
	 * 是否已登陆
	 * 
	 * @return true: 已登陆, false: 未登陆
	 * @since 1.0.0
	 * @author Kevice
	 * @time 2013年9月29日 上午1:25:31
	 */
	boolean hasLogin();

}

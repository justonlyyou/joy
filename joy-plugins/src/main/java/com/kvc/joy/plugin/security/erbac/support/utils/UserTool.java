package com.kvc.joy.plugin.security.erbac.support.utils;

import com.kvc.joy.plugin.security.user.model.po.TUserBasic;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;

/**
 * 
 * @since 1.0.0
 * @author Kevice
 * @time 2013年10月5日 下午4:17:32
 */
public class UserTool {
	
	private UserTool() {
	}
	
	/**
	 * 获取当前登陆的用户，请确定在web上下文中调用该方法，否则将返回null
	 * 
	 * @return TErbacUser对象
	 * @since 1.0.0
	 * @author Kevice
	 * @time 2013年10月5日 下午4:28:07
	 */
	public static TUserBasic getCurrentUser() {
		Subject subject = SecurityUtils.getSubject();
		Object principal = subject.getPrincipal();
		return (TUserBasic) principal;
	}

}

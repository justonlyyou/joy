package com.kvc.joy.web.support.utils;

import com.kvc.joy.plugin.security.user.model.po.TUserBasic;

import javax.servlet.http.HttpSession;

/**
 * 
 * @author Kevice
 * @time 2012-6-14 下午10:06:47
 */
public class HttpSessionTool {

	private HttpSessionTool() {
	}

	public static HttpSession getSession() {
		return HttpRequestTool.getRequest().getSession();
	}

	public static TUserBasic getUser() {
		return (TUserBasic) getSession().getAttribute("userId");
	}

}

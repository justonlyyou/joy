package com.kvc.joy.web.support.utils;

import com.kvc.joy.plugin.security.erbac.model.po.TErbacUser;

import javax.servlet.http.HttpSession;

/**
 * 
 * @author 唐玮琳
 * @time 2012-6-14 下午10:06:47
 */
public class HttpSessionTool {

	private HttpSessionTool() {
	}

	public static HttpSession getSession() {
		return HttpRequestTool.getRequest().getSession();
	}

	public static TErbacUser getUser() {
		return (TErbacUser) getSession().getAttribute("userId");
	}

}

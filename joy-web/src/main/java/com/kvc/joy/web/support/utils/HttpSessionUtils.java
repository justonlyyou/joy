package com.kvc.joy.web.support.utils;

import javax.servlet.http.HttpSession;

import com.kvc.joy.plugin.security.erbac.model.po.TErbacUser;

/**
 * 
 * @author 唐玮琳
 * @time 2012-6-14 下午10:06:47
 */
public class HttpSessionUtils {

	private HttpSessionUtils() {
	}

	public static HttpSession getSession() {
		return HttpRequestUtils.getRequest().getSession();
	}

	public static TErbacUser getUser() {
		return (TErbacUser) getSession().getAttribute("userId");
	}

}

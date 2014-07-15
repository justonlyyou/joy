package org.joy.web.spmvc.core;

import org.joy.commons.exception.ServiceException;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 
 * @since 1.0.0
 * @author Kevice
 * @time 2013年10月9日 下午9:34:09
 */
public class WebGlobalInterceptor implements HandlerInterceptor {

	@Override
	public void afterCompletion(HttpServletRequest req, HttpServletResponse resp, Object obj, Exception e)
			throws Exception {
//        if(e instanceof ServiceException) {
//            resp.getWriter().write(e.getMessage());
//        }
	}

	@Override
	public void postHandle(HttpServletRequest req, HttpServletResponse resp, Object obj, ModelAndView vw)
			throws Exception {
		System.out.println("postHandle");
	}

	@Override
	public boolean preHandle(HttpServletRequest req, HttpServletResponse resp, Object obj) throws Exception {
		System.out.println("preHandle");
		return true;
	}

}

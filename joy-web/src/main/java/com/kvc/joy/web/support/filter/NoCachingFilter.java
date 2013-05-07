package com.kvc.joy.web.support.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletResponse;

public class NoCachingFilter implements Filter {

	protected FilterConfig filterConfig = null;

	/**
	 * 是否将客户端报告的Encoding设置忽略，强制使用我们设置的Encoding? 默认是忽略
	 */
	protected boolean ignore = true;

	public void destroy() {

		this.filterConfig = null;

	}

	/**
	 * 对当前request进行encoding设置（如果不选择忽略客户端设置的话）
	 * @param request 当前处理的SevletRequest
	 * @param result 当前处理的SevletResponse
	 * @param chain 当前Filter链
	 * @exception IOException I/O错误发生
	 * @exception ServletException Servelt错误发生
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

		HttpServletResponse httpResponse = (HttpServletResponse) response;

		httpResponse.setHeader("Cache-Control", "no-cache");
		httpResponse.setDateHeader("Expires", 0);
		httpResponse.setHeader("Pragma", "no-cache");
		chain.doFilter(request, response);
	}

	/**
	 * 初始化
	 * @param filterConfig Filter设置对象
	 */
	public void init(FilterConfig filterConfig) throws ServletException {

		this.filterConfig = filterConfig;

	}

}

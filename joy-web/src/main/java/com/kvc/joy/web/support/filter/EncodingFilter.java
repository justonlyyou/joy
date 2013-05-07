package com.kvc.joy.web.support.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

/**
 * 编码过滤
 */
public class EncodingFilter implements Filter {

	public EncodingFilter() {
		encoding = "GBK";
		filterConfig = null;
		ignore = true;
	}

	/** destory */
	public void destroy() {
		encoding = null;
		filterConfig = null;
	}

	/**
	 * 过滤
	 * @param request ServletRequest
	 * @param response ServletResponse
	 * @param chain FilterChain
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) {
		try {
			if (ignore || request.getCharacterEncoding() == null) {
				HttpServletRequest httpRequest = (HttpServletRequest) request;
				String encoding = selectEncoding(request);
				if (encoding != null) {
					String uri = httpRequest.getRequestURI();
					if (uri.indexOf("/xxhc/") != -1) {
						request.setCharacterEncoding("utf-8");
					} else {
						request.setCharacterEncoding(encoding);
					}
				}

			}
			chain.doFilter(request, response);
		} catch (IOException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		} catch (ServletException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}

	/**
	 * 初始化
	 * @param filterConfig FilterConfig
	 * @throws ServletException ServletException
	 */
	public void init(FilterConfig filterConfig) throws ServletException {
		this.filterConfig = filterConfig;
		encoding = filterConfig.getInitParameter("encoding");
		String value = filterConfig.getInitParameter("ignore");
		if (value == null) {
			ignore = true;
		} else if (value.equalsIgnoreCase("true")) {
			ignore = true;
		} else if (value.equalsIgnoreCase("yes")) {
			ignore = true;
		} else {
			ignore = false;
		}
	}

	/**
	 * @param request ServletRequest
	 * @return String
	 */
	protected String selectEncoding(ServletRequest request) {
		return encoding;
	}

	/** encoding */
	protected String encoding;

	/** filterConfig */
	protected FilterConfig filterConfig;

	/** ignore */
	protected boolean ignore;
}

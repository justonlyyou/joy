package com.kvc.joy.web.spmvc.modules.plugins.security.login.filter;

import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.kvc.joy.commons.lang.string.StringTool;
import com.kvc.joy.plugin.security.erbac.model.po.TErbacUser;
import com.kvc.joy.plugin.security.login.support.ipmatch.IpMatchFacility;

/**
 * 登录过滤器
 * 
 * @author 唐玮琳
 */
public class LoginFilter implements Filter {

	private static Logger logger = LoggerFactory.getLogger(LoginFilter.class);
	private static IpMatchFacility ipMatch;
	private String loginPage;

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain) throws IOException, ServletException {
		String currentIpAdress = currentIp((HttpServletRequest) request);
		if (StringTool.isNotBlank(currentIpAdress)) {
			// 当前操作ip是否在授权的范围内
			boolean isIpAuth = ipMatch.isMatch(currentIpAdress);
			if (isIpAuth == false) {
				response.setContentType("text/plain;charset=utf-8");
				response.getWriter().print("{info:'登陆IP：" + currentIpAdress + "不在授权范围内'}");
				return;
			}
		} else {
			logger.warn("ip获取失败！[ip:" + currentIpAdress + "]");
		}

		HttpServletRequest httpRequest = (HttpServletRequest) request;
		HttpServletResponse httpResponse = (HttpServletResponse) response;
		HttpSession httpSession = httpRequest.getSession();

		String uri = httpRequest.getRequestURI();
		TErbacUser user = (TErbacUser) httpSession.getAttribute(TErbacUser.HTTP_SESSION_USER_ID);
		if (user == null && !uri.contains(loginPage) && !uri.endsWith("login.do") && (uri.endsWith(".jsp") || uri.endsWith(".do"))) { // TODO
			request.getRequestDispatcher(loginPage).forward(request, response);
//			httpResponse.sendRedirect(httpRequest.getContextPath() + "/" +loginPage);
		} else {
			filterChain.doFilter(request, response);
		}
	}

	public void init(FilterConfig filterConfig) throws ServletException {
		loginPage = filterConfig.getInitParameter("loginPage");
		String ipZone = filterConfig.getInitParameter("ipZone");
		if (StringTool.isNotBlank(ipZone)) {
			logger.info("授权IP段为：" + ipZone);
			ipMatch = new IpMatchFacility(ipZone);
		} else {
			throw new ServletException("未配置授权IP段,请配置WEB-INF\\Web.xml");
		}
	}

	private String currentIp(HttpServletRequest httpRequest) {
		String currentIp = null;
		String ip = httpRequest.getHeader("x-forwarded-for");
		if (StringTool.isBlank(ip) || "unknown".equalsIgnoreCase(ip)) {
			currentIp = httpRequest.getHeader("Proxy-Client-IP");
		}
		if (StringTool.isBlank(ip) || "unknown".equalsIgnoreCase(ip)) {
			currentIp = httpRequest.getHeader("WL-Proxy-Client-IP");
		}
		if (StringTool.isBlank(ip) || "unknown".equalsIgnoreCase(ip)) {
			currentIp = httpRequest.getRemoteAddr();
		}

		if ("0:0:0:0:0:0:0:1".equals(currentIp)) { // 表示通过远程登陆访问页面
			try {
				currentIp = InetAddress.getLocalHost().getHostAddress();
			} catch (UnknownHostException e) {
				logger.error("", e);
			}
		}
		logger.debug("当前用户的IP为:" + currentIp);
		return currentIp;
	}

	public void destroy() {
	}
}

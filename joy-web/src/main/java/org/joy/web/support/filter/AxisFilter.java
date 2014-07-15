package org.joy.web.support.filter;

import org.joy.commons.lang.string.StringTool;
import org.joy.commons.log.Log;
import org.joy.commons.log.LogFactory;
import org.joy.plugin.security.user.support.ipmatch.IpMatchFacility;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

public class AxisFilter implements Filter{
	
	protected static final Log logger = LogFactory.getLog(AxisFilter.class);
	
	private IpMatchFacility ipMatch;

	public void init(FilterConfig arg0) throws ServletException {
		String axisIP = arg0.getInitParameter("axisIP");
		if (StringTool.isNotBlank(axisIP)) {
			logger.info("授权IP段为：" + axisIP);
			ipMatch = new IpMatchFacility(axisIP);
		}
	}

	public void doFilter(ServletRequest req, ServletResponse resp, FilterChain filterChain) throws IOException, ServletException {
		HttpServletRequest httpRequest = (HttpServletRequest) req;
		String clientIP = getIpAddr(httpRequest);
		if (ipMatch.isMatch(clientIP)) {
			filterChain.doFilter(req, resp);
		} else {
			logger.info("该请求[IP：" + clientIP + "]未在授权范围");
        }
	}
	public static String getIpAddr(HttpServletRequest request) {
		String ip = request.getHeader("x-forwarded-for");
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("WL-Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getRemoteAddr();
		}
		return ip;
	}
	public void destroy() {
		// TODO Auto-generated method stub
		
	}

}

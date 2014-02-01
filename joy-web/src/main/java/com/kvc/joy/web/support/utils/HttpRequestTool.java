package com.kvc.joy.web.support.utils;

import com.kvc.joy.commons.bean.Pair;
import com.kvc.joy.commons.lang.ArrayTool;
import com.kvc.joy.commons.lang.string.StringTool;
import com.kvc.joy.commons.log.Log;
import com.kvc.joy.commons.log.LogFactory;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 
 * @author 唐玮琳
 * @time 2012-6-14 下午10:05:28
 */
public class HttpRequestTool {
	
	private static final Log logger = LogFactory.getLog(HttpRequestTool.class);

	private HttpRequestTool() {
	}

	public static HttpServletRequest getRequest() {
		ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
		return attr.getRequest();
	}

	public static String getParameter(String paramName) {
		return getRequest().getParameter(paramName);
	}
	
	@SuppressWarnings("unchecked")
	public static Map<String, String[]> getParameterMap() {
		return getRequest().getParameterMap();
	}
	
	/**
	 * 与getParameterMap()的差别在于，它的返回值类型为Map<String, String>，
	 * 所以当同一参数名对应的value值有多个时，只返回第一个，这一点类似于getParameterValues()与getParameter()的差别
	 * 
	 * @return
	 * @since 1.0.0
	 * @author 唐玮琳
	 * @time 2013年10月9日 下午10:14:50
	 */
	public static Map<String, String> getParameters() {
		Map<String, String[]> parameterMap = getParameterMap();
		Map<String, String> parameters = new HashMap<String, String>(parameterMap.size());
		for (Entry<String, String[]> entry : parameterMap.entrySet()) {
			String param = entry.getKey();
			String[] values = entry.getValue();
			String value = "";
			if(ArrayTool.isNotEmpty(values)) {
				value = values[0];
			}
			parameters.put(param, value);
		}
		return parameters;
	}

	public static int getIntParam(String paramName) {
		return Integer.valueOf(getParameter(paramName));
	}

	/**
	 * 获取请求的真实ip地址，支持多级反向代理
	 * 
	 * @return ip地址
	 * @since 1.0.0
	 * @author 唐玮琳
	 * @time 2013年10月1日 下午5:44:28
	 */
	public static String getIpAddr() {
		HttpServletRequest request = getRequest();
		String ip = request.getHeader("x-forwarded-for");
		if (StringTool.isBlank(ip) || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("Proxy-Client-IP");
		}
		if (StringTool.isBlank(ip) || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("WL-Proxy-Client-IP");
		}
		if (StringTool.isBlank(ip) || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getRemoteAddr();
		}

		// 处理多级反向代理
		if (ip.contains(",")) {
			ip = ip.split(",")[0];
		}

		if ("0:0:0:0:0:0:0:1".equals(ip)) { // 表示通过远程登陆访问页面
			try {
				ip = InetAddress.getLocalHost().getHostAddress();
			} catch (UnknownHostException e) {
				 logger.error(e);
			}
		}
		// logger.debug("当前用户的IP为:" + currentIp);

		return ip;
	}

	/**
	 * 获取请求的浏览器信息
	 * 
	 * @return Pair<浏览器名称，版本>
	 * @since 1.0.0
	 * @author 唐玮琳
	 * @time 2013年10月1日 下午6:11:43
	 */
	public static Pair<String, String> getBrowserInfo() {
		String agent = getRequest().getHeader("User-Agent");
		String name = "unknown";
		String version = "unknown";
		Pattern pattern = null;
		Matcher matcher;
		if (agent.contains("MSIE")) {
			name = "MSIE"; // 微软IE
			pattern = Pattern.compile(name + "\\s([0-9.]+)");
		} else if (agent.contains("Firefox")) {
			name = "Firefox"; // 火狐
			pattern = Pattern.compile(name + "\\/([0-9.]+)");
		} else if (agent.contains("Chrome")) {
			name = "Chrome"; // Google
			pattern = Pattern.compile(name + "\\/([0-9.]+)");
		} else if (agent.contains("Opera")) {
			name = "Opera"; // Opera
			pattern = Pattern.compile("Version\\/([0-9.]+)");
		} else if (agent.contains("Safari")) {
			name = "Safari"; // Opera
			pattern = Pattern.compile("Version\\/([0-9.]+)");
		}
		if (pattern != null) {
			matcher = pattern.matcher(agent);
			if (matcher.find()) {
				version = matcher.group(1);
			}
		}
		return new Pair<String, String>(name, version);
	}

	/**
	 * 获取请求的操作系统信息
	 * 
	 * @return Pair<操作系统名称，版本>
	 * @since 1.0.0
	 * @author 唐玮琳
	 * @time 2013年10月1日 下午6:12:43
	 */
	public static Pair<String, String> getOsInfo() {
		//TODO
		String agent = getRequest().getHeader("User-Agent");
		String name = "unknown";
		String version = "unknown";
        if(agent.contains("Windows")){   
            name = "Windows"; //如：win7 = Windows NT 6.1  
            Pattern pattern = Pattern.compile(name + "\\s([a-zA-Z0-9]+\\s[0-9.]+)");
            Matcher matcher = pattern.matcher(agent);
            if(matcher.find()) {
            	version = matcher.group(1);   
            }
        } else if(agent.contains("FreeBSD")) {
        	name = "FreeBSD";
        } else if(agent.contains("Macintosh")) {
        	name = "Mac";
        } else if(agent.contains("SunOS")) {
        	name = "Solaris";
        } else if(agent.contains("Linux")) {
        	name = "Linux";
        	if (agent.contains("Ubuntu")) {
        		version = "Ubuntu";
			}
        }
        
        return new Pair<String, String>(name, version);
	}

}

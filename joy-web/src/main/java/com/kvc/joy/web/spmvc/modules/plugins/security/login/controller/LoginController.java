package com.kvc.joy.web.spmvc.modules.plugins.security.login.controller;

import com.kvc.joy.commons.bean.Pair;
import com.kvc.joy.commons.lang.string.StringTool;
import com.kvc.joy.plugin.image.captcha.consts.CaptchaConsts;
import com.kvc.joy.plugin.security.login.support.vo.LoginVo;
import com.kvc.joy.plugin.support.PluginBeanFactory;
import com.kvc.joy.web.support.utils.HttpRequestTool;
import com.kvc.joy.web.support.utils.HttpSessionTool;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 
 * @author 唐玮琳
 * @time 2013-2-23 下午7:46:37
 */
@Controller
public class LoginController {

	private static final String LOGIN_VIEW_NAME = "joy/plugins/login/login";

	// @Resource
	// private ICaptchaService captchaService;

	@RequestMapping(value = "/login", method = RequestMethod.POST)
	@ResponseBody
	public String login(@RequestParam("username") String username, @RequestParam("password") String password,
			@RequestParam(value = "captcha", required = false) String captcha,
			@RequestParam(value = "rememberMe", required = false) String rememberMe) {
		boolean captchaRequire = PluginBeanFactory.getLoginLogService().shouldCaptchaRequire(username);
		if (StringTool.isBlank(captcha)) {
			if (captchaRequire) {
				return "captchaRequire";
			}
		}

		LoginVo loginVo = createLoginVo(username, password, captcha, rememberMe, captchaRequire);
		return PluginBeanFactory.getLoginService().login(loginVo);
	}

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String login() {
		boolean hasLogin = PluginBeanFactory.getLoginService().hasLogin();
		if (hasLogin) {
			return "redirect:";
		} else {
			return LOGIN_VIEW_NAME;
		}
	}

	@RequestMapping(value = "")
	public String home() {
		return "index.jsp";
	}

	private LoginVo createLoginVo(String username, String password, String captcha, String rememberMe,
			boolean captchaRequire) {
		LoginVo loginVo = new LoginVo();
		loginVo.setUserAccount(username);
		loginVo.setUserPassword(password);
		 loginVo.setRememberMe(StringTool.isNotBlank(rememberMe) ? "1" : "0");
		loginVo.setCaptchaRequire(captchaRequire);
		loginVo.setCaptchaClient(captcha);
		loginVo.setLoginIp(HttpRequestTool.getIpAddr());
		Pair<String, String> osInfo = HttpRequestTool.getOsInfo();
		loginVo.setOsType(osInfo.getLeft());
		loginVo.setOsVersion(osInfo.getRight());
		Pair<String, String> browserInfo = HttpRequestTool.getBrowserInfo();
		loginVo.setBroswerType(browserInfo.getLeft());
		loginVo.setBroswerVersion(browserInfo.getRight());
		String captchaGenTime = (String) HttpSessionTool.getSession().getAttribute(CaptchaConsts.CAPTCHA_SESSION_DATE);
		loginVo.setCaptchaGenTime(captchaGenTime);
		String captchaServer = (String) HttpSessionTool.getSession().getAttribute(CaptchaConsts.CAPTCHA_SESSION_KEY);
		loginVo.setCaptchaServer(captchaServer);
		return loginVo;
	}

}
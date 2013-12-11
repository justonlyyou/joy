package com.kvc.joy.plugin.security.login.service.impl;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;

import com.kvc.joy.commons.log.Log;
import com.kvc.joy.commons.log.LogFactory;
import com.kvc.joy.plugin.security.erbac.model.po.TErbacUser;
import com.kvc.joy.plugin.security.login.model.po.TLoginLog;
import com.kvc.joy.plugin.security.login.service.ILoginLogService;
import com.kvc.joy.plugin.security.login.service.ILoginService;
import com.kvc.joy.plugin.security.login.support.enums.LoginState;
import com.kvc.joy.plugin.security.login.support.vo.LoginVo;
import com.kvc.joy.plugin.support.PluginBeanFactory;

/**
 * 用户登陆服务
 * 
 * @since 1.0.0
 * @author 唐玮琳
 * @time 2013年9月29日 上午1:18:42
 */
public class LoginService  implements ILoginService {
	
	private ILoginLogService loginLogService;
	private Log log = LogFactory.getLog(getClass());

	@Override
	public String login(LoginVo loginVo) {
		// validate
		// / captcha
		String errMsg = null;
		if (loginVo.isCaptchaRequire()) {
			errMsg = validateCaptcha(loginVo);
			if (errMsg != null) {
				loginVo.setLoginState(LoginState.CAPTCHA_ERR);
				loginLogService.logOnLogin(loginVo);
				return errMsg;
			}	
		}
		
		// / username & password
		Subject currentUser = SecurityUtils.getSubject();
		if (currentUser.isAuthenticated() == false) {
			errMsg = validateUserAndPassword(currentUser, loginVo);
		} else { // 反复登录
			TErbacUser user = (TErbacUser) currentUser.getPrincipal();
			if (user.getAccount().equalsIgnoreCase(loginVo.getUserAccount()) == false) { // 若是登录名不合
				currentUser.logout();
				errMsg = validateUserAndPassword(currentUser, loginVo);
			}
		}
		
		if (errMsg == null) {
			loginVo.setLoginState(LoginState.SUCCESS);
			TLoginLog logOnLogin = loginLogService.logOnLogin(loginVo);
			PluginBeanFactory.getLogoutService().mendLogoutLogOnLogin(logOnLogin);
		}
		
		//TODO 多处登陆
		
		//TODO 帐号状态

		return errMsg;
	}

	@Override
	public boolean hasLogin() {
		Subject currentUser = SecurityUtils.getSubject();
		return currentUser.isAuthenticated();
	}
	
	private String validateCaptcha(LoginVo loginVo) {
		String errMsg = null;

		// 二次校验验证码(第一次为通过jquer.validate的remote配置校验)
		boolean validateTime = PluginBeanFactory.getCaptchaService().validateTime(loginVo.getCaptchaGenTime());
		if (validateTime == false) {
			errMsg = "验证码已过期，请重新获取.";
		} else {
			boolean sucess = PluginBeanFactory.getCaptchaService().validateCode(
					loginVo.getCaptchaServer(), loginVo.getCaptchaClient());
			if (sucess == false) {
				errMsg = "验证码不正确.";
			}
		}

		return errMsg;
	}

	private String validateUserAndPassword(Subject currentUser, LoginVo loginVo) {
		String account = loginVo.getUserAccount();
		String password = loginVo.getUserPassword();
		boolean rememberMe = loginVo.isRememberMe();
				
		String errMsg = null;
		UsernamePasswordToken token = new UsernamePasswordToken(account, password);
		token.setRememberMe(rememberMe);
		try {
			currentUser.login(token);
		} catch (UnknownAccountException uae) {
			log.error(uae);
			errMsg = "用户名或密码错误！"; // 用户名不存在，为了安全性，不精确提示
		} catch (IncorrectCredentialsException ice) {
			log.error(ice);
			errMsg = "用户名或密码错误！";
			loginVo.setLoginState(LoginState.PASSWORD_ERR);
			loginLogService.logOnLogin(loginVo);
		} catch (LockedAccountException lae) {
			log.error(lae);
			errMsg = "您的帐号已被锁定！";
			loginVo.setLoginState(LoginState.ACCOUNT_LOCK);
			loginLogService.logOnLogin(loginVo);
		} catch (AuthenticationException ae) {
			log.error(ae);
			errMsg = "授权发生异常！";
		} catch (Exception ae) {
			log.error(ae);
			errMsg = "发生未预期的错误！";
		}
		return errMsg;
	}
	
	public void setLoginLogService(ILoginLogService loginLogService) {
		this.loginLogService = loginLogService;
	}

}

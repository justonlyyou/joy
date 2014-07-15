package org.joy.plugin.security.user.support.vo;

import org.joy.plugin.security.user.model.po.TUserLoginLog;

/**
 * 
 * @since 1.0.0
 * @author Kevice
 * @time 2013年10月1日 下午5:16:14
 */
public class UserLoginVo extends TUserLoginLog {

	private boolean captchaRequire; // 是否需要验证码
	private String captchaClient; // 用户提交的验证码
	private String captchaServer; // 系统生成的验证码
	private String captchaGenTime; // 验证码生成时间

	public boolean isCaptchaRequire() {
		return captchaRequire;
	}

	public void setCaptchaRequire(boolean captchaRequire) {
		this.captchaRequire = captchaRequire;
	}
	
	public String getCaptchaClient() {
		return captchaClient;
	}

	public void setCaptchaClient(String captchaClient) {
		this.captchaClient = captchaClient;
	}

	public String getCaptchaGenTime() {
		return captchaGenTime;
	}

	public void setCaptchaGenTime(String captchaGenTime) {
		this.captchaGenTime = captchaGenTime;
	}

	public String getCaptchaServer() {
		return captchaServer;
	}

	public void setCaptchaServer(String captchaServer) {
		this.captchaServer = captchaServer;
	}

}

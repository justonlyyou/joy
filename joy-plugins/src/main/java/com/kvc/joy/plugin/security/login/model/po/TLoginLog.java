package com.kvc.joy.plugin.security.login.model.po;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.apache.commons.lang3.StringUtils;

import com.kvc.joy.core.persistence.entity.UuidEntity;
import com.kvc.joy.plugin.security.login.support.enums.LoginFailReason;

@Entity
@Table(name = "T_LOGIN_LOG")
public class TLoginLog extends UuidEntity {

	private String userId; // 用户
	private String userAccount; // 用户帐号
	private String userName; // 姓名
	private String userPassword; // 密码
	private String loginTime; // 登陆时间
	private String logoutTime; // 登出时间
	private String loginIp; // 登陆IP
	private String broswerType; // 使用的浏览器类型
	private String broswerVersion; // 使用的浏览器版本
	private String osType; // 使用的操作系统类型
	private String osVersion; // 使用的操作系统版本
	private boolean loginSuccess; // 是否登陆成功
	private LoginFailReason loginFailReason; // 登陆失败原因
	private boolean rememberMe; // 记住我
	

	@Column(length = 32)
	public String getUserAccount() {
		return userAccount;
	}

	public void setUserAccount(String userAccount) {
		this.userAccount = userAccount;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserPassword() {
		return userPassword;
	}

	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}

	@Column(length = 14)
	public String getLoginTime() {
		return loginTime;
	}

	public void setLoginTime(String loginTime) {
		this.loginTime = loginTime;
	}

	@Column(length = 14)
	public String getLogoutTime() {
		return logoutTime;
	}

	public void setLogoutTime(String logoutTime) {
		this.logoutTime = logoutTime;
	}

	public String getLoginIp() {
		return loginIp;
	}

	public void setLoginIp(String loginIp) {
		this.loginIp = loginIp;
	}

	public String getBroswerType() {
		return broswerType;
	}

	public void setBroswerType(String broswerType) {
		this.broswerType = broswerType;
	}

	public String getOsType() {
		return osType;
	}

	public void setOsType(String osType) {
		this.osType = osType;
	}

	public boolean isLoginSuccess() {
		return loginSuccess;
	}

	public void setLoginSuccess(boolean loginSuccess) {
		this.loginSuccess = loginSuccess;
	}

	@Transient
	public LoginFailReason getLoginFailReason() {
		return loginFailReason;
	}

	public void setLoginFailReason(LoginFailReason loginFailReason) {
		this.loginFailReason = loginFailReason;
	}
	
	@Column(length = 2)
	public String getLoginFailReasonCode() {
		return loginFailReason == null ? null : loginFailReason.getCode();
	}

	public void setLoginFailReasonCode(String loginFailReasonCode) {
		if (StringUtils.isBlank(loginFailReasonCode)) {
			this.loginFailReason = null;
		} else {
			this.loginFailReason = LoginFailReason.enumOf(loginFailReasonCode);	
		}
	}

	public boolean isRememberMe() {
		return rememberMe;
	}

	public void setRememberMe(boolean rememberMe) {
		this.rememberMe = rememberMe;
	}

	public String getBroswerVersion() {
		return broswerVersion;
	}

	public void setBroswerVersion(String broswerVersion) {
		this.broswerVersion = broswerVersion;
	}

	public String getOsVersion() {
		return osVersion;
	}

	public void setOsVersion(String osVersion) {
		this.osVersion = osVersion;
	}

	@Column(length = 32)
	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}
	
}

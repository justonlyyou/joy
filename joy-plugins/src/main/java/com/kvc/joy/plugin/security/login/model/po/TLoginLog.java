package com.kvc.joy.plugin.security.login.model.po;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.apache.commons.lang3.StringUtils;

import com.kvc.joy.core.persistence.entity.UuidEntity;
import com.kvc.joy.core.persistence.orm.jpa.annotations.Comment;
import com.kvc.joy.plugin.security.login.support.enums.LoginState;

@Entity
@Table(name = "t_login_log")
@Comment("登陆日志")
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
	private LoginState loginState; // 登陆状态
	private String rememberMe; // 记住我
	

	@Column(length = 32)
	@Comment("用户账号")
	public String getUserAccount() {
		return userAccount;
	}

	public void setUserAccount(String userAccount) {
		this.userAccount = userAccount;
	}

	@Column(length=64)
	@Comment("用户姓名")
	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	@Column(length=32)
	@Comment("用户密码")
	public String getUserPassword() {
		return userPassword;
	}

	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}

	@Column(length = 14)
	@Comment("登陆时间")
	public String getLoginTime() {
		return loginTime;
	}

	public void setLoginTime(String loginTime) {
		this.loginTime = loginTime;
	}

	@Column(length = 14)
	@Comment("登出时间")
	public String getLogoutTime() {
		return logoutTime;
	}

	public void setLogoutTime(String logoutTime) {
		this.logoutTime = logoutTime;
	}

	@Column(length = 32)
	@Comment("登陆IP")
	public String getLoginIp() {
		return loginIp;
	}

	public void setLoginIp(String loginIp) {
		this.loginIp = loginIp;
	}

	@Column(length = 32)
	@Comment("浏览器类型")
	public String getBroswerType() {
		return broswerType;
	}

	public void setBroswerType(String broswerType) {
		this.broswerType = broswerType;
	}

	@Column(length = 32)
	@Comment("操作系统类型")
	public String getOsType() {
		return osType;
	}

	public void setOsType(String osType) {
		this.osType = osType;
	}

	@Transient
	public LoginState getLoginState() {
		return loginState;
	}

	public void setLoginState(LoginState loginState) {
		this.loginState = loginState;
	}
	
	@Column(length = 2)
	@Comment(value="登陆状态代码", codeId="login_state")
	public String getLoginStateCode() {
		return loginState == null ? null : loginState.getCode();
	}

	public void setLoginStateCode(String loginStateCode) {
		if (StringUtils.isBlank(loginStateCode)) {
			this.loginState = null;
		} else {
			this.loginState = LoginState.enumOf(loginStateCode);	
		}
	}

	@Column(length = 1, nullable = false)
	@Comment("是否记住我")
	public String getRememberMe() {
		return rememberMe;
	}
	
	public boolean rememberMe() {
		return "1".equals(rememberMe);
	}

	public void setRememberMe(String rememberMe) {
		this.rememberMe = rememberMe;
	}

	@Column(length = 32)
	@Comment("浏览器版本")
	public String getBroswerVersion() {
		return broswerVersion;
	}

	public void setBroswerVersion(String broswerVersion) {
		this.broswerVersion = broswerVersion;
	}

	@Column(length = 32)
	@Comment("操作系统版本")
	public String getOsVersion() {
		return osVersion;
	}

	public void setOsVersion(String osVersion) {
		this.osVersion = osVersion;
	}

	@Column(length = 32)
	@Comment("用户ID")
	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}
	
}

package com.kvc.joy.plugin.security.login.model.po;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.apache.commons.lang3.StringUtils;

import com.kvc.joy.core.persistence.entity.UuidEntity;
import com.kvc.joy.plugin.security.login.support.enums.LogoutMethod;

/**
 * 
 * @since 1.0.0
 * @author 唐玮琳
 * @time 2013年10月15日 上午9:57:43
 */
@Entity
@Table(name = "T_LOGOUT_LOG")
public class TLogoutLog extends UuidEntity {

	private String userId;
	private String loginLogId;
	private String logoutTime;
	private LogoutMethod logoutMethod;

	@Column(length = 32)
	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	@Column(length = 14)
	public String getLogoutTime() {
		return logoutTime;
	}

	public void setLogoutTime(String logoutTime) {
		this.logoutTime = logoutTime;
	}

	@Transient
	public LogoutMethod getLogoutMethod() {
		return logoutMethod;
	}

	public void setLogoutMethod(LogoutMethod logoutMethod) {
		this.logoutMethod = logoutMethod;
	}

	public String getLogoutMethodCode() {
		return logoutMethod == null ? null : logoutMethod.getCode();
	}

	public void setLogoutMethodCode(String logoutMethodCode) {
		if (StringUtils.isBlank(logoutMethodCode)) {
			this.logoutMethod = null;
		} else {
			this.logoutMethod = LogoutMethod.enumOf(logoutMethodCode);	
		}
	}

	@Column(length = 32)
	public String getLoginLogId() {
		return loginLogId;
	}

	public void setLoginLogId(String loginLogId) {
		this.loginLogId = loginLogId;
	}
	
}

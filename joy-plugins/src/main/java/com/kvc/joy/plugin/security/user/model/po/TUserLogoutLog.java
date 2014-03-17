package com.kvc.joy.plugin.security.user.model.po;

import com.kvc.joy.core.persistence.support.entity.UuidEntity;
import com.kvc.joy.core.persistence.orm.jpa.annotations.Comment;
import com.kvc.joy.plugin.security.user.support.enums.LogoutMethod;
import org.apache.commons.lang3.StringUtils;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;

/**
 * 
 * @since 1.0.0
 * @author 唐玮琳
 * @time 2013年10月15日 上午9:57:43
 */
@Entity
@Table(name = "t_user_logout_log")
@Comment("登出日志")
public class TUserLogoutLog extends UuidEntity {

	private String userId;
	private String loginLogId;
	private String logoutTime;
	private LogoutMethod logoutMethod;

	@Column(length = 32, nullable = false)
	@Comment("用户ID")
	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	@Column(length = 14, nullable = false)
	@Comment("登出时间")
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

	@Column(length = 2, nullable = false)
	@Comment(value="登出方式代码", codeId="logout_method")
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
	@Comment("登陆日志ID")
	public String getLoginLogId() {
		return loginLogId;
	}

	public void setLoginLogId(String loginLogId) {
		this.loginLogId = loginLogId;
	}
	
}

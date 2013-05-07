package com.kvc.joy.plugin.security.erbac.model.po;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;

/**
 * 
 * @author 唐玮琳
 * @time 2012-6-20 下午11:51:45
 */
@IdClass(TErbacGroupUserId.class)
@Entity
@Table(name = "T_ERBAC_GROUP_USER")
public class TErbacGroupUser {

	private String groupId;
	private String userId;

	@Id
	@Column(length = 32, name = "GROUP_ID")
	public String getGroupId() {
		return groupId;
	}

	public void setGroupId(String groupId) {
		this.groupId = groupId;
	}

	@Id
	@Column(length = 32, name = "USER_ID")
	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

}
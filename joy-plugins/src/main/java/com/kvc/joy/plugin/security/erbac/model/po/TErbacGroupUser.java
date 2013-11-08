package com.kvc.joy.plugin.security.erbac.model.po;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;

import com.kvc.joy.core.persistence.orm.jpa.annotations.Comment;

/**
 * 
 * @author 唐玮琳
 * @time 2012-6-20 下午11:51:45
 */
@IdClass(TErbacGroupUserId.class)
@Entity
@Table(name = "t_erbac_group_user")
@Comment("组和用户关联表")
public class TErbacGroupUser {

	private String groupId;
	private String userId;

	@Id
	@Column(length = 32, name = "GROUP_ID")
	@Comment("组ID")
	public String getGroupId() {
		return groupId;
	}

	public void setGroupId(String groupId) {
		this.groupId = groupId;
	}

	@Id
	@Column(length = 32, name = "USER_ID")
	@Comment("用户ID")
	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

}
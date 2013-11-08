package com.kvc.joy.plugin.security.erbac.model.po;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.kvc.joy.commons.support.IListToTreeRestrict;
import com.kvc.joy.core.persistence.entity.UuidEntity;
import com.kvc.joy.core.persistence.orm.jpa.annotations.Comment;

@Entity
@Table(name = "t_erbac_organization")
@Comment("组织机构")
public class TErbacOrganization extends UuidEntity implements IListToTreeRestrict<String> {

	private String parentId; // 父组织id
	private String name; // 组织名称
	private String desc; // 组织描述
	private String createTime; // 成立时间

	@Column(length = 32)
	@Comment("父项ID")
	public String getParentId() {
		return parentId;
	}

	public void setParentId(String parentId) {
		this.parentId = parentId;
	}

	@Column(name = "ORG_NAME", length = 64)
	@Comment("名称")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "DESCRIPTION")
	@Comment("描述")
	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	@Column(length = 8)
	@Comment("成立日期")
	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

}

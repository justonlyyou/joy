package com.kvc.joy.core.sysres.menu.po;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.kvc.joy.commons.bean.IEntity;
import com.kvc.joy.commons.support.IListToTreeRestrict;
import com.kvc.joy.core.persistence.orm.jpa.annotations.Comment;

@Entity
@Table(name = "T_SYS_MENU")
@Comment("系统菜单")
public class TSysMenu implements IEntity<String>, IListToTreeRestrict<String> {

	private String id; // 编号
	private String text; // 结点名称
	private String parentId; // 父结点编号
	private String url; // 地址
	private String orderNum; // 序号
	private String icon;
	private boolean active;
	private boolean deleted;
	
	public TSysMenu() {
	}
	
	public TSysMenu(String id) {
		this.id = id;
	}

	@Id
	@GeneratedValue(strategy=GenerationType.TABLE)
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	@Column(length=64, nullable=false)
	@Comment("文本")
	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	@Column(length=32)
	public String getParentId() {
		return parentId;
	}

	public void setParentId(String parentId) {
		this.parentId = parentId;
	}

	@Column(length=128)
	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	@Column(length=16)
	public String getOrderNum() {
		return orderNum;
	}

	public void setOrderNum(String orderNum) {
		this.orderNum = orderNum;
	}

	@Column(length=128)
	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}

	@Column(length = 1, nullable = false, columnDefinition="boolean default false")
	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	@Column(length = 1, nullable = false, columnDefinition="boolean default false")
	public boolean isDeleted() {
		return deleted;
	}

	public void setDeleted(boolean deleted) {
		this.deleted = deleted;
	}

}

package com.kvc.joy.core.sysres.menu.model.po;

import com.kvc.joy.commons.bean.IEntity;
import com.kvc.joy.commons.support.IListToTreeRestrict;
import com.kvc.joy.core.persistence.orm.jpa.annotations.Comment;
import com.kvc.joy.core.persistence.orm.jpa.annotations.DefaultValue;

import javax.persistence.*;

@Entity
@Table(name = "t_sys_menu")
@Comment("系统菜单")
public class TSysMenu implements IEntity<String>, IListToTreeRestrict<String> {

	private String id; // 编号
	private String text; // 结点名称
	private String parentId; // 父结点编号
	private String url; // 地址
	private String orderNum; // 序号
	private String icon;
	private String active;
	private String deleted;

	public TSysMenu() {
	}

	public TSysMenu(String id) {
		this.id = id;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.TABLE)
	@Column(length = 32, nullable = false)
	@Comment("主键")
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	@Column(length = 64, nullable = false)
	@Comment("文本")
	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	@Column(length = 32)
	@Comment("父项ID")
	public String getParentId() {
		return parentId;
	}

	public void setParentId(String parentId) {
		this.parentId = parentId;
	}

	@Column(length = 128)
	@Comment("URL")
	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	@Column(length = 16)
	@Comment("排序序号")
	public String getOrderNum() {
		return orderNum;
	}

	public void setOrderNum(String orderNum) {
		this.orderNum = orderNum;
	}

	@Column(length = 128)
	@Comment("图标URL")
	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}

	@Column(length = 1, nullable = false)
	@DefaultValue("0")
	@Comment("是否启用")
	public String getActive() {
		return active;
	}

	public boolean active() {
		return "1".equals(active);
	}
	
	public void setActive(String active) {
		this.active = active;
	}

	@Column(length = 1, nullable = false)
	@DefaultValue("0")
	@Comment("是否已删除")
	public String getDeleted() {
		return deleted;
	}
	
	@Transient
	public boolean deleted() {
		return "1".equals(deleted);
	}

	public void setDeleted(String deleted) {
		this.deleted = deleted;
	}

}

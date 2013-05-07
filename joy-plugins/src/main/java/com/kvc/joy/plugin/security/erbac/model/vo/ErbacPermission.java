package com.kvc.joy.plugin.security.erbac.model.vo;

import com.kvc.joy.plugin.security.erbac.model.po.TErbacAuthority;
import com.kvc.joy.plugin.security.erbac.support.utils.ShiroPermissionExpUtils;

/**
 * 
 * @author 唐玮琳
 * @time 2013-2-22 下午11:31:49
 */
public class ErbacPermission {

	private String domain = ShiroPermissionExpUtils.PERMISSION_ALL; // 资源
	private String action = ShiroPermissionExpUtils.PERMISSION_ALL; // 操作
	private String instance = ShiroPermissionExpUtils.PERMISSION_ALL; // 资源实例

	public ErbacPermission() {
	}

	public ErbacPermission(String domain) {
		this.domain = domain;
	}

	public ErbacPermission(String domain, String action) {
		this.domain = domain;
		this.action = action;
	}

	public ErbacPermission(String domain, String action, String instance) {
		this.domain = domain;
		this.action = action;
		this.instance = instance;
	}
	
	public ErbacPermission(TErbacAuthority authority) {
		this(authority.getDomain(), authority.getAction(), authority.getInstance());
	}

	public String getDomain() {
		return domain;
	}

	public void setDomain(String domain) {
		this.domain = domain;
	}

	public String getAction() {
		return action;
	}

	public void setAction(String action) {
		this.action = action;
	}

	public String getInstance() {
		return instance;
	}

	public void setInstance(String instance) {
		this.instance = instance;
	}
	
	@Override
	public String toString() {
		return ShiroPermissionExpUtils.createShiroPermissionExp(this);
	}

}

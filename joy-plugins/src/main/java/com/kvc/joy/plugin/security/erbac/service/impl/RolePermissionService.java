package com.kvc.joy.plugin.security.erbac.service.impl;

import com.kvc.joy.plugin.security.erbac.model.po.TErbacAuthority;
import com.kvc.joy.plugin.security.erbac.model.vo.ErbacPermission;
import com.kvc.joy.plugin.security.erbac.service.IRolePermissionService;

import java.util.List;

/**
 * 
 * @author Kevice
 * @time 2013-2-23 上午12:09:53
 */
public class RolePermissionService implements IRolePermissionService{

	@Override
	public List<TErbacAuthority> getPermissions(String roleId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean isPermitted(String roleId, ErbacPermission permission) {
		// TODO Auto-generated method stub
		return false;
	}

}

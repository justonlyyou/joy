package org.joy.plugin.security.erbac.service.impl;

import org.joy.plugin.security.erbac.model.po.TErbacAuthority;
import org.joy.plugin.security.erbac.model.vo.ErbacPermission;
import org.joy.plugin.security.erbac.service.IRolePermissionService;

import java.util.List;

/**
 * 角色权限服务
 *
 * @since 1.0.0
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

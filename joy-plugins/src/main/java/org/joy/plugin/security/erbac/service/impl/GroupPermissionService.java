package org.joy.plugin.security.erbac.service.impl;

import org.joy.plugin.security.erbac.model.po.TErbacAuthority;
import org.joy.plugin.security.erbac.model.vo.ErbacPermission;
import org.joy.plugin.security.erbac.service.IGroupPermissionService;

import java.util.List;

/**
 * 组权限表达式服务
 *
 * @since 1.0.0
 * @author Kevice
 * @time 2013-2-23 上午12:09:33
 */
public class GroupPermissionService implements IGroupPermissionService {

	@Override
	public List<TErbacAuthority> getPermissions(String groupId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean isPermitted(String groupId, ErbacPermission permission) {
		// TODO Auto-generated method stub
		return false;
	}

}

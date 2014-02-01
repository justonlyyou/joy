package com.kvc.joy.plugin.security.erbac.service.impl;

import com.kvc.joy.plugin.security.erbac.model.po.TErbacAuthority;
import com.kvc.joy.plugin.security.erbac.model.vo.ErbacPermission;
import com.kvc.joy.plugin.security.erbac.service.IGroupPermissionService;

import java.util.List;

/**
 * 
 * @author 唐玮琳
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

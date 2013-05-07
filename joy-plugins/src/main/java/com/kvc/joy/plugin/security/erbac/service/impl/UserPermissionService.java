package com.kvc.joy.plugin.security.erbac.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;

import com.kvc.joy.plugin.security.erbac.model.po.TErbacAuthority;
import com.kvc.joy.plugin.security.erbac.model.vo.ErbacPermission;
import com.kvc.joy.plugin.security.erbac.service.IUserPermissionService;
import com.kvc.joy.plugin.security.erbac.support.utils.ShiroPermissionExpUtils;

/**
 * 
 * @author 唐玮琳
 * @time 2013-2-23 上午12:04:19
 */
public class UserPermissionService implements IUserPermissionService {

	@Override
	public List<TErbacAuthority> getPermissions(String userId) {
		// TODO Auto-generated method stub
		List<TErbacAuthority> authorities = new ArrayList<TErbacAuthority>();
		TErbacAuthority authority = new TErbacAuthority();
		authority.setDomain("printer");
		authority.setAction("print");
		authorities.add(authority);
		
		authority = new TErbacAuthority();
		authority.setDomain("/manage");
		authority.setAction("edit");
		authorities.add(authority);
		
		return authorities;
	}

	@Override
	public List<ErbacPermission> getPermissionExps(String userId) {
		List<TErbacAuthority> permissions = getPermissions(userId);
		List<ErbacPermission> permissionExps = new ArrayList<ErbacPermission>(permissions.size());
		for (TErbacAuthority authority : permissions) {
			permissionExps.add(new ErbacPermission(authority));
		}
		return permissionExps;
	}

	@Override
	public List<String> getPermissionStringExps(String userId) {
		List<TErbacAuthority> permissions = getPermissions(userId);
		List<String> permissionExps = new ArrayList<String>(permissions.size());
		for (TErbacAuthority authority : permissions) {
			permissionExps.add(ShiroPermissionExpUtils.createShiroPermissionExp(authority));
		}
		return permissionExps;
	}
	
	@Override
	public boolean isPermitted(ErbacPermission permission) {
		Subject currentUser = SecurityUtils.getSubject();
		return currentUser.isPermitted(permission.toString());
	}

}

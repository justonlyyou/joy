package com.kvc.joy.plugin.security.erbac.service;

import java.util.List;

import com.kvc.joy.plugin.security.erbac.model.po.TErbacAuthority;
import com.kvc.joy.plugin.security.erbac.model.vo.ErbacPermission;

/**
 * 
 * @author 唐玮琳
 * @time 2013-2-23 上午12:07:00
 */
public interface IRolePermissionService {

	/**
	 * 计算指定角色的所有权限
	 * 
	 * @param roleId
	 * @return
	 * @author 唐玮琳
	 * @time 2013-2-22 下午11:14:46
	 */
	List<TErbacAuthority> getPermissions(String roleId);
	
	/**
	 * 判断角色是否有给定的权限
	 * 
	 * @param roleId
	 * @param permission
	 * @return
	 * @author 唐玮琳
	 * @time 2013-2-23 上午12:03:13
	 */
	boolean isPermitted(String roleId, ErbacPermission permission);
	
}

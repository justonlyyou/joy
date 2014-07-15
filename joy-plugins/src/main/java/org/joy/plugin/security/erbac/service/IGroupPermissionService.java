package org.joy.plugin.security.erbac.service;

import org.joy.plugin.security.erbac.model.po.TErbacAuthority;
import org.joy.plugin.security.erbac.model.vo.ErbacPermission;

import java.util.List;

/**
 * 
 * @author Kevice
 * @time 2013-2-23 上午12:08:29
 */
public interface IGroupPermissionService {

	/**
	 * 计算指定组的所有权限
	 * 
	 * @param groupId
	 * @return
	 * @author Kevice
	 * @time 2013-2-22 下午11:14:46
	 */
	List<TErbacAuthority> getPermissions(String groupId);
	
	/**
	 * 判断组是否有给定的权限
	 * 
	 * @param groupId
	 * @param permission
	 * @return
	 * @author Kevice
	 * @time 2013-2-23 上午12:03:13
	 */
	boolean isPermitted(String groupId, ErbacPermission permission);
	
}

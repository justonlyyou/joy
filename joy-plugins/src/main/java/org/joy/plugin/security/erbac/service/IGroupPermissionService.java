package org.joy.plugin.security.erbac.service;

import org.joy.plugin.security.erbac.model.po.TErbacAuthority;
import org.joy.plugin.security.erbac.model.vo.ErbacPermission;

import java.util.List;

/**
 * 组权限接口
 *
 * @since 1.0.0
 * @author Kevice
 * @time 2013-2-23 上午12:08:29
 */
public interface IGroupPermissionService {

	/**
	 * 计算指定组的所有权限
	 * 
	 * @param groupId 组id
	 * @return List<权限信息对象>
     * @since 1.0.0
	 * @author Kevice
	 * @time 2013-2-22 下午11:14:46
	 */
	List<TErbacAuthority> getPermissions(String groupId);
	
	/**
	 * 判断组是否有给定的权限
	 * 
	 * @param groupId 组id
	 * @param permission 权限表达式对象
	 * @return true：有权限，false：没权限
     * @since 1.0.0
	 * @author Kevice
	 * @time 2013-2-23 上午12:03:13
	 */
	boolean isPermitted(String groupId, ErbacPermission permission);
	
}

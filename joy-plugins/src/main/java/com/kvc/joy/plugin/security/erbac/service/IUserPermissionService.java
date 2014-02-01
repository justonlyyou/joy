package com.kvc.joy.plugin.security.erbac.service;

import com.kvc.joy.plugin.security.erbac.model.po.TErbacAuthority;
import com.kvc.joy.plugin.security.erbac.model.vo.ErbacPermission;

import java.util.List;

/**
 * 
 * @author 唐玮琳
 * @time 2013-2-22 下午10:59:04
 */
public interface IUserPermissionService {
	
	/**
	 * 计算指定用户的所有权限 <br/>
	 * 		用户权限 = 用户本身独有的权限 +用户所属角色的权限 + 用户所属组的权限 
	 * 
	 * @param userId 用户id
	 * @return
	 * @author 唐玮琳
	 * @time 2013-2-22 下午11:14:46
	 */
	List<TErbacAuthority> getPermissions(String userId);
	
	/**
	 * 
	 * 
	 * @param userId
	 * @return
	 * @author 唐玮琳
	 * @time 2013-2-23 下午3:18:09
	 */
	List<ErbacPermission>getPermissionExps(String userId);
	
	/**
	 * 
	 * 
	 * @param userId
	 * @return
	 * @author 唐玮琳
	 * @time 2013-2-23 下午3:19:21
	 */
	List<String>getPermissionStringExps(String userId);
	
	/**
	 * 判断当前用户是否有给定的权限
	 * 
	 * @param permission
	 * @return
	 * @author 唐玮琳
	 * @time 2013-2-23 上午12:03:13
	 */
	boolean isPermitted(ErbacPermission permission);

}

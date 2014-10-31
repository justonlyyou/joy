package org.joy.plugin.security.erbac.service;

import org.joy.plugin.security.erbac.model.po.TErbacAuthority;
import org.joy.plugin.security.erbac.model.vo.ErbacPermission;

import java.util.List;

/**
 * 用户权限服务接口
 *
 * @since 1.0.0
 * @author Kevice
 * @time 2013-2-22 下午10:59:04
 */
public interface IUserPermissionService {
	
	/**
	 * 计算指定用户的所有权限 <br/>
	 * 		用户权限 = 用户本身独有的权限 +用户所属角色的权限 + 用户所属组的权限 
	 * 
	 * @param userId 用户id
	 * @return List<权限>
	 * @author Kevice
	 * @time 2013-2-22 下午11:14:46
     * @since 1.0.0
	 */
	List<TErbacAuthority> getPermissions(String userId);
	
	/**
	 * 返回用户的所有权限表达式对象
	 * 
	 * @param userId 用户id
	 * @return List<权限表达式VO>
     * @since 1.0.0
	 * @author Kevice
	 * @time 2013-2-23 下午3:18:09
	 */
	List<ErbacPermission>getPermissionExps(String userId);
	
	/**
	 * 返回用户的所有权限表达式字符串
	 * 
	 * @param userId 用户id
	 * @return List<权限表达式字符串>
     * @since 1.0.0
	 * @author Kevice
	 * @time 2013-2-23 下午3:19:21
	 */
	List<String>getPermissionStringExps(String userId);
	
	/**
	 * 判断当前用户是否有给定的权限
	 * 
	 * @param permission 权限表达式对象
	 * @return true: 有权限，false：没权限
     * @since 1.0.0
	 * @author Kevice
	 * @time 2013-2-23 上午12:03:13
	 */
	boolean isPermitted(ErbacPermission permission);

}

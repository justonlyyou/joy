package org.joy.plugin.security.erbac.support.utils;

import org.joy.commons.exception.SystemException;
import org.joy.commons.lang.string.StringTool;
import org.joy.plugin.security.erbac.model.po.TErbacAuthority;
import org.joy.plugin.security.erbac.model.vo.ErbacPermission;

/**
 * 
 * @author Kevice
 * @time 2013-2-22 下午11:38:37
 */
public class ShiroPermissionExpTool {
	
	public static final String PERMISSION_SEPARATOR = ":";
	public static final String PERMISSION_ALL = "*";

	/**
	 * 
	 * 
	 * @param domain
	 * @param action
	 * @param instance
	 * @return
	 * @author Kevice
	 * @time 2013-2-22 下午11:45:28
	 */
	public static String createShiroPermissionExp(String domain, String action, String instance) {
		domain = StringTool.isBlank(domain) ? PERMISSION_ALL : domain.trim();
		action = StringTool.isBlank(action) ? PERMISSION_ALL : action.trim();
		instance = StringTool.isBlank(instance) ? PERMISSION_ALL : instance.trim();
		return domain + PERMISSION_SEPARATOR + action + PERMISSION_SEPARATOR + instance;
	}
	
	/**
	 * 
	 * 
	 * @param authority
	 * @return
	 * @author Kevice
	 * @time 2013-2-23 下午12:17:06
	 */
	public static String createShiroPermissionExp(TErbacAuthority authority) {
		return createShiroPermissionExp(authority.getDomain(), authority.getAction(), authority.getInstance());
	}

	/**
	 * 
	 * 
	 * @param permission
	 * @return
	 * @author Kevice
	 * @time 2013-2-22 下午11:47:29
	 */
	public static String createShiroPermissionExp(ErbacPermission permission) {
		return createShiroPermissionExp(permission.getDomain(), permission.getAction(), permission.getInstance());
	}
	
	/**
	 * 
	 * 
	 * @param permissionExp
	 * @return
	 * @author Kevice
	 * @time 2013-2-23 上午12:01:04
	 */
	public static ErbacPermission parsePermissionExp(String permissionExp) {
		if(StringTool.isBlank(permissionExp)) {
			throw new SystemException("参数permissionExp不能为空！");
		}
		String[] values = permissionExp.split(PERMISSION_SEPARATOR);
		ErbacPermission erbacPermission = new ErbacPermission(values[0]);
		if(values.length > 1) {
			erbacPermission.setAction(values[1]);	
		}
		if(values.length > 2) {
			erbacPermission.setInstance(values[2]);	
		}
		return erbacPermission;
	}

}

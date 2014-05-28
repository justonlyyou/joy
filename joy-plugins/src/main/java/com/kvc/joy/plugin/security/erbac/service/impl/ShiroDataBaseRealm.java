package com.kvc.joy.plugin.security.erbac.service.impl;

import com.kvc.joy.core.persistence.orm.jpa.JpaTool;
import com.kvc.joy.plugin.security.erbac.service.IUserPermissionService;
import com.kvc.joy.plugin.security.user.model.po.TUserBasic;
import com.kvc.joy.plugin.security.user.model.po.TUserBasic_;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationException;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;

import javax.annotation.Resource;
import java.util.List;

/**
 * 
 * @author Kevice
 * @time 2013-2-23 下午3:02:24
 * @since 1.0.0
 */
public class ShiroDataBaseRealm extends AuthorizingRealm {

	@Resource
	private IUserPermissionService userPermissionService;

	/**
	 * 当用户进行访问链接时的授权方法
	 */
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
		if (principals == null) {
			throw new AuthorizationException("Principal对象不能为空");
		}

		TUserBasic user = (TUserBasic) principals.fromRealm(getName()).iterator().next();
		List<String> permissions = userPermissionService.getPermissionStringExps(user.getId());

		SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
		info.addStringPermissions(permissions);
		return info;
	}

	/**
	 * 用户登录的认证方法
	 */
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
		UsernamePasswordToken usernamePasswordToken = (UsernamePasswordToken) token;
		String username = usernamePasswordToken.getUsername();
		if (username == null) {
			throw new AccountException("用户名不能为空");
		}

		TUserBasic user = JpaTool.uniqueResult(JpaTool.search(TUserBasic.class, TUserBasic_.account, username));
		if (user == null) {
			throw new UnknownAccountException("用户不存在");
		}

		return new SimpleAuthenticationInfo(user, user.getPassword(), getName());
	}

}

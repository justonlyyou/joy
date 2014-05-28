package com.kvc.joy.plugin.security.erbac.service.impl;

import com.kvc.joy.commons.lang.string.StringTool;
import com.kvc.joy.plugin.security.user.model.po.TUserBasic;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authc.credential.SimpleCredentialsMatcher;
import org.apache.shiro.subject.PrincipalCollection;

/**
 *
 * @author Kevice
 * @time 2014-5-28 上午10:22:24
 * @since 1.0.0
 */
public class JoyCredentialsMatcher extends SimpleCredentialsMatcher {

    @Override
    public boolean doCredentialsMatch(AuthenticationToken authcToken, AuthenticationInfo info) {
        UsernamePasswordToken token = (UsernamePasswordToken) authcToken;
        PrincipalCollection principals = info.getPrincipals();
        TUserBasic user = (TUserBasic) principals.getPrimaryPrincipal();
        Object tokenCredentials = encrypt(String.valueOf(token.getPassword()), user);
        Object accountCredentials = getCredentials(info);
        return equals(tokenCredentials, accountCredentials);
    }

    private String encrypt(String data, TUserBasic user) {
        return StringTool.toMd5HexStr(data, user.getId());
    }

}

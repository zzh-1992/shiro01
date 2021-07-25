package com.grapefruit.shiro.realm;

import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;

/**
 * @author 柚子苦瓜茶
 * @version 1.0
 */
public class MyShiroRealm extends AuthorizingRealm {

    /**
     * 用户认证
     *
     * @param token
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {

        UsernamePasswordToken usernamePasswordToken = (UsernamePasswordToken) token;

        // 获取用户信息
        String username = usernamePasswordToken.getUsername();
        String password = new String(usernamePasswordToken.getPassword());

        //从数据库获取用户信息 mysql
        // 对比用户信息

        if (username == null) {
            return null;
        } else {
            SimpleAuthenticationInfo simpleAuthenticationInfo = new SimpleAuthenticationInfo(username, password, getName());
            System.out.println("===========认证转授权==========");
            return simpleAuthenticationInfo;
        }
    }

    /**
     * 用户授权
     *
     * @param principals
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        System.out.println("========开始授权======");
        String username = principals.getPrimaryPrincipal().toString();
        //User User = (User)principals.getPrimaryPrincipal().toString();
        SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();
        if (username.equals("123")) {
            System.out.println("开始--123--授权------");
            simpleAuthorizationInfo.addStringPermission("bg:bg1");
            simpleAuthorizationInfo.addStringPermission("bg:bg2");
            return simpleAuthorizationInfo;
        }
        if (username.equals("789")) {
            System.out.println("开始--789--授权------");
            simpleAuthorizationInfo.addStringPermission("bg:bg2");
            simpleAuthorizationInfo.addStringPermission("bg:bg1");
            simpleAuthorizationInfo.addStringPermission("user:add");
            return simpleAuthorizationInfo;
        }
        if (username.equals("13632422349")) {
            System.out.println("开始--1363222329--授权------");
            simpleAuthorizationInfo.addStringPermission("user:add");
            simpleAuthorizationInfo.addStringPermission("user:delete");
            return simpleAuthorizationInfo;
        }

        return simpleAuthorizationInfo;

    }
}

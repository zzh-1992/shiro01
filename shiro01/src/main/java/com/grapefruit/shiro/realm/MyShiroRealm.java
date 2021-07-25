package com.grapefruit.shiro.realm;

import com.grapefruit.shiro.entity.Grape;
import com.grapefruit.shiro.mapper.GrapeMapper;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author 柚子苦瓜茶
 * @version 1.0
 */
public class MyShiroRealm extends AuthorizingRealm {

    private ThreadLocal<Grape> threadLocal = new ThreadLocal<>();

    @Autowired
    private GrapeMapper mapper;

    public Grape getUserInfo(String name) {
        Grape grape = mapper.selectGrape(name);
        threadLocal.set(grape);
        return grape;
    }

    /**
     * 用户认证
     *
     * @param token
     * @return AuthenticationInfo
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {

        UsernamePasswordToken usernamePasswordToken = (UsernamePasswordToken) token;

        // 获取用户信息
        String username = usernamePasswordToken.getUsername();
        String password = new String(usernamePasswordToken.getPassword());

        //从数据库获取用户信息 mysql
        Grape grape = getUserInfo(username);

        // 对比用户信息
        if (grape.getName() == null || !grape.getPassword().equals(password)) {
            return null;
        } else {
            SimpleAuthenticationInfo simpleAuthenticationInfo = new SimpleAuthenticationInfo(username, password,
                    getName());
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

        // 从线程获取用户
        Grape grape = threadLocal.get();

        String permission = grape.getPermission();
        String[] strings = permission.split(",");
        for (String s : strings) {
            simpleAuthorizationInfo.addStringPermission(s);
        }
        return simpleAuthorizationInfo;
    }
}

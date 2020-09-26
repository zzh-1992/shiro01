package com.grapefruit.shiro.config;


import com.grapefruit.shiro.filter.MyLogoutFilter;
import com.grapefruit.shiro.realm.MyShiroRealm;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.realm.Realm;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.servlet.Filter;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author 柚子苦瓜茶
 * @version 1.0
 */
@Configuration
public class ShiroConfiguration {

    /**
     * 领域 安全组件
     * @return
     */
    @Bean
    public Realm realm(){
        return new MyShiroRealm();
    }

    @Bean
    public org.apache.shiro.mgt.SecurityManager securityManager(Realm realm){
        DefaultWebSecurityManager defaultWebSecurityManager = new DefaultWebSecurityManager();
        defaultWebSecurityManager.setRealm(realm);
        return defaultWebSecurityManager;
    }

    /**
     * 过滤器的注册器
     * @param myLogoutFilter
     * @return
     */
    @Bean
    public FilterRegistrationBean filterRegistrationBean(MyLogoutFilter myLogoutFilter){
        FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean(myLogoutFilter);
        filterRegistrationBean.setEnabled(false);
        return filterRegistrationBean;
    }

    @Bean
    public ShiroFilterFactoryBean shiroFilterFactoryBean(SecurityManager securityManager){
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        shiroFilterFactoryBean.setSecurityManager(securityManager);

        /*Map<String, Filter> filters = new LinkedHashMap<String, Filter>();
        filters.put("logout",new MyLogoutFilter());
        shiroFilterFactoryBean.setFilters(filters);*/

        LinkedHashMap<String,String> linkedHashMap = new LinkedHashMap<String,String>();

        //为了测试
        linkedHashMap.put("/bg/bg1","perms[bg:bg1]");
        linkedHashMap.put("/bg/bg2","perms[bg:bg2]");

        //放行(无需认证)
        linkedHashMap.put("/mess","anon");
        linkedHashMap.put("/MyLogoutRedirectURL","anon");
        linkedHashMap.put("/login","anon");
        //登出设置
        linkedHashMap.put("/logout","myLogoutFilter");

        //扫尾
        linkedHashMap.put("/**","authc");
        //anon: 无需认证即可访问
        //authc: 需要认证才可访问
        //user: 点击“记住我”功能可访问
        //perms: 拥有权限才可以访问
        //role: 拥有某个角色权限才能访问
        shiroFilterFactoryBean.setFilterChainDefinitionMap(linkedHashMap);

        shiroFilterFactoryBean.setUnauthorizedUrl("/");
        shiroFilterFactoryBean.setSuccessUrl("/admin/index");
        shiroFilterFactoryBean.setLoginUrl("/");

        return shiroFilterFactoryBean;
    }

    /**
     * 配置自定义登出过滤器
     * @return 自定义登出过滤器实例
     */
    @Bean()
    public MyLogoutFilter myLogoutFilter(){
        return new MyLogoutFilter();
    }
}


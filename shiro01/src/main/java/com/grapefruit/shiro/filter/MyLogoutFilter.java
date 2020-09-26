package com.grapefruit.shiro.filter;

import org.apache.shiro.session.Session;
import org.apache.shiro.session.SessionException;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.authc.LogoutFilter;
import org.apache.shiro.web.util.WebUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import java.util.Locale;

/**
 * @author 柚子苦瓜茶
 * @version 1.0
 */
public class MyLogoutFilter extends LogoutFilter {

    /**
     * 自定义登出的跳转路径(覆盖父类的成员变量)
     */
    private  String redirectUrl = "/MyLogoutRedirectURL";

    Logger log = LoggerFactory.getLogger(LogoutFilter.class);


    @Override
    protected boolean preHandle(ServletRequest request, ServletResponse response) throws Exception {
        Subject subject = getSubject(request, response);
        // Check if POST only logout is enabled
        if (isPostOnlyLogout()) {

            // check if the current request's method is a POST, if not redirect
            if (!WebUtils.toHttp(request).getMethod().toUpperCase(Locale.ENGLISH).equals("POST")) {
                return onLogoutRequestNotAPost(request, response);
            }
        }
        //String redirectUrl = getRedirectUrl(request, response, subject);
        //try/catch added for SHIRO-298:
        try {
            subject.logout();
        } catch (SessionException ise) {
            log.debug("Encountered session exception during logout.  This can generally safely be ignored.", ise);
        }
        System.out.println("=============自定义登出过滤器启动==============");
        issueRedirect(request, response, redirectUrl);
        return false;
    }
}

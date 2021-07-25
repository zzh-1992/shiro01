package com.grapefruit.shiro.controller;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.DisabledAccountException;
import org.apache.shiro.authc.ExcessiveAttemptsException;
import org.apache.shiro.authc.ExpiredCredentialsException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author 柚子苦瓜茶
 * @version 1.0
 */
@Controller
public class IndexController {

    @GetMapping("/")
    public String toIndex() {
        return "login";
    }

    @RequestMapping("/login")
    public String loginAction(@RequestParam String username, @RequestParam String password, Model model) {
        String mess;

        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken usernamePasswordToken = new UsernamePasswordToken();
        usernamePasswordToken.setUsername(username);
        usernamePasswordToken.setPassword(password.toCharArray());
        try {
            System.out.println("====/login=========开始尝试登陆成功=============");
            subject.login(usernamePasswordToken);
            return "user/add";

        } catch (LockedAccountException la) {
            mess = la.getMessage();
            System.out.println("LockedAccountException");

        } catch (DisabledAccountException da) {
            mess = da.getMessage();
            System.out.println("DisabledAccountException");

        } catch (UnknownAccountException ua) {
            mess = "账户不存在";
            System.out.println("UnknownAccountException");

        } catch (ExcessiveAttemptsException ea) {
            mess = ea.getMessage();
            System.out.println("ExcessiveAttemptsException");

        } catch (IncorrectCredentialsException ia) {
            mess = ia.getMessage();
            mess = "密码错误";
            System.out.println("IncorrectCredentialsException");

        } catch (ExpiredCredentialsException ec) {
            mess = ec.getMessage();
            System.out.println("ExpiredCredentialsException");

        } catch (Exception e) {
            mess = e.getMessage();
            System.out.println("Exception");
        }
        model.addAttribute("mess", mess);
        return "mess";
    }

/*    @GetMapping("/logout")
    public String logout(){
        //手动登出
        //Subject subject = SecurityUtils.getSubject();
        //subject.logout();
        System.out.println("==================logout===========");
        return "logout";
    }*/

    @GetMapping("/MyLogoutRedirectURL")
    public String logout00() {
        System.out.println("==================MyLogoutRedirectURL===========");
        return "MyLogoutRedirectURL";
    }
}


































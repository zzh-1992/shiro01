package com.grapefruit.shiro.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author 柚子苦瓜茶
 * @version 1.0
 */
@Controller
public class AdminController {

    @RequestMapping("/bg/bg1")
    public String adminIndex() {
        return "bg/bg1";
    }
    @RequestMapping("/bg/bg2")
    public String adminIndex2() {
        return "bg/bg2";
    }

}

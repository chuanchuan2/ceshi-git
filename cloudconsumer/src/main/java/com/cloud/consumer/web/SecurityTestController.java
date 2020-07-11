package com.cloud.consumer.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/security")
public class SecurityTestController {

    //访问自定义登录页面测试
    @RequestMapping("/securityindex")
    public String securityLoginPage(){
        return "index2";
    }

    //访问自定义登录页面测试
    @RequestMapping("/securitysuccess")
    public String securitysuccess(){
        return "login";
    }

    //访问自定义登录页面测试
    @RequestMapping("/securityfail")
    public String securityfail(){
        return "failpage";
    }

}

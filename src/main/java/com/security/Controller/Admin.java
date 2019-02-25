package com.security.Controller;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by WFX1024 on 2019/2/25.
 */
@Controller
public class Admin {

    @RequestMapping(value = "/admin")
    public String admin(){
        return "/admin";
    }


    @RequestMapping("/whoim")
    @ResponseBody
    public Object whoIm(){
        return SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }

    @RequestMapping("/test")
    @ResponseBody
    public Object test(){
        return "测试方法！";
    }

    @RequestMapping("/")
    public Object index(){
        return "index";
    }
}

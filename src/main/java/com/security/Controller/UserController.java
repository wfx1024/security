package com.security.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by WFX1024 on 2019/2/25.
 */
@Controller
@RequestMapping(value = "/user")
public class UserController {
    @GetMapping(value = "/login")
    public String getUsers() {
        return "login";
    }


    @RequestMapping(value = "/error")
    public String error() {
        return "error";
    }
    @RequestMapping(value = "/index")
    public String index() {
        return "index";
    }
}

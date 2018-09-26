package com.example.demo.controller;

import com.example.demo.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;

@Controller
public class LoginController {

    @Autowired
    private LoginService loginService;

    @RequestMapping("help")//登陆页面
    public String help() {
        return "other-login";
    }

    @RequestMapping("login")//登陆判断
    public String login(@RequestParam("phone") String phone, @RequestParam("pass") String pass, HttpSession session) {
        if (loginService.judge(phone, pass) != -1) {
            session.setAttribute("login", loginService.judge(phone, pass));
            return "redirect:table.html";
        } else {
            return "err";
        }

    }
}

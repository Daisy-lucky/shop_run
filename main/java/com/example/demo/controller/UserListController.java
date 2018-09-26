package com.example.demo.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class UserListController {

    @RequestMapping("userlist")
    public String userlist(){
        return "userlist";
    }
}

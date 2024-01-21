package com.brow.caloriescalc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/main")
public class MainController {

    @GetMapping("/")
    public String index(){
        return "main-view3";
    }

    @GetMapping("/register")
    public String register(){
        return "register";
    }

}

package com.brow.caloriescalc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class ViewController {

    @GetMapping
    public String index(){
        return "main-page";
    }

    @GetMapping("/calc")
    public String register(){
        return "main-calc-view";
    }

}

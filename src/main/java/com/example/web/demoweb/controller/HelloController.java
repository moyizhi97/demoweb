package com.example.web.demoweb.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {
    @RequestMapping("/zz.png")
    public String hello(){
        return "aaa";
    }
}

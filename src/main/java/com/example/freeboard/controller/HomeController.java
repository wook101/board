package com.example.freeboard.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {
    // 홈 화면
    @GetMapping("/")
    public String getMain() {
        return "main";
    }
}

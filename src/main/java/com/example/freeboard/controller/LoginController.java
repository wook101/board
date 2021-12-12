package com.example.freeboard.controller;

import java.util.Collections;
import java.util.Map;

import javax.servlet.http.HttpSession;


import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;


import com.example.freeboard.service.LoginService;

@Controller
public class LoginController {
    private final LoginService loginService;
    private final BCryptPasswordEncoder passwordEncoder;

    public LoginController(LoginService loginService, BCryptPasswordEncoder passwordEncoder) {
        this.loginService = loginService;
        this.passwordEncoder = passwordEncoder;
    }

    //로그인 페이지
    @GetMapping("/login")
    public String loginPage() {
        return "/login";
    }


    //로그인 요청시
    @ResponseBody
    @PostMapping(value = "/loginCheck")
    public Map<String,Boolean> loginCheck(@RequestBody Map<String, String> param, HttpSession session) {
        /* 유효성 검증 */
        String userID = (String) param.get("userID");
        String rawPassword = (String) param.get("password");
        String encodedPassword = loginService.getEncPassword(userID);
        Boolean passwordMatch = passwordEncoder.matches(rawPassword, encodedPassword);

        //로그인 성공, 세션에 저장
        if(passwordMatch){
            int user_id = loginService.getUserTableId(userID);
            session.setAttribute("user_id", user_id);
        }

        return Collections.singletonMap("success", passwordMatch);
    }

    //로그인 상태 확인
    @ResponseBody
    @PostMapping("/loginStateCheck")
    public boolean postLoginStatus(HttpSession session) {
        if (session.getAttribute("user_id") == null)
            return false;
        return true;
    }


}

package com.example.freeboard.controller;

import java.util.Map;

import javax.servlet.http.HttpSession;


import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
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

    //로그인정보 세션에 저장
    @PostMapping("/perform_login")
    public String postLoginform(@RequestParam(name = "userID") String userID, HttpSession session) {
        int user_id = loginService.getUserTableId(userID);
        session.setAttribute("user_id", user_id);
        return "redirect:/board";
    }


    /* 유효성 검증 */
    //로그인 폼
    @ResponseBody
    @PostMapping(value = "/loginCheck", produces = "application/text; charset=UTF8")
    public String postAjaxLoginCheck(@RequestBody Map<String, String> param) {
        String userID = (String) param.get("userID");
        String rawPassword = (String) param.get("password");
        String encodedPassword = loginService.getEncPassword(userID);
        Boolean passwordMatch = passwordEncoder.matches(rawPassword, encodedPassword);
        if (passwordMatch) {
            return "1";            //로그인 성공
        } else {
            return "0";            //로그인 실패
        }
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

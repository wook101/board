package com.example.freeboard.controller;

import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.freeboard.service.BoardService;

@Controller
public class LoginController {
		@Autowired
		BoardService boardService;
		
		//로그인
		@GetMapping("/login")
		public String getLogin() {
			return "login";
		}
		
		//로그인정보 세션에 저장
		@PostMapping("/loginForm")
		public String postLoginform(@RequestParam (name="userID") String userID,
													@RequestParam (name="password") String password,
													HttpSession session) {
			
			int user_id = boardService.getUserTableId(userID);
			session.setAttribute("user_id", user_id);
			return "redirect:/board";
		}
		
		
		/* 유효성 검증 */
		//로그인 폼 
		@ResponseBody
		@PostMapping(value="/loginCheck", produces = "application/text; charset=UTF8")
		public String postAjaxLoginCheck(@RequestBody Map<String, String> param) {
			String userID = (String) param.get("userID");
			String password = (String) param.get("password");
			String loginCheckCount = Integer.toString(boardService.loginCheck(userID,password));
			return loginCheckCount;
		}
		
		//회원가입 폼 
		@ResponseBody
		@PostMapping(value="/formValidation", produces = "application/text; charset=UTF8")
		public String postAjaxUserCheck(@RequestBody Map<String, String> param) {
			String type = (String) param.get("type");
			String formVal = (String) param.get("formVal");
			String checkCount = Integer.toString(boardService.joinFormVaildation(type, formVal));
			return checkCount;
		}
		
		//로그인 상태 확인
		@ResponseBody
		@PostMapping("/loginStateCheck")
		public boolean postLoginStatus(HttpSession session) {
			if(session.getAttribute("user_id")==null) 
				return false;
			return true;
		}
		
		//로그아웃
		@GetMapping("/logout")
		public String logout(HttpSession session) {
			session.invalidate();	//세션 무효화
			return "redirect:/";
		}
		
		
}

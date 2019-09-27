package com.example.freeboard.controller;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.freeboard.dto.JoinformVo;
import com.example.freeboard.service.BoardService;

@Controller
public class JoinController {
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	BoardService boardService;
	
	@Autowired
	BCryptPasswordEncoder passwordEncoder;
	
	// 회원가입
	@GetMapping("/join")
	public String getJoin() {
		return "join";
	}

	// 회원가입 폼 유효성 검사
	@ResponseBody
	@PostMapping(value = "/formValidation", produces = "application/text; charset=UTF8")
	public String postAjaxUserCheck(@RequestBody Map<String, String> param) {
		String type = (String) param.get("type");
		String formVal = (String) param.get("formVal");
		String checkCount = Integer.toString(boardService.joinFormVaildation(type, formVal));
		return checkCount;
	}

	// 회원정보 저장
	@PostMapping("/joinForm")
	public String postJoinform(JoinformVo joinformvo) {
		String encPassword = passwordEncoder.encode(joinformvo.getPassword());		//패스워드 암호화
		joinformvo.setPassword(encPassword);
		boardService.joinFormInsert(joinformvo);
		return "redirect:/login";
	}
}

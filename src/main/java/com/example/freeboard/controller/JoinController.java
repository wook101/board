package com.example.freeboard.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.freeboard.dto.JoinformVo;
import com.example.freeboard.service.BoardService;

@Controller
public class JoinController {
		@Autowired
		BoardService boardService;
		
		//회원가입
		@GetMapping("/join")
		public String getJoin() {
			return "join";
		}
		//회원정보 저장
		@PostMapping("/joinForm")
		public String postJoinform(JoinformVo joinformvo) {
			boardService.joinFormInsert(joinformvo);
			return "redirect:/login";
		}
}

package com.example.freeboard.controller;


import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.example.freeboard.dto.WriteFormDto;
import com.example.freeboard.service.WriteService;

@Controller
public class WriteController {
		
		@Autowired
		WriteService writeService;
		
		//글쓰기 등록
		@PostMapping("/writeUpload")
		public String postWriteUpload(@RequestParam(name="file") MultipartFile file, WriteFormDto writeFormVo, HttpSession session) {																		
			int user_id = (int) session.getAttribute("user_id");
			writeService.writeRegister(user_id, file, writeFormVo);				
			return "redirect:/board";
		}

		
}

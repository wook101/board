package com.example.freeboard.controller;

import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.example.freeboard.dto.WriteFormVo;
import com.example.freeboard.service.BoardService;

@Controller
public class WriteController {
		@Autowired
		BoardService boardService;
		
		//글쓰기 등록
		@PostMapping("/writeUpload")
		public String postWriteUpload(@RequestParam(name="file") MultipartFile file, WriteFormVo writeFormVo, HttpSession session) {																		
			int user_id = (int) session.getAttribute("user_id");
			boardService.writeRegister(user_id, file, writeFormVo);				
			return "redirect:/board";
		}
		
		//글 수정
		@PostMapping("/update/{id}")
		public String update(@PathVariable(name="id") int id,@RequestParam(name="hashCode") String hashCode,
																						@RequestParam(name="file") MultipartFile file,
																						WriteFormVo writeFormVo, HttpSession session){
			int user_id = (int) session.getAttribute("user_id");
			Integer fileHashCode = hashCode.equals("null") ? null : Integer.parseInt(hashCode);
			boardService.updatePost(id, writeFormVo,user_id, file, fileHashCode);		//수정 로직 수행
			return "redirect:/board";
		}
		
		//글 수정 // 이미지 파일 삭제
		@ResponseBody
		@DeleteMapping("/deleteImg")
		public int deleteImg(@RequestBody Map<String, String> param) {
			Integer delHashCode =  Integer.parseInt(param.get("delHashCode"));
			return boardService.deleteFileInfo(delHashCode);
		}
		
		
}

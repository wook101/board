package com.example.freeboard.controller;

import java.util.Collections;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.freeboard.dto.DetailInfoDto;
import com.example.freeboard.dto.ReplyListInfoDto;
import com.example.freeboard.service.DetailService;

@Controller
public class DetailController {
		@Autowired
		DetailService detailService;
		
		//글 상세정보
		@GetMapping("/detail")
		public String getDetail(ModelMap modelMap, @RequestParam(name="id") int board_id) {
			DetailInfoDto detailInfo = detailService.detailInfoById(board_id);
			List<ReplyListInfoDto> replyListInfo =	detailService.replyListInfoById(board_id);
			modelMap.addAttribute("replyListInfo", replyListInfo);
			modelMap.addAttribute("detailInfo", detailInfo);
			return "detail";
		}
		
		//이미지 파일 랜더링
		@GetMapping("/imgDownload")
		public void getImgeDownload(HttpServletResponse response, @RequestParam(name="id") int id) {
			detailService.fileDownload(id, response);
		}
		
		//글 수정 폼으로 이동
		@GetMapping("/updateForm/{id}")
		public String updateForm(HttpServletResponse response, ModelMap modelMap,@PathVariable(name="id") int board_id){
			DetailInfoDto detailInfo = detailService.detailInfoById(board_id);
			modelMap.addAttribute("detailInfo", detailInfo);
			return "updateForm";
		}
		
		//글 삭제
		@ResponseBody
		@PostMapping("/delete/{id}")
		public Map<String, Object> deletePost(@PathVariable(name="id") int id, @RequestBody Map<String,Object> param){
			String strHashCode = (String) param.get("delHashCode");
			Integer hashCode = strHashCode.equals("null") ? null : Integer.parseInt(strHashCode);
			int result = detailService.deletePostById(id, hashCode);
			return Collections.singletonMap("result", result > 0 ? true: false);
		}
		
		//댓글 등록
		@PostMapping("/replyForm")
		public String replyRegister(@RequestParam(name="board_id") int board_id,
												 @RequestParam(name="comment") String comment, HttpSession session) {
			int user_id= (int) session.getAttribute("user_id");
			detailService.replyRegister(user_id, board_id, comment);
			return "redirect:/detail?id="+board_id;
		}
		
		//댓글 삭제
		@ResponseBody
		@DeleteMapping(value ="/deleteReply/{reply_id}", produces = "application/text; charset=UTF8")
		public String deleteReply(@PathVariable("reply_id") int reply_id) {
			int  result = detailService.deleteReplyById(reply_id);
			return Integer.toString(result);
		}
}

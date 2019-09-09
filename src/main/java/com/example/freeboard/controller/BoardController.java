package com.example.freeboard.controller;


import java.util.Collections;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
import org.springframework.web.multipart.MultipartFile;

import com.example.freeboard.dto.JoinformVo;
import com.example.freeboard.dto.ListInfoVo;
import com.example.freeboard.dto.ReplyListInfoVo;
import com.example.freeboard.dto.WriteFormVo;
import com.example.freeboard.dto.DetailInfoVo;
import com.example.freeboard.service.BoardService;

@Controller
public class BoardController {
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	BoardService boardService;
	
	//홈 화면
	@GetMapping("/")
	public String getMain(HttpSession session) {
		return "main";
	}
	
	//자유 게시판
	@GetMapping("/board")
	public String getBoard(ModelMap modelMap,@RequestParam(name="start", defaultValue="0") int start) {
		
		List<ListInfoVo>boardList =  boardService.getListInfo(start);							//조회한 게시물 리스트
		int totalListCount =boardService.getListCount();
		boardService.pagination(modelMap, start, totalListCount,"board","null")	;			//페이징
		modelMap.addAttribute("boardList",boardList);
		return "board";
	}
	
	//글 상세정보
	@GetMapping("/detail")
	public String getDetail(ModelMap modelMap, @RequestParam(name="id") int board_id, HttpSession session) {
		int user_id = (int) session.getAttribute("user_id");
		DetailInfoVo detailInfo = boardService.detailInfoById(board_id);
		List<ReplyListInfoVo> replyListInfo =	boardService.replyListInfoById(board_id);
		modelMap.addAttribute("replyListInfo", replyListInfo);
		modelMap.addAttribute("detailInfo", detailInfo);
		return "detail";
	}
	
	//이미지 파일 랜더링
	@GetMapping("/imgDownload")
	public void getImgeDownload(HttpServletResponse response, @RequestParam(name="id") int id) {
		boardService.fileDownload(id, response);
	}

	//글쓰기
	@GetMapping("/write")
	public String getWrite() {
		return "write";
	}
	
	
	//글쓰기 등록
	@PostMapping("/writeUpload")
	public String postWriteUpload(@RequestParam(name="file") MultipartFile file, WriteFormVo writeFormVo, HttpSession session) {																		
		int user_id = (int) session.getAttribute("user_id");
		boardService.writeRegister(user_id, file, writeFormVo);				
		return "redirect:/board";
	}
	
	//글 수정 폼
	@GetMapping("/updateForm/{id}")
	public String updateForm(HttpServletResponse response, ModelMap modelMap,@PathVariable(name="id") int board_id){
		DetailInfoVo detailInfo = boardService.detailInfoById( board_id);
		modelMap.addAttribute("detailInfo", detailInfo);
		return "updateForm";
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
	
	//글 삭제
	@ResponseBody
	@PostMapping("/delete/{id}")
	public Map<String, Object> deletePost(@PathVariable(name="id") int id, @RequestBody Map<String,Object> param){
		String strHashCode = (String) param.get("delHashCode");
		Integer hashCode = strHashCode.equals("null") ? null : Integer.parseInt(strHashCode);
		int result = boardService.deletePostById(id, hashCode);
		return Collections.singletonMap("result", result > 0 ? true: false);
	}

	//검색
	@GetMapping("/search")
	public String searchData(ModelMap modelMap, @RequestParam(name="start", defaultValue="0") int start,
																		@RequestParam("searchKeyword") String searchKeyword) {
		
		logger.debug("검색 키워드: "+searchKeyword);
		//List<ListInfoVo>searchList =  boardService.getListInfo(start);
		List<ListInfoVo>searchList =	 boardService.getSearchListInfo(searchKeyword, start);
		int totalListCount =boardService.getSearchListCount(searchKeyword);
		boardService.pagination(modelMap, start, totalListCount, "search", searchKeyword);
		modelMap.addAttribute("boardList",searchList);
		return "board";
	}
	
	//댓글 등록
	@PostMapping("/replyForm")
	public String replyRegister(@RequestParam(name="board_id") int board_id,
											 @RequestParam(name="comment") String comment, HttpSession session) {
		int user_id= (int) session.getAttribute("user_id");
		boardService.replyRegister(user_id, board_id, comment);
		return "redirect:/detail?id="+board_id;
	}
	
	
}

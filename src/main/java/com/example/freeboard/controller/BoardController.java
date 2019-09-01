package com.example.freeboard.controller;


import java.util.ArrayList;
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
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.example.freeboard.dto.JoinformVo;
import com.example.freeboard.dto.ListInfoVo;
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
	public String getMain() {
		logger.debug("메인 페이지");
		return "main";
	}
	
	//자유 게시판
	@GetMapping("/board")
	public String getBoard(ModelMap modelMap,@RequestParam(name="start", defaultValue="0") int start) {
		int listCount = BoardService.LIMIT;											//한 화면에 보여질 게시물 수
		int totalListCount = boardService.getListCount();							//총 게시물 수		
		int totalPage = totalListCount / listCount;									//총 페이지 수
		int pageCount = 5;																	//한 화면에 보여질 페이지 수
		int page = start / listCount + 1;													//현재 페이지
		int block = page / pageCount + 1;												//현재 블록
		boolean nextPageArrow = true;													//다음 화살표 true/false
		int prePageStart = 0;																	//이전 화살표 start값
		int nextPageStart = 0;																//다음 화살표 start값
		List<Integer> pageList = new ArrayList<>();								//페이지 번호들을 담을 리스트
		List<ListInfoVo>boardList =  boardService.getListInfo(start);		//조회한 게시물 리스트
		if(totalListCount % listCount>0) {
			totalPage++;
		}
		if(page % pageCount ==0) {
			block--;
		}
		prePageStart = (block-1) * pageCount * listCount - listCount;
		nextPageStart = block * pageCount * listCount;
		
		for(int i=block*pageCount-4;i<=block*pageCount;i++) {
			if(i>totalPage) {
				nextPageArrow = false;
				break;
			}
			pageList.add(i);
		}
		modelMap.addAttribute("listCount",listCount);
		modelMap.addAttribute("pageList", pageList);
		modelMap.addAttribute("boardList",boardList);
		modelMap.addAttribute("prePageStart",prePageStart);
		modelMap.addAttribute("nextPageStart",nextPageStart);
		modelMap.addAttribute("nextPageArrow",nextPageArrow);
		return "board";
	}
	
	//게시판 리스트
	@GetMapping("/detail")
	public String getDetail(ModelMap modelMap, @RequestParam(name="id") int id) {
		DetailInfoVo detailInfo = boardService.detailInfoById(id);
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
	
	//로그인
	@GetMapping("/login")
	public String getLogin() {
		return "login";
	}
	
	//로그인정보 저장
	@PostMapping("/loginForm")
	public String postLoginform(@RequestParam (name="userID") String userID,
												@RequestParam (name="password") String password,
												HttpSession session) {
		String nickName = boardService.getNickName(userID);
		session.setAttribute("nickName", nickName);					//세션에 닉네임 저장
		session.setAttribute("userID", userID);  							//세션에 아이디 저장
		return "redirect:/board";
	}
	
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

	//글쓰기 등록
	@PostMapping("/writeUpload")
	public String postWriteUpload(@RequestParam(name="file") MultipartFile file, WriteFormVo writeFormVo, HttpSession session) {																		
		String nickName = (String) session.getAttribute("nickName");
		boardService.writeRegister(file, nickName, writeFormVo);				
		return "redirect:/board";
	}
	
	//로그인 상태 확인
	@ResponseBody
	@PostMapping("/loginStateCheck")
	public boolean postLoginStatus(HttpSession session) {
		if(session.getAttribute("userID")==null) 
			return false;
		return true;
	}
	
	//로그아웃
	@GetMapping("/logout")
	public String logout(HttpSession session) {
		session.invalidate();	//세션 무효화
		return "redirect:/";
	}
	
	//글 수정 폼
	@GetMapping("/updateForm/{id}")
	public String updateForm(HttpServletResponse response, ModelMap modelMap,@PathVariable(name="id") int id){
		DetailInfoVo detailInfo = boardService.detailInfoById(id);
		modelMap.addAttribute("detailInfo", detailInfo);
		return "updateForm";
	}
	
	//글 수정
	@PostMapping("/update/{id}")
	public String update(@PathVariable(name="id") int id,@RequestParam(name="hashCode") String hashCode,
																					@RequestParam(name="file") MultipartFile file,
																					WriteFormVo writeFormVo){
		Integer fileHashCode = hashCode.equals("null") ? null : Integer.parseInt(hashCode);
		boardService.updatePost(id, writeFormVo, file, fileHashCode);		//수정 로직 수행
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
		int result = boardService.deletePostById(id,hashCode);
		return Collections.singletonMap("result", result > 0 ? true: false);
	}

	
}

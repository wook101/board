package com.example.freeboard.controller;



import java.util.List;



import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;



import com.example.freeboard.dto.ListInfoVo;
import com.example.freeboard.dto.ReplyListInfoVo;
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
	
	//글쓰기
	@GetMapping("/write")
	public String getWrite() {
		return "write";
	}
	

	//검색
	@GetMapping("/search")
	public String searchData(ModelMap modelMap, @RequestParam(name="start", defaultValue="0") int start,
																		@RequestParam("searchKeyword") String searchKeyword) {
		
		logger.debug("검색 키워드: "+searchKeyword);
		List<ListInfoVo>searchList =	 boardService.getSearchListInfo(searchKeyword, start);
		int totalListCount =boardService.getSearchListCount(searchKeyword);
		boardService.pagination(modelMap, start, totalListCount, "search", searchKeyword);
		modelMap.addAttribute("boardList",searchList);
		return "board";
	}
	
	
	
	
}

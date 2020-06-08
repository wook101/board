package com.example.freeboard.service;

import java.util.List;



import org.springframework.ui.ModelMap;



import com.example.freeboard.dto.BoardListDto;


public interface BoardService {
	
	public static final int LIMIT = 10; // 한 화면에 보여줄 게시물 수
	public List<BoardListDto> getListInfo(int start); // 게시글 목록
	public int getListCount(); // 총 게시글 수
	public void pagination(ModelMap modelMap, int start, int totalListCount); // 페이징																										// 처리
	public List<BoardListDto> getSearchListInfo(String searchKeyword, int start); // 검색 리스트
	public int getSearchListCount(String searchKeyword); // 검색 리스트 수
	
	
}

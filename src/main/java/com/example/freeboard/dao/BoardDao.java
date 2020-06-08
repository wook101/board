package com.example.freeboard.dao;


import java.util.List;


import com.example.freeboard.dto.BoardListDto;


public interface BoardDao {						
	public List<BoardListDto> getBoardList(int start, int limit);	//게시글 목록
	public int getListCount(); 										//총 게시글 수 
	public List<BoardListDto> getSearchListInfo(String searchKeyword, int start, int limit); //게시글 검색 목록
	public int getSearchListCount(String searchKeyword);	//검색한 게시글 수
}

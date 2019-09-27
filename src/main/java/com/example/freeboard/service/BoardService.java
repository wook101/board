package com.example.freeboard.service;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.ui.ModelMap;
import org.springframework.web.multipart.MultipartFile;

import com.example.freeboard.dto.DetailInfoVo;
import com.example.freeboard.dto.JoinformVo;
import com.example.freeboard.dto.ListInfoVo;
import com.example.freeboard.dto.ReplyListInfoVo;
import com.example.freeboard.dto.WriteFormVo;

public interface BoardService {
	//public static final String filePath = "boardImgFile/";															//호스팅서버 파일 경로
	public static final String filePath = "c:/Users/ehddn/Desktop/게시판 프로젝트/imgFile/";			//로컬서버 파일 경로
	public static final int LIMIT = 10;																					//한 화면에 보여줄 게시물 수	
	public String getEncPassword(String userID);																		//로그인 검사를 위한 암화된 패스워드
	public int joinFormVaildation(String type, String formVal);													//회원가입 폼 검사
	public int joinFormInsert(JoinformVo joinformvo);																//회원가입 정보 삽입
	public int getUserTableId(String userID);																			//user 테이블 id
	public void fileDownload(int id, HttpServletResponse response);											//파일 다운로드
	public List<ListInfoVo> getListInfo(int start);																		//게시판 리스트
	public int getListCount();																									//게시판 리스트 카운팅
	public DetailInfoVo detailInfoById(int board_id);																	//게시판 리스트 상세정보
	public void writeRegister(int user_id, MultipartFile file, WriteFormVo writeFormVo);  			//글쓰기 등록
	public int deletePostById(int id, Integer hashCode);															//글삭제
	public int deleteFileInfo(Integer delHashCode);  																	//파일 정보 삭제
	public void updatePost(int id, WriteFormVo writeFormVo,int user_id, MultipartFile file, Integer fileHashCode); //글 정보 갱신
	public void pagination(ModelMap modelMap, int start, int totalListCount, String type, String searchKeyword); //페이징 처리
	public List<ListInfoVo> getSearchListInfo(String searchKeyword, int start);							//검색 리스트
	public int getSearchListCount(String searchKeyword);															//검색 리스트 수
	public void replyRegister(int user_id, int board_id, String comment);									//댓글 등록
	public List<ReplyListInfoVo>replyListInfoById(int board_id);												//댓글 리스트
	public int deleteReplyById(int reply_id);																			//댓글 삭제
}

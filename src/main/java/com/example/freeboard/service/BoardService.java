package com.example.freeboard.service;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.web.multipart.MultipartFile;

import com.example.freeboard.dto.DetailInfoVo;
import com.example.freeboard.dto.JoinformVo;
import com.example.freeboard.dto.ListInfoVo;
import com.example.freeboard.dto.WriteFormVo;

public interface BoardService {
	//public static final String filePath = "boardImgFile/";															//호스팅서버 파일 경로
	public static final String filePath = "c:/Users/ehddn/Desktop/게시판 프로젝트/imgFile/";			//로컬서버 파일 경로
	public static final int LIMIT = 10;																					//한 화면에 보여줄 게시물 수	
	public int loginCheck(String userID, String password);														//로그인 폼 검사
	public int joinFormVaildation(String type, String formVal);													//회원가입 폼 검사
	public int joinFormInsert(JoinformVo joinformvo);																//회원가입 정보 삽입
	public String getNickName(String userID);																			//닉네임 
	public void fileDownload(int id, HttpServletResponse response);											//파일 다운로드
	public List<ListInfoVo> getListInfo(int start);																		//게시판 리스트
	public int getListCount();																									//게시판 리스트 카운팅
	public DetailInfoVo detailInfoById(int id);																			//게시판 리스트 상세정보
	public void writeRegister(MultipartFile file, String nickName, WriteFormVo writeFormVo);  	//글쓰기 등록
	public int deletePostById(int id, Integer hashCode);															//글삭제
	public int deleteFileInfo(Integer delHashCode);  																	//파일 정보 삭제
	public void updatePost(int id, WriteFormVo writeFormVo, MultipartFile file, Integer fileHashCode); //글 정보 갱신
}

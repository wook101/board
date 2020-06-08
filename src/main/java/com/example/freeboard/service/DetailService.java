package com.example.freeboard.service;

import java.util.List;

import javax.servlet.http.HttpServletResponse;



import com.example.freeboard.dto.DetailInfoDto;
import com.example.freeboard.dto.ReplyListInfoDto;


public interface DetailService {
	public DetailInfoDto detailInfoById(int board_id); // 게시글 상세정보
	public void fileDownload(int id, HttpServletResponse response); // 파일 다운로드
	public int deletePostById(int id, Integer hashCode); // 글삭제
	public int deleteFileInfo(Integer delHashCode); // 파일 정보 삭제
	public void replyRegister(int user_id, int board_id, String comment); // 댓글 등록
	public List<ReplyListInfoDto> replyListInfoById(int board_id); // 댓글 목록
	public int deleteReplyById(int reply_id); // 댓글 삭제
}

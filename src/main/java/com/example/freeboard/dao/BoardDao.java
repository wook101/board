package com.example.freeboard.dao;


import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.example.freeboard.dto.DetailInfoVo;
import com.example.freeboard.dto.FileInfoVo;
import com.example.freeboard.dto.JoinformVo;
import com.example.freeboard.dto.ListInfoVo;
import com.example.freeboard.dto.ReplyListInfoVo;
import com.example.freeboard.dto.WriteFormVo;

public interface BoardDao {
	public int joinFormInsert(JoinformVo joinformvo); 
	public String getEncPassword(String userID);
	public int joinFormVaildation(String type, String formVal); 
	public int fileInfoInsert(int board_id, MultipartFile file); 
	public int writeFormInsert(int user_id, WriteFormVo writeFormVo, int views, Integer hashCode);
	public int getUserTableId(String userID);
	public int getBoardLastId();																							
	public List<ListInfoVo> getListInfo(int start, int limit);
	public int getListCount(); 
	public DetailInfoVo detailInfoById(int board_id); 
	public int viewsCountUpdate(int id); 
	public Integer getFileHashCodeById(int id); 
	public FileInfoVo getFileInfo(Integer fileHashCode);
	public int deletePostById(int board_id);
	public int deleteFileInfo(Integer fileHashCode);
	public int deleteReplyInfo(int board_id);
	public void updateDetailHashCode(Integer fileHashCode);
	public void updateDetailInfo(int id, WriteFormVo writeFormVo, Integer hashCode);
	public List<ListInfoVo> getSearchListInfo(String searchKeyword, int start, int limit);
	public int getSearchListCount(String searchKeyword);
	public void replyRegister(int user_id, int board_id, String comment);
	public List<ReplyListInfoVo>replyListInfoById(int board_id);
	public int deleteReplyById(int reply_id);
}

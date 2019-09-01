package com.example.freeboard.dao;


import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.example.freeboard.dto.DetailInfoVo;
import com.example.freeboard.dto.FileInfoVo;
import com.example.freeboard.dto.JoinformVo;
import com.example.freeboard.dto.ListInfoVo;
import com.example.freeboard.dto.WriteFormVo;

public interface BoardDao {
	public int joinFormInsert(JoinformVo joinformvo); 
	public int loginCheck(String userID, String password); 
	public int joinFormVaildation(String type, String formVal); 
	public int fileInfoInsert(MultipartFile file); 
	public int writeFormInsert(String nickName, WriteFormVo writeFormVo, int views,Integer hashCode); 
	public String getNickName(String userID); 
	public List<ListInfoVo> getListInfo(int start, int limit);
	public int getListCount(); 
	public DetailInfoVo detailInfoById(int id); 
	public int viewsCountUpdate(int id); 
	public Integer getFileHashCodeById(int id); 
	public FileInfoVo getFileInfo(Integer fileHashCode);
	public int deletePostById(int id);
	public int deleteFileInfo(Integer fileHashCode);
	public void updateDetailHashCode(Integer fileHashCode);
	public void updateDetailInfo(int id, WriteFormVo writeFormVo, Integer hashCode);

}

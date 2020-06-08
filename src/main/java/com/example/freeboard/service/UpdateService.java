package com.example.freeboard.service;

import org.springframework.web.multipart.MultipartFile;

import com.example.freeboard.dto.WriteFormDto;

public interface UpdateService {
	public void updatePost(int id, WriteFormDto writeFormVo, int user_id, MultipartFile file, Integer fileHashCode); // 글 정보갱신	
}

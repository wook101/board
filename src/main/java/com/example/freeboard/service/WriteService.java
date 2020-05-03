package com.example.freeboard.service;

import org.springframework.web.multipart.MultipartFile;

import com.example.freeboard.dto.WriteFormDto;

public interface WriteService {
	public void writeRegister(int user_id, MultipartFile file, WriteFormDto writeFormVo); // 글쓰기 등록
}

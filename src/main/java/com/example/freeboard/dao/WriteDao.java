package com.example.freeboard.dao;

import org.springframework.web.multipart.MultipartFile;

import com.example.freeboard.dto.WriteFormDto;

public interface WriteDao {
	public int writeFormInsert(int user_id, WriteFormDto writeFormVo, int views, Integer hashCode); //글쓰기 폼정보 삽입
	public int fileInfoInsert(int board_id, MultipartFile file); //이미지 파일 정보 삽입
	public int getBoardLastId(); // board 테이블 마지막 id
}

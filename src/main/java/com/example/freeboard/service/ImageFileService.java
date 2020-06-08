package com.example.freeboard.service;

import org.springframework.web.multipart.MultipartFile;

public interface ImageFileService {
	// public static final String filePath = "boardImgFile/"; //호스팅서버 파일 경로
	public static final String filePath = "c:/Users/ehddn/Desktop/바탕화면 정리하기!/Places/Containers/프로젝트/게시판/imgFile/"; // 로컬서버 파일경로
	public int upload(MultipartFile file);
	public String regex(String text);																		

}

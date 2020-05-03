package com.example.freeboard.service;

import com.example.freeboard.dto.JoinformDto;

public interface JoinService {
	public int joinFormVaildation(String type, String formVal); // 회원가입 폼 검사
	public int joinFormInsert(JoinformDto joinformvo); // 회원가입 정보 삽입
}

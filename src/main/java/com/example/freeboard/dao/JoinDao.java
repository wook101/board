package com.example.freeboard.dao;

import com.example.freeboard.dto.JoinformDto;

public interface JoinDao {
	public int joinFormInsert(JoinformDto joinformvo);			// 회원가입
	public int joinFormVaildation(String type, String formVal); // 회원가입 폼체크
}

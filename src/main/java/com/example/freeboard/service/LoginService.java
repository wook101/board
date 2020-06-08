package com.example.freeboard.service;

public interface LoginService {
	public String getEncPassword(String userID); // 로그인 검사를 위한 암화된 패스워드
	public int getUserTableId(String userID); // user 테이블 id
}

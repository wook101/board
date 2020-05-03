package com.example.freeboard.dao;

public interface LoginDao {
	public String getEncPassword(String userID);	//암호화 된 패스워드
	public int getUserTableId(String userID);		//user테이블의 id
}

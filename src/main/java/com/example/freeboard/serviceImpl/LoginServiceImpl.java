package com.example.freeboard.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.freeboard.dao.LoginDao;
import com.example.freeboard.service.LoginService;

@Service
public class LoginServiceImpl implements LoginService {
	private final LoginDao loginDao;

	public LoginServiceImpl(LoginDao loginDao){
		this.loginDao = loginDao;
	}

	// 로그인 검사를 위한 암화된 패스워드
	@Override
	public String getEncPassword(String userID) {
		return loginDao.getEncPassword(userID);
	}

	// user테이블의 id
	@Override
	public int getUserTableId(String userID) {
		return loginDao.getUserTableId(userID);
	}

}

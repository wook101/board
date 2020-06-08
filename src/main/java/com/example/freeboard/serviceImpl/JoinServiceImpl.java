package com.example.freeboard.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.example.freeboard.dao.JoinDao;
import com.example.freeboard.dto.JoinformDto;
import com.example.freeboard.service.JoinService;

@Service
public class JoinServiceImpl implements JoinService {
	
	@Autowired
	JoinDao joinDao;
	
	// 회원가입 정보 삽입
	@Override
	public int joinFormInsert(JoinformDto joinformvo) {
		return joinDao.joinFormInsert(joinformvo);
	}

	// 회원가입 폼 검사
	@Override
	public int joinFormVaildation(String type, String formVal) {
		return joinDao.joinFormVaildation(type, formVal);
	}
}

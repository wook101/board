package com.example.freeboard.dao;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.web.multipart.MultipartFile;

import com.example.freeboard.config.ApplicationConfig;
import com.example.freeboard.dto.JoinformVo;

public class Test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ApplicationContext ac = new AnnotationConfigApplicationContext(ApplicationConfig.class);
		BoardDao boardDao = ac.getBean(BoardDao.class);
		
		System.out.println(boardDao.deleteReplyById(12));
	}

}

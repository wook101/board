package com.example.freeboard.dao;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.example.freeboard.config.ApplicationConfig;

public class Test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ApplicationContext ac = new AnnotationConfigApplicationContext(ApplicationConfig.class);
		BoardDao boardDao = ac.getBean(BoardDao.class);
		boardDao.updateDetailHashCode(303639991);
	}

}

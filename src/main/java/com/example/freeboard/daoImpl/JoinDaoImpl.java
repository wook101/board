package com.example.freeboard.daoImpl;

import static com.example.freeboard.dao.BoardDaoSqls.EMAIL_VALIDATION;
import static com.example.freeboard.dao.BoardDaoSqls.INSERT_JOINFORM;
import static com.example.freeboard.dao.BoardDaoSqls.NICKNAME_VALIDATION;
import static com.example.freeboard.dao.BoardDaoSqls.USERID_VALIDATION;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.example.freeboard.dao.JoinDao;
import com.example.freeboard.dto.JoinformDto;

@Repository
public class JoinDaoImpl implements JoinDao {

	private NamedParameterJdbcTemplate jdbc;
	
	public JoinDaoImpl(DataSource datasource) {
		this.jdbc = new NamedParameterJdbcTemplate(datasource);
	}
	
	// 회원가입
	@Override
	public int joinFormInsert(JoinformDto joinformvo) {
		Map<String, Object> param = new HashMap<>();
		param.put("userID", joinformvo.getUserID());
		param.put("nickName", joinformvo.getNickName());
		param.put("password", joinformvo.getPassword());
		param.put("email", joinformvo.getEmail());
		return jdbc.update(INSERT_JOINFORM, param);
	}

	// 회원가입 폼 체크
	@Override
	public int joinFormVaildation(String type, String formVal) {
		Map<String, String> param = Collections.singletonMap("formVal", formVal);
		String sql = "";
		if (type.equals("userID"))
			sql = USERID_VALIDATION;
		else if (type.equals("nickName"))
			sql = NICKNAME_VALIDATION;
		else
			sql = EMAIL_VALIDATION;
		return jdbc.queryForObject(sql, param, Integer.class);
	}
}

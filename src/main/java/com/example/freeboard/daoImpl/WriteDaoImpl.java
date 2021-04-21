package com.example.freeboard.daoImpl;

import static com.example.freeboard.dao.BoardDaoSqls.INSERT_FILEINFO;
import static com.example.freeboard.dao.BoardDaoSqls.INSERT_WRITEFORM;
import static com.example.freeboard.dao.BoardDaoSqls.SELECT_BOARD_ID;

import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.web.multipart.MultipartFile;

import com.example.freeboard.dao.WriteDao;
import com.example.freeboard.dto.WriteFormDto;

@Repository
public class WriteDaoImpl implements WriteDao {
	private NamedParameterJdbcTemplate jdbc;
	
	public WriteDaoImpl(DataSource datasource) {
		this.jdbc = new NamedParameterJdbcTemplate(datasource);
	}
	
	// 글쓰기 폼 정보 삽입
	@Override
	public int writeFormInsert(int user_id, WriteFormDto writeFormVo, int views, Integer hashCode) {
		Map<String, Object> param = new HashMap<>();
		param.put("user_id", user_id);
		param.put("title", writeFormVo.getTitle());
		param.put("content", writeFormVo.getContent());
		param.put("create_date", new Date());
		param.put("views", views);
		param.put("hashCode", hashCode);
		return jdbc.update(INSERT_WRITEFORM, param);
	}

	// 이미지 파일 정보 삽입
	@Override
	public int fileInfoInsert(int board_id, MultipartFile file) {
		Map<String, Object> param = new HashMap<>();
		param.put("board_id", board_id);
		param.put("originalFileName", file.getOriginalFilename());
		param.put("contentType", file.getContentType());
		param.put("size", file.getSize());
		param.put("hashCode", file.hashCode());
		return jdbc.update(INSERT_FILEINFO, param);
	}

	// board 테이블 마지막 id
	@Override
	public int getBoardLastId() {
		try {
			return jdbc.queryForObject(SELECT_BOARD_ID, Collections.emptyMap(), int.class);
		} catch (EmptyResultDataAccessException e) {
			return 1;
		}
	}
}

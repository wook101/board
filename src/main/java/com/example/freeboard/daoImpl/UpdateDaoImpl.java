package com.example.freeboard.daoImpl;

import static com.example.freeboard.dao.BoardDaoSqls.UPDATE_DETAIL_INFO;
import static com.example.freeboard.dao.BoardDaoSqls.UPDATE_FILE_HASHCODE;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.example.freeboard.dao.UpdateDao;
import com.example.freeboard.dto.WriteFormDto;

@Repository
public class UpdateDaoImpl implements UpdateDao {
	private NamedParameterJdbcTemplate jdbc;
	
	public UpdateDaoImpl(DataSource datasource) {
		this.jdbc = new NamedParameterJdbcTemplate(datasource);
	}
	
	
	// 파일 삭제후 해쉬코드 null로 갱신
	@Override
	public void updateDetailHashCode(Integer fileHashCode) {
		Map<String, Object> param = Collections.singletonMap("fileHashCode", fileHashCode);
		jdbc.update(UPDATE_FILE_HASHCODE, param);
	}

	// 상세 정보 갱신
	@Override
	public void updateDetailInfo(int id, WriteFormDto writeFormVo, Integer hashCode) {
		Map<String, Object> param = new HashMap<>();
		param.put("id", id);
		param.put("title", writeFormVo.getTitle());
		param.put("content", writeFormVo.getContent());
		param.put("hashCode", hashCode);
		jdbc.update(UPDATE_DETAIL_INFO, param);
	}
}

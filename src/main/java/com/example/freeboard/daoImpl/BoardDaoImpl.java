package com.example.freeboard.daoImpl;



import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


import javax.sql.DataSource;


import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;


import com.example.freeboard.dao.BoardDao;
import com.example.freeboard.dto.BoardListDto;


import static com.example.freeboard.dao.BoardDaoSqls.*;
@Repository
public class BoardDaoImpl implements BoardDao {
		
	private NamedParameterJdbcTemplate jdbc;
	private RowMapper<BoardListDto> boardlistRowMapper = BeanPropertyRowMapper.newInstance(BoardListDto.class);
	
	public BoardDaoImpl(DataSource datasource) {
		this.jdbc = new NamedParameterJdbcTemplate(datasource);
	}
	
	
	//게시글 목록 
	@Override
	public List<BoardListDto> getBoardList(int start, int limit){
		Map<String, Object> param = new HashMap<>();
		param.put("start", start);
		param.put("limit", limit);
		return jdbc.query(SELECT_LIST_INFO, param, boardlistRowMapper);
	}
	
	
	//총 게시글 수
	@Override
	public int getListCount() {
		return jdbc.queryForObject(SELECT_LIST_COUNT, Collections.emptyMap(), int.class);
	}
	
	
	//게시글 검색 목록
	@Override
	public List<BoardListDto> getSearchListInfo(String searchKeyword, int start, int limit) {
		Map<String,Object> param = new HashMap<>();
		param.put("keyword", "%"+searchKeyword+"%");
		param.put("start", start);
		param.put("limit", limit);
		return jdbc.query(SELECT_SEARCH_LIST, param, boardlistRowMapper);
	}
	
	
	//검색한 게시글 수
	@Override
	public int getSearchListCount(String searchKeyword) {
		return jdbc.queryForObject(SELECT_SEARCH_LIST_COUNT, Collections.singletonMap("keyword", "%"+searchKeyword+"%"), int.class);
	}
	
	
	
	
	
	
}

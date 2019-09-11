package com.example.freeboard.dao;


import java.io.File;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


import javax.sql.DataSource;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.web.multipart.MultipartFile;

import com.example.freeboard.dto.DetailInfoVo;
import com.example.freeboard.dto.FileInfoVo;
import com.example.freeboard.dto.JoinformVo;
import com.example.freeboard.dto.ListInfoVo;
import com.example.freeboard.dto.ReplyListInfoVo;
import com.example.freeboard.dto.WriteFormVo;

import static com.example.freeboard.dao.BoardDaoSqls.*;
@Repository
public class BoardDaoImpl implements BoardDao {
		
	private NamedParameterJdbcTemplate jdbc;
	private RowMapper<ListInfoVo> listInfoRowMapper = BeanPropertyRowMapper.newInstance(ListInfoVo.class);
	private RowMapper<DetailInfoVo> detailInfoRowMapper = BeanPropertyRowMapper.newInstance(DetailInfoVo.class);
	private RowMapper<FileInfoVo> fileInfoRowMapper = BeanPropertyRowMapper.newInstance(FileInfoVo.class);
	private RowMapper<ReplyListInfoVo> replyInfoRowMapper = BeanPropertyRowMapper.newInstance(ReplyListInfoVo.class);
	public BoardDaoImpl(DataSource datasource) {
		this.jdbc = new NamedParameterJdbcTemplate(datasource);
	}
	
	//회원가입
	@Override
	public int joinFormInsert(JoinformVo joinformvo) {
		Map<String, Object> param = new HashMap<>();
		param.put("userID", joinformvo.getUserID());
		param.put("nickName", joinformvo.getNickName());
		param.put("password", joinformvo.getPassword());
		param.put("email", joinformvo.getEmail());
		return jdbc.update(INSERT_JOINFORM, param);
	}
	
	//로그인 폼 체크
	@Override
	public int loginCheck(String userID, String password) {
		Map<String,String> param = new HashMap<>();
		param.put("userID", userID);
		param.put("password", password);
		return jdbc.queryForObject(COUNT_LOGIN_CHECK, param, Integer.class);
	}
	
	//회원가입 폼 체크
	@Override
	public int joinFormVaildation(String type, String formVal) {
		Map<String,String> param = Collections.singletonMap("formVal", formVal);
		String sql="";
		if(type.equals("userID")) 
			sql=USERID_VALIDATION;
		else if(type.equals("nickName")) 
			sql=NICKNAME_VALIDATION;
		else
			sql=EMAIL_VALIDATION;
		return jdbc.queryForObject(sql, param, Integer.class);
	}

	//이미지 파일 정보 삽입
	@Override
	public int fileInfoInsert(int board_id, MultipartFile file) {
		Map<String,Object> param = new HashMap<>();
		param.put("board_id", board_id);
		param.put("originalFileName", file.getOriginalFilename());
		param.put("contentType", file.getContentType());
		param.put("size",file.getSize());
		param.put("hashCode", file.hashCode());
		return jdbc.update(INSERT_FILEINFO, param);
	}
	//글쓰기 폼 정보 삽입
	@Override
	public int writeFormInsert(int user_id, WriteFormVo writeFormVo, int views, Integer hashCode) {
		Map<String, Object> param = new HashMap<>();
		param.put("user_id", user_id);
		param.put("title", writeFormVo.getTitle());
		param.put("content", writeFormVo.getContent());
		param.put("create_date", new Date());
		param.put("views", views);
		param.put("hashCode", hashCode);
		return jdbc.update(INSERT_WRITEFORM, param);
	}
	
	//user테이블의 id
	@Override
	public int getUserTableId(String userID) {
		Map<String,String> param = Collections.singletonMap("userID", userID);
		try {
			return jdbc.queryForObject(SELECT_ID_BY_USERID, param, int.class);
		}catch(EmptyResultDataAccessException e) {
			return 0;
		}
	}
	//board 테이블 마지막 id																							
	@Override
	public int getBoardLastId() {
		try {
			return jdbc.queryForObject(SELECT_BOARD_ID, Collections.emptyMap(), int.class);
		}catch(EmptyResultDataAccessException e) {
			return 1;
		}
	}
	
	//리스트 정보
	@Override
	public List<ListInfoVo> getListInfo(int start, int limit){
		Map<String, Object> param = new HashMap<>();
		param.put("start", start);
		param.put("limit", limit);
		return jdbc.query(SELECT_LIST_INFO, param, listInfoRowMapper);
	}
	
	//리스트 카운트
	@Override
	public int getListCount() {
		return jdbc.queryForObject(SELECT_LIST_COUNT, Collections.emptyMap(), int.class);
	}
	//리스트 상세정보
	@Override
	public DetailInfoVo detailInfoById(int board_id) {
		return jdbc.queryForObject(SELECT_DETAIL_INFO_BYID, Collections.singletonMap("board_id", board_id), detailInfoRowMapper);
	}
	//조회수 증가
	@Override
	public int viewsCountUpdate(int id) {
		Map<String, Object> param = Collections.singletonMap("id", id);
		return jdbc.update(UPDATE_VIEWS_COUNT, param);
	}
	//이미지 파일 해쉬코드
	@Override
	public Integer getFileHashCodeById(int id) {
		Map<String, Object> param = Collections.singletonMap("id", id);
		return jdbc.queryForObject(SELECT_FILE_HASHCODE_BYID, param, Integer.class);
	}
	//이미지 파일 이름
	@Override
	public FileInfoVo getFileInfo(Integer fileHashCode) {
		Map<String, Object> param = Collections.singletonMap("fileHashCode", fileHashCode);
		return jdbc.queryForObject(SELECT_FILE_INFO, param, fileInfoRowMapper);
	}
	
	//글 정보 삭제
	@Override
	public int deletePostById(int board_id) {
		return jdbc.update(DELETE_POST_BYID, Collections.singletonMap("board_id", board_id));
	}
	//댓글 정보 삭제
	@Override
	public int deleteReplyInfo(int board_id) {
		return jdbc.update(DELETE_REPLY_BYID, Collections.singletonMap("board_id", board_id));
	}
	
	//파일 정보 삭제
	@Override
	public int deleteFileInfo(Integer fileHashCode) {
		Map<String,Object> param = Collections.singletonMap("fileHashCode", fileHashCode);
		return jdbc.update(DELETE_FILE_INFO, param);
	}
	
	
	//파일 삭제후 해쉬코드 null로 갱신
	@Override
	public void updateDetailHashCode(Integer fileHashCode) {
		Map<String,Object> param = Collections.singletonMap("fileHashCode", fileHashCode);
		jdbc.update(UPDATE_FILE_HASHCODE, param);
	}
	
	//글 정보 갱신
	@Override
	public void updateDetailInfo(int id, WriteFormVo writeFormVo, Integer hashCode) {
		Map<String,Object> param = new HashMap<>();
		param.put("id", id);
		param.put("title", writeFormVo.getTitle());
		param.put("content", writeFormVo.getContent());
		param.put("hashCode", hashCode);
		jdbc.update(UPDATE_DETAIL_INFO, param);
	}

	@Override
	public List<ListInfoVo> getSearchListInfo(String searchKeyword, int start, int limit) {
		Map<String,Object> param = new HashMap<>();
		param.put("keyword", "%"+searchKeyword+"%");
		param.put("start", start);
		param.put("limit", limit);
		return jdbc.query(SELECT_SEARCH_LIST, param, listInfoRowMapper);
	}
	
	@Override
	public int getSearchListCount(String searchKeyword) {
		return jdbc.queryForObject(SELECT_SEARCH_LIST_COUNT, Collections.singletonMap("keyword", "%"+searchKeyword+"%"), int.class);
	}
	//댓글 등록
	@Override
	public void replyRegister(int user_id, int board_id, String comment) {
		Map<String, Object> param = new HashMap<>();
		param.put("user_id",user_id);
		param.put("board_id", board_id);
		param.put("comment", comment);
		param.put("create_date", new Date());
		jdbc.update(INSERT_REPLY_REGISTER, param);
	}

	//댓글 리스트
	@Override
	public List<ReplyListInfoVo>replyListInfoById(int board_id){
		return jdbc.query(SELECT_REPLY_INFO, Collections.singletonMap("board_id", board_id), replyInfoRowMapper);
	}
	//댓글 삭제
	@Override
	public int deleteReplyById(int reply_id) {
		return jdbc.update(DELETE_REPLY, Collections.singletonMap("reply_id", reply_id));
	}


	
	
	
	
}

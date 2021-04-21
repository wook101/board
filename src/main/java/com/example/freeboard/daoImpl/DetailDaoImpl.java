package com.example.freeboard.daoImpl;

import static com.example.freeboard.dao.BoardDaoSqls.DELETE_FILE_INFO;
import static com.example.freeboard.dao.BoardDaoSqls.DELETE_POST_BYID;
import static com.example.freeboard.dao.BoardDaoSqls.DELETE_REPLY;
import static com.example.freeboard.dao.BoardDaoSqls.DELETE_REPLY_BYID;
import static com.example.freeboard.dao.BoardDaoSqls.INSERT_REPLY_REGISTER;
import static com.example.freeboard.dao.BoardDaoSqls.SELECT_DETAIL_INFO_BYID;
import static com.example.freeboard.dao.BoardDaoSqls.SELECT_FILE_HASHCODE_BYID;
import static com.example.freeboard.dao.BoardDaoSqls.SELECT_FILE_INFO;
import static com.example.freeboard.dao.BoardDaoSqls.SELECT_REPLY_INFO;
import static com.example.freeboard.dao.BoardDaoSqls.UPDATE_VIEWS_COUNT;

import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.example.freeboard.dao.DetailDao;
import com.example.freeboard.dto.DetailInfoDto;
import com.example.freeboard.dto.FileInfoDto;
import com.example.freeboard.dto.ReplyListInfoDto;


@Repository
public class DetailDaoImpl implements DetailDao {
    private NamedParameterJdbcTemplate jdbc;
    private RowMapper<DetailInfoDto> detailInfoRowMapper = BeanPropertyRowMapper.newInstance(DetailInfoDto.class);
    private RowMapper<FileInfoDto> fileInfoRowMapper = BeanPropertyRowMapper.newInstance(FileInfoDto.class);
    private RowMapper<ReplyListInfoDto> replyInfoRowMapper = BeanPropertyRowMapper.newInstance(ReplyListInfoDto.class);

    public DetailDaoImpl(DataSource datasource) {
        this.jdbc = new NamedParameterJdbcTemplate(datasource);
    }

    // 조회수 증가
    @Override
    public int viewsCountUpdate(int id) {
        Map<String, Object> param = Collections.singletonMap("id", id);
        return jdbc.update(UPDATE_VIEWS_COUNT, param);
    }

    // 게시글 상세정보
    @Override
    public DetailInfoDto detailInfoById(int board_id) {
        return jdbc.queryForObject(SELECT_DETAIL_INFO_BYID, Collections.singletonMap("board_id", board_id),
                detailInfoRowMapper);
    }

    // 이미지 파일 해쉬코드
    @Override
    public Integer getFileHashCodeById(int id) {
        Map<String, Object> param = Collections.singletonMap("id", id);
        return jdbc.queryForObject(SELECT_FILE_HASHCODE_BYID, param, Integer.class);
    }

    // 이미지 파일 이름
    @Override
    public FileInfoDto getFileInfo(Integer fileHashCode) {
        Map<String, Object> param = Collections.singletonMap("fileHashCode", fileHashCode);
        return jdbc.queryForObject(SELECT_FILE_INFO, param, fileInfoRowMapper);
    }

    // 글 정보 삭제
    @Override
    public int deletePostById(int board_id) {
        return jdbc.update(DELETE_POST_BYID, Collections.singletonMap("board_id", board_id));
    }

    // 파일 정보 삭제
    @Override
    public int deleteFileInfo(Integer fileHashCode) {
        Map<String, Object> param = Collections.singletonMap("fileHashCode", fileHashCode);
        return jdbc.update(DELETE_FILE_INFO, param);
    }

    // 댓글 정보 삭제
    @Override
    public int deleteReplyInfo(int board_id) {
        return jdbc.update(DELETE_REPLY_BYID, Collections.singletonMap("board_id", board_id));
    }

    // 댓글 등록
    @Override
    public void replyRegister(int user_id, int board_id, String comment) {
        Map<String, Object> param = new HashMap<>();
        param.put("user_id", user_id);
        param.put("board_id", board_id);
        param.put("comment", comment);
        param.put("create_date", new Date());
        jdbc.update(INSERT_REPLY_REGISTER, param);
    }

    // 댓글 목록
    @Override
    public List<ReplyListInfoDto> replyListInfoById(int board_id) {
        return jdbc.query(SELECT_REPLY_INFO, Collections.singletonMap("board_id", board_id), replyInfoRowMapper);
    }

    // 댓글 삭제
    @Override
    public int deleteReplyById(int reply_id) {
        return jdbc.update(DELETE_REPLY, Collections.singletonMap("reply_id", reply_id));
    }
}

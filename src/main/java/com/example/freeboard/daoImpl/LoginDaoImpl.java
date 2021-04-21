package com.example.freeboard.daoImpl;

import static com.example.freeboard.dao.BoardDaoSqls.SELECT_ID_BY_USERID;
import static com.example.freeboard.dao.BoardDaoSqls.SELECT_LOGIN_PASSWORD;

import java.util.Collections;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.example.freeboard.dao.LoginDao;

@Repository
public class LoginDaoImpl implements LoginDao {
    private NamedParameterJdbcTemplate jdbc;

    public LoginDaoImpl(DataSource datasource) {
        this.jdbc = new NamedParameterJdbcTemplate(datasource);
    }

    // 암호화된 패스워드
    @Override
    public String getEncPassword(String userID) {
        try {
            return jdbc.queryForObject(SELECT_LOGIN_PASSWORD, Collections.singletonMap("userID", userID), String.class);
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    // user테이블의 id
    @Override
    public int getUserTableId(String userID) {
        Map<String, String> param = Collections.singletonMap("userID", userID);
        try {
            return jdbc.queryForObject(SELECT_ID_BY_USERID, param, int.class);
        } catch (EmptyResultDataAccessException e) {
            return 0;
        }
    }

}

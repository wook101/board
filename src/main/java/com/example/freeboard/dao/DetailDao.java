package com.example.freeboard.dao;

import java.util.List;

import com.example.freeboard.dto.DetailInfoDto;
import com.example.freeboard.dto.FileInfoDto;
import com.example.freeboard.dto.ReplyListInfoDto;


public interface DetailDao {
    public int viewsCountUpdate(int id);                //조회 수 업데이트
    public DetailInfoDto detailInfoById(int board_id);    //게시글 상세정보
    public Integer getFileHashCodeById(int id);        // 이미지 파일 해쉬코드
    public FileInfoDto getFileInfo(Integer fileHashCode);    // 이미지 파일 이름
    public int deletePostById(int board_id);                // 글 정보 삭제
    public int deleteFileInfo(Integer fileHashCode);        // 파일 정보 삭제
    public int deleteReplyInfo(int board_id);                // 댓글 정보 삭제
    public void replyRegister(int user_id, int board_id, String comment);    // 댓글 등록
    public List<ReplyListInfoDto> replyListInfoById(int board_id);            // 댓글 목록
    public int deleteReplyById(int reply_id);                                // 댓글 삭제
}

package com.example.freeboard.dao;

import com.example.freeboard.dto.WriteFormDto;

public interface UpdateDao {
	public void updateDetailInfo(int id, WriteFormDto writeFormVo, Integer hashCode);	// 상세 정보 갱신
	public void updateDetailHashCode(Integer fileHashCode);	// 파일 삭제후 해쉬코드 null로 갱신
}

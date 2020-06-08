package com.example.freeboard.serviceImpl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.example.freeboard.dao.WriteDao;
import com.example.freeboard.dto.WriteFormDto;
import com.example.freeboard.service.ImageFileService;
import com.example.freeboard.service.WriteService;

@Service
public class WriteServiceImpl implements WriteService {

	@Autowired
	private WriteDao writeDao;
	
	@Autowired
	private ImageFileService imageFileService;
	// 글쓰기 등록
	@Override
	@Transactional(readOnly = false)
	public void writeRegister(int user_id, MultipartFile file, WriteFormDto writeFormVo) {

		if (!file.isEmpty()) {
			int board_id; // 삽입할 글 id
			Integer hashCode = imageFileService.upload(file); // 파일 업로드 후 hashCode가져옴
			writeDao.writeFormInsert(user_id, writeFormVo, 0, hashCode); // 글쓰기 정보 삽입
			board_id = writeDao.getBoardLastId();
			writeDao.fileInfoInsert(board_id, file); // 파일 정보 삽입
		} else {
			writeDao.writeFormInsert(user_id, writeFormVo, 0, null); // 글쓰기 정보 삽입
		}

	}
	
}

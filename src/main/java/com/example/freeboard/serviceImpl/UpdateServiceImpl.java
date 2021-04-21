package com.example.freeboard.serviceImpl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;


import com.example.freeboard.dao.UpdateDao;
import com.example.freeboard.dao.WriteDao;
import com.example.freeboard.dto.WriteFormDto;
import com.example.freeboard.service.ImageFileService;
import com.example.freeboard.service.UpdateService;

@Service
public class UpdateServiceImpl implements UpdateService {
	private final WriteDao writeDao;
	private final UpdateDao updateDao;
	private final ImageFileService imageFileService;

	public UpdateServiceImpl(WriteDao writeDao, UpdateDao updateDao, ImageFileService imageFileService) {
		this.writeDao = writeDao;
		this.updateDao = updateDao;
		this.imageFileService = imageFileService;
	}

	// 글 정보 갱신
	@Override
	@Transactional(readOnly = false)
	public void updatePost(int id, WriteFormDto writeFormVo, int user_id, MultipartFile file, Integer fileHashCode) {
		if (!file.isEmpty()) {
			writeDao.fileInfoInsert(user_id, file); // 파일 정보 삽입
			fileHashCode = imageFileService.upload(file); // 파일 업로드
		}
		updateDao.updateDetailInfo(id, writeFormVo, fileHashCode); // 상세 정보 갱신
	}

}

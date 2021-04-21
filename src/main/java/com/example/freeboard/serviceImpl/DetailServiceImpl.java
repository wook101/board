package com.example.freeboard.serviceImpl;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.OutputStream;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import com.example.freeboard.dao.DetailDao;
import com.example.freeboard.dao.UpdateDao;
import com.example.freeboard.dto.DetailInfoDto;
import com.example.freeboard.dto.FileInfoDto;
import com.example.freeboard.dto.ReplyListInfoDto;

import com.example.freeboard.service.DetailService;
import com.example.freeboard.service.ImageFileService;

@Service
public class DetailServiceImpl implements DetailService {
    private final DetailDao detailDao;
    private final UpdateDao updateDao;
    private final ImageFileService imageFileService;

    public DetailServiceImpl(DetailDao detailDao, UpdateDao updateDao, ImageFileService imageFileService) {
        this.detailDao = detailDao;
        this.updateDao = updateDao;
        this.imageFileService = imageFileService;
    }

    // 게시글 상세 정보
    @Override
    @Transactional(readOnly = false)
    public DetailInfoDto detailInfoById(int board_id) {
        detailDao.viewsCountUpdate(board_id); // 조회수 업데이트
        return detailDao.detailInfoById(board_id);
    }

    // 파일 다운로드
    @Override
    @Transactional(readOnly = false)
    public void fileDownload(int id, HttpServletResponse response) {
        Integer hashCode = detailDao.getFileHashCodeById(id);
        FileInfoDto fileInfo = detailDao.getFileInfo(hashCode);
        String extension = imageFileService.regex(fileInfo.getContentType());
        String path = ImageFileService.filePath + hashCode + extension;

        response.setHeader("Content-Disposition", "attachment; filename=\"" + fileInfo.getOriginalFileName() + "\";");
        response.setHeader("Content-Transfer-Encoding", "binary");
        response.setHeader("Pragma", "no-cache;");
        response.setHeader("Expires", "-1;");

        try (FileInputStream fis = new FileInputStream(path);
             BufferedInputStream bis = new BufferedInputStream(fis);
             OutputStream out = response.getOutputStream();
             BufferedOutputStream bos = new BufferedOutputStream(out);) {
            int readCount = 0;
            byte[] buffer = new byte[1024];
            while ((readCount = bis.read(buffer)) != -1) {
                bos.write(buffer, 0, readCount);
            }
        } catch (Exception e) {

        }

    }

    // 게시글 삭제
    @Override
    @Transactional(readOnly = false)
    public int deletePostById(int board_id, Integer delHashCode) {
        detailDao.deleteReplyInfo(board_id); // 댓글 모두 삭제
        if (delHashCode != null)
            deleteFileInfo(delHashCode); // 파일 정보 삭제
        return detailDao.deletePostById(board_id); // 글 정보 삭제
    }

    // 파일 정보 삭제
    @Override
    @Transactional(readOnly = false)
    public int deleteFileInfo(Integer delHashCode) {
        FileInfoDto fileInfo = detailDao.getFileInfo(delHashCode);
        String extension = imageFileService.regex(fileInfo.getContentType());
        String path = ImageFileService.filePath + delHashCode + extension;
        File file = new File(path);
        if (file.exists())
            file.delete();
        updateDao.updateDetailHashCode(delHashCode);
        return detailDao.deleteFileInfo(delHashCode);
    }

    // 댓글 등록
    @Override
    public void replyRegister(int user_id, int board_id, String comment) {
        detailDao.replyRegister(user_id, board_id, comment);
    }

    // 댓글 목록
    @Override
    public List<ReplyListInfoDto> replyListInfoById(int board_id) {
        return detailDao.replyListInfoById(board_id);
    }

    // 댓글 삭제
    @Override
    public int deleteReplyById(int reply_id) {
        return detailDao.deleteReplyById(reply_id);
    }

}

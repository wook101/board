package com.example.freeboard.serviceimpl;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.example.freeboard.dao.BoardDao;
import com.example.freeboard.dto.DetailInfoVo;
import com.example.freeboard.dto.FileInfoVo;
import com.example.freeboard.dto.JoinformVo;
import com.example.freeboard.dto.ListInfoVo;
import com.example.freeboard.dto.WriteFormVo;
import com.example.freeboard.service.BoardService;

@Service
public class BoardServiceImpl implements BoardService{
	
	@Autowired
	BoardDao boardDao;
	
	//회원가입 정보 삽입
	@Override
	public int joinFormInsert(JoinformVo joinformvo) {
		return boardDao.joinFormInsert(joinformvo);
	}
	
	//닉네임
	@Override
	public String getNickName(String userID) {
		return boardDao.getNickName(userID);
	}
	
	//로그인 폼 검사
	@Override
	public int loginCheck(String userID, String password) {
		return boardDao.loginCheck(userID, password);
	}
	
	//회원가입 폼 검사
	@Override
	public int joinFormVaildation(String type, String formVal) {
		return boardDao.joinFormVaildation(type, formVal);
	}
	
	//글쓰기 등록
	@Override
	@Transactional(readOnly=false)
	public void writeRegister(MultipartFile file, String nickName, WriteFormVo writeFormVo) {
		Integer hashCode = null;
		if(!file.isEmpty()) {														
			boardDao.fileInfoInsert(file);															//파일 정보 삽입
			hashCode=upload(file);																	//파일 업로드
		}
		boardDao.writeFormInsert(nickName, writeFormVo,0, hashCode);			//글쓰기 정보 삽입
	}
	
	//정규표현식 (파일 확장자)
	public static String regex(String text) {
		String regex = "\\w+[/.]";
		Pattern p = Pattern.compile(regex);
		Matcher m = p.matcher(text);
		if(m.find()) {
			return  "."+text.replace(m.group(),"");
		}
		return null;
	}
	//파일 업로드
	public int upload(MultipartFile file) {
		String extension=regex(file.getContentType());
		Integer hashCode = file.hashCode();
		String path = filePath+hashCode+extension;
		try(FileOutputStream fos = new FileOutputStream(path);
				InputStream is =  file.getInputStream();
				) {		
			 	int readCount = 0;
			 	byte[] buffer = new byte[1024];
			 	while((readCount = is.read(buffer)) != -1){
			 		fos.write(buffer,0,readCount);
			 	}
		}catch(Exception e) {
			System.out.println("file Save Error");
		}
		return hashCode;
	}
	
	//파일 다운로드
	@Override
	@Transactional(readOnly=false)
	public void fileDownload(int id, HttpServletResponse response) {
		Integer hashCode = boardDao.getFileHashCodeById(id);
		FileInfoVo fileInfo = boardDao.getFileInfo(hashCode);
		String extension = regex(fileInfo.getContentType());
		String path = filePath+hashCode+extension;
		
		response.setHeader("Content-Disposition", "attachment; filename=\"" + fileInfo.getOriginalFileName() + "\";");
		response.setHeader("Content-Transfer-Encoding", "binary");
		response.setHeader("Pragma", "no-cache;");
		response.setHeader("Expires", "-1;");

		try (
				FileInputStream fis = new FileInputStream(path); 
				OutputStream out = response.getOutputStream();
			)
		{
			int readCount = 0;
			byte[] buffer = new byte[1024];
			while ((readCount = fis.read(buffer)) != -1) {
				out.write(buffer, 0, readCount);
			}
		} catch (Exception e) {
			throw new RuntimeException("file Save Error");
		}

	}
	
	//게시판 리스트 
	@Override
	public List<ListInfoVo> getListInfo(int start) {
		return boardDao.getListInfo(start, LIMIT);
	}
	
	//게시판 리스트 카운팅
	@Override
	public int getListCount() {
		return boardDao.getListCount();
	}
	
	//게시판 리스트 상세 정보
	@Override
	@Transactional(readOnly=false)
	public DetailInfoVo detailInfoById(int id) {
		boardDao.viewsCountUpdate(id);					//조회수 업데이트
		return boardDao.detailInfoById(id);
	}
	
	//글삭제
	@Override
	@Transactional(readOnly=false)
	public int deletePostById(int id, Integer delHashCode) {
		if(delHashCode!=null)
			deleteFileInfo(delHashCode);						//파일 정보 삭제
		return boardDao.deletePostById(id);				//글 정보 삭제
	}
	
	//파일 정보 삭제
	@Override
	@Transactional(readOnly=false)
	public int deleteFileInfo(Integer delHashCode) {
		FileInfoVo fileInfo = boardDao.getFileInfo(delHashCode);
		String extension = regex(fileInfo.getContentType());
		String path = filePath+delHashCode+extension;
		File file = new File(path);
		if(file.exists()) 
			file.delete();
		boardDao.updateDetailHashCode(delHashCode);
		return boardDao.deleteFileInfo(delHashCode);
	}
	
	//상세 정보 갱신
	@Override
	@Transactional(readOnly=false)
	public void updatePost(int id, WriteFormVo writeFormVo, MultipartFile file, Integer fileHashCode) {
		if(!file.isEmpty()) { 
			boardDao.fileInfoInsert(file);												//파일 정보 삽입
			fileHashCode=upload(file);													//파일 업로드
		}
		boardDao.updateDetailInfo(id, writeFormVo,fileHashCode);		//글정보 갱신
	}
	
	

	
	
}

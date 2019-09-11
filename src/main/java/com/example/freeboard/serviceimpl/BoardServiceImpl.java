package com.example.freeboard.serviceimpl;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.ModelMap;
import org.springframework.web.multipart.MultipartFile;

import com.example.freeboard.dao.BoardDao;
import com.example.freeboard.dto.DetailInfoVo;
import com.example.freeboard.dto.FileInfoVo;
import com.example.freeboard.dto.JoinformVo;
import com.example.freeboard.dto.ListInfoVo;
import com.example.freeboard.dto.ReplyListInfoVo;
import com.example.freeboard.dto.WriteFormVo;
import com.example.freeboard.service.BoardService;

@Service
public class BoardServiceImpl implements BoardService{
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	BoardDao boardDao;
	
	//회원가입 정보 삽입
	@Override
	public int joinFormInsert(JoinformVo joinformvo) {
		return boardDao.joinFormInsert(joinformvo);
	}
	//user테이블의 id
	@Override
	public int getUserTableId(String userID) {
		return boardDao.getUserTableId(userID);
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
	public void writeRegister(int user_id, MultipartFile file, WriteFormVo writeFormVo) {
	
		if(!file.isEmpty()) {
			int board_id; 																			//삽입할 글 id
			Integer hashCode=upload(file);													//파일 업로드 후 hashCode가져옴
			boardDao.writeFormInsert(user_id, writeFormVo,0, hashCode);	   	//글쓰기 정보 삽입
			board_id = boardDao.getBoardLastId();
			boardDao.fileInfoInsert(board_id, file);										//파일 정보 삽입
		}else {
			boardDao.writeFormInsert(user_id, writeFormVo,0, null);				//글쓰기 정보 삽입
		}
		
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
			 BufferedOutputStream bos =new BufferedOutputStream(fos);
			 InputStream is =  file.getInputStream();
			 BufferedInputStream bis =new BufferedInputStream(is);

				) {		
			 	int readCount = 0;
			 	byte[] buffer = new byte[1024];
			 	while((readCount = bis.read(buffer)) != -1){
			 		bos.write(buffer,0,readCount);
			 	}
		}catch(Exception e) {
			e.printStackTrace();
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
				BufferedInputStream bis = new BufferedInputStream(fis);
				OutputStream out = response.getOutputStream();
				BufferedOutputStream bos =new BufferedOutputStream(out);
			)
		{
			int readCount = 0;
			byte[] buffer = new byte[1024];
			while ((readCount = bis.read(buffer)) != -1) {
				bos.write(buffer, 0, readCount);
			}
		} catch (Exception e) {
			logger.debug("파일 다운로드 error");
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
	public DetailInfoVo detailInfoById(int board_id) {
		boardDao.viewsCountUpdate(board_id);					//조회수 업데이트
		return boardDao.detailInfoById( board_id);
	}
	
	//게시글 삭제
	@Override
	@Transactional(readOnly=false)
	public int deletePostById(int board_id, Integer delHashCode) {
		boardDao.deleteReplyInfo(board_id);								//댓글 모두 삭제
		if(delHashCode!=null)
			deleteFileInfo(delHashCode);										//파일 정보 삭제
		return boardDao.deletePostById(board_id);						//글 정보 삭제
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
	public void updatePost(int id, WriteFormVo writeFormVo, int user_id, MultipartFile file, Integer fileHashCode) {
		if(!file.isEmpty()) { 
			boardDao.fileInfoInsert(user_id, file);									//파일 정보 삽입
			fileHashCode=upload(file);													//파일 업로드
		}
		boardDao.updateDetailInfo(id, writeFormVo,fileHashCode);		//글정보 갱신
	}

	//페이징 처리
	@Override
	public void pagination(ModelMap modelMap, int start, int totalListCount, String type, String keyword) { 
																										//리스트의 시작튜플, 총 게시물 수, 유형(board or search)
		int listCount = BoardService.LIMIT;											//한 화면에 보여질 게시물 수									
		int totalPage = totalListCount / listCount;									//총 페이지 수
		int pageCount = 5;																	//한 화면에 보여질 페이지 수
		int page = start / listCount + 1;													//현재 페이지
		int block = page / pageCount + 1;												//현재 블록
		boolean nextPageArrow = true;													//다음 화살표 true/false
		int prePageStart = 0;																	//이전 화살표 start값
		int nextPageStart = 0;																//다음 화살표 start값
		List<Integer> pageList = new ArrayList<>();								//페이지 번호들을 담을 리스트
		if(totalListCount % listCount>0) {
			totalPage++;
		}
		if(page % pageCount ==0) {
			block--;
		}
		prePageStart = (block-1) * pageCount * listCount - listCount;
		nextPageStart = block * pageCount * listCount;
		
		for(int i=block*pageCount-4;i<=block*pageCount;i++) {
			if(i>totalPage) {
				nextPageArrow = false;
				break;
			}
			pageList.add(i);
		}
		modelMap.addAttribute("listCount",listCount);
		modelMap.addAttribute("pageList", pageList);
		modelMap.addAttribute("prePageStart",prePageStart);
		modelMap.addAttribute("nextPageStart",nextPageStart);
		modelMap.addAttribute("nextPageArrow",nextPageArrow);
		modelMap.addAttribute("type", type);
		modelMap.addAttribute("keyword", keyword);
		
	}
	
	
	//검색 리스트
	@Override
	public List<ListInfoVo> getSearchListInfo(String searchKeyword, int start) {
		return boardDao.getSearchListInfo(searchKeyword, start, LIMIT);
	}
	//검색 리스트 수
	@Override
	public int getSearchListCount(String searchKeyword) {
		return boardDao.getSearchListCount(searchKeyword);
	}
	
	//댓글 등록
	@Override
	public void replyRegister(int user_id, int board_id, String comment) {
		boardDao.replyRegister(user_id, board_id, comment);
	}
	//댓글 리스트
	@Override
	public List<ReplyListInfoVo>replyListInfoById(int board_id){
		return boardDao.replyListInfoById(board_id);
	}
	//댓글 삭제
	@Override
	public int deleteReplyById(int reply_id) {
		return boardDao.deleteReplyById(reply_id);
	}
	

	
	
}

package com.example.freeboard.dao;

public class BoardDaoSqls {
	//회원가입 정보 삽입
	public static final String INSERT_JOINFORM = "insert into join_form(userID,nickName,password,email) values(:userID,:nickName,:password,:email)";
	
	
	//userID,nickName, email 디비 존재여부 체크
	public static final String USERID_VALIDATION = "select count(*) from join_form where userID=:formVal";
	public static final String NICKNAME_VALIDATION = "select count(*) from join_form where nickName=:formVal";
	public static final String EMAIL_VALIDATION = "select count(*) from join_form where email=:formVal";
	
	//login 체크
	public static final String COUNT_LOGIN_CHECK = "select count(*) from join_form where userID=:userID and password=:password";
	
	//이미지 파일 정보 삽입
	public static final String INSERT_FILEINFO = "insert into file(originalFileName,contentType, size, hashCode) values(:originalFileName,:contentType, :size, :hashCode)";
	//글쓰기 폼 정보 삽입
	public static final String INSERT_WRITEFORM = "insert into detail(nickName, title, content, create_date, views, file_hashCode) values(:nickName, :title, :content, :create_date,:views, :hashCode)";
	
	//닉네임 얻기
	public static final String SELELCT_NICKNAME_BYID ="select nickName from join_form where userID=:userID";
	
	//게시판 리스트 
 	public static final String SELECT_LIST_INFO = "select id, nickName, title, create_date, views from detail order by id desc limit :start,:limit";
 	//게시판 리스트 카운트
 	public static final String SELECT_LIST_COUNT = "select count(*) from detail";
 	//게시판 리스트 상세정보
 	public static final String SELECT_DETAIL_INFO_BYID = "select * from detail where id=:id";
 	
 	//조회수 증가
 	public static final String UPDATE_VIEWS_COUNT = "update detail set views=views+1 where id=:id";
 	//이미지 파일 정보
 	public static final String SELECT_FILE_INFO = "select * from file where hashCode=:fileHashCode";
 	//이미지 파일 해쉬코드
 	public static final String SELECT_FILE_HASHCODE_BYID = "select file_hashCode from detail where id=:id";
 	
 	//글 삭제
 	public static final String DELETE_POST_BYID="delete from detail where id=:id";
 	//파일 정보 삭제
 	public static final String DELETE_FILE_INFO="delete from file where hashCode=:fileHashCode";
 	//파일 삭제후 해쉬코드 null로 갱신
 	public static final String UPDATE_FILE_HASHCODE="update detail set file_hashCode = null where file_hashCode=:fileHashCode";
 	//글 정보 갱신
 	public static final String UPDATE_DETAIL_INFO= "update detail set title=:title, content=:content, file_hashCode=:hashCode where id=:id";
 	
 	
}

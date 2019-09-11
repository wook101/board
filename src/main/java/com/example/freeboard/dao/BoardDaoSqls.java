package com.example.freeboard.dao;

public class BoardDaoSqls {
	//회원가입 정보 삽입
	public static final String INSERT_JOINFORM = "insert into user(userID,nickName,password,email) values(:userID,:nickName,:password,:email)";
	
	
	//userID,nickName, email 디비 존재여부 체크
	public static final String USERID_VALIDATION = "select count(*) from user where userID=:formVal";
	public static final String NICKNAME_VALIDATION = "select count(*) from user where nickName=:formVal";
	public static final String EMAIL_VALIDATION = "select count(*) from user where email=:formVal";
	
	//login 체크
	public static final String COUNT_LOGIN_CHECK = "select count(*) from user where userID=:userID and password=:password";
	
	//이미지 파일 정보 삽입
	public static final String INSERT_FILEINFO = "insert into file(board_id, originalFileName,contentType, size, hashCode) values(:board_id,:originalFileName,:contentType, :size, :hashCode)";
	//글쓰기 폼 정보 삽입
	public static final String INSERT_WRITEFORM = "insert into board(user_id, title, content, create_date, views, file_hashCode) values(:user_id, :title, :content, :create_date,:views, :hashCode)";
	
	//user테이블의 id
	public static final String SELECT_ID_BY_USERID ="select id from user where userID=:userID";
	//board테이블의 마지막 id
	public static final String SELECT_BOARD_ID ="select id from board order by id desc limit 1";

	//게시판 리스트 
 	public static final String SELECT_LIST_INFO = "select board.id, nickName, title, create_date, views from user, board where user.id=board.user_id order by id desc limit :start,:limit";
 	//게시판 리스트 카운트
 	public static final String SELECT_LIST_COUNT = "select count(*) from board";
 	//게시판 리스트 상세정보
 	public static final String SELECT_DETAIL_INFO_BYID = "select board.id,user_id,nickName,title,content,create_date,views,file_hashCode from user, board where user.id=board.user_id and board.id=:board_id";

 	//조회수 증가
 	public static final String UPDATE_VIEWS_COUNT = "update board set views=views+1 where id=:id";
 	//이미지 파일 정보
 	public static final String SELECT_FILE_INFO = "select * from file where hashCode=:fileHashCode";
 	//이미지 파일 해쉬코드
 	public static final String SELECT_FILE_HASHCODE_BYID = "select file_hashCode from board where id=:id";
	
 	//글 정보 삭제
 	public static final String DELETE_POST_BYID="delete from board where id=:board_id";
 	//댓글 정보 삭제
 	public static final String DELETE_REPLY_BYID="delete from reply where board_id=:board_id";
 	//파일 정보 삭제
 	public static final String DELETE_FILE_INFO="delete from file where hashCode=:fileHashCode";
 	//파일 삭제후 해쉬코드 null로 갱신
 	public static final String UPDATE_FILE_HASHCODE="update board set file_hashCode = null where file_hashCode=:fileHashCode";
 	//글 정보 갱신
 	public static final String UPDATE_DETAIL_INFO= "update board set title=:title, content=:content, file_hashCode=:hashCode where id=:id";
 	
 	//검색 리스트
 	public static final String SELECT_SEARCH_LIST = "select * from board where title like :keyword order by id desc limit :start,:limit" ;
 	//검색 리스트 수
 	public static final String SELECT_SEARCH_LIST_COUNT = "select count(*) from board where title like :keyword";
 	
 	//댓글 등록
 	public static final String INSERT_REPLY_REGISTER="insert into reply(user_id,board_id,comment,create_date)values(:user_id,:board_id,:comment,:create_date)";
 	//댓글 리스트(조인 두번)
 	public static final String SELECT_REPLY_INFO ="select reply.id, reply.user_id, nickName, comment, reply.create_date from user, board, reply where user.id=reply.user_id and board.id=reply.board_id and board_id=:board_id";
 	//댓글 삭제
 	public static final String DELETE_REPLY = "delete from reply where id=:reply_id";
}

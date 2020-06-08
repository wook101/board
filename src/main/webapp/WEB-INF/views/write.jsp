<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="layout" uri="http://kwonnam.pe.kr/jsp/template-inheritance"%>
<layout:extends name="base">
	<layout:put block="css"><link rel="stylesheet" href="<c:url value="/resources/css/write.css"></c:url>"></layout:put>
	<layout:put block="js"><script src="<c:url value="/resources/js/write.js"></c:url>"></script></layout:put>
	<layout:put block="board_active">tab_active</layout:put>
	<layout:put block="contents">
		<div class="jumbotron">
			<h4>글쓰기</h4>
			<form action="/writeUpload" method="post" class="writeForm" enctype="multipart/form-data">
				<div class="form-group">
					<label for="title">제목</label>
					<input type="text" class="form-control" id="title" name="title"> 
				</div>
				<div class="form-group">
					<label for="content">내용</label>
					<textarea class="form-control" id="content" maxlength="399" name="content"></textarea>
					<div style="text-align:right"><em id="textNum">0</em>/400</div>
				</div>
				<div class="form-group">
					<label class="btn btn-dark" for="addFile">파일첨부</label>
					<input type="file" id="addFile" name="file" accept="image/gif, image/jpeg, image/png">
					<div class="thumbnail_area">
						<img id="thumbnailImg" class="img-thumbnail" style="display:none">
					</div>
				</div>
				<div class="register_area">
					<button type="submit" id="registerBtn" class="btn btn-default">등록</button>
				</div>				
			</form>
		</div>
	</layout:put>
</layout:extends>
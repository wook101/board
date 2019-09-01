<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="layout" uri="http://kwonnam.pe.kr/jsp/template-inheritance"%>
<layout:extends name="base">
	<layout:put block="css"><link rel="stylesheet" href="<c:url value="/resources/css/updateForm.css"></c:url>"></layout:put>
	<layout:put block="js"><script src="<c:url value="/resources/js/updateForm.js"></c:url>"></script></layout:put>
	<layout:put block="board_active">tab_active</layout:put>
	<layout:put block="contents">
		<div class="jumbotron"> 
			<h4>수정하기</h4>
			<form action="/update/${detailInfo.id }" method="post" class="writeForm" enctype="multipart/form-data">
				<div class="form-group">
					<label for="title">제목</label>
					<input type="text" class="form-control" id="title" name="title" value="${detailInfo.title }"> 
				</div>
				<div class="form-group">
					<label for="content">내용</label>
					<textarea class="form-control" id="content" maxlength="399" name="content">${detailInfo.content }</textarea>
					<div style="text-align:right"><em id="textNum">${fn:length(detailInfo.content) }</em>/400</div>
				</div>
				<div class="form-group">
					<label class="btn btn-dark" for="addFile">파일첨부</label>
					<input type="file" id="addFile" name="file" accept="image/gif, image/jpeg, image/png">
				</div>
				
				<c:choose>	 
					<c:when test="${null ne detailInfo.file_hashCode}">
						<div class="thumbnail_area">
							<input type="hidden" id="deleteImg" name="hashCode" value="${detailInfo.file_hashCode}">
							<img id="closeBtn" src="<c:url value="/resources/img/close5.png"></c:url>">
							<img id="thumbnailImg" class="img-thumbnail" src="/imgDownload?id=${detailInfo.id }">
						</div>
					</c:when>
					<c:otherwise>
						<div class="thumbnail_area" style="display:none">
							<input type="hidden" name="hashCode" value="null" >
							<img id="closeBtn" src="<c:url value="/resources/img/close5.png"></c:url>">
							<img id="thumbnailImg" class="img-thumbnail" >
						</div>
					</c:otherwise>
				</c:choose>
				<div class="register_area">
					<button type="submit" id="updateBtn" class="btn btn-success">수정</button>
				</div>				
			</form>
		</div>
		
	</layout:put>
</layout:extends>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="layout" uri="http://kwonnam.pe.kr/jsp/template-inheritance"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<layout:extends name="base">
	<layout:put block="js"><script src="<c:url value="resources/js/detail.js"></c:url>"></script></layout:put>
	<layout:put block="css"><link rel="stylesheet" href="<c:url value="resources/css/detail.css"></c:url>"></layout:put>
	<layout:put block="board_active">tab_active</layout:put>
	<layout:put block="contents">
		<div class="jumbotron">
			<div class="title">
				<!--  
				<i class="listID">${detailInfo.id }</i>
				-->
				${detailInfo.title }
			</div>
			<div class="userInfo_area">
				<span><i>작성자 </i><em class="nickName">${detailInfo.nickName }</em></span>
				<span><i>등록일 </i><em class="date">
					<fmt:formatDate value="${detailInfo.create_date }" pattern="yyyy-MM-dd"/></em>
				</span>
				<br class="br">
				<span><i>조회수 </i><em class="views">${detailInfo.views }</em></span>			
			</div>
			<div class="btn_area">
				<c:if test="${sessionScope.nickName eq detailInfo.nickName }">
					<button class="btn btn-secondary btn-sm" id="updateBtn" value="${detailInfo.id }">수정</button>
					<button class="btn btn-secondary btn-sm" id="deleteBtn" value="${detailInfo.id }">삭제</button>
				</c:if>
				<a class="btn btn-dark btn-sm" id="list"  href="board">목록보기</a>
			</div>
			<div class="content_area">
				<c:if test="${null ne detailInfo.file_hashCode}">
					<img id="imgTag" src="imgDownload?id=${detailInfo.id }" data-hashCode="${detailInfo.file_hashCode}">
				</c:if>
				<div class="content_text">${detailInfo.content }</div>
			</div>
		</div>

	</layout:put>
</layout:extends>
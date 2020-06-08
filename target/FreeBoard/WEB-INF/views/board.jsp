<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="layout" uri="http://kwonnam.pe.kr/jsp/template-inheritance"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<layout:extends name="base">
	<layout:put block="js"><script type="text/javascript" src="<c:url value="/resources/js/board.js"></c:url>"></script></layout:put>
	<layout:put block="css"><link rel="stylesheet" href="<c:url value="/resources/css/board.css"></c:url>"></layout:put>
	<layout:put block="title">자유 게시판</layout:put>
	<layout:put block="board_active">tab_active</layout:put>
	<layout:put block="contents">
		<form id="searchForm" method="get" action="/search">
			<div id="search_group" class="form-group">
				<input type="text" name="searchKeyword" id="searchInput">
				<button type="submit" class="btn btn-secondary btn-sm" id="searchBtn">검색</button>
			</div>
		</form>
		<c:if test="${empty boardList}">
			<h3>글이 존재하지 않습니다.</h3>
		</c:if>
		<c:if test="${!empty boardList}">
		<table class="table">
				<thead>
					<tr>
						<th class="th_id">번호</th>
						<th>제목</th>
						<th class="th_writer">글쓴이</th>
						<th class="th_date">등록일</th>
						<th class="th_views">조회수</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${boardList }" var="list">
						<tr onclick="location.href='detail?id=${list.id }'">
							<td class="th_id">${list.id }</td>
							<td>${list.title }</td>
							<td class="th_writer">${list.nickName }</td>
							<td class="th_date"><fmt:formatDate value="${list.create_date }" pattern="yyyy-MM-dd"/></td>
							<td class="th_views">${list.views }</td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</c:if>
			<div class="writeArea">
				<button type="button" id="writeBtn" class="btn btn-default btn-sm">글쓰기</button>
			</div>
		<c:if test="${!empty boardList}">
			<ul class="pagination pagination-sm justify-content-center ">
				
				<c:if test="${preArrowPage >= 1 }">
					<li class="page-item"><a class="page-link" href="${type}?page=${preArrowPage }&searchKeyword=${keyword}">&laquo;</a></li>
				</c:if>
				
				<c:forEach items="${pageList}" var="page" varStatus="status">
					<li class="page-item"><a class="page-link" href="${type}?page=${page}&searchKeyword=${keyword}">${page}</a></li>
				</c:forEach>
				 
				<c:if test="${nextArrowPage <= totalPage}">				
					<li class="page-item"><a class="page-link" href="${type}?page=${nextArrowPage }&searchKeyword=${keyword}">&raquo;</a></li>
				</c:if>
				
			</ul>
		</c:if>
			
			
	</layout:put>
</layout:extends>
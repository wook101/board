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
						<tr onclick="location.href='/detail?id=${list.id }'">
							<td class="th_id">${list.id }</td>
							<td>${list.title }</td>
							<td class="th_writer">${list.nickName }</td>
							<td class="th_date"><fmt:formatDate value="${list.create_date }" pattern="yyyy-MM-dd"/></td>
							<td class="th_views">${list.views }</td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
	
			<div class="writeArea">
				<button type="button" id="writeBtn" class="btn btn-success btn-sm">글쓰기</button>
			</div>
	
			<ul class="pagination pagination-sm justify-content-center ">
				<c:if test="${prePageStart > 0 }">
					<li class="page-item"><a class="page-link" href="/board?start=${prePageStart }">&laquo;</a></li>
				</c:if>
				<c:forEach items="${pageList}" var="pageIndex" varStatus="status">
					<li class="page-item"><a class="page-link" href="/board?start=${(pageIndex-1)*listCount }">${pageIndex }</a></li>
				</c:forEach>
				<c:if test="${nextPageArrow}">				
					<li class="page-item"><a class="page-link" href="/board?start=${nextPageStart }">&raquo;</a></li>
				</c:if>
			</ul>
	</layout:put>
</layout:extends>
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
				<c:if test="${sessionScope.user_id eq detailInfo.user_id }">
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
			
			
			<div id="reply_area">
			
				<div id="replywWtrite">
					<h5>댓글</h5>
					<div style="border:solid 1px #c3c3c3">
						<form id="replyForm" method="post" action="/FreeBoard/replyForm?board_id=${detailInfo.id }">
							<textarea id="write_textarea" name="comment" placeholder="로그인 후 이용가능!!"></textarea>
							<div class="replyBtn_area">
								<button type="submit" id="replyBtn">등록</button>
							</div>
						</form>
					</div>
				</div>
			

				<div id="replyView">
					<ul>
						<c:forEach items="${replyListInfo}" var="list">
							<li>
								<span>
									<i>${list.nickName }</i>
									<em><fmt:formatDate value="${list.create_date }" pattern="yyyy.MM.dd"/></em>
									<c:if test="${sessionScope.user_id eq list.user_id}">
										<img class="reply_delete" data-replyid="${list.id }" src="<c:url value="/resources/img/delete_icon.png"></c:url>"></img>
									</c:if>
								</span>					
								<p>${list.comment }</p>	
							</li>
						</c:forEach>				
					</ul>
				</div>
			</div>
			
		</div>

	</layout:put>
</layout:extends>
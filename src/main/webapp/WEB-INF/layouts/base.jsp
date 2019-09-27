<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="layout" uri="http://kwonnam.pe.kr/jsp/template-inheritance"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
<link rel="stylesheet" href="<c:url value="/resources/css/base.css"></c:url>">
<layout:block name="css"></layout:block>
<title>
	<layout:block name="title"></layout:block>
</title>
</head>
<body>
	<div class="container-fluid">
	<div class="row">
		<nav class="navbar col-md-12">
				<div class="col-md-1"></div>
				<div class="col-md-10">
					<ul class="navbar-nav">
						<li class="nav-item"><img class="home" onclick="location.href='/FreeBoard'" src="<c:url value="/resources/img/home4.png"></c:url>"></li>
						<li class="nav-item <layout:block name="board_active"></layout:block>"><a class="nav-link" href="/FreeBoard/board">자유 게시판</a></li>
						<li class="dropdown_area">
							<div class="dropdown">
								<button type="button" class="btn dropdown-toggle <layout:block name="access_active"></layout:block>" data-toggle="dropdown">
									접속
								</button>
								<div class="dropdown-menu">
									<c:choose>
										<c:when test="${sessionScope.user_id eq null }">
											<a class="dropdown-item" href="/FreeBoard/login">로그인</a>
										</c:when>
										<c:otherwise>
											<a class="dropdown-item" href="/FreeBoard/logout">로그아웃</a>
										</c:otherwise>
									</c:choose>
									<a class="dropdown-item" href="/FreeBoard/join">회원가입</a>
								</div>
							</div>
						</li>
					</ul>
				</div>
				<div class="col-md-1"></div>
		</nav>
	</div>
	<div class="row">
		<div class="col-md-1"></div>
		<div class="col-md-10">
			<div class="container">
				<layout:block name="contents"></layout:block>
			</div>
		</div>
		<div class="col-md-1"></div>
	</div>
</div>
</body>

<script src="https://code.jquery.com/jquery-3.2.1.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>
<layout:block name="js"></layout:block>

</html>
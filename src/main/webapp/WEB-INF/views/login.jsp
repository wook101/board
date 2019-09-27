<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="layout"
	uri="http://kwonnam.pe.kr/jsp/template-inheritance"%>
<layout:extends name="base">
	<layout:put block="css">
		<link rel="stylesheet"
			href="<c:url value="resources/css/login.css"></c:url>">
	</layout:put>
	<layout:put block="js">
		<script src="<c:url value="resources/js/login.js"></c:url>"></script>
	</layout:put>
	<layout:put block="access_active">tab_active</layout:put>
	<layout:put block="contents">
	
		<div class="row">
			<div class="col-md-1"></div>		
			<div class="col-md-10">
				<div class="jumbotron text-center">
					<h2>로그인</h2>
					<form class="loginForm" method="post" action="perform_login">
						<div class="form-group">
							<input type="text" class="form-control" placeholder="아이디"
								name="userID" id="userID">
						</div>
						<div class="form-group">
							<input type="password" class="form-control" placeholder="비밀번호"
								name="password" id="password">
							<div class="form_feedback"></div>
						</div>
						<button id="loginBtn" type="submit"
							class="btn btn-default form-control">로그인</button>
					</form>
				</div>
			</div>
			<div class="col-md-1"></div>
		</div>
		
	</layout:put>
</layout:extends>
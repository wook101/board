<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="layout" uri="http://kwonnam.pe.kr/jsp/template-inheritance"%>
<layout:extends name="base">
	<layout:put block="css"><link rel="stylesheet" href="<c:url value="/resources/css/join.css"></c:url>"></layout:put>
	<layout:put block="js"><script src="<c:url value="/resources/js/join.js"></c:url>"></script></layout:put>
	<layout:put block="access_active">tab_active</layout:put>
	<layout:put block="contents">
		<div class="row">
			<div class="col-md-1"></div>
			<div class="col-md-10">
				<div class="jumbotron">
					<h2>회원가입</h2>
					<form class="joinForm" method="post" action="joinForm">
						<div class="form-gruop">
							<label for="userID">아이디 : </label>
							<input type="text" class="form-control" id="userID" name="userID">
							<div class="userID_feedback"></div>
						</div>
						<div class="form-gruop">
							<label for="nickName">닉네임 : </label>
							<input type="text" class="form-control" id="nickName" name="nickName">
							<div class="nickName_feedback"></div>
						</div>
						<div class="form-gruop">
							<label for="password">비밀번호 : </label>
							<input type="password" class="form-control" id="password" name="password">
						</div>
						<div class="form-gruop">
							<label for="passwordCheck">비밀번호 확인: </label>
							<input type="password" class="form-control" id="passwordCheck">
							<div class="passwordCheck_feedback"></div>
						</div>
						<div class="form-gruop">
							<label for="email">이메일 : </label>
							<input type="email" class="form-control" id="email" name="email">
							<div class="email_feedback"></div>
						</div>
							<button id="joinBtn" type="submit" class="btn btn-success form-control">가입하기</button>						
					</form>
				</div>
			</div>
			<div class="col-md-1"></div>						
		</div>
		
		
		
	</layout:put>
</layout:extends>
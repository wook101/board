<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="layout" uri="http://kwonnam.pe.kr/jsp/template-inheritance"%>
<layout:extends name="base">
	<layout:put block="js"><script type="text/javascript" src="<c:url value="/resources/js/main.js"></c:url>"></script></layout:put>
	<layout:put block="css"><link rel="stylesheet" href="<c:url value="/resources/css/main.css"></c:url>"></layout:put>
	<layout:put block="title">메인</layout:put>
	<layout:put block="contents">
		<div class="main_area">SpringMVC 연습 <br>게시판</div>
	</layout:put>
</layout:extends>
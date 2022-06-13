<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
메인화면입니다 ...<br>

<c:if test="${ empty user }">
<a href="../member/insert_form.do">회원가입</a>
<a href="../member/login_form.do">로그인</a>
</c:if>


<c:if test="${ not empty user }">
${ user.m_nickname }님 환영합니다.
<input type="button" value="로그아웃"
				   onclick="location.href='../member/logout.do';">

</c:if>

</body>
</html>
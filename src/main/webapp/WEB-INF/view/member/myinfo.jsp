<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>내정보</title>
</head>
<body>
	<c:forEach items="${list}" var="list">
		<span class="email">이메일: ${list.email}</span> <br />
		<span class="auth">권한: ${list.auth}</span> <br />
		<span class="name">이름: ${list.name}</span> <br />
		<span class="joinDate">가입일 : ${list.joinDate}</span> <br />
		<span class="loginDate">최근 로그인: ${list.loginDate}</span> <br />
	</c:forEach>
	<button id="updateButton">
		<a href="<c:url value='updateMember'/>">회원정보 수정하기</a>
	</button>
	<script>
		const update = document.getElementById('updateButton');
		update.addEventListener('click',function updatefunc(){
			
		})
	</script>
</body>
</html>
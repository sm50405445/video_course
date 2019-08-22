<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
    <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>로그인</title>
</head>
<body>

<h1>Custom Login Page</h1>
<h2><c:out value="${msg}"/></h2>

<form action="<c:url value='/login'/>" method="post">
<label for="email">email</label>
<input type="text" name="email"/>
<label for="password">password</label>
<input type="password" name="password"/>
<input type="submit" value="제출" />
<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
</form>

<a href="${google_url}">구글 로그인</a>
<a href="${naver_url}">네이버로그인</a>


</body>
</html>
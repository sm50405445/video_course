<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원가입</title>
</head>
<body>
<form:form method="post" commandName="memberJoin">
	<label for="email">email</label>:
	<form:input path="email" />
	<form:errors path="email" /> <br />
	
	<label for="name">name</label>:
	<form:input path="name"/>
	<form:errors path="name"/> <br />
	
	<label for="mId">ID</label>
	<form:input path="mId"/>
	<form:errors path="mId"/>
	
	<label for="password">암호입력</label>:
	<form:password path="password"/>
	<form:errors path="password"/> <br />
	
	<label for="confirmPassword">암호확인</label>:
	<form:password path="confirmPassword"/>
	<form:errors path="confirmPassword"/> <br />
	
	<input type="submit" value="회원가입" />
</form:form>
	<a href="${google_url}">구글 회원가입</a>
</body>
</html>
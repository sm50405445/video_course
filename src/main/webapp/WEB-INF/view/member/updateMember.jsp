<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원수정</title>
</head>
<body>
	<c:import url="/common/header" />
	<form:form commandName="updateMember">
		<label for="name">이름:</label> <br />
		<form:input path="name"/>
		<form:errors path="name"/>
		<label for="password">비밀번호</label> <br />
		<form:password path="password"/>
		<form:errors path="password" />
		<label for="confirmPassword">비밀번호 확인</label>
		<form:password path="confirmPassword"/>
		<form:errors path="confirmPassword" />
		<input type="submit" value="정보 수정하기"/>
	</form:form>
</body>

</html>
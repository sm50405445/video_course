<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>비디오 업로드</title>
</head>
<body>
	<form:form method="post" action="videoUpload" enctype="multipart/form-data" modelAttribute="formUpload">
		<input type="file" name="files" multiple="multiple" required=true accept="video/*" />
		<form:errors path="files" cssStyle="color: red"/>
		<input type="text" name="title" multiple="multiple" />
		<form:errors path="title" cssStyle="color: red"/>
		<input type="submit" value="등록" />
	</form:form>

</body>
</html>
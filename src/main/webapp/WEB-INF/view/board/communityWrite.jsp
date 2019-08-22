<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>글쓰기</title>
	<link href="http://netdna.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.css" rel="stylesheet"> 
	<script src="http://cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.js"></script> 
	<script src="http://netdna.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.js"></script> 
	<link href="https://cdnjs.cloudflare.com/ajax/libs/summernote/0.8.11/summernote-bs4.css" rel="stylesheet">
    <script src="https://cdnjs.cloudflare.com/ajax/libs/summernote/0.8.11/summernote-bs4.js"></script>
</head>
<body>
	<script>
	$(document).ready(function() {
		  $('#summernote').summernote({
		    lang: 'ko-KR', // default: 'en-US'
		    height: 500,
		    width: 800
		  });
		});
	</script>

		<span class="main__header-title">자유게시판입니다. 앞으로 원하는 강의 및 여러가지 요청사항을 적어주시기 바랍니다.</span>
	
	
	<form:form method="post" commandName="boardContent">
				
		<label for="btitle">제목</label>
		<form:input path="btitle"/>
		<form:errors path="btitle"/> <br />

		<label for="bcontent">내용</label>
		<form:textarea path="bcontent" id="summernote"/>
		<form:errors path="bcontent" /> <br />

  		<input type="submit" value="글 등록" />
  		
	</form:form>

</body>
</html>
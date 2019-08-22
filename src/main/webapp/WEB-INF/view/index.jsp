<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>최고의 온라인 사이트입니다.</title>
<link rel="stylesheet" href="css/style.css" media="all" />
</head>
<body>
	<c:import url="common/header.jsp" />
	
	<main class="main__courses">
		<c:forEach items="${lists}" var="lists">
			<div class="col-xs-12 col-sm-6 col-md-4 main__course-item">
				<div class="main__course-group">
					<div class="main__course-image">
					</div>
					<span class="course__group-title">
					${list.videoGroup}
					</span>
				</div>
			<div class="main__course-title">
				<a href="videoDetail/${lists.title}">${lists.title}</a>
			</div>
			<div class="main__course-teacher">
				${lists.MId}
			</div>
			</div>
		</c:forEach>	
	</main>
	
	<c:import url="common/footer.jsp" />
</body>
</html>
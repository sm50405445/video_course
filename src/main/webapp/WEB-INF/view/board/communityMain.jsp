<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
    <%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>자유게시판</title>
<link rel="stylesheet" href="css/style.css" media="all" />

</head>
<body>
	<section class="board__list">
			<div class="board__list-top">
				<span class="list-top__totalContent">
					총 ${AllCount}개의 글
				</span>
				<a href="video_course-project/board/communityWrite">글쓰기</a>
			</div>
			<table class="board__content">
				
				<thead class="board__content-head">
				</thead>
				<tbody class="board__content-list">
				<c:forEach items="${list}" var="list">
				<tr>
						<td>${list.bno}</td>
						<td>${list.MId}</td>
						<td><a href="<c:url value='/board/view/${list.bno}'/>">${list.btitle }</a></td>
						<td>${list.bcontent }</td>
						<td>${list.blikeCount }</td>
						<td>${list.bcommentCount}</td>
						<td>${list.bdate}</td>
						<td>${list.bhit}</td>
				</tr>
				</c:forEach>
				</tbody>
				
			</table>
			
			<div class="board__list-page">
				<ul class="page_list">
					<c:if test="${page.startPage>page.pageBlock}">
						<li class="board__carrot">
						<a href="<c:url value='/board/communityMain/${page.startPage-page.pageBlock}'/>">이전</a>
						</li>
					</c:if>
				
				<c:forEach var="i" begin="${page.startPage}" end="${page.endPage}" step="1">
					<c:if test="${page.pageNum eq i}">
						<li class="page__active">
						<a href="<c:url value='/board/communityMain/${i}'/>">${i}</a>
						</li>		
					</c:if>
					<c:if test="${page.pageNum ne i}">
						<li class="page__notActive">
						<a href="<c:url value='/board/communityMain/${i}'/>">${i}</a>
						</li>
					</c:if>
				</c:forEach>
					
				<c:if test="${page.endPage<page.pageCount}">	
					<li class="board__carrot">
					<a href="<c:url value='/board/communityMain/${page.startPage+page.pageBlock}'/>">다음</a>
					</li>
				</c:if>
				</ul>
			</div>
	</section>
		
</body>
</html>
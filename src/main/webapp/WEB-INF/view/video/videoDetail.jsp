<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Video Detail</title>
<link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.8.2/css/all.css" integrity="sha384-oS3vJWv+0UjzBfQzYUhtDYW+Pj2yciDJxpsK1OYPAYjqT085Qq/1cq5FLXAZQ7Ay" crossorigin="anonymous">
	<script src="http://cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.js"></script> 
</head>
<body>
	<c:forEach items="${list}" var="list">
	<main>
		<div class="video-detail__container">
		
			<div class="videoPlayer" id="jsVideoPlayer">
				<video controlsList="nodownload" src="/video/${url}" width="480" height="320" controls="true">
				</video>
			</div>
			<div class="videoPlayer__controls">
				<div class="videoPlayer__column"></div>
				<div class="videoPlayer__column"></div>
				<div class="videoPlayer__column"></div>
				<div class="videoPlayer__column"></div>	
			</div>
			<div class="video__info">
				<a href="">Edit Video</a>
			</div>
			
		</div>
	</main>
	
	<section class="video__comment">
		<span class="video__comment-title">
			Comment
		</span> <br />
		<table>
		<tbody id="commentList">
			
		</tbody>
		</table>
			<input type="hidden" name="vno" value="${list.vno}" id="vno"/>
			<input type="text" name="cContent" id="cContent"/>
			<span class="content__error"></span>
			<!-- 나중에 userSession으로 바꾸기 -->
			<input type="hidden" name="mId" value="${list.MId}" id="mId"/>
			<input type="button" value="답글달기" id="submitComment"/>
			
	</section>
	</c:forEach>
	<script src="<c:url value='/resources/js/reply.js'/>"></script>

	<script>
	var commentListTable = $('#commentList')
	
	var vno = $('#vno').val();
	
	replyService.getlist(vno,function(list){
		var str="";
		for(var i = 0 ; i<list.length ; i++){
			str+="<tr><td>"
			str+=list[i].mid
			str+="</td><td>"
			str+=list[i].ccontent
			str+="</td><td>"
			str+=replyService.displayTime(list[i].idate)
			str+="</td></tr>"
		}
		commentListTable.html(str);
	})
	
		$('#submitComment').click(function(){
			var commentInfo = {
				'vno' : $('#vno').val(),
				'cContent' : $('#cContent').val(),
				'mId' : $('#mId').val()
			}
			replyService.add(commentInfo,
			function(result){
			if(result==='0'){
				$('.content__error').html('해당 항목은 필수입니다. 글자제한 1000') 
				return false;
			}else{
				commentListTable.html('')
				replyService.getlist(vno,function(list){
					var str="";
					for(var i = 0 ; i<list.length ; i++){
						str+="<tr><td>"
						str+=list[i].mid
						str+="</td><td>"
						str+=list[i].ccontent
						str+="</td><td>"
						str+=replyService.displayTime(list[i].idate)
						str+="</td></tr>"
					}
					commentListTable.html(str);
				})
				
			}
		})
	})
	
	</script>
</body>
</html>
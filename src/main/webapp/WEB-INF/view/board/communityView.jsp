<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>자유게시판</title>
<script type="text/javascript"
	src="https://code.jquery.com/jquery-3.3.1.min.js"
	integrity="sha256-FgpCb/KJQlLNfOu91ta32o/NMZxltwRo8QtmkMRdAu8="
	crossorigin="anonymous"></script>
</head>
<body>
	<c:forEach items="${list}" var="list">
	<section class="board__list">
		<div class="board__list-top">
			<span class="list-top__totalContent"> view 페이지 </span>
			<button class="list-top__writebtn">글쓰기</button>
		</div>
		<table class="board__content">
		
			<thead class="board__content-head">
			</thead>
			<tbody class="board__content-list">
				
					<tr>
						<td>${list.bno}
						<input type="hidden" class="list__bno" value="${list.bno}" />
						</td>
						<td class="listMid">${list.MId}</td>
						<td>${list.btitle }</td>
						<td>${list.bcontent }</td>
						<td>${list.blikeCount }</td>
						<td>${list.bdate}</td>
						<td>${list.bhit}</td>
					</tr>
				
			</tbody>
		</table>
		<button class="btn_like">좋아요!</button>
	</section>
	
	<section class="community__comment">
		<span class="community__comment-title">
			Comment
		</span> <br />
		<table>
		<tbody id="commentList">
			
		</tbody>
		</table>
			<input type="hidden" name="bno" value="${list.bno}" id="bno"/>
			<input type="text" name="cContent" id="cContent"/>
			<span class="content__error"></span>
			<!-- 나중에 userSession으로 바꾸기 -->
			<input type="hidden" name="mId" value="${USER_SESSION}" id="mId"/>
			<input type="button" value="답글달기" id="submitComment"/>
			
	</section>
	</c:forEach>
	
	<script src="<c:url value='/resources/js/boardLike.js'/>"></script>
	<script src="<c:url value='/resources/js/boardReply.js'/>"></script>
	
	<script>
	$('.btn_like').click(function() {
		var bno = $('.list__bno').val();
		console.log(bno)
		var mId = '${USER_SESSION}';
		var listInform = {
				bno : bno,
				mId : mId
			}
		likeService.like(listInform,
				function(result){
				console.log(result)
		})
	});
	
	var commentListTable = $('#commentList')
	
	var bno = $('#bno').val();
	
	replyService.getlist(bno,function(list){
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
				'bno' : $('#bno').val(),
				'cContent' : $('#cContent').val(),
				'mId' : $('#mId').val()
			}
			console.log(commentInfo)
			replyService.add(commentInfo,
			function(result){
			if(result==='0'){
				$('.content__error').html('해당 항목은 필수입니다. 글자제한 1000') 
				return false;
			}else{
				commentListTable.html('')
				replyService.getlist(bno,function(list){
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
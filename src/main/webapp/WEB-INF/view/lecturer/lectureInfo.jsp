<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>강사전용 강의 수정 page</title>
<script src="http://cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.js"></script>
</head>
<body>
	<c:import url="../common/header.jsp" />
	<h1 class="errorMsg"><c:out value="${msg}"/></h1>
	<span class="title">강의 수정 페이지</span>
	<div class="lecture__info">
		<form action="<c:url value='lecturer/changeInform'/>">
		<c:forEach items="${lectureInfo}" var="lectureInfo">
			<c:if test="${lectureInfo.enabled eq 1}">
			<div class="lecture__info-item">
				<input type="checkbox" name="contentCheckbox" id="" />
				<span class="info-item__title">
					${lectureInfo.MId}
				</span>
				<span class="info-item__group">
					강의 그룹: ${lectureInfo.videoGroup}
				</span>
				<span class="info-item__filename">
					파일 이름: ${lectureInfo.fileName}
				</span>
				<span class="info-item__update">
					업데이트 : ${lectureInfo.udate}
				</span>
				<input type="hidden" value="${lectureInfo.vno}" class="videoNumber"/>
				<input type="hidden" value="${lectureInfo.videoGroup}" class="videoGroup" />
			</div>
			</c:if>
		</c:forEach>
		<span class="notAllowed">미승인 동영상</span>
		<c:forEach items="${lectureInfo}" var="lectureInfo">
			<c:if test="${lectureInfo.enabled eq 0}" var="testval">
				<div class="lecture__info-item">
				<input type="checkbox" name="contentCheckbox" id="" />
				<span class="info-item__title">
					${lectureInfo.MId}
				</span>
				<span class="info-item__group">
					강의 그룹: ${lectureInfo.videoGroup}
				</span>
				<span class="info-item__filename">
					파일 이름: ${lectureInfo.fileName}
				</span>
				<span class="info-item__update">
					업데이트 : ${lectureInfo.udate}
				</span>
				<input type="hidden" value="${lectureInfo.vno}" class="videoNumber"/>
				<input type="hidden" value="${lectureInfo.videoGroup}" class="videoGroup" />
			</div>
			</c:if>
		</c:forEach>
		</form>
	</div>
	<br />
	<select name="lectureList" id="selectBox">
	<option value="">선택</option>
		<c:forEach items="${lectureGroup}" var="lectureGroup">
			<option value="${lectureGroup}">${lectureGroup}</option>
		</c:forEach>
	</select>
	<button class="btn btn-primary" id="changeBtn">변경하기</button>
	<button class="btn btn-primary" id="selectBtn">선택</button>
	<br />
	<span class="group__insert-title">그룹 추가</span>
	<form action="<c:url value='/lecturer/insertGroup'/>" method="POST" id="insertGroup">
	<input type="text" name="videoGroup" id="insertGroupName"/>
	<form:errors path="videoGroup" />
	<input type="button" value="등록" id="insertGroupBtn"/>
	</form>
	
	<script>
		$('#insertGroupBtn').click(function(){
			const groupName = $('#insertGroupName');
			if(groupName==null || groupName==""){
				alert('그룹 이름은 필수입니다.');
				return false;
			}
			
			$('#insertGroup').submit();
		})
	</script>
	
	<script src="<c:url value='/resources/js/lectureGroupUpdate.js'/>">
	</script>
</body>
</html>
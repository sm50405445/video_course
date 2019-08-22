<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
    <%@taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
    
	<link href="http://netdna.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.css" rel="stylesheet"> 
	<script src="http://cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.js"></script> 
	<script src="http://netdna.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.js"></script>
	<link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.8.2/css/all.css" integrity="sha384-oS3vJWv+0UjzBfQzYUhtDYW+Pj2yciDJxpsK1OYPAYjqT085Qq/1cq5FLXAZQ7Ay" crossorigin="anonymous">
	<link rel="stylesheet" href="<c:url value='/resources/css/style.css'/>" rel="stylesheet" />
	
	<header class="header">
		<c:choose>
			<c:when test="${USER_SESSION eq null}">
				<nav class="nav navbar-default navbar-fixed-top" role="navigation">
					<div class="navbar-header">
							<button class="navbar-toggle" data-toggle="collapse"
								data-target="#menu1">
								<span class="sr-only">Toggle-navigation</span> <span class="icon-bar"></span>
								<span class="icon-bar"></span> <span class="icon-bar"></span>
							</button>
							<a href="<c:url value='/'/>" class="navbar-brand">LSM COURSE</a>
					</div>
					<div class="collapse navbar-collapse" id="menu1">
							<ul class="nav navbar-nav">
								<li>${USER_SESSION}</li>
								<li><a href="#">All Course</a></li>
								<li><a href="board/communityMain/">Community</a></li>
								<li><a href="login">login</a></li>
								<li><a href="joinMember">sign-up</a></li>
							</ul>
					</div>
					</nav>
			</c:when>
		<c:otherwise>
		<c:set value="${USER_AUTH}" var="AUTH" />
		<c:if test="${AUTH == '[Role_lecturer]'}" var="testval">
				<nav class="nav navbar-default navbar-fixed-top" role="navigation">
				<div class="navbar-header">
						<button class="navbar-toggle" data-toggle="collapse"
							data-target="#menu2">
							<span class="sr-only">Toggle-navigation</span> <span class="icon-bar"></span>
							<span class="icon-bar"></span> <span class="icon-bar"></span>
						</button>
						<a href="<c:url value='/'/>" class="navbar-brand">LSM COURSE</a>
				</div>
				<div class="collapse navbar-collapse" id="menu2">
						<ul class="nav navbar-nav">
							<li><a href="lecturer/inform">Mylecture</a></li>
							<li><a href="<c:url value='/videoUpload'/>">video Upload</a></li>
							<li><a href="board/communityMain/">Community</a></li>
							<li><a href="myInfo">MyInfo</a></li>
						</ul>
						
						<ul class="nav navbar-nav navbar-right">
							<li>
								<button type="button" class="btn btn-primary btn-lg" data-toggle="modal" data-target="#logout">
								logout
								</button>
							</li>
						</ul>
				</div>
			</nav>
		</c:if>
		<c:if test="${AUTH == '[Role_Member]'}">
			<nav class="nav navbar-default navbar-fixed-top" role="navigation">
				<div class="navbar-header">
						<button class="navbar-toggle" data-toggle="collapse"
							data-target="#menu2">
							<span class="sr-only">Toggle-navigation</span> <span class="icon-bar"></span>
							<span class="icon-bar"></span> <span class="icon-bar"></span>
						</button>
						<a href="<c:url value='/'/>" class="navbar-brand">LSM COURSE</a>
				</div>
				<div class="collapse navbar-collapse" id="menu2">
						<ul class="nav navbar-nav">
							<li><a href="#">All Course</a></li>
							<li><a href="">MyCourse</a></li>
							<li><a href="board/communityMain/">Community</a></li>
							<li><a href="myInfo">MyInfo</a></li>
						</ul>
						
						<ul class="nav navbar-nav navbar-right">
							<li>
								<button type="button" class="btn btn-primary btn-lg" data-toggle="modal" data-target="#logout">
								logout
								</button>
							</li>
						</ul>
				</div>
			</nav>
		</c:if>
		</c:otherwise>
	</c:choose>
		
		<!-- logout -->
		<div class="modal fade" id="logout" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
		  <div class="modal-dialog">
		    <div class="modal-content">
		      <div class="modal-header">
		        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
		        <h4 class="modal-title" id="myModalLabel">logout</h4>
		      </div>
		      <div class="modal-body">
		        <form action="<c:url value='/logout'/>" method="post" id="logoutForm">
		        	<div class="form-group">
					<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
					<label for="logoutBtn">로그아웃 합니까?</label>
					<button id="logoutBtn" class="btn btn-default">로그아웃 하기</button>
					</div>
				</form>
		      </div>
		      <div class="modal-footer">
		        <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
		      </div>
		    </div>
		  </div>
		</div>
		
	</header>
	
	<script>
		$('#logoutBtn').on('click',function(e){
			e.preventDefault();
			$('#logoutForm').submit();
		})
	</script>
	

	<c:if test="${param.logout != null}">
		<script>
			$(document).ready(function(){
				alert('로그아웃 하였습니다.')
				location.href="/videocourse"
			})
		</script>
	</c:if>
		
	
	
	

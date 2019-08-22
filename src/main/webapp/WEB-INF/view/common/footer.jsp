<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
    <footer class="footer">
    	<div class="footer__chatRoom">
    		<div class="footer__chatRoom-head">
    			<span class="chatRoom-head__title"></span>
    			<span class="chatRoom-head__title">
    			Messanger
    			</span>
    			<span class="chatRoom-head__title"></span>
    			<span class="chatRoom-head__title">
    			<i class="fas fa-times exitIcon"></i>
    			</span>
    		</div>
    		
    		<div class="footer__chatRoom-body">
    		<!-- 메인 화면 -->
    			<div class="chatRoom__search">
    				<input type="text" />
    				<i class="fas fa-search"></i>
    			</div>
    			<div class="chatRoom__friends-list">
    				<div class="friends__section-title">
    					Friends
    				</div>
    				<div class="friends__section-inform">
	    				<img src="https://avatarfiles.alphacoders.com/104/104546.jpg" alt="" />
	    				<a href="#">profile</a>
    				</div>
    			</div>
    		</div>
    		<!-- 채팅하기 -->
    			
    		
    		<div class="footer__chatRoom-footerWrapper">
    		<div class="footer__chatRoom-footer">
    		<nav class="tab-bar">
    			<a href=""><i class="fas fa-users"></i></a>
    			<a href=""><i class="fas fa-comment-dots"></i></a>
    			<a href=""><i class="fas fa-search"></i></a>
    		</nav>
			</div>
			</div>
    	</div>
	    <i class="fas fa-comment-dots chatIcon"></i>

    </footer>
    
  <script>
  	const chatIcon = $('.chatIcon');
  	const chatRoom = $('.footer__chatRoom');
  	const exitIcon = $('.exitIcon');
  	
  	chatIcon.click(function(){
  		if(chatIcon.css("opacity","1")){
  			chatIcon.css("opacity","0")
  			chatIcon.css("z-index","-1")
  			chatRoom.css("opacity","1")
  			chatRoom.css("z-index","3")
  		}
  	})
  	
  	exitIcon.click(function(){
  		chatRoom.css("opacity","0")
  		chatRoom.css("z-index","-1")
  		chatIcon.css("opacity","1")
  		chatIcon.css("z-index","3")
  	})
  </script> 
package com.portfolio.videocourse.security;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import lombok.extern.log4j.Log4j;

@Log4j
@Component("AccessDenied")
public class CustomAccessDeniedHandler implements AccessDeniedHandler{

	@Override
	public void handle(HttpServletRequest request, HttpServletResponse response,
			AccessDeniedException accessDeniedException) throws IOException, ServletException {
		
		System.out.println("디나이 핸들러 입성");
		
		log.error("Access Denied Handler");
		
		log.error("Redirect..");
		
		String errorMessage = "권한이 없습니다. 관리자와 상의하세요";
		
		request.setAttribute("NoAuth_msg",errorMessage);
		
		response.sendRedirect("/video_course-project/accessDenied");
		
	}
}


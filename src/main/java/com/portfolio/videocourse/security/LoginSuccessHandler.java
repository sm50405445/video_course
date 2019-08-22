package com.portfolio.videocourse.security;

import java.io.IOException;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.WebAttributes;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import com.portfolio.videocourse.service.member.MemberService;

import lombok.extern.log4j.Log4j;

@Component("authSuccess")
@Log4j
public class LoginSuccessHandler implements AuthenticationSuccessHandler{
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());

	private RedirectStrategy redirectStratagy = new DefaultRedirectStrategy();
	
	@Autowired
	public MemberService memberService;
	
		@Override
		public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
				Authentication authentication) throws IOException, ServletException {
			System.out.println("로그인 성공");
			
			clearAuthenticationAttributes(request);
	
			System.out.println(memberService);
			System.out.println(authentication.getName());
			String MemberId = memberService.checkId(authentication.getName());
			
			
			HttpSession session = request.getSession();
			
			session.setAttribute("USER_SESSION", MemberId);
			session.setAttribute("USER_AUTH",authentication.getAuthorities());

			String targetUrl = determineTargetUrl(authentication);
			redirectStratagy.sendRedirect(request, response, targetUrl);
			memberService.visitLog(authentication.getName());
		}

	private void clearAuthenticationAttributes(HttpServletRequest request) {
		HttpSession session = request.getSession(false);
		
		if(session==null) {
			return;
		}
		
		session.removeAttribute(WebAttributes.AUTHENTICATION_EXCEPTION);
	}

	protected String determineTargetUrl(Authentication authentication) {
		Set<String> auth = AuthorityUtils.authorityListToSet(authentication.getAuthorities());
		System.out.println("auth : "+auth);
		if(auth.contains("Role_Admin")) {
			return "/";
		}else if(auth.contains("Role_Member")){
			return "/";
		}else if(auth.contains("Role_lecturer")){
			return "/";
		}else {
			throw new IllegalStateException();
		}
	}
	
	public void setRedirectStrategy(RedirectStrategy redirectStrategy) {
        this.redirectStratagy = redirectStrategy;
    }
    protected RedirectStrategy getRedirectStrategy() {
        return redirectStratagy;
    }
}

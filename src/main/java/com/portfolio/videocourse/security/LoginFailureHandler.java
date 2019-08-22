package com.portfolio.videocourse.security;

import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import com.portfolio.videocourse.service.member.MemberService;

import lombok.extern.log4j.Log4j;

@Log4j
@Component("authFailure")
public class LoginFailureHandler implements AuthenticationFailureHandler {

	private Pattern pattern;
	private Matcher matcher;

	private static final String EMAIL_PATTERN = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
			+ "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

	@Autowired
	public MemberService memberService;

	@Autowired
	public PasswordEncoder passwordEncoder;

	@Override
	public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException exception) throws IOException, ServletException {
		System.out.println("로그인 실패");
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		log.info(password);
		log.info(email);

		String msg = "";

		String idChk = memberService.checkId(email);

		if (email == null || email.trim().equals("") || email.length() == 0) {
			request.setAttribute("msg", msg);
			request.getRequestDispatcher("/login").forward(request, response);
			// response.sendRedirect(request.getContextPath()+"/login");
			// request.getRequestDispatcher(request.getContextPath()+"/login").forward(request,
			// response)
			return;
		}
		if (idChk == null) {
			msg = "해당 이메일이 없습니다";
			request.setAttribute("msg", msg);
			request.getRequestDispatcher("/login").forward(request, response);
			return;
		}
		if (email != null && email.trim().length() > 0) {
			pattern = Pattern.compile(EMAIL_PATTERN);
			matcher = pattern.matcher(email.toString());
			if (!matcher.matches()) {
				msg = "올바른 이메일 형식의 아이디를 적어주세요";
				request.setAttribute("msg", msg);
				request.getRequestDispatcher("/login").forward(request, response);
				return;
			}
			
		}

		if (password == null || password.trim().equals("") || password.length() == 0) {
			msg = "비밀번호를 입력하세요";
			request.setAttribute("msg", msg);
			request.getRequestDispatcher("/login").forward(request, response);
			return;
		} else {
			msg = "비밀번호가 틀렸습니다.";
			request.setAttribute("msg", msg);
			request.getRequestDispatcher("/login").forward(request, response);
			return;
		}

	}
}

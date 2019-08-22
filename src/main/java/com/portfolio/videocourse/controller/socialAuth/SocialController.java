package com.portfolio.videocourse.controller.socialAuth;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.portfolio.videocourse.service.member.MemberService;

@Controller
public class SocialController {

	@Autowired
	private MemberService memberService;
	

	@RequestMapping(value = "/auth2Callback", method = { RequestMethod.GET, RequestMethod.POST })
	public String googleSignInCallBack(@RequestParam("code") String code, HttpSession session, Model model)
			throws Exception {

		System.out.println("code = " + code);
		memberService.getGoogleLogin(code, session);
		return "redirect:/";
	}

	@RequestMapping(value = "/auth/naverCallback", method = { RequestMethod.GET,RequestMethod.POST })
	public String NaverCallback(Model model, String code,String state,HttpSession session) throws Exception {
		
		String accessToken = memberService.getAccessToken(code, state);
		System.out.println(accessToken);
		memberService.naverSigninCallback(accessToken,session);
		
		
		return "redirect:/";
	}

}

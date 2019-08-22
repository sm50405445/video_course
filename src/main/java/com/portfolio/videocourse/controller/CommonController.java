package com.portfolio.videocourse.controller;

import java.math.BigInteger;
import java.net.URLEncoder;
import java.security.SecureRandom;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.social.google.connect.GoogleConnectionFactory;
import org.springframework.social.oauth2.GrantType;
import org.springframework.social.oauth2.OAuth2Operations;
import org.springframework.social.oauth2.OAuth2Parameters;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.portfolio.videocourse.common.NaverAPI;
import com.portfolio.videocourse.service.video.VideoService;
import com.portfolio.videocourse.vo.auth.LoginAuthVO;
import com.portfolio.videocourse.vo.video.VideoVO;

import lombok.extern.log4j.Log4j;

@Controller
@Log4j
public class CommonController {
	
	public static final String ACCESS_DENIED = "/security/accessDenied";
	public static final String LOGOUT_PAGE = "/member/logout";
	public static final String USER_LOGIN = "/member/login";

	@Autowired
	private VideoService videoService;
	
	@Autowired
	private GoogleConnectionFactory googleConnectionFactory;
	
	
	@Autowired
	@Qualifier("naverLoginValue") 
	private LoginAuthVO naverLogin;
	 
	
	@Autowired
	private OAuth2Parameters oAuth2Parameters;
	private OAuth2Operations oauthOperations;
	
	@RequestMapping("/")
	public String main(Model model) {
		
		List<VideoVO> lists = videoService.lists();
		for(int i = 0 ; i < lists.size() ; i++) {
			lists.get(i).getMId();
		}
		model.addAttribute("lists",lists);
		
		return "index";
	}
	
	@RequestMapping("accessDenied")
	public String accessDenied() {
		return ACCESS_DENIED;
	}
	
	@RequestMapping(method=RequestMethod.GET,value="login")
	public String login(HttpServletRequest req,Model model,HttpSession session) throws Exception {
		
		oauthOperations = googleConnectionFactory.getOAuthOperations();
		String scope = oAuth2Parameters.getScope();
		System.out.println("scope = "+scope);
		String google_url = oauthOperations.buildAuthenticateUrl(GrantType.AUTHORIZATION_CODE, oAuth2Parameters);
		String naverClientId = naverLogin.getClientId(); 
		String naverClientSecret = naverLogin.getClientSecret(); 
		String naverRedirectURI = URLEncoder.encode(naverLogin.getRedirectUrl(),"UTF-8");
		SecureRandom random = new SecureRandom();
		String state = new BigInteger(130,random).toString();
		String naver_url = NaverAPI.getTokenUrl(naverClientId,naverClientSecret,naverRedirectURI,state,session);

		model.addAttribute("google_url",google_url);
		model.addAttribute("naver_url",naver_url);
		
		return USER_LOGIN;
	}
	
	@RequestMapping(method=RequestMethod.POST,value="login")
	public String postLogin(HttpServletRequest req,Model model) {

		return USER_LOGIN;
	}
	
	
	@RequestMapping(value = "/logout",method= RequestMethod.POST)
	public void getLogout() {
		System.out.println("로그아웃");
		log.info("post custom logout");
	}
	
	@RequestMapping(value="/member/logout")
	public String logoutSuccess() {
		return LOGOUT_PAGE;
	}
	

}

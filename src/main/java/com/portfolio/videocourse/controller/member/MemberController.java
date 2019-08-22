package com.portfolio.videocourse.controller.member;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.social.google.connect.GoogleConnectionFactory;
import org.springframework.social.oauth2.GrantType;
import org.springframework.social.oauth2.OAuth2Operations;
import org.springframework.social.oauth2.OAuth2Parameters;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.portfolio.videocourse.service.member.MemberService;
import com.portfolio.videocourse.validator.member.MemberJoinValidator;
import com.portfolio.videocourse.validator.member.MemberUpdateValidator;
import com.portfolio.videocourse.vo.member.MemberVO;

import lombok.extern.log4j.Log4j;



@Controller
@Transactional
@Log4j
public class MemberController {
	
	public static final String USER_JOIN_FORM = "member/joinMember";
	public static final String INDEX = "index";
	public static final String USER_INFO = "member/myinfo";
	public static final String UPDATE_MEMBER = "member/updateMember";
	
	@Autowired
	private GoogleConnectionFactory googleConnectionFactory;
	
	@Autowired
	private OAuth2Parameters googleOAuth2parameters;
	private OAuth2Operations oauthOperations;
	
	@Autowired
	private MemberService memberService;

	@ModelAttribute("memberJoin")
	public MemberVO memberForm() {
		return new MemberVO();
	}
	
	//회원가입
	@RequestMapping(method=RequestMethod.GET,value="joinMember")
	public String getMember(Model model) {
		
		oauthOperations = googleConnectionFactory.getOAuthOperations();
		String google_url = oauthOperations.buildAuthenticateUrl(GrantType.AUTHORIZATION_CODE, googleOAuth2parameters);
		model.addAttribute("google_url",google_url);
		
		return USER_JOIN_FORM;
	}
	
	@RequestMapping(method=RequestMethod.POST,value="joinMember")
	public String postMember(@ModelAttribute("memberJoin") MemberVO memberJoin, Errors errors) {
		System.out.println("회원가입 post");
		new MemberJoinValidator().validate(memberJoin,errors);
		if(errors.hasErrors()) {
			System.out.println("회원가입 오류");
			return USER_JOIN_FORM;
		}
		String emailChk = memberService.checkId(memberJoin.getEmail());
		if(emailChk!=null) {
			errors.rejectValue("email", "duplicate");
			return USER_JOIN_FORM;
		}
		String mIdChk = memberService.getMId(memberJoin.getMId());
		if(mIdChk!=null) {
			errors.rejectValue("mId","duplicate");
			return USER_JOIN_FORM;
		}
		try {
			int joinResult = memberService.MemberJoin(memberJoin);
			System.out.println(joinResult);
			if(joinResult==1) {
				return "redirect:/";
			}else {
				return USER_JOIN_FORM;
			}
		}catch(Exception e) {
			e.printStackTrace();
			return USER_JOIN_FORM;
		}
	}
	
	//로그인
	/*@ModelAttribute("memberLogin")
	public MemberVO getMemberLogin() {
		return new MemberVO();
	}*/
	
	/*@RequestMapping(method=RequestMethod.POST,value="login")
	public String postLogin(@ModelAttribute("memberLogin") MemberVO member,Errors errors,HttpSession session) {
		System.out.println("로그인");
		new MemberLoginValidator().validate(member, errors);
		if(errors.hasErrors()) {
			System.out.println("로그인 오류");
			return USER_LOGIN;
		}
		String idChk = memberService.checkId(member.getEmail());
		if(idChk==null) {
			errors.rejectValue("email", "NotJoinMember");
			return USER_LOGIN;
		}
		int result = memberService.chkPassword(member);
		if(result==1) {
			System.out.println("로그인 성공");
			String mId = memberService.checkMId(member.getEmail());
			session.setAttribute("USER_SESSION", mId);
			session.setMaxInactiveInterval(60*60);
			memberService.visitLog(member.getEmail());
			return "redirect:/";
		}else {
			System.out.println("로그인 실패");
			errors.rejectValue("email", "password.NotMatched");
			return USER_LOGIN;
		}
	}*/
	
	
	@RequestMapping(value="myInfo",method=RequestMethod.GET)
	public String MyInfo(Model model,HttpSession session) {
		
		String mId = (String)session.getAttribute("USER_SESSION");
		List<MemberVO> list = memberService.getList(mId);
		String auth = list.get(0).getAuth();
		if(auth.equals("Role_Member")) {
			list.get(0).setAuth("회원");
		}
		model.addAttribute("list",list);
		
		return USER_INFO;
	}
	
	@RequestMapping(value="updateMember",method=RequestMethod.GET)
	public String updateMember() {
		
		return UPDATE_MEMBER;
	}
	
	@ModelAttribute("updateMember")
	public MemberVO MemberForm() {
		return new MemberVO();
	}
	
	@RequestMapping(value="updateMember",method=RequestMethod.POST)
	public String updatePostMember(@ModelAttribute("updateMember") MemberVO updateMember,
			HttpSession session,HttpServletRequest req,Errors errors) {
		
		new MemberUpdateValidator().validate(updateMember, errors);
		
		if(errors.hasErrors()) {
			System.out.println("에러발생");
			return UPDATE_MEMBER;
		}
		MemberVO vo = new MemberVO();
		vo.setMId((String)session.getAttribute("USER_SESSION"));
		vo.setName(updateMember.getName());
		vo.setPassword(updateMember.getPassword());
		System.out.println(vo.toString());
		int result = memberService.MemberUpdate(vo);
		System.out.println("result : "+result);
		return "redirect:/";
	}
}

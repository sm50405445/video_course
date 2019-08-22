package com.portfolio.videocourse.service.member;

import java.util.List;

import javax.servlet.http.HttpSession;

import com.portfolio.videocourse.vo.member.MemberVO;

public interface MemberService {
	
	public int MemberJoin(MemberVO member);
	public String checkId(String id);
	public int chkPassword(MemberVO member);
	public int visitLog(String email);
	public String getMId(String mId);
	public List<MemberVO> getList(String mId);
	public String getPassword(String email);
	public int MemberUpdate(MemberVO vo);
	public String getAuth(String email);
	public void getCodeAndHandlingToken(String code);
	public int getGoogleLogin(String code,HttpSession session);
	public String getAccessToken(String code, String state) throws Exception;
	public int naverSigninCallback(String accessToken,HttpSession session) throws Exception;
}

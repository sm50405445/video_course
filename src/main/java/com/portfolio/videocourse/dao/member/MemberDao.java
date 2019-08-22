package com.portfolio.videocourse.dao.member;

import java.util.List;

import com.portfolio.videocourse.vo.member.MemberVO;

public interface MemberDao {

	public int MemberJoin(MemberVO member);
	//email임
	public String chkId(String id);
	//email로 아이디 가져오기
	public String getMidfromEmail(String email);
	//아이디 중복 확인
	public String getId(String mId);
	public String getPassword(String email);
	public List<MemberVO> MyInfo(String email);
	public int updateMember(MemberVO vo);
	public int changePassword(String oldPassword);
	public int loginLog(String email);
	public String getAuth(String email);
	
	public void logout();
}

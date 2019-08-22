package com.portfolio.videocourse.dao.member;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.mybatis.spring.SqlSessionTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.portfolio.videocourse.vo.member.MemberVO;

@Repository
public class MemberDaoImpl implements MemberDao{
	
	@Autowired
	private SqlSessionTemplate sqlsession;
	
	private static final String namespace = "com.portfolio.videocourse.dao.member.MemberDao";
	
	private static final Logger logger = LoggerFactory.getLogger(MemberDaoImpl.class);
	
	@Override
	public int MemberJoin(MemberVO member) {
		return sqlsession.insert(namespace+".MemberJoin",member);
	}
	@Override
	public String getMidfromEmail(String email) {
		return sqlsession.selectOne(namespace+".getMidFromEmail",email);
	}
	
	@Override
	public String getId(String mId) {	
		return sqlsession.selectOne(namespace+".CheckMid",mId);
	}
	
	@Override
	public String getPassword(String email) {
		return sqlsession.selectOne(namespace+".GetPassword",email);
	}
	
	@Override
	public List<MemberVO> MyInfo(String mId) {
		List<MemberVO> list = sqlsession.selectList(namespace+".MyInfo",mId);
		return list;
	}

	@Override
	public int updateMember(MemberVO vo) {
		return sqlsession.update(namespace+".memberInfoUpdate",vo);
	}
	@Override
	public int changePassword(String oldPassword) {
		// TODO Auto-generated method stub
		return 0;
	}
	@Override
	public int loginLog(String email) {
		return sqlsession.update(namespace+".visitLog",email);
		
	}
	
	@Override
	public void logout() {
		
	}
	@Override
	public String chkId(String id) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public String getAuth(String email) {
		return sqlsession.selectOne(namespace+".getAuth",email);
	}
	
}

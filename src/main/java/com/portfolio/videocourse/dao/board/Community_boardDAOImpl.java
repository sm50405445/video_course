package com.portfolio.videocourse.dao.board;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.portfolio.videocourse.common.PageDTO;
import com.portfolio.videocourse.vo.board.CommentVO;
import com.portfolio.videocourse.vo.board.Community_BoardVO;
import com.portfolio.videocourse.vo.board.LikeVO;

@Repository
public class Community_boardDAOImpl implements Community_BoardDAO{

	@Autowired
	private SqlSessionTemplate sqlSession;
	
	private static final String namespace = "com.portfolio.videocourse.dao.board.Community_Board";
	
	private static final Logger logger = LoggerFactory.getLogger(Community_boardDAOImpl.class);
	
	
	@Override
	public int write(Community_BoardVO vo) {
		return sqlSession.insert(namespace+".Write",vo);
	}

	@Override
	public int reply(Community_BoardVO vo) {
		return sqlSession.insert(namespace+".replyWrite",vo);
	}

	@Override
	public void replyShape(Community_BoardVO vo) {
		sqlSession.update(namespace+".replyShape",vo);
	}

	@Override
	public int getAllCount() {
		return sqlSession.selectOne(namespace+".getAllCount");
	}

	@Override
	public List<Community_BoardVO> getBoardList(PageDTO page) {
		return sqlSession.selectList(namespace+".list",page);
	}
	
	@Override
	public void upHit(int bno) {
		sqlSession.update(namespace+".upHit",bno);
	}

	@Override
	public int addLike(LikeVO vo) {
		return sqlSession.insert(namespace+".addLike",vo);
	}
	@Override
	public int remLike(LikeVO vo) {
		System.out.println(vo);
		return sqlSession.delete(namespace+".remLike",vo);
	}
	@Override
	public int checkLikeId(LikeVO vo) {
		int result = sqlSession.selectOne(namespace+".checkLikeId",vo);
		System.out.println("dao : "+result);
		return result;
	}
	
	@Override
	public int getLikeCount(int bno) {
		return sqlSession.selectOne(namespace+".getLikeCount",bno);
	}

	@Override
	public List<Community_BoardVO> view(int bno) {
		return sqlSession.selectList(namespace+".view",bno);
	}

	@Override
	public int commentWrite(CommentVO vo) {
		// TODO Auto-generated method stub
		return sqlSession.insert(namespace+".commentWrite",vo);
	}
	
	@Override
	public List<CommentVO> CommentList(int bno) {
		// TODO Auto-generated method stub
		return sqlSession.selectList(namespace+".commentList",bno);
	}

	
}

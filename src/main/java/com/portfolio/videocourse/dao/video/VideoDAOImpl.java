package com.portfolio.videocourse.dao.video;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.portfolio.videocourse.vo.video.CommentVO;
import com.portfolio.videocourse.vo.video.VideoVO;

@Repository
public class VideoDAOImpl implements VideoDAO {
	
	@Autowired
	private SqlSessionTemplate sqlSession;
	
	private static final String namespace = "com.portfolio.videocourse.dao.video.VideoDAO";
			
	@Override
	public int upload(VideoVO vo) {
		return sqlSession.insert(namespace+".videoUpload",vo);
	}
	
	@Override
	public List<VideoVO> lists() {
		return sqlSession.selectList(namespace+".videoList");
	}
	
	@Override
	public List<VideoVO> list(String title) {
		return sqlSession.selectList(namespace+".videoDetail",title);
	}
	
	@Override
	public int commentWrite(CommentVO vo) {
		return sqlSession.insert(namespace+".commentWrite",vo);
	}
	
	@Override
	public List<CommentVO> CommentList(int vno) {
		return sqlSession.selectList(namespace+".commentList",vno);
	}
	
}

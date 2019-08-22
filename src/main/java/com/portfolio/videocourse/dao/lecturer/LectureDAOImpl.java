package com.portfolio.videocourse.dao.lecturer;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.portfolio.videocourse.vo.lecture.LectureGroupVO;
import com.portfolio.videocourse.vo.lecture.LectureInfoGetJsonVO;
import com.portfolio.videocourse.vo.video.VideoVO;

@Repository
public class LectureDAOImpl implements LectureDAO{

	@Autowired
	public SqlSessionTemplate sqlSession;
	
	private static final String namespace = "com.portfolio.videocourse.dao.lecturer";
	
	@Override
	public List<? extends VideoVO> getLectureList(LectureGroupVO vo) {
		return sqlSession.selectList(namespace+".videoListFromLecturer",vo);
	}
	@Override
	public int insertGroup(LectureGroupVO vo) {
		return sqlSession.insert(namespace+".insertGroup",vo);
	}
	@Override
	public List<String> getGroupName(String mId) {
		return sqlSession.selectList(namespace+".getGroupName",mId);
	}
	@Override
	public int changeGroup(LectureGroupVO vo) {
		return sqlSession.update(namespace+".changeGroup",vo);
	}
	
}

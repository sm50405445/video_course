package com.portfolio.videocourse.dao.lecturer;

import java.util.List;

import com.portfolio.videocourse.vo.lecture.LectureGroupVO;
import com.portfolio.videocourse.vo.lecture.LectureInfoGetJsonVO;
import com.portfolio.videocourse.vo.video.VideoVO;

public interface LectureDAO {

	public List<? extends VideoVO> getLectureList(LectureGroupVO vo);
	public int insertGroup(LectureGroupVO vo);
	public List<String> getGroupName(String mId);
	public int changeGroup(LectureGroupVO vo);
}

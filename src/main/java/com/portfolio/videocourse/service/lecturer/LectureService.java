package com.portfolio.videocourse.service.lecturer;

import java.util.List;

import com.portfolio.videocourse.vo.lecture.LectureGroupVO;
import com.portfolio.videocourse.vo.lecture.LectureInfoGetJsonVO;
import com.portfolio.videocourse.vo.video.VideoVO;

public interface LectureService {
	
	public List<? extends VideoVO> getLists(LectureGroupVO vo);
	public List<String> getGroupName(String mId);
	public int changeVideoGroup(List<Integer> vno, String videoGroup);
	public int insertGroup(LectureGroupVO vo);
}

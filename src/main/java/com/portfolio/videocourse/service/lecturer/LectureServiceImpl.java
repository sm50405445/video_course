package com.portfolio.videocourse.service.lecturer;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.portfolio.videocourse.dao.lecturer.LectureDAO;
import com.portfolio.videocourse.vo.lecture.LectureGroupVO;
import com.portfolio.videocourse.vo.lecture.LectureInfoGetJsonVO;
import com.portfolio.videocourse.vo.video.VideoVO;

@Service
public class LectureServiceImpl implements LectureService{

	@Autowired
	public LectureDAO lectureDAO;
	
	@Override
	public List<? extends VideoVO> getLists(LectureGroupVO vo) {	
		return lectureDAO.getLectureList(vo);
	}
	
	@Override
	public List<String> getGroupName(String mId) {
		return lectureDAO.getGroupName(mId);
	}
	
	@Override
	public int changeVideoGroup(List<Integer> vno, String videoGroup) {
		LectureGroupVO vo = new LectureGroupVO();
		
		int result[] = new int[vno.size()];
		
		for(int i = 0 ; i<vno.size() ; i++) {
			vo.setVno(vno.get(i));
			vo.setVideoGroup(videoGroup);
			result[i] = lectureDAO.changeGroup(vo);
		}
		
		String resultValue = "";
		
		for(int i = 0 ; i<result.length ; i++) {
			if(result[i]==1) {
				resultValue="Y";
			}else {
				resultValue="N";
				break;
			}
		}
		
		System.out.println(resultValue);
		System.out.println(result);
		
		if(resultValue.equals("Y")) {
			return 1;
		}else {
			return 0;
		}
	}
	
	@Override
	public int insertGroup(LectureGroupVO vo) {
		return lectureDAO.insertGroup(vo);
	}
		
}

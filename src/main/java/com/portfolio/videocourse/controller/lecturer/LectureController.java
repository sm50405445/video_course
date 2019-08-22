package com.portfolio.videocourse.controller.lecturer;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.portfolio.videocourse.service.lecturer.LectureService;
import com.portfolio.videocourse.vo.lecture.LectureGroupVO;
import com.portfolio.videocourse.vo.lecture.LectureInfoGetJsonVO;
import com.portfolio.videocourse.vo.video.VideoVO;

@Controller
@RequestMapping("/lecturer")
public class LectureController {

	private static final String LECTURE_INFORMATION = "/lecturer/lectureInfo";
	
	@Autowired
	public LectureService lectureService;
	
	@RequestMapping("inform")
	public String lectureInform(HttpSession session,Model model) {
		String mId = (String)session.getAttribute("USER_SESSION");
		
		LectureGroupVO vo = new LectureGroupVO();
		vo.setMId(mId);
		List<? extends VideoVO> lectureInfo = lectureService.getLists(vo);
		List<String> lectureGroup = lectureService.getGroupName(mId);
		
		for(int i = 0 ; i<lectureInfo.size() ; i++) {
			System.out.println(lectureInfo.get(i).getEnabled());
		}
		
		model.addAttribute("lectureInfo",lectureInfo);
		model.addAttribute("lectureGroup",lectureGroup);
		
		return LECTURE_INFORMATION;
	}
	
	@RequestMapping("changeInform")
	@ResponseBody
	public String changeInform(@RequestBody LectureInfoGetJsonVO changeData,Model model) {
		
		List<Integer> vno = changeData.getVno();
		String videoGroup = changeData.getGroupName();
		
		String msg="";
		if(videoGroup==null || videoGroup.trim().isEmpty() || videoGroup.equals("")) {
			msg = "비디오 그룹을 선택해주세요!";
			model.addAttribute("msg",msg);
			return "0";
		}

		int result = lectureService.changeVideoGroup(vno,videoGroup);

		
		System.out.println(result);
		if(result==1) {
			return "1";
		}else {
			msg="업데이트에 실패했습니다. 관리자와 상의하세요";
			model.addAttribute("msg",msg);
			return "-1";
		}
	}
	
	@RequestMapping("lectureUpdateOk")
	public String UpdateOk() {
		return "lecturer/lectureUpdateOk";
	}
	
	
	@RequestMapping(value="insertGroup", method=RequestMethod.POST)
	public String insertGroup(HttpSession session,@ModelAttribute LectureGroupVO videoGroup,Errors errors,Model model) {
		
		String mId = (String)session.getAttribute("USER_SESSION");
		videoGroup.setMId(mId);
		
		if(videoGroup.getVideoGroup()==null || videoGroup.getVideoGroup().trim().length()==0 || videoGroup.getVideoGroup().equals("")) {
			LectureGroupVO vo = new LectureGroupVO();
			vo.setMId(mId);
			List<? extends VideoVO> lectureInfo = lectureService.getLists(vo);
			List<String> lectureGroup = lectureService.getGroupName(mId);
			System.out.println(lectureGroup.get(0));
			model.addAttribute("lectureInfo",lectureInfo);
			model.addAttribute("lectureGroup",lectureGroup);
			model.addAttribute("msg","그룹 이름은 필수입니다");
			return LECTURE_INFORMATION;
		}

		lectureService.insertGroup(videoGroup);

		return "lecturer/lectureUpdateOk";
	}
	
}

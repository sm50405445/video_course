package com.portfolio.videocourse.controller.video;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.portfolio.videocourse.common.FileUploadDTO;
import com.portfolio.videocourse.service.video.VideoService;
import com.portfolio.videocourse.validator.video.VideoCommentValidator;
import com.portfolio.videocourse.validator.video.VideoUploadValidator;
import com.portfolio.videocourse.vo.video.CommentVO;
import com.portfolio.videocourse.vo.video.VideoVO;

@Controller
@Transactional
public class VideoController {

	public static final String UPLOAD_FORM = "video/videoUpload";
	public static final String VideoDetail = "video/videoDetail";

	@Autowired
	private VideoService videoService;

	@RequestMapping(method = RequestMethod.GET, value = "/videoUpload")
	public String uploadFormGet() {
		return UPLOAD_FORM;
	}

	@RequestMapping(method = RequestMethod.POST, value = "/videoUpload")
	public String uploadForm(@ModelAttribute("formUpload") FileUploadDTO files, HttpSession session,
			BindingResult result,Errors errors) {

		new VideoUploadValidator().validate(files, result);
		
		if (result.hasErrors()) {
			System.out.println("에러발생");
			return UPLOAD_FORM;
		}
		
		
		String mId = (String) session.getAttribute("USER_SESSION");
		System.out.println(mId);
		videoService.videoUpload(files, mId);
		System.out.println("업로드 성공");
		return "video_course-project";
	}
	
	@RequestMapping(method = RequestMethod.GET,value="videoDetail/{title}")
	public String videoDetail(@PathVariable("title") String title,Model model) {
		
		List<VideoVO> list = videoService.listWithtitle(title);
		List<CommentVO> commentList = videoService.commentLists(list.get(0).getVno());
		String url = list.get(0).getUuid()+list.get(0).getFileName();
		model.addAttribute("url",url);
		model.addAttribute("list",list);
		model.addAttribute("commentList",commentList);
		
		return VideoDetail;
	}

	@RequestMapping(method=RequestMethod.POST,value="video/commentWrite",headers = {
    "content-type=application/json" })
	public @ResponseBody String commentInform(@RequestBody CommentVO commentInfo,Errors errors) {
		
		System.out.println(commentInfo.toString());
		new VideoCommentValidator().validate(commentInfo, errors);
		if(errors.hasErrors()) {
			System.out.println("댓글 오류 발생");
			return "0";
		}
		if(videoService.commentWrite(commentInfo)==1) {
			
			return String.valueOf(videoService.commentLists(commentInfo.getVno()));
		}else {
			return "0";
		}
	}
	
	@RequestMapping(method=RequestMethod.GET,value="video/commentList/{vno}")
	public @ResponseBody List<CommentVO> commentList(@PathVariable("vno") int vno) {
		System.out.println("json 코멘트 처리 컨트롤러");
		return videoService.commentLists(vno);
	}
}

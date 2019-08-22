package com.portfolio.videocourse.service.video;


import java.util.List;

import com.portfolio.videocourse.common.FileUploadDTO;
import com.portfolio.videocourse.vo.video.CommentVO;
import com.portfolio.videocourse.vo.video.VideoVO;

public interface VideoService {

	public int videoUpload(FileUploadDTO files,String mId);
	public List<VideoVO> lists();
	public List<VideoVO> listWithtitle(String title);
	public int commentWrite(CommentVO vo);
	public List<CommentVO> commentLists(int vno);
}

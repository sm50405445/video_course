package com.portfolio.videocourse.dao.video;

import java.util.List;

import com.portfolio.videocourse.vo.video.CommentVO;
import com.portfolio.videocourse.vo.video.VideoVO;

public interface VideoDAO {
	
	public int upload(VideoVO vo);
	public List<VideoVO> lists();
	public List<VideoVO> list(String title);
	public int commentWrite(CommentVO vo);
	public List<CommentVO> CommentList(int vno);
}

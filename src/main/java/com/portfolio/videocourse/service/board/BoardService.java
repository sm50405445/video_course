package com.portfolio.videocourse.service.board;

import java.util.List;

import com.portfolio.videocourse.common.PageDTO;
import com.portfolio.videocourse.vo.board.CommentVO;
import com.portfolio.videocourse.vo.board.Community_BoardVO;
import com.portfolio.videocourse.vo.board.LikeVO;

public interface BoardService {

	public int write(Community_BoardVO write);
	public List<Community_BoardVO> list(PageDTO page);
	public int getAllCount();
	public List<Community_BoardVO> view(int pageNumber);
	public int like(LikeVO vo);
	public int commentWrite(CommentVO vo);
	public List<CommentVO> commentLists(int bno);
	
}

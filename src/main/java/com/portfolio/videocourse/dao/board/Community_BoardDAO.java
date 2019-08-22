package com.portfolio.videocourse.dao.board;

import java.util.List;

import com.portfolio.videocourse.common.PageDTO;
import com.portfolio.videocourse.vo.board.Community_BoardVO;
import com.portfolio.videocourse.vo.board.LikeVO;
import com.portfolio.videocourse.vo.board.CommentVO;

public interface Community_BoardDAO {

	public int write(Community_BoardVO vo);
	public int reply(Community_BoardVO vo);
	public void replyShape(Community_BoardVO vo);
	public int getAllCount();
	public List<Community_BoardVO> getBoardList(PageDTO page);
	public void upHit(int bno);
	
	public int checkLikeId(LikeVO vo);
	public int addLike(LikeVO vo);
	public int remLike(LikeVO vo);
	public int getLikeCount(int bno);
	
	public List<Community_BoardVO> view(int bno);
	
	public int commentWrite(CommentVO vo);
	public List<CommentVO> CommentList(int bno);
}

package com.portfolio.videocourse.service.board;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.portfolio.videocourse.common.PageDTO;
import com.portfolio.videocourse.dao.board.Community_BoardDAO;
import com.portfolio.videocourse.vo.board.CommentVO;
import com.portfolio.videocourse.vo.board.Community_BoardVO;
import com.portfolio.videocourse.vo.board.LikeVO;

@Service
public class BoardServiceImpl implements BoardService{

	@Autowired
	public Community_BoardDAO boardDAO;
	
	@Override
	public int write(Community_BoardVO write) {
		
		return boardDAO.write(write);
	}
	
	@Override
	public List<Community_BoardVO> list(PageDTO page) {
		int startRow = (page.getPageNum()-1)*(page.getPageSize())+1;
		page.setStartRow(startRow);
		page.setCount(boardDAO.getAllCount());
		List<Community_BoardVO> list = boardDAO.getBoardList(page);
		
		return list;
	}
	
	public List<Community_BoardVO> view(int pageNumber){
		boardDAO.upHit(pageNumber);
		List<Community_BoardVO> list = boardDAO.view(pageNumber);
		return list;
	}
	
	@Override
	public int like(LikeVO vo) {
		int result = boardDAO.checkLikeId(vo);
		System.out.println("service :"+result);
		if(result==1) {
			boardDAO.remLike(vo);
			return 1;
		}else {
			boardDAO.addLike(vo);
			return 1;
		}	
	}
	
	@Override
	public int getAllCount() {
		return boardDAO.getAllCount();
	}
	
	@Override
	public List<CommentVO> commentLists(int bno) {
		return boardDAO.CommentList(bno);
	}
	
	@Override
	public int commentWrite(CommentVO vo) {
		return boardDAO.commentWrite(vo);
	}
}

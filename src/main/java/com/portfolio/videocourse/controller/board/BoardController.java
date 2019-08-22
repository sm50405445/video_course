package com.portfolio.videocourse.controller.board;

import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.portfolio.videocourse.common.PageDTO;
import com.portfolio.videocourse.service.board.BoardService;
import com.portfolio.videocourse.validator.board.BoardWriteValidator;
import com.portfolio.videocourse.vo.board.CommentVO;
import com.portfolio.videocourse.vo.board.Community_BoardVO;
import com.portfolio.videocourse.vo.board.LikeVO;

import lombok.extern.log4j.Log4j;

@RequestMapping("board")
@Controller
@Transactional
@Log4j
public class BoardController {

	public static final String BOARD_MAIN = "board/communityMain";
	public static final String BOARD_WRITE = "board/communityWrite";
	public static final String BOARD_VIEW = "board/communityView";
	
	@Autowired
	public BoardService boardService;
	
	//게시판 메인
	@RequestMapping(method=RequestMethod.GET,value= {"communityMain/{pageNum}","communityMain"})
	public String boardlist(Model model,@PathVariable(value="pageNum") Optional<Integer> pageNum) {
		PageDTO page = new PageDTO();
		System.out.println(pageNum);
		if(pageNum.isPresent()) {
			int pagenum = pageNum.get();
			page.setPageSize(10);
			page.setPageNum(pagenum);
			page.setCount(boardService.getAllCount());
			List<Community_BoardVO> list = boardService.list(page);
			
			page.Init();
			log.info(page);
			int boardAllCount = boardService.getAllCount();
			
			model.addAttribute("AllCount",boardAllCount);
			model.addAttribute("list",list);
			model.addAttribute("page",page);
			
			return BOARD_MAIN;
		}else {
			page.setPageSize(10);
			page.setPageNum(1);
			page.setCount(boardService.getAllCount());
			List<Community_BoardVO> list = boardService.list(page);
			
			page.Init();
			log.info(page);
			int boardAllCount = boardService.getAllCount();
			
			model.addAttribute("AllCount",boardAllCount);
			model.addAttribute("list",list);
			model.addAttribute("page",page);
			
			return BOARD_MAIN;
		}
		
		
	}
	//게시판 글 1개보기
	@RequestMapping(value="view/{bno}")
	public String boardView(@PathVariable("bno") int boardNumber, Model model) {
		
		List<Community_BoardVO> list = boardService.view(boardNumber);
		
		model.addAttribute("list",list);
		
		return BOARD_VIEW;
	}
	
	@ModelAttribute("boardContent")
	public Community_BoardVO getWriteInfo() {
		return new Community_BoardVO();
	}
	
	@RequestMapping(method=RequestMethod.GET,value="communityWrite")
	public String boardWriteGet() {
		return BOARD_WRITE;
	}
	
	@RequestMapping(method=RequestMethod.POST,value="communityWrite")
	public String boardWritePost(@ModelAttribute("boardContent") Community_BoardVO board,Errors errors,HttpSession session) {
		System.out.println("글작성");
		board.setMId((String)session.getAttribute("USER_SESSION"));
		new BoardWriteValidator().validate(board,errors);
		if(errors.hasErrors()) {
			System.out.println("글 형식 확인");
			return BOARD_WRITE;
		}
		System.out.println(board.toString());
		String content = board.getBcontent();
		content = content.substring(3,content.length());
		content = content.substring(0,content.length()-4);
		board.setBcontent(content);
		
		if(boardService.write(board)==1) {
			System.out.println("글작성 성공");
			return "redirect:/"+BOARD_MAIN;
		}else {
			return "redirect:/"+BOARD_MAIN;
		}
	}
	
	
	
	@RequestMapping(method=RequestMethod.POST,value="heartOn",headers= {"content-type=application/json"})
	public @ResponseBody String heartAdd(@RequestBody LikeVO listInform){
		System.out.println(listInform.toString());
		int result = boardService.like(listInform);
		return String.valueOf(result);
	}
	
	@RequestMapping(method=RequestMethod.POST,value="commentWrite")
	public @ResponseBody String commentInform(@RequestBody CommentVO commentInfo,Errors errors) {
		
		System.out.println(commentInfo.toString());
		
		if(commentInfo.getCContent()==null||commentInfo.getCContent().equals("")|| commentInfo.getCContent().trim().length()>1000) {
			return "0";
		}
		if(boardService.commentWrite(commentInfo)==1) {
			return String.valueOf(boardService.commentLists(commentInfo.getBno()));
		}else {
			return "0";
		}
	}
	
	@RequestMapping(method=RequestMethod.GET,value="commentList/{bno}")
	public @ResponseBody List<CommentVO> commentList(@PathVariable int bno) {
		System.out.println("json 코멘트 처리 컨트롤러");
		
		return boardService.commentLists(bno);	
	}
}

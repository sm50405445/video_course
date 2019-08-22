package com.portfolio.videocourse.common;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class PageDTO {
	//시작 게시글 넘버
	private int startRow;
	//한 화면에 보여질 게시글 수
	private int pageSize;
	//전체 게시글 수
	private int count;
	//페이지 넘버
	private int pageNum;
	//밑에 나오는 페이지 수
	private int pageCount;
	//한화면에 보여질 페이지 수
	private int pageBlock;
	private int startPage;
	private int endPage;
	private int hit;

	public void Init() {
		setPageCount(count/pageSize+(count%pageSize==0?0:1));
		setPageBlock(10);
		setStartPage(((pageNum-1)/pageBlock)*pageBlock+1);
		int endPage = startPage+pageBlock-1;
		if(endPage>pageCount)
			endPage=pageCount;
		setEndPage(endPage);
	}
	

	
}

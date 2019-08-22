package com.portfolio.videocourse.vo.board;

import java.sql.Timestamp;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class Community_BoardVO {

	private int bno;
	private String mId;
	private int available;
	private String btitle;
	private String bcontent;
	private int bstep;
	private int bgroup;
	private int bindent;
	private int bhit;
	private Timestamp bdate;
	private int blikeCount;
	private int bcommentCount;
	
	
}

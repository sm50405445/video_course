package com.portfolio.videocourse.vo.board;

import java.sql.Timestamp;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Data
public class CommentVO {

	private int cno;
	private int bno;
	private String cContent;
	private int cgroup;
	private int cstep;
	private int cindent;
	private String mId;
	private Timestamp idate;
	
	public CommentVO() {
		// TODO Auto-generated constructor stub
	}
	
	@JsonCreator
	public CommentVO(@JsonProperty("cContent") String cContent,@JsonProperty("mId") String mId,
			@JsonProperty("idate") Timestamp idate) {
		this.mId = mId;
		this.cContent = cContent;
		this.idate = idate;
	}
	
}

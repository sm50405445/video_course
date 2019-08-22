package com.portfolio.videocourse.vo.video;

import java.sql.Timestamp;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
@Getter
@Setter
@Data
public class CommentVO {
	
	private int cno;
	private int vno;
	private String cContent;
	private int cgroup;
	private int cstep;
	private int cindent;
	private String mId;
	private Timestamp idate;
	
	public CommentVO() {
	
	}
	
	@JsonCreator
	public CommentVO(@JsonProperty("cContent") String cContent,@JsonProperty("mId") String mId,
			@JsonProperty("idate") Timestamp idate) {
		this.mId = mId;
		this.cContent = cContent;
		this.idate = idate;
	}
	
}

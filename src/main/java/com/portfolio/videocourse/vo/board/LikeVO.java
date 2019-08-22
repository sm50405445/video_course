package com.portfolio.videocourse.vo.board;

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
public class LikeVO {

	private int bno;
	private String mId;
	
	@JsonCreator
	public LikeVO(@JsonProperty("mId") String mId) {
		this.mId = mId;
	}
}

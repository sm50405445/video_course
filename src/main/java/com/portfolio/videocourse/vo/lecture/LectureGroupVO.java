package com.portfolio.videocourse.vo.lecture;

import com.portfolio.videocourse.vo.video.VideoVO;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class LectureGroupVO extends VideoVO{

	private String videoGroup;
	private int vno;
	private String mId;
}

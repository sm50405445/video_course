package com.portfolio.videocourse.vo.lecture;

import java.util.List;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
public class LectureInfoGetJsonVO {

	private List<Integer> vno;
	private String groupName;
}

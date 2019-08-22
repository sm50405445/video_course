package com.portfolio.videocourse.vo.video;

import java.sql.Timestamp;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Data
public class VideoVO {
	
	private int vno;
	private String mId;
	private String title;
	private String uuid;
	private String fileName;
	private Timestamp udate;
	private int enabled;
	
}

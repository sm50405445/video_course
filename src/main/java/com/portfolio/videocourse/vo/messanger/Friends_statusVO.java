package com.portfolio.videocourse.vo.messanger;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
public class Friends_statusVO {

	private String userId1;
	private String userId2;
	private int send_status;
	private int request_status;
}

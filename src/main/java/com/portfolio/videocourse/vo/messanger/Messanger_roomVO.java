package com.portfolio.videocourse.vo.messanger;

import java.sql.Timestamp;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Data
public class Messanger_roomVO {

	private int roomnum;
	private String sender;
	private String receiver;
	private String sendMessage;
	private Timestamp sendDate;
	private String receiveMessage;
	private Timestamp receivedDate;
	
}

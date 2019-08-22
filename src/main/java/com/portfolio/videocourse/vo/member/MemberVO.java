package com.portfolio.videocourse.vo.member;

import java.sql.Timestamp;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class MemberVO {

	private int id;
	private String email;
	private String mId;
	private String auth;
	private String name;
	private String password;
	private String confirmPassword;
	private Timestamp joinDate;
	private Timestamp loginDate;
	private int enabled;
	private String logintype;
}

package com.portfolio.videocourse.vo.auth;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class LoginAuthVO{

	private String redirectUrl;
	private String clientId;
	private String clientSecret;
 
	
}

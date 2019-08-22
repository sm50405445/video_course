package com.portfolio.videocourse.common;

import javax.servlet.http.HttpSession;

public class NaverAPI{
	
	public static String getTokenUrl(String clientId,String clientSecret,String redirectURI,String state,HttpSession session) {
		
		
		String url= "https://nid.naver.com/oauth2.0/authorize?"+
				 "response_type=code" + "&"+
				 "client_id="+clientId+"&"+
				 "redirect_uri="+redirectURI+"&"+
				 "state="+state;
		return url;
	}
}

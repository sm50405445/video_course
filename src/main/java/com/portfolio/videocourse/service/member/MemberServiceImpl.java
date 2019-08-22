package com.portfolio.videocourse.service.member;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpSession;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.social.connect.Connection;
import org.springframework.social.google.api.Google;
import org.springframework.social.google.api.impl.GoogleTemplate;
import org.springframework.social.google.api.plus.Person;
import org.springframework.social.google.api.plus.PlusOperations;
import org.springframework.social.google.connect.GoogleConnectionFactory;
import org.springframework.social.oauth2.AccessGrant;
import org.springframework.social.oauth2.OAuth2Operations;
import org.springframework.social.oauth2.OAuth2Parameters;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.portfolio.videocourse.dao.member.MemberDao;
import com.portfolio.videocourse.vo.auth.LoginAuthVO;
import com.portfolio.videocourse.vo.member.MemberVO;

@Service
public class MemberServiceImpl implements MemberService{

	@Autowired
	private MemberDao memberDao;
	
	@Autowired
	private GoogleConnectionFactory googleConnectionFactory;

	@Autowired
	private OAuth2Parameters googleOAuth2parameters;
	private OAuth2Operations oauthOperations;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	private MemberService memberService;
	
	@Autowired
	@Qualifier("naverLoginValue") 
	private LoginAuthVO naverLogin;
	
	@Override
	@Transactional
	public int MemberJoin(MemberVO member) {
		String passEncoder = passwordEncoder.encode(member.getPassword());
		member.setPassword(passEncoder);
		int result = memberDao.MemberJoin(member);
		System.out.println(result);
		return result;
	}
	//이메일로 아이디 체크
	@Override
	public String checkId(String email) {
		return memberDao.getMidfromEmail(email);
	}
	@Override
	public String getMId(String mId) {
		return memberDao.getId(mId);
	}

	@Override
	public int chkPassword(MemberVO member) {
		
		String userPassword = memberDao.getPassword(member.getEmail());
		boolean confirmResult = passwordEncoder.matches(member.getPassword(),userPassword);
		if(confirmResult) {
			//비밀번호 아이디 일치시
			return 1;
		}else {
			return 0;
		}
	}
	
	@Override
	public int visitLog(String email) {
		return memberDao.loginLog(email);
	}
	
	@Override
	public List<MemberVO> getList(String mId) {
		return memberDao.MyInfo(mId);
	}
	
	@Override
	public String getPassword(String email) {
		return memberDao.getPassword(email);
	}
	
	@Override
	public int MemberUpdate(MemberVO vo) {
		String password = passwordEncoder.encode(vo.getPassword());
		vo.setPassword(password);
		System.out.println(vo.toString());
		return memberDao.updateMember(vo);
	}
	
	@Override
	public String getAuth(String email) {
		return memberDao.getAuth(email);
	}
	@Override
	public void getCodeAndHandlingToken(String code) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public int getGoogleLogin(String code,HttpSession session) {
		oauthOperations = googleConnectionFactory.getOAuthOperations();
		AccessGrant accessGrant = oauthOperations.exchangeForAccess(code, googleOAuth2parameters.getRedirectUri(),
				null);

		String accessToken = accessGrant.getAccessToken();
		Long expireTime = accessGrant.getExpireTime();
		System.out.println("expireTime = " + expireTime);
		System.out.println("accessToken = " + accessToken);

		if (expireTime != null && expireTime < System.currentTimeMillis()) {
			accessToken = accessGrant.getRefreshToken();
			System.out.println("refreshToken = " + accessToken);
		}
		Connection<Google> connection = googleConnectionFactory.createConnection(accessGrant);
		Google google = connection == null ? new GoogleTemplate(accessToken) : connection.getApi();
		PlusOperations plusOperations = google.plusOperations();
		Person profile = plusOperations.getGoogleProfile();

		Map<String, String> emails = profile.getEmails();

		Iterator<String> keys = emails.keySet().iterator();
		String email = keys.next();
		System.out.println(email);
		
		MemberVO vo = new MemberVO();
		if (memberService.checkId(email) != null) {
			session.setAttribute("USER_SESSION", profile.getId());
			session.setAttribute("USER_AUTH", "[" + memberService.getAuth(email) + "]");
		} else {
			vo.setMId(profile.getId());
			vo.setEmail(email);
			String randomPassword = UUID.randomUUID() + vo.getMId();
			vo.setPassword(passwordEncoder.encode(randomPassword));
			vo.setName(profile.getDisplayName());
			System.out.println(vo.toString());
			int result = memberService.MemberJoin(vo);
			if (result != 1) {
				System.out.println(vo);
				new Exception();
			}
		}

		// AccessToken 취소
		try {
			String revokeUrl = "https://accounts.google.com/o/oauth2/revoke?token=" + accessToken + "";
			URL url = new URL(revokeUrl);
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("GET");
			conn.setDoOutput(true);

			BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream(), "UTF-8"));
			String inputLine;
			StringBuffer response = new StringBuffer();
			while ((inputLine = in.readLine()) != null) {
				response.append(inputLine);
			}
			in.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}
	
	@Override
	public String getAccessToken(String code, String state) throws Exception{
		String redirecUri = URLEncoder.encode(naverLogin.getRedirectUrl(),"UTF-8");
		String accessToken = "";
		String tokenUrl;
		tokenUrl = "https://nid.naver.com/oauth2.0/token?grant_type=authorization_code&";
		tokenUrl += "client_id="+naverLogin.getClientId()+"&client_secret="+naverLogin.getClientSecret()+"&";
		tokenUrl += "redirect_uri="+redirecUri+"&code="+code+"&state="+state;
		URL url = new URL(tokenUrl);
		HttpURLConnection con = (HttpURLConnection)url.openConnection();
		con.setRequestMethod("GET");
		int responseCode = con.getResponseCode();
		BufferedReader br;
		System.out.println("responseCode="+responseCode);
		if(responseCode==200)
			br = new BufferedReader(new InputStreamReader(con.getInputStream()));
		else
			br = new BufferedReader(new InputStreamReader(con.getErrorStream()));
		
		String inputLine;
		StringBuffer res = new StringBuffer();
		while((inputLine=br.readLine())!=null) {
			res.append(inputLine);
		}
		br.close();
		if(responseCode==200) {
			JSONParser parser = new JSONParser();
			JSONObject object = (JSONObject)parser.parse(res.toString());
			accessToken = object.get("access_token").toString();
		}
		
		return accessToken;
	}
	
	@Override
	public int naverSigninCallback(String accessToken,HttpSession session) throws Exception {
		String header = "Bearer " + accessToken;
		String apiUrl = "https://openapi.naver.com/v1/nid/me";
		URL url = new URL(apiUrl);
		HttpURLConnection con = (HttpURLConnection)url.openConnection();
		con.setRequestMethod("GET");
		con.setRequestProperty("Authorization", header);
		int responseCode = con.getResponseCode();
		BufferedReader br;
        if(responseCode==200) { // 정상 호출
            br = new BufferedReader(new InputStreamReader(con.getInputStream()));
        } else {  // 에러 발생
            br = new BufferedReader(new InputStreamReader(con.getErrorStream()));
        }
        String inputLine;
        StringBuffer response = new StringBuffer();
        while ((inputLine = br.readLine()) != null) {
            response.append(inputLine);
        }
        System.out.println(response.toString());
        br.close();
        
        JSONParser parser = new JSONParser();
        JSONObject obj = (JSONObject)parser.parse(response.toString());
        obj = (JSONObject)parser.parse(obj.get("response").toString());
        String email = obj.get("email").toString();
        String mId = obj.get("nickname").toString();
        int result = 0;
        if(memberService.checkId(obj.get("email").toString())!=null) {
        	session.setAttribute("USER_SESSION", mId);
        	session.setAttribute("USER_AUTH", "[" + memberService.getAuth(email) + "]");
			result = 1;
			return result;
        }else {
        	MemberVO vo = new MemberVO();
            vo.setEmail(obj.get("email").toString());
            String password = UUID.randomUUID()+vo.getEmail();
            vo.setPassword(password);
            vo.setMId(obj.get("nickname").toString());
            result = memberService.MemberJoin(vo);
            return result;
        }

	}
	
}

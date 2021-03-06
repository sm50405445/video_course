package com.portfolio.videocourse.validator.member;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.portfolio.videocourse.vo.member.MemberVO;

public class MemberJoinValidator implements Validator {

	private Pattern pattern;
	private Matcher matcher;
	private static final String EMAIL_PATTERN = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"    
			   + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
	
	@Override
	public boolean supports(Class<?> clazz) {
	
		return MemberVO.class.isAssignableFrom(clazz);
	}
	
	@Override
	public void validate(Object target, Errors errors) {
		
		MemberVO member = (MemberVO)target;
		if(member.getEmail().trim().length()==0||member.getEmail()==null) {
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "email", "required.email");
		}
		
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name", "required.name");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "required.password");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "confirmPassword", "required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "mId", "required.mId");
		
		if(member.getPassword().trim().length()>1&&member.getPassword().trim().length()<10||member.getPassword().trim().length()>16) {
			errors.rejectValue("password", "passwordLengthFalse");
		}

		if(!member.getPassword().equals(member.getConfirmPassword())) {
			errors.rejectValue("confirmPassword", "notSame");
		}
		
		if(member.getEmail()!=null&&member.getEmail().trim().length()>0) {
			pattern = Pattern.compile(EMAIL_PATTERN);
			matcher = pattern.matcher(member.getEmail().toString());
			if(!matcher.matches()) {
				errors.rejectValue("email", "email.regex");
			}
		}
		
		
	}
}

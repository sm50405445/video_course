package com.portfolio.videocourse.validator.member;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.portfolio.videocourse.vo.member.MemberVO;

public class MemberUpdateValidator implements Validator{

	@Override
	public boolean supports(Class<?> clazz) {
		return MemberVO.class.isAssignableFrom(clazz);
	}
	
	@Override
	public void validate(Object target, Errors errors) {
		
		MemberVO vo = (MemberVO)target;
		
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name", "required.name");
		
		if(!vo.getPassword().equals(vo.getConfirmPassword())) {
			errors.rejectValue("confirmPassword", "notSame");
		}
		
		if(vo.getPassword().trim().length()>0 && vo.getPassword().trim().length()>10) {
			errors.rejectValue("password", "passwordLengthFalse");
		}
		
	}
}

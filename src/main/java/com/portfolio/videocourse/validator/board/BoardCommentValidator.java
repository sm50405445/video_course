package com.portfolio.videocourse.validator.board;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.portfolio.videocourse.vo.board.CommentVO;

public class BoardCommentValidator implements Validator{

	@Override
	public boolean supports(Class<?> clazz) {
		return CommentVO.class.isAssignableFrom(clazz);
	}
	
	@Override
	public void validate(Object target, Errors errors) {
		CommentVO comment = (CommentVO) target;
		
		System.out.println(comment.getCContent());
		
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "cContent", "required.content");
		
		if(comment.getCContent().trim().length()>1000) {
			errors.rejectValue("cContent", "content.length");
		}
		
	}
}

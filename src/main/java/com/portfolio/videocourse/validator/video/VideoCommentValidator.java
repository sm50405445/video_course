package com.portfolio.videocourse.validator.video;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.portfolio.videocourse.vo.video.CommentVO;

public class VideoCommentValidator implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		return CommentVO.class.isAssignableFrom(clazz);
	}
	
	@Override
	public void validate(Object target, Errors errors) {
		CommentVO comment = (CommentVO) target;
		
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "cContent", "required.content");
		
		if(comment.getCContent().trim().length()>1000) {
			errors.rejectValue("cContent", "content.length");
		}
	}
}

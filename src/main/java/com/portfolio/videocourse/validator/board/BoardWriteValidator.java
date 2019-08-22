package com.portfolio.videocourse.validator.board;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.portfolio.videocourse.vo.board.Community_BoardVO;

public class BoardWriteValidator implements Validator{

	@Override
	public boolean supports(Class<?> clazz) {
	
		return Community_BoardVO.class.isAssignableFrom(clazz);
	}
	
	@Override
	public void validate(Object target, Errors errors) {
		
		Community_BoardVO board = (Community_BoardVO)target;
		
		String btitle = board.getBtitle();
		String bcontent = board.getBcontent();
		
		System.out.println(board.toString());
		System.out.println(btitle.trim().length()==0);
		System.out.println(btitle=="");
		System.out.println(bcontent.trim().length()==0);
		System.out.println(bcontent=="");
		
		
		if(btitle.trim().length()==0 || btitle==null || btitle=="") {
			errors.rejectValue("btitle", "required.title");
		}
		if(bcontent.trim().length()==0 || bcontent==null || bcontent=="") {
			errors.rejectValue("bcontent", "required.content");
		}

		if(btitle.trim().length()>100) {
			errors.rejectValue("btitle", "title.length");
		}
		
		if(bcontent.trim().length()>1000) {
			errors.rejectValue("bcontent", "content.length");
		}
		
	}
}

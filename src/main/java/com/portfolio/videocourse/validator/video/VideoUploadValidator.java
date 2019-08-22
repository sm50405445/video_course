package com.portfolio.videocourse.validator.video;


import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import com.portfolio.videocourse.common.FileUploadDTO;

public class VideoUploadValidator implements Validator{

	
	@Override
	public boolean supports(Class<?> clazz) {
		// TODO Auto-generated method stub
		return FileUploadDTO.class.isAssignableFrom(clazz);
	}
	
	@Override
	public void validate(Object target, Errors errors) {
		FileUploadDTO fileupload = (FileUploadDTO) target;
		
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "title", "Videotitle.required");
		
		CommonsMultipartFile[] files = fileupload.getFiles(); 
		
		for(CommonsMultipartFile file : files) {
			
			if(file.getOriginalFilename().trim().length()==0||file.getSize()==0) {
				errors.rejectValue("files", "file.missing");
			}
			
			if(file.getSize()>=52428800) {
				errors.rejectValue("files", "file_size_over");
			}
		}
		
	}
	
}

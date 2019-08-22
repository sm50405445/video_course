package com.portfolio.videocourse.common;

import org.springframework.web.multipart.commons.CommonsMultipartFile;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FileUploadDTO {

	private CommonsMultipartFile[] files;
	private String title;
	
}

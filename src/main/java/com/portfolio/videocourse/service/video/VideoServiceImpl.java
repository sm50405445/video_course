package com.portfolio.videocourse.service.video;

import java.io.File;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.portfolio.videocourse.common.FileUploadDTO;
import com.portfolio.videocourse.dao.video.VideoDAO;
import com.portfolio.videocourse.vo.video.CommentVO;
import com.portfolio.videocourse.vo.video.VideoVO;

import lombok.extern.log4j.Log4j;

@Log4j
@Service
@Transactional
public class VideoServiceImpl implements VideoService{
	
	@Autowired
	private VideoDAO videoDAO;

	@Override
	public int videoUpload(FileUploadDTO uploadfiles,String mId) {
		
		String path = "c:\\upload\\video";
		File uploadDir = new File(path);
		
		if(!uploadDir.exists()) {
			uploadDir.mkdirs();
		}
		
		CommonsMultipartFile[] files = uploadfiles.getFiles();
		
		for(CommonsMultipartFile upload : files) {
			
			VideoVO vo = new VideoVO();
			String uuid = UUID.randomUUID().toString();
			String fileName = upload.getOriginalFilename();
			String fullName = uuid+fileName;
			
			File saveFile = new File(path,fullName);
			vo.setFileName(fileName);
			vo.setMId(mId);
			vo.setUuid(uuid);
			vo.setTitle(uploadfiles.getTitle());
			try {
				upload.transferTo(saveFile);
				videoDAO.upload(vo);
				
			}catch(Exception e) {
				log.error(e.getMessage());
			}
		}
		
		return 1;
	}
	
	@Override
	public List<VideoVO> lists() {
		
		return videoDAO.lists();
	}
	
	@Override
	public List<VideoVO> listWithtitle(String title) {
		
		return videoDAO.list(title);
	}
	
	@Override
	public int commentWrite(CommentVO vo) {
		return videoDAO.commentWrite(vo);
	}
	
	@Override
	public List<CommentVO> commentLists(int vno) {
		return videoDAO.CommentList(vno);
	}
}

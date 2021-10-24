package com.perrapp.services;

import org.springframework.web.multipart.MultipartFile;

import com.perrapp.entities.dto.PictureDTO;

public interface PictureService {

	public PictureDTO loadPicture(MultipartFile file, String id) throws Exception;
	
	public void deletePicture(String id) throws Exception ;
	
}

package com.cos.photogramstart.service;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.cos.photogramstart.config.auth.PrincipalDetails;
import com.cos.photogramstart.domain.image.Image;
import com.cos.photogramstart.domain.image.ImageRepository;
import com.cos.photogramstart.web.dto.image.ImageUploadDTO;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class ImageService {
	
	private final ImageRepository imageRepository;
	
	@Value("${file.path}")
	private String uploadFolder;
	
	public void pictureUpload(ImageUploadDTO imageUploadDTO ,PrincipalDetails principalDetails) {
		//파일명이 똑같게 되면... 폴더에 똑같은 이름으로 들어간다...
		//UUID란 ? 네트워크 상에서 고유성이 보장되는 ID를 만들기위한 표준 규약
		UUID uuid = UUID.randomUUID();
		
		//실제 파일 이름
		String imageFileName = uuid+"_"+imageUploadDTO.getFile().getOriginalFilename();   //1..jpg
		System.out.println("이미지 파일이름 :"+imageFileName);

		Path imageFilePath = Paths.get(uploadFolder+imageFileName);
		
		//통신, I/O -> 예외가 발생할 수 있다.
		try {
			Files.write(imageFilePath,imageUploadDTO.getFile().getBytes());
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		// image 테이블에 저장 
		Image image =  imageUploadDTO.toEntity(imageFileName, principalDetails.getUser());
		Image imageEntity = imageRepository.save(image);
		
		
	}
}












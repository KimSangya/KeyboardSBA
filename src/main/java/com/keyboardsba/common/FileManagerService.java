package com.keyboardsba.common;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component 
public class FileManagerService {
	  public static final String FILE_UPLOAD_PATH = "D:\\kimgeonho\\6_spring_project\\keyboardSBA\\workspace\\images/"; // 학원
	 // public static final String FILE_UPLOAD_PATH = "D:\\KimGeonHo\\6_springProject\\keyboardSBA\\workspace\\images/"; // 노트북, 상수라는 의미
	 // public static final String FILE_UPLOAD_PATH = "E:\\kimgeonho\\6_SpringProject\\KeyboardSBA\\workspace\\images/"; // 데탑
	
	
	public String uploadFile(MultipartFile file, String loginId) {
		
		String directoryName = loginId + "_" + System.currentTimeMillis();
		String filePath = FILE_UPLOAD_PATH + directoryName + "/";
		
		File directiory = new File(filePath);
		if(directiory.mkdir() == false) { 
			return null;
		}
		
		try {
			byte[] bytes = file.getBytes();
			Path path = Paths.get(filePath + file.getOriginalFilename());
			Files.write(path, bytes);
		} catch (IOException e) {
			e.printStackTrace();
			return null; 
		}
		return "/images/" + directoryName + "/" + file.getOriginalFilename();
		
	}
	

	public void deleteFile(String imagePath) {
		Path path = Paths.get(FILE_UPLOAD_PATH + imagePath.replace("/images/", ""));
		
		if(Files.exists(path)) {
			try {
				// 파일 삭제
				Files.delete(path);
			} catch (IOException e) {
				log.info("[FileManagerService 파일 삭제] 삭제 실패. path:{}", path.toString());
				return;
			}
			
			// 폴더(directory) 삭제
			path = path.getParent(); // 부모로드
			if(Files.exists(path)) {
				try {
					Files.delete(path);
				} catch (IOException e) {
					log.info("[FileManagerService 폴더 삭제] 삭제 실패. path:{}", path.toString());
					return;
				}
			}
		}
		
	}
}

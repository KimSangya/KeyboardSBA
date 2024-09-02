package com.keyboardsba.alert.bo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.keyboardsba.alert.domain.Alert;
import com.keyboardsba.alert.mapper.AlertMapper;
import com.keyboardsba.common.FileManagerService;
import com.keyboardsba.item.domain.Item;

@Service
public class AlertBO {
	
	@Autowired
	private FileManagerService fileManagerService;
	
	@Autowired
	private AlertMapper alertMapper;
	
	public void addAlert(int userId, String userLoginId,
			String subject, String content, MultipartFile file) {
		// userLoginId는 따로 저장하려고 보내주는 값이라고 생각하면 됨.
		
		String imagePath = null;
		if(file != null) {
			// 업로드 할 이미지가 있을때에만 업로드를 해주는 것이다.
			// 이미지 업로드할 기능마다 족족 다 바꿔줘야하니, 코드가 중복되는 방식을 막으려면 class를 만들어서 보완하게 하면 된다.
			imagePath = fileManagerService.uploadFile(file, userLoginId);
		}
		
		alertMapper.insertAlert(userId, subject, content, imagePath);
	}
	
	public List<Alert> getAlertList() {
		List<Alert> AlertList = alertMapper.selectAlertList();
		return AlertList;
	}
}

package com.keyboardsba.alert;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.keyboardsba.alert.bo.AlertBO;

import jakarta.servlet.http.HttpSession;

@RequestMapping("/alert")
@RestController
public class AlertRestController {
	
	@Autowired
	private AlertBO alertBO;
	
	@PostMapping("/create")
	public Map<String, Object> alertCreate(
			@RequestParam("subject") String subject,
			@RequestParam("content") String content,
			@RequestParam(value = "file", required = false) MultipartFile file,
			HttpSession session
			) {
		
		// 글쓴이 번호를 session에서 꺼낸다.
		int userId = (int)session.getAttribute("userId");
		String userLoginId = (String)session.getAttribute("loginId");
		
		// db insert
		alertBO.addAlert(userId, userLoginId, subject, content, file);
		
		// 응답값
		Map<String, Object> result = new HashMap<>();
		result.put("code", 200);
		result.put("result", "성공");
		
		return result;
	}
	
	@DeleteMapping("/delete")
	public Map<String, Object> alertDelete(
			@RequestParam("alertId") int alertId,
			HttpSession session){
		
		alertBO.deleteAlert(alertId);
		
		Map<String, Object> result = new HashMap<>();
		result.put("code", 200);
		result.put("result", "성공");
		
		return result;
	}
	
}

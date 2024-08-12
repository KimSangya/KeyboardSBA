package com.keyboardsba.item;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Update;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.keyboardsba.item.bo.ItemBO;
import com.keyboardsba.user.bo.UserBO;
import com.keyboardsba.user.entity.UserEntity;

import jakarta.servlet.http.HttpSession;

@RequestMapping("/item")
@RestController
public class ItemRestController {
	
	@Autowired
	private ItemBO itemBO;
	
	@PostMapping("/create") // 완료
	public Map<String, Object> create(@RequestParam("productName") String productName,
			@RequestParam("productPrice") int productPrice,
			@RequestParam("productStatus") String productStatus,
			@RequestParam("productValue") String productValue,
			@RequestParam("writeTextArea") String writeTextArea,
			@RequestParam("file") MultipartFile file,
			HttpSession session) {
		
	
		Integer userId = (Integer)session.getAttribute("userId");
		String loginId = (String)session.getAttribute("loginId");
		
		// 응답 값 처리
		Map<String, Object> result = new HashMap<>();
		
		if(userId == null) {
			result.put("code", "403");
			result.put("error_message", "로그인을 먼저 해주세요");
		}
		
		// db 처리
		itemBO.addItem(userId, loginId, productName, productPrice, productStatus, productValue,
				writeTextArea, file);
		
		result.put("code", 200);
		result.put("result", "성공");
		
		return result;
	}
	
	@PostMapping("/contact")
	public Map<String, Object> contact(
			@RequestParam("contactId") int contactId,
			HttpSession session) {
		
		Map<String, Object> result = new HashMap<>();
		Map<String, Object> list = itemBO.selectUserId(contactId);
		
		String name = (String) list.get("name");
		String phoneNumber = (String) list.get("phoneNumber");
		String email = (String) list.get("email");
		
		String modalContent = "<p>이름: " + name + "</p>" +
                "<p>전화번호: " + phoneNumber + "</p>" + 
				"<p>이메일 : " + email + "</p>";
		
		result.put("code", 200);
		result.put("result", modalContent);
		return result;
	}
	
	@PutMapping("/update") // 완료
	public Map<String, Object> update(@RequestParam("itemId") int itemId,
			@RequestParam("productName") String productName,
			@RequestParam("productPrice") int productPrice,
			@RequestParam("productStatus") String productStatus,
			@RequestParam("productValue") String productValue,
			@RequestParam("writeTextArea") String writeTextArea,
			@RequestParam(value = "time", required = false) String time,
			@RequestParam("file") MultipartFile file,
			HttpSession session) {
		
	
		Integer userId = (Integer)session.getAttribute("userId");
		String loginId = (String)session.getAttribute("loginId");
		
		// 응답 값 처리
		Map<String, Object> result = new HashMap<>();
		
		if(userId == null) {
			result.put("code", "403");
			result.put("error_message", "로그인을 먼저 해주세요");
		}
		
		// db 처리
		itemBO.updateItem(itemId, userId, loginId, productName, productPrice, productStatus, productValue,
				writeTextArea, time, file);
		
		result.put("code", 200);
		result.put("result", "성공");
		
		return result;
	}
}

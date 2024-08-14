package com.keyboardsba.chat;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.keyboardsba.chat.bo.ChatBO;

import jakarta.servlet.http.HttpSession;

@RequestMapping("/chat")
@RestController
public class ChatRestController {

	@Autowired
	private ChatBO chatBO;
	
	@PostMapping("/create")
	public Map<String, Object> create(
			@RequestParam("itemId") int itemId,
			@RequestParam("content") String content,
			HttpSession session) {
		
		Map<String, Object> result = new HashMap<>();
		
		Integer userId = (Integer)session.getAttribute("userId");
		String loginId = (String)session.getAttribute("loginId");
		
		if(userId == null) {
			result.put("code", 403);
			result.put("error_message", "로그인을 해주세요.");
		} 
		
		chatBO.addChat(itemId, userId, loginId, content);
		
		result.put("code", 200);
		result.put("result", "성공");
		
		return result;
	}
}

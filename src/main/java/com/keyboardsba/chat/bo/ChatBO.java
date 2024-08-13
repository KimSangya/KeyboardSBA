package com.keyboardsba.chat.bo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.keyboardsba.chat.domain.Chat;
import com.keyboardsba.chat.mapper.ChatMapper;

@Service
public class ChatBO {
	
	@Autowired
	private ChatMapper chatMapper;
	
	
	public void addChat(int itemId, int userId, String content) {
		chatMapper.insertChat(itemId, userId, content);
	}
	
	public List<Chat> getChatListByItemId(int itemId) {
		return chatMapper.selectChatList(itemId);
	}
}

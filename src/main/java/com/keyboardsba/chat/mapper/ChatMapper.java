package com.keyboardsba.chat.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.keyboardsba.chat.domain.Chat;

@Mapper
public interface ChatMapper {

	public void insertChat(
			@Param("itemId") int itemId,
			@Param("userId") int userId,
			@Param("loginId") String loginId,
			@Param("content") String content);
	
	public List<Chat> selectChatList(int itemId);
}

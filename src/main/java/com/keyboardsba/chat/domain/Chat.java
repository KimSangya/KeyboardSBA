package com.keyboardsba.chat.domain;

import java.time.LocalDateTime;

import lombok.Data;
import lombok.ToString;

@ToString
@Data
public class Chat {

	private int id;
	private int userId;
	private int itemId;
	private String content;
	private LocalDateTime createdAt;
	private LocalDateTime updatedAt;
	
}

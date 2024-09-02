package com.keyboardsba.alert.domain;

import java.time.LocalDateTime;

import lombok.Data;
import lombok.ToString;

@ToString
@Data
public class Alert {
	
	private int id;
	private int userId;
	private String subject;
	private String content;
	private LocalDateTime createdAt;
	private LocalDateTime updatedAt;
}

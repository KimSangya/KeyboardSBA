package com.keyboardsba.post.domain;

import java.time.LocalDateTime;

import lombok.Data;
import lombok.ToString;

@ToString // 안쪽의 내용을 보여주게 되는 친구
@Data // 따로 데이터 어노테이션이 붙게 되면 알아서 getter, setter를 만들어주게 된다.
public class Post {
	private int id;
	private int userId;
	private String subject;
	private String content;
	private String imageUrl;
	private LocalDateTime createdAt;
	private LocalDateTime updatedAt;
	
}
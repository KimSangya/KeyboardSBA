package com.keyboardsba.item.domain;

import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.format.annotation.DateTimeFormat;

import com.keyboardsba.user.entity.UserEntity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@ToString
@Data
public class Item {
	private int id;
	private String loginId;
	private int userId;
	private String title;
	private int price;
	private String status;
	private String type;
	private String content;
	private LocalDateTime time;
	private String imageUrl;
	private LocalDateTime createdAt;
	private LocalDateTime updatedAt;
}

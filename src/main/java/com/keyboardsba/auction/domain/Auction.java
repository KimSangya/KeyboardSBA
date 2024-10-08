package com.keyboardsba.auction.domain;

import java.time.LocalDateTime;

import lombok.Data;
import lombok.ToString;

@ToString
@Data
public class Auction {

	private int id;
	private int userId;
	private String loginId;
	private int itemId;
	private int paid;
	private LocalDateTime createdAt;
	private LocalDateTime updatedAt;
	
}

package com.keyboardsba.auction.bo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.keyboardsba.common.FileManagerService;
import com.keyboardsba.item.bo.ItemBO;
import com.keyboardsba.item.domain.Item;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class AuctionBO {
	
	@Autowired
	private ItemBO itemBO;
	
	public List<Item> getItemListByType(String type) {
		return itemBO.getItemListByType(type);
	}
	
	public Item getItemByItemId(int itemId) {
		return itemBO.getItemByItemId(itemId);
	}
	
	public Item getItemByItemIdAndUserId(int itemId, int userId) {
		return itemBO.getItemByItemIdAndUserId(itemId, userId);
	}
	
	public void updateItem(int itemId, int userId, String loginId, String productName, int productPrice, 
			String productStatus, String productValue, String writeTextArea, String time,  MultipartFile file) {	
		itemBO.updateItem(itemId, userId, loginId, productName, productPrice, productStatus, productValue, writeTextArea, time, file);
	}
}
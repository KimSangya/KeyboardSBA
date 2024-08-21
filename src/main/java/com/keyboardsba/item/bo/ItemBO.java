package com.keyboardsba.item.bo;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.keyboardsba.common.FileManagerService;
import com.keyboardsba.item.domain.Item;
import com.keyboardsba.item.mapper.ItemMapper;
import com.keyboardsba.user.bo.UserBO;

import lombok.extern.slf4j.Slf4j;


@Slf4j
@Service
public class ItemBO {

	@Autowired
	private FileManagerService fileManagerService;
	
	@Autowired
	private ItemMapper itemMapper;
	
	@Autowired
	private UserBO userBO;
	
	
	public void addItem(int userId, String loginId, String productName, int productPrice, 
		String productStatus, String productValue, String writeTextArea, LocalDateTime time, MultipartFile file) {
		
		String imagePath = null;
		if(file != null) {
			imagePath = fileManagerService.uploadFile(file, loginId);
		}
		
		itemMapper.insertItem(userId, loginId, productName, productPrice, productStatus, productValue, time, writeTextArea, imagePath);
	}
	
	public void updateItem(int itemId, int userId, String loginId, String productName, int productPrice, 
			String productStatus, String productValue, String writeTextArea, LocalDateTime time, MultipartFile file) {
			
			Item item = itemMapper.selectItemByItemIdAndUserId(itemId, userId);
			
			if (item == null) {
				log.warn("[글 수정] post is null. userId:{}, itemId:{}", userId, itemId);
				return;
			}
			
			String imagePath = null;
			
			
			if(file != null) {
				imagePath = fileManagerService.uploadFile(file, loginId);
				if(imagePath != null && item.getImageUrl() != null) {
					fileManagerService.deleteFile(item.getImageUrl());
				}
			}
			
			itemMapper.updateItem(itemId, userId, loginId, productName, productPrice, productStatus, productValue, writeTextArea, time, imagePath);
		}
	
	public List<Item> getItemListByType(String type) {
		List<Item> ItemList = itemMapper.selectItemListByType(type);
		return ItemList;
	}
	
	public Item getItemByItemId(int itemId) {
		return itemMapper.selectItemByItemId(itemId);
	}
	
	public Item getItemByItemIdAndUserId(int itemId, int userId) {
		return itemMapper.selectItemByItemIdAndUserId(itemId, userId);
	}
	
	public int getItemByUserId(int contactId) {
		return itemMapper.selectUserId(contactId);
	}
	
	public Map<String, Object> selectUserId(int contactId) {
		int userId = getItemByUserId(contactId);
		return userBO.selectUserId(userId);
	}
}

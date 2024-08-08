package com.keyboardsba.item.bo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.keyboardsba.common.FileManagerService;
import com.keyboardsba.item.domain.Item;
import com.keyboardsba.item.mapper.ItemMapper;

@Service
public class ItemBO {

	@Autowired
	private FileManagerService fileManagerService;
	
	@Autowired
	private ItemMapper itemMapper;
	
	public void addItem(int userId, String loginId, String productName, int productPrice, 
		String productStatus, String productValue, String writeTextArea, MultipartFile file) {
		
		String imagePath = null;
		if(file != null) {
			imagePath = fileManagerService.uploadFile(file, loginId);
		}
		
		itemMapper.insertItem(userId, loginId, productName, productPrice, productStatus, productValue, writeTextArea, imagePath);
	}
	
	public List<Item> getItemListByType(String type) {
		List<Item> ItemList = itemMapper.selectItemListByType(type);
		return ItemList;
	}
	
	public Item getItemByItemId(int itemId) {
		return itemMapper.selectItemByItemId(itemId);
	}
	
}

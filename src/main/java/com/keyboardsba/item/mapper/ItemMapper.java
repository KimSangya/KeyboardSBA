package com.keyboardsba.item.mapper;

import java.time.LocalDateTime;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.keyboardsba.item.domain.Item;
import com.keyboardsba.user.entity.UserEntity;

@Mapper
public interface ItemMapper {
	
	public void insertItem(
			@Param("userId") int userId,
			@Param("loginId") String loginId,
			@Param("productName") String productName, 
			@Param("productPrice") int productPrice, 
			@Param("productStatus") String productStatus, 
			@Param("productValue") String productValue,
			@Param("time") LocalDateTime time,
			@Param("writeTextArea") String writeTextArea, 
			@Param("imagePath") String imagePath);
	
	public void updateItem(
			@Param("itemId") int itemId,
			@Param("userId") int userId,
			@Param("loginId") String loginId,
			@Param("productName") String productName, 
			@Param("productPrice") int productPrice, 
			@Param("productStatus") String productStatus, 
			@Param("productValue") String productValue, 
			@Param("writeTextArea") String writeTextArea, 
			@Param("time") LocalDateTime time,
			@Param("imagePath") String imagePath);
	
	public List<Item> selectItemListByType(String type);
	
	public List<Item> selectItemList();
	
	public Item selectItemByItemId(int itemId);
	
	public Item selectItemByItemIdAndUserId(
			@Param("itemId") int itemId,
			@Param("userId") int userId);
	
	
	public int selectUserId(int contactId);
	
	public void deleteItem(int itemId);
	
	public void deleteItemDetail(
			@Param("itemId") int itemId,
			@Param("userId") int userId);
}

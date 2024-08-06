package com.keyboardsba.item.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.keyboardsba.item.domain.Item;

@Mapper
public interface ItemMapper {
	
	public void insertItem(
			@Param("userId") int userId,
			@Param("loginId") String loginId,
			@Param("productName") String productName, 
			@Param("productPrice") int productPrice, 
			@Param("productStatus") String productStatus, 
			@Param("productValue") String productValue, 
			@Param("writeTextArea") String writeTextArea, 
			@Param("imagePath") String imagePath);
	
	public List<Item> selectItemListByType(String type);
}

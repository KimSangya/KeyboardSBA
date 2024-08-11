package com.keyboardsba.auction.bo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.keyboardsba.item.bo.ItemBO;
import com.keyboardsba.item.domain.Item;

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
}

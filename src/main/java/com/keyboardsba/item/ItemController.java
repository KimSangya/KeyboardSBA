package com.keyboardsba.item;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.keyboardsba.item.bo.ItemBO;
import com.keyboardsba.item.domain.Item;

import jakarta.servlet.http.HttpSession;

@RequestMapping("/item")
@Controller
public class ItemController {
	
	@Autowired
	private ItemBO itemBO;
	
	@GetMapping("/item-list-view")
	public String itemList(
			Model model, HttpSession session) {
		
		List<Item> itemKeyBoardList = itemBO.getItemListByType("keyboard"); 
		List<Item> itemKeyCapList = itemBO.getItemListByType("키캡"); 
		List<Item> itemKeyTradeList = itemBO.getItemListByType("교환"); 
		
		model.addAttribute("itemKeyBoardList", itemKeyBoardList);
		model.addAttribute("itemKeyCapList", itemKeyCapList);
		model.addAttribute("itemKeyTradeList", itemKeyTradeList);
		
		return "item/itemList";
	}
	
	@GetMapping("/item-keyboard-view")
	public String Keyboard() {
		return "item/keyboard";
	}
	
	@GetMapping("/item-keycap-view")
	public String Keycap() {
		return "item/keycap";
	}
	
	@GetMapping("/item-trade-view")
	public String Trade() {
		return "item/trade";
	}
	
	@GetMapping("/item-create-view")
	public String Create() {
		return "item/create";
	}
	
	@GetMapping("/item-detail-view")
	public String Detail() {
		return "item/detail";
	}
}

package com.keyboardsba.item;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

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
		List<Item> itemKeyCapList = itemBO.getItemListByType("keycap"); 
		List<Item> itemKeyTradeList = itemBO.getItemListByType("trade"); 
		List<Item> auctionList = itemBO.getItemListByType("auction");

		model.addAttribute("itemKeyBoardList", itemKeyBoardList);
		model.addAttribute("itemKeyCapList", itemKeyCapList);
		model.addAttribute("itemKeyTradeList", itemKeyTradeList);
		model.addAttribute("auctionList", auctionList);
		
		return "item/itemList";
	}
	
	@GetMapping("/item-keyboard-view")
	public String Keyboard(Model model, HttpSession session) {
		List<Item> itemKeyBoardList = itemBO.getItemListByType("keyboard"); 
		model.addAttribute("itemKeyBoardList", itemKeyBoardList);
		return "item/keyboard";
	}
	
	@GetMapping("/item-keycap-view")
	public String Keycap(Model model, HttpSession session) {
		List<Item> itemKeyCapList = itemBO.getItemListByType("keycap"); 
		model.addAttribute("itemKeyCapList", itemKeyCapList);
		return "item/keycap";
	}
	
	@GetMapping("/item-trade-view")
	public String Trade(Model model, HttpSession session) {
		List<Item> itemTradeList = itemBO.getItemListByType("trade");
		model.addAttribute("itemTradeList", itemTradeList);
		return "item/trade";
	}
	
	@GetMapping("/item-create-view")
	public String Create() {
		return "item/create";
	}
	
	@GetMapping("/item-detail-view")
	public String Detail(
			@RequestParam("itemId") int itemId,
			Model model, HttpSession session) {
		
		Integer userId = (Integer)session.getAttribute("userId");
		
		if(userId == null) {
			return "redirect:/";
		}
		
		Item item = itemBO.getItemByItemId(itemId);
		
		model.addAttribute("item", item);
		return "item/detail";
	}
	
	@GetMapping("/item-update-view")
	public String Update(
			@RequestParam("itemId") int itemId,
			Model model, HttpSession session ) {
		
		Integer userId = (Integer)session.getAttribute("userId");
		
		if(userId == null) {
			return "redirect:/";
		}
		
		Item item = itemBO.getItemByItemIdAndUserId(itemId, userId);
		
		model.addAttribute("item", item);
		
		return "item/update";
	
	}
	
}

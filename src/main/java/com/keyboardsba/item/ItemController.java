package com.keyboardsba.item;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/item")
@Controller
public class ItemController {
	
	@GetMapping("/item-list-view")
	public String itemList() {
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

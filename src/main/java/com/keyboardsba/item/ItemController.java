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
}

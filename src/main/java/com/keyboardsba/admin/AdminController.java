package com.keyboardsba.admin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/admin")
@Controller
public class AdminController {

	@GetMapping("/item-list-view")
	public String ItemListView() {
		return "admin/itemList";
	}
	
	@GetMapping("/user-list-view")
	public String UserListView() {
		return "admin/userList";
	}
	
	@GetMapping("/alert-list-view")
	public String KeyboardListView() {
		return "admin/alertList";
	}
	
	@GetMapping("/post-list-view")
	public String PostListView() {
		return "admin/postList";
	}
}

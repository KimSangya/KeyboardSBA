package com.keyboardsba.admin;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.keyboardsba.admin.bo.AdminBO;
import com.keyboardsba.alert.domain.Alert;
import com.keyboardsba.item.domain.Item;
import com.keyboardsba.post.domain.Post;
import com.keyboardsba.user.entity.UserEntity;

@RequestMapping("/admin")
@Controller
public class AdminController {
	
	@Autowired
	private AdminBO adminBO;
	
	@GetMapping("/alert")
	public String alertList(Model model) {
		List<Alert> alertList = adminBO.getAlertList();
		model.addAttribute("alertList", alertList);
		return "admin/alertList";
	}
	
	@GetMapping("/item")
	public String itemList(Model model) {
		List<Item> itemList = adminBO.getItemList();
		model.addAttribute("itemList", itemList);
		return "admin/itemList";
	}
	
	@GetMapping("/post")
	public String postList(Model model) {
		List<Post> postList = adminBO.getPostList();
		model.addAttribute("postList", postList);
		return "admin/postList";
	}
	
	@GetMapping("/user")
	public String userList(Model model) {
		List<UserEntity> userList = adminBO.getUserList();
		model.addAttribute("userList", userList);
		return "admin/userList";
	}
	
}

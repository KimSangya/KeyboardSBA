package com.keyboardsba.user;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/user")
@Controller
public class UserController {
	
	@GetMapping("/sign-up-view")
	public String signUp() {
		return "user/signUp";
	}
	
	@GetMapping("/sign-in-view")
	public String signIn() {
		return "user/signIn";
	}
}

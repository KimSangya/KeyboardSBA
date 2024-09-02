package com.keyboardsba.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.keyboardsba.kakao.bo.KakaoBO;

import jakarta.servlet.http.HttpSession;

@RequestMapping("/user")
@Controller
public class UserController {
	
	@Autowired
	private KakaoBO kakaoBO;
	
	@Value("${spring.security.oauth2.client.registration.kakao.client-id}")
	private String client_id;

	@Value("${spring.security.oauth2.client.registration.kakao.redirect-uri}")
	private String redirect_uri;
	
	@GetMapping("/sign-up-view")
	public String signUp() {
		
		return "user/signUp";
	}
	
	@GetMapping("/sign-in-view")
	public String signIn(Model model) {
		String location = "https://kauth.kakao.com/oauth/authorize?response_type=code&client_id="+client_id+"&redirect_uri="+redirect_uri;
        model.addAttribute("location", location);
		return "user/signIn";
	}
	
	@RequestMapping("/sign-out")
	public String signOut(HttpSession session) {
		// session 내용 비움
		session.removeAttribute("userId"); // 키 이름을 넣으면 되는 것이다.
		session.removeAttribute("loginId");
		session.removeAttribute("userName");
		session.removeAttribute("admin");
		
		// 로그인 페이지로 이동
		return "redirect:/user/sign-in-view";
	}
}

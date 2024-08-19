package com.keyboardsba.auction;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.keyboardsba.auction.bo.AuctionBO;
import com.keyboardsba.chat.domain.Chat;
import com.keyboardsba.item.domain.Item;

import jakarta.servlet.http.HttpSession;

@RequestMapping("/auction")
@RestController
public class AuctionRestController {
	
	@Autowired
	private AuctionBO auctionBO;
	
	@PutMapping("/update") // 완료
	public Map<String, Object> update(@RequestParam("itemId") int itemId,
			@RequestParam("productName") String productName,
			@RequestParam("productPrice") int productPrice,
			@RequestParam("productStatus") String productStatus,
			@RequestParam("productValue") String productValue,
			@RequestParam("writeTextArea") String writeTextArea,
			@RequestParam("time") String time,
			@RequestParam("file") MultipartFile file,
			HttpSession session) {
		
	
		Integer userId = (Integer)session.getAttribute("userId");
		String loginId = (String)session.getAttribute("loginId");
		
		// 응답 값 처리
		Map<String, Object> result = new HashMap<>();
		
		if(userId == null) {
			result.put("code", "403");
			result.put("error_message", "로그인을 먼저 해주세요");
		}
		
		// db 처리
		auctionBO.updateItem(itemId, userId, loginId, productName, productPrice, productStatus, productValue,
				writeTextArea, time, file);
			
		result.put("code", 200);
		result.put("result", "성공");
		
		return result;
	}
	
	@PostMapping("/create") // 완료
	public Map<String, Object> create(@RequestParam("productName") String productName,
			@RequestParam("productPrice") int productPrice,
			@RequestParam("productStatus") String productStatus,
			@RequestParam("productValue") String productValue,
			@RequestParam("writeTextArea") String writeTextArea,
			@RequestParam("time") String time,
			@RequestParam("file") MultipartFile file,
			HttpSession session) {
		
	
		Integer userId = (Integer)session.getAttribute("userId");
		String loginId = (String)session.getAttribute("loginId");
		
		// 응답 값 처리
		Map<String, Object> result = new HashMap<>();
		
		if(userId == null) {
			result.put("code", "403");
			result.put("error_message", "로그인을 먼저 해주세요");
		}
		
		// db 처리
		auctionBO.addItem(userId, loginId, productName, productPrice, productStatus, productValue,
				writeTextArea, time, file);
		
		result.put("code", 200);
		result.put("result", "성공");
		
		return result;
	}
	
	@PostMapping("/paid")
	public Map<String, Object> paid(
			@RequestParam("itemId") int itemId,
			@RequestParam("content") String content,
			HttpSession session) {
		
		Map<String, Object> result = new HashMap<>();
		
		Integer userId = (Integer)session.getAttribute("userId");
		String loginId = (String)session.getAttribute("loginId");
		
		if(userId == null) {
			result.put("code", 403);
			result.put("error_message", "로그인을 해주세요.");
		} 
		
		// 만약 가격이 낮을 경우
		int paid = (int)Integer.parseInt(content);
		int maxPrice = auctionBO.getItemPaidByItemId(itemId);
		
		int diff = paid - maxPrice;
		
		if(maxPrice > paid) {
			result.put("code", 400);
			result.put("error_message", "최대 입찰가보다 가격이 낮습니다.");
			return result;
		} 
		
		if(diff < 5000) {
			result.put("code", 400);
			result.put("error_message", "최대 가격보다 5000원 이상의 가격을 적어주세요.");
			return result;
		}
		
		auctionBO.addPaid(itemId, userId, loginId, paid);
		
		result.put("code", 200);
		result.put("result", "성공");
		
		return result;
	}
}

package com.keyboardsba.auction;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.keyboardsba.auction.bo.AuctionBO;
import com.keyboardsba.auction.domain.Auction;
import com.keyboardsba.chat.domain.Chat;
import com.keyboardsba.item.domain.Item;

import jakarta.servlet.http.HttpSession;

@RequestMapping("/auction")
@Controller
public class AuctionController {
	
	@Autowired
	private AuctionBO auctionBO;
	
	@GetMapping("/auction-list-view")
	public String PostListView(Model model, HttpSession session) {
		List<Item> auctionList = auctionBO.getItemListByType("auction");
		model.addAttribute("auctionList", auctionList);
		
		return "auction/auctionList";
	}
	
	@GetMapping("/auction-create-view")
	public String Create() {
		return "auction/create";
	}
	
	@GetMapping("/auction-detail-view")
	public String Detail(
			@RequestParam("itemId") int itemId,
			Model model, HttpSession session) {
		
		Integer userId = (Integer)session.getAttribute("userId");
		
		if(userId == null) {
			return "redirect:/";
		}
		
		Item item = auctionBO.getItemByItemId(itemId);
		
		model.addAttribute("item", item);
		return "auction/detail";
	}
	
	@GetMapping("/auction-update-view")
	public String Update(
			@RequestParam("itemId") int itemId,
			Model model, HttpSession session ) {
		
		Integer userId = (Integer)session.getAttribute("userId");
		
		if(userId == null) {
			return "redirect:/";
		}
		
		Item item = auctionBO.getItemByItemIdAndUserId(itemId, userId);
		
		model.addAttribute("item", item);
		
		return "auction/update";
	
	}
	
	@GetMapping("/chat") 
	public String chat(@RequestParam("chatId") int itemId,
			Model model, HttpSession session) {
		
		Item item = auctionBO.getItemByItemId(itemId);
		List<Chat> chatList = auctionBO.getChatListByItemId(itemId);
		
		model.addAttribute("item", item);
		model.addAttribute("chatList", chatList);
		
		return "auction/chat";
	}
	
	@GetMapping("/paid") 
	public String paid(@RequestParam("paidId") int itemId,
			Model model, HttpSession session) {
		
		Item item = auctionBO.getItemByItemId(itemId);
		List<Auction> paidList = auctionBO.getAuctionListByItemId(itemId);
		
		model.addAttribute("item", item);
		model.addAttribute("paidList", paidList);
		
		return "auction/paid";
	}
}

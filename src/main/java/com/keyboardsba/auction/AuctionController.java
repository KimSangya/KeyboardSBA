package com.keyboardsba.auction;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/auction")
@Controller
public class AuctionController {
	
	@GetMapping("/auction-list-view")
	public String PostListView() {
		return "auction/auctionList";
	}
}

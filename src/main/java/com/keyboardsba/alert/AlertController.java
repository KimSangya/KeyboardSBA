package com.keyboardsba.alert;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/alert")
@Controller
public class AlertController {
	
	@GetMapping("/alert-detail-view")
	public String alert() {
		return "alert/alertCreate";
	}
}
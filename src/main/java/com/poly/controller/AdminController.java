package com.poly.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class AdminController {
	
	@RequestMapping("/elise/admin")
	public String index() {
		return "redirect:/assets/manager/index.html";
	}
}

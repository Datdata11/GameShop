package com.poly.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.poly.service.UserService;

@Controller
public class AuthController {
	@Autowired
	UserService userService;

	@RequestMapping("/elise/login")
	public String login() {
		return "user/login";
	}

	@RequestMapping("/elise/login/success")
	public String success(Model model, HttpServletRequest request) {
		model.addAttribute("message", "Đăng nhập thành công!");
		System.out.println(request.isUserInRole("MAN"));
		return "forward:/elise/login";
	}

	@RequestMapping("/elise/login/error")
	public String error(Model model) {
		model.addAttribute("message", "Thông tin đăng nhập chưa chính xác!");
		return "forward:/elise/login";
	}
	
	@RequestMapping("/elise/logoff/success")
	public String logoff(Model model) {
		model.addAttribute("message", "Đăng xuất thành công!");
		return "forward:/elise/login";
	}

	@RequestMapping("auth/access/denied")
	public String access(Model model) {
		model.addAttribute("message", "Bạn không có quyền truy cập!");
		return "forward:/elise/login";
	}

	// OAuth2
	@RequestMapping("/elise/oauth2/login/success")
	public String success(OAuth2AuthenticationToken oauth2) {
		userService.loginFromOAuth2(oauth2);
		return "forward:/elise/login/success";
	}

}

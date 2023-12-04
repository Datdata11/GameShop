package com.poly.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.poly.entity.Account;
import com.poly.service.AccountService;
import com.poly.service.OrderService;

@Controller
public class AccountController {
	@Autowired
	HttpServletRequest request;
	@Autowired
	AccountService accService;
	@Autowired
	OrderService orderService;

	@RequestMapping("/elise/account/checkout")
	public String checkout() {
		return "user/checkout";
	}

	@GetMapping("/elise/account/profile")
	public String profile(Model model) {
		model.addAttribute("account", getLogAcc());
		return "user/profile";
	}

	@PostMapping("/acount/profile/change")
	public String updateProfile(Model model, @Validated @ModelAttribute("account") Account account,
			BindingResult result) {
		if (result.hasErrors()) {
			model.addAttribute("message", "Kiểm tra lại thông tin nhập");
		} else {
			accService.update(account);
			model.addAttribute("message", "Cập nhật thành công");
		}
		return "user/profile";
	}

	@GetMapping("/elise/account/order-history")
	public String history(Model model) {
		model.addAttribute("orderHistory", orderService.findByAccount(getLogAcc()));
		return "user/history";
	}

	@ModelAttribute("logAcc")
	public Account getLogAcc() {
		Account acc = new Account();
		try {
			// đăng nhập bằng form
			acc = accService.findById(request.getRemoteUser());
		} catch (Exception e) {
			// đăng nhập bằng Google
			acc = accService.findByEmail(request.getRemoteUser());
		} finally {
			// đăng nhập bằng Facebook
			Map<String, Object> userDetails = ((DefaultOAuth2User) SecurityContextHolder.getContext()
					.getAuthentication().getPrincipal()).getAttributes();
			String email = (String) userDetails.get("email");
			acc = accService.findByEmail(email);
		}
		return acc;
	}
}

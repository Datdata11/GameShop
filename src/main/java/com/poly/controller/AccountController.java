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
	public String checkout(Model model) {
		model.addAttribute("logAcc", getLogAcc());
		return "user/checkout";
	}
	
	@GetMapping("/elise/account/checkout/update")
	public String updateProfile() {
		return "redirect:/elise/account/profile";
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
	
	@GetMapping("/elise/account/change-password")
	public String changePassword() {
		return "user/change-password";
	}
	
	@PostMapping("/elise/account/change-password")
	public String changePassword(Model model) {
		String password = request.getParameter("password");
		String newPassword = request.getParameter("newPassword");
		String confirmPassword = request.getParameter("confirmPassword");
		System.out.println(password + newPassword + confirmPassword);
		if (!newPassword.equals(confirmPassword)) {
			model.addAttribute("message", "Xác nhận mật khẩu không giống mật khẩu mới");
			return "user/change-password";
		}
		Account acc = getLogAcc();
		if (!password.equals(acc.getPassword())) {
			model.addAttribute("message", "Mật khẩu hiện tại không chính xác");
			return "user/change-password";
		}
		acc.setPassword(newPassword);
		accService.update(acc);
		model.addAttribute("message", "Thay đổi mật khẩu thành công");
		return "user/change-password";
	}

	public Account getLogAcc() {
		Account acc = new Account();
		try {
			// đăng nhập bằng form, không tìm thấy sẽ xảy ra ngoại lệ
			acc = accService.findById(request.getRemoteUser());
		} catch (Exception e) {
			try {
				// đăng nhập bằng Google, nếu không tìm thấy thì xảy ra ngoại lệ
				acc = accService.findByEmail(request.getRemoteUser());
			} catch (Exception e2) {
				// đăng nhập bằng Facebook
				Map<String, Object> userDetails = ((DefaultOAuth2User) SecurityContextHolder.getContext()
						.getAuthentication().getPrincipal()).getAttributes();
				String email = (String) userDetails.get("email");
				acc = accService.findByEmail(email);
			}
		}
		return acc;
	}
}

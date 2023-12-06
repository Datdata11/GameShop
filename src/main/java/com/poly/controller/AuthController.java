package com.poly.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.poly.entity.Account;
import com.poly.entity.MailInfo;
import com.poly.service.AccountService;
import com.poly.service.AuthorityService;
import com.poly.service.MailerService;
import com.poly.utils.service.UserService;

@Controller
public class AuthController {
	@Autowired
	UserService userService;
	@Autowired
	MailerService mailService;
	@Autowired
	AccountService accService;
	@Autowired
	AuthorityService authService;
	

	@GetMapping("/elise/forgot-password")
	public String forgotPassword(Model model) {
		Account acc = new Account();
		model.addAttribute("account", acc);
		return "user/forgot-password";
	}

	@PostMapping("/forgot-password")
	public String forgotPassword(Model model, @ModelAttribute("account") Account account) {
		String username = account.getUsername();
		String email = account.getEmail();
		if (username.length() == 0 || email.length() == 0) {
			model.addAttribute("message", "Kiểm tra lại thông tin nhập");
			return "user/forgot-password";
		}
		Account data = new Account();
		try {
			data = accService.findById(username);
			if (!data.getEmail().equalsIgnoreCase(email)) {
				throw new Exception("Email không chính xác");
			}
		} catch (Exception e) {
			model.addAttribute("message", "Tên đăng nhập hoặc email không chính xác");
			return "user/forgot-password";
		}
		try {
			MailInfo mail = new MailInfo();
			mail.setTo(email);
			mail.setSubject("<Elise> Email nhận lại mật khẩu");
			String str = "<div>"
					+ "        Thân gửi " + username + ",<br>"
					+ "        Elise Shop xin gửi bạn mật khẩu đăng nhập tài khoản:"
					+ "        <ul>"
					+ "            <li>Tên đăng nhập: " + username + "</li>"
					+ "            <li>Mật khẩu: <span style=\"color: red;\">" + data.getPassword() + "</span></li>"
					+ "        </ul>"
					+ "        Mật khẩu có khả năng bị tiết lộ, bạn nên thực hiện thay đổi mật khẩu sau khi đăng nhập."
					+ "        <br><i>Lưu ý : Đây là email tự động vui lòng không phản hồi email này.</i>"
					+ "    </div>";
			mail.setBody(str);
			mailService.queue(mail);
			model.addAttribute("message", "Đã gửi mật khẩu đến email đăng ký");
		} catch (Exception e) {
			model.addAttribute("message", "Quá trình gửi mật khẩu đã sảy ra lỗi");
		}

		return "user/forgot-password";
	}

	@GetMapping("/elise/signin")
	public String signin(Model model) {
		Account acc = new Account();
		model.addAttribute("account", acc);
		return "user/signin";
	}

	@PostMapping("/signin")
	public String signin(Model model, @Validated @ModelAttribute("account") Account account, BindingResult result) {
		if (result.hasErrors()) {
			model.addAttribute("message", "Kiểm tra lại thông tin nhập");
		} else {
			accService.create(account);			
			authService.create(account, "CUS");
			model.addAttribute("message", "Đăng ký thành công! Hãy thực hiện đăng nhập");
		}
		return "user/signin";
	}

	@RequestMapping("/elise/login")
	public String login() {
		return "user/login";
	}

	@RequestMapping("/elise/login/success")
	public String success(Model model) {
		model.addAttribute("message", "Đăng nhập thành công!");
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

	@CrossOrigin("*")
	@ResponseBody
	@RequestMapping("/elise/rest/authentication")
	public Object getAuthentication(HttpSession session) {
		return session.getAttribute("authentication");
	}

}

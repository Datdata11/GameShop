package com.poly.utils.service;

import java.util.Base64;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.stereotype.Service;

import com.poly.entity.Account;
import com.poly.service.AccountService;
import com.poly.service.AuthorityService;

@Service
public class UserService implements UserDetailsService {

	@Autowired
	AccountService accService;
	@Autowired
	AuthorityService authService;
	@Autowired
	HttpSession session;

	@Autowired
	BCryptPasswordEncoder pe;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		try {
			Account account = accService.findById(username);
			// Tạo UserDetails từ Account
			String password = pe.encode(account.getPassword());
			String[] roles = authService.findByAccount(account).stream().map(au -> au.getRole().getId())
					.collect(Collectors.toList()).toArray(new String[0]);
			//
			Map<String, Object> authentication = new HashMap<>();
			authentication.put("account", account);
			byte[] token = (username + ":" + account.getPassword()).getBytes();
			authentication.put("token", "Basic " + Base64.getEncoder().encodeToString(token));
			session.setAttribute("authentication", authentication);
			return User.withUsername(username).password(password).roles(roles).build();
		} catch (Exception e) {
			throw new UsernameNotFoundException(username + " not found.");
		}
	}

	public void loginFromOAuth2(OAuth2AuthenticationToken oauth2) {
		String email = oauth2.getPrincipal().getAttribute("email");
		UserDetails user;
		try {
			Account account = accService.findByEmail(email);
			user = loadUserByUsername(account.getUsername());
		} catch (Exception e) {
			String password = Long.toHexString(System.currentTimeMillis());
			String username = email.substring(0, email.indexOf("@")) + password;
			
			Account newAcc = new Account();
			newAcc.setUsername(username);
			newAcc.setPassword(password);
			newAcc.setFullname("");
			newAcc.setAddress("");
			newAcc.setPhone("");
			newAcc.setEmail(email);
			newAcc.setRole(false);
			accService.create(newAcc);

			authService.create(newAcc, "CUS");
			
			user = User.withUsername(email).password(pe.encode(password)).roles("CUS").build();
			Authentication auth = new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());
			SecurityContextHolder.getContext().setAuthentication(auth);
		}		
	}
}

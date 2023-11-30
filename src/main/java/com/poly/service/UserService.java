package com.poly.service;

import java.util.stream.Collectors;

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

@Service
public class UserService implements UserDetailsService {

	@Autowired
	AccountService accService;
	@Autowired
	AuthorityService authService;

	@Autowired
	BCryptPasswordEncoder pe;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		try {
			Account account = accService.findById(username);
			// Tạo UserDetails từ Account
			String password = account.getPassword();
			String[] roles = authService.findByAccount(account).stream().map(au -> au.getRole().getId())
					.collect(Collectors.toList()).toArray(new String[0]);
			return User.withUsername(username).password(pe.encode(password)).roles(roles).build();
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
			e.printStackTrace();
			String password = Long.toHexString(System.currentTimeMillis());
			String username = email.substring(0, email.indexOf("@")) + password;
			
			Account newAcc = new Account();
			newAcc.setUsername(username);
			newAcc.setPassword(pe.encode(password));
			newAcc.setFullname("");
			newAcc.setAddress("");
			newAcc.setPhone("");
			newAcc.setEmail(email);
			newAcc.setRole(false);
			accService.create(newAcc);

			authService.create(username, "CUS");
			
			user = User.withUsername(email).password(pe.encode(password)).roles("CUS").build();
			Authentication auth = new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());
			SecurityContextHolder.getContext().setAuthentication(auth);
		}		
	}
}

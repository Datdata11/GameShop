package com.poly.rest.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.poly.repository.RoleDAO;
import com.poly.entity.Authority;
import com.poly.service.AccountService;
import com.poly.service.AuthorityService;

@CrossOrigin("*")
@RestController
@RequestMapping("/elise/rest/authorities")
public class AuthorityRestController {

	@Autowired
	AuthorityService authService;

	@Autowired
	RoleDAO roleDao;

	@Autowired
	AccountService accService;

	@GetMapping()
	public Map<String, Object> getAuthorities() {
		Map<String, Object> data = new HashMap<>();
		data.put("authorities", authService.findAll());
		data.put("roles", roleDao.findAll());
		data.put("accounts", accService.findAll());
		return data;
	}

	@PostMapping()
	public Authority create(@RequestBody Authority authority) {
		return authService.create(authority.getAccount(), authority.getRole().getId());
	}

	@DeleteMapping("/{id}")
	public void delete(@PathVariable("id") Integer id) {
		authService.deleteById(id);
	}
}

package com.poly.rest.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.poly.entity.Account;
import com.poly.service.AccountService;

@CrossOrigin("*")
@RestController
@RequestMapping("/elise/rest/accounts")
public class AccountRestController {
	@Autowired
	AccountService service;
	
	@GetMapping()
	public List<Account> getAll() {
		return service.findAll();
	}

	@GetMapping("/{username}")
	public Account getOne(@PathVariable("username") String username) {
		return service.findById(username);
	}

	@PostMapping()
	public Account create(@RequestBody Account account) {
		return service.create(account);
	}

	@PutMapping("/{username}")
	public Account update(@PathVariable("username") String username, @RequestBody Account account) {
		return service.update(account);
	}
	
	@DeleteMapping("/{username}")
	public void delete(@PathVariable("username") String username) {
		service.deleteById(username);
	}
}

package com.poly.service;

import java.util.List;

import com.poly.entity.Account;
import com.poly.entity.Authority;

public interface AuthorityService {
	
	List<Authority> findByAccount(Account account);

	Authority create(String username, String role);

	List<Authority> findAll();

	void deleteById(Integer id);
	
}

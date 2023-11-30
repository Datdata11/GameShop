package com.poly.service;

import java.util.List;

import com.poly.entity.Account;

public interface AccountService {

	List<Account> findAll();

	Account findById(String username);

	Account create(Account account);

	Account update(Account account);

	void deleteById(String username);

	Account findByEmail(String email);

}

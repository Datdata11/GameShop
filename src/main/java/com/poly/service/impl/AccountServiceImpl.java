package com.poly.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.poly.entity.Account;
import com.poly.repository.AccountDAO;
import com.poly.service.AccountService;

@Service
public class AccountServiceImpl implements AccountService {
	@Autowired
	AccountDAO dao;

	@Override
	public List<Account> findAll() {
		return dao.findAll();
	}

	@Override
	public Account findById(String username) {
		return dao.findById(username).get();
	}

	@Override
	public Account create(Account account) {
		if (dao.existsById(account.getUsername())) {
			throw new RuntimeException("Duplicated primary key");
		}		
		return dao.save(account);
	}

	@Override
	public Account update(Account account) {
		if (!dao.existsById(account.getUsername())) {
			throw new RuntimeException("Not existed");
		}
		return dao.save(account);
	}

	@Override
	public void deleteById(String username) {
		dao.deleteById(username);
	}

	@Override
	public Account findByEmail(String email) {
		return dao.findByEmail(email);
	}
}

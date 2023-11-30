package com.poly.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.poly.entity.Account;
import com.poly.entity.Authority;
import com.poly.repository.AuthorityDAO;
import com.poly.service.AuthorityService;


@Service
public class AuthorityServiceImpl implements AuthorityService {
	@Autowired
	AuthorityDAO dao;

	@Override
	public List<Authority> findByAccount(Account account) {
		return dao.findByAccount(account);
	}

	@Override
	public Authority create(String username, String role) {
		return dao.save(username, role);
	}

	@Override
	public List<Authority> findAll() {
		return dao.findAll();
	}

	@Override
	public void deleteById(Integer id) {
		dao.deleteById(id);
	}

}

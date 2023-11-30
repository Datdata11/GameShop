package com.poly.repository;

import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import com.poly.entity.Order;
import com.poly.entity.Account;

public interface OrderDAO extends JpaRepository<Order, Long> {
	List<Order> findByAccount(Account account, Sort sort);	
	
	List<Order> findAll(Sort sort);

}

package com.poly.service;

import java.util.List;

import com.fasterxml.jackson.databind.JsonNode;
import com.poly.entity.Account;
import com.poly.entity.CustomerReport;
import com.poly.entity.Order;
import com.poly.entity.ProductReport;

public interface OrderService {

	List<Order> findAll();

	Order update(Order order);

	void deleteById(Long id);

	Order findById(Long id);

	List<CustomerReport> findSpentCustomers();

	List<Order> findByAccount(Account logAcc);

	Order create(JsonNode orderData);

	List<ProductReport> findSoldProduct();

	

}

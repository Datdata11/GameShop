package com.poly.rest.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.poly.entity.CustomerReport;
import com.poly.entity.ProductReport;
import com.poly.service.AccountService;
import com.poly.service.OrderService;
import com.poly.service.ProductService;

@CrossOrigin("*")
@RestController
@RequestMapping("/elise/rest/reports")
public class ReportRestController {
	@Autowired
	ProductService productService;
	@Autowired
	AccountService accountService;
	@Autowired
	OrderService orderService;
	
	@GetMapping("/sold-products")
	public List<ProductReport> soldProduct (){
		return orderService.findSoldProduct();
	}
	
	@GetMapping("/customers")
	public List<CustomerReport> spentCustomer (){
		return orderService.findSpentCustomers();
	}
}

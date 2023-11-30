package com.poly.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.poly.entity.Account;
import com.poly.entity.CustomerReport;
import com.poly.entity.Order;
import com.poly.entity.OrderDetail;
import com.poly.repository.AccountDAO;
import com.poly.repository.OrderDAO;
import com.poly.repository.OrderDetailDAO;
import com.poly.service.OrderService;

import com.fasterxml.jackson.core.type.TypeReference;

@Service
public class OrderServiceImpl implements OrderService {
	@Autowired
	OrderDAO dao;
	@Autowired
	OrderDetailDAO detailDao;
	@Autowired
	AccountDAO accDao;

	@Override
	public List<Order> findAll() {
		return dao.findAll();
	}

	@Override
	public Order update(Order order) {
		Order oldOrder = dao.findById(order.getId()).get();
		for (OrderDetail detail : oldOrder.getDetails()) {
			Boolean deleted = true;
			for (OrderDetail dt : order.getDetails()) {
				if (detail.getId() == dt.getId()) {
					detail.setOrder(order);
					detailDao.save(dt);
					deleted = false;
					break;
				}
			}
			if (deleted) {
				detailDao.delete(detail);
			}
		}
		accDao.save(order.getAccount());
		return dao.save(order);
	}

	@Override
	public void deleteById(Long id) {
		Order order = dao.findById(id).get();
		for (OrderDetail detail : order.getDetails()) {
			detailDao.delete(detail);
		}
		dao.deleteById(id);
	}

	@Override
	public Order findById(Long id) {
		return dao.findById(id).get();
	}

	@Override
	public List<CustomerReport> findSpentCustomers() {
		List<CustomerReport> list = detailDao.findAllSpentAccount();
		return list;
	}

	@Override
	public List<Order> findByAccount(Account logAcc) {
		return dao.findByAccount(logAcc, Sort.by(Direction.DESC, "id"));
	}

	@Override
	public Order create(JsonNode orderData) {
		ObjectMapper mapper = new ObjectMapper();
		
		Order order = mapper.convertValue(orderData, Order.class);
		dao.save(order);
		
		TypeReference<List<OrderDetail>> type = new TypeReference<List<OrderDetail>>() {};
		List<OrderDetail> details = mapper.convertValue(orderData.get("details"), type)
				.stream().peek(detail -> detail.setOrder(order)).collect(Collectors.toList());
		detailDao.saveAll(details);
		
		return order;
	}

}

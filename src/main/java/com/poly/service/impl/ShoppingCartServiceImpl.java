package com.poly.service.impl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.context.annotation.SessionScope;

import com.poly.entity.Cart;
import com.poly.entity.Product;
import com.poly.repository.ProductDAO;
import com.poly.service.ShoppingCartService;

@SessionScope
@Service
public class ShoppingCartServiceImpl implements ShoppingCartService {
	@Autowired
	ProductDAO prdDao;

	private static Map<String, Cart> map = new HashMap<>();

	@Override
	public Cart add(String id) {
		Product product = prdDao.findById(id).orElse(null);
		Cart item = new Cart(product.getId(), product.getName(), product.getImages().get(0).getName(), product.getPrice(), 1);
		if (!map.containsKey(id)) {
			map.put(item.getId(), item);
		} else {
			int oldQuantity = map.get(id).getQuantity();
			map.get(id).setQuantity(oldQuantity + 1);
		}
		return item;
	}

	@Override
	public void remove(String id) {
		if (map.containsKey(id)) {
			map.remove(id);
		}
	}

	@Override
	public Cart update(String id, int qty) {
		Cart item = map.get(id);
		// Update or remove product
		if (qty > 0) {
			item.setQuantity(qty);
		} else {
			map.remove(id);
		}
		return item;
	}

	@Override
	public void clear() {
		map.clear();
	}

	@Override
	public Map<String, Cart> getItems() {
		return map;
	}

	@Override
	public int getCount() {
		int count = 0;
		for (Cart item : map.values()) {
			count += item.getQuantity();
		}
		return count;
	}

	@Override
	public double getAmount() {
		double amount = 0;
		for (Cart item : map.values()) {
			amount += item.getPrice() * item.getQuantity();
		}
		return amount;
	}

	@Override
	public double getShipping() {
		if (getAmount() < 1500000) {
			return 20000;
		} else {
			return 0;
		}
	}

	@Override
	public double getPayment() {
		return getAmount() - getShipping();
	}

}

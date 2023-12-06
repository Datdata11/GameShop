package com.poly.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.poly.entity.Collection;
import com.poly.entity.Product;

public interface ProductService {

	Page<Product> findAllSortByCreateDate(Pageable page);

	Page<Product> findAllSortByTotalSold(Pageable page);

	Product findById(String id);

	List<Product> findAll();

	Product create(Product product);

	Product update(Product product);

	void deleteById(String id);

	long countProduct();

	List<Product> findByType(String type);
	
	Page<Product> findByType(String type, Pageable page);

	Page<Product> findByCollection(Collection collection, Pageable page);

	List<Product> findByCollection(Collection collection);

	Page<Product> findByKeyword(String keyword, Pageable page);

	List<Product> findByKeyword(String keyword);
}

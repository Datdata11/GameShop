package com.poly.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.poly.entity.Collection;
import com.poly.entity.Product;

public interface ProductDAO extends JpaRepository<Product, String> {
	@Query(value = "SELECT o FROM Product o ORDER BY o.totalSold DESC")
	Page<Product> findAllSortByTotalSold(Pageable page);
	
	@Query(value = "SELECT o FROM Product o ORDER BY o.collection.createDate DESC")
	Page<Product> findAllSortByCreateDate(Pageable page);

	List<Product> findByType(String type);
	
	Page<Product> findByType(String type, Pageable page);
	
	List<Product> findByCollection(Collection collection);
	
	Page<Product> findByCollection(Collection collection, Pageable page);

	@Query(value = "SELECT o FROM Product o WHERE o.totalSold > 0 ORDER BY o.totalSold DESC")
	List<Product> findSoldProducts();
}

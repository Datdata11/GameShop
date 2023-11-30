package com.poly.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.poly.entity.Image;

public interface ImageDAO extends JpaRepository<Image, Long> {
	@Query("SELECT o FROM Image o WHERE o.product.id = ?1")
	List<Image> findByProduct(String productId);
}

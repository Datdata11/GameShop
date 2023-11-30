package com.poly.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.poly.entity.Collection;

public interface CollectionDAO extends JpaRepository<Collection, String>{
	@Query(value = "SELECT o FROM Collection o ORDER BY o.createDate DESC")
	List<Collection> findAll();
}

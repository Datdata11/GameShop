package com.poly.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.poly.entity.Banner;
import java.util.List;


public interface BannerDAO extends JpaRepository<Banner, Long> {
	Banner findByName(String name);
	
	@Query("SELECT o FROM Banner o WHERE o.collection.id = ?1")
	List<Banner> findByCollection(String collectionId);

}

package com.poly.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.poly.entity.Collection;

public interface CollectionService {

	List<Collection> findAll();

	Collection findById(String id);

	Collection create(Collection collection);

	Collection update(Collection collection);

	void deleteById(String id);

	long countCollection();

	Page<Collection> findAll(Pageable page);

}

package com.poly.rest.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.poly.entity.Collection;
import com.poly.service.CollectionService;

@CrossOrigin("*")
@RestController
@RequestMapping("/elise/rest/collections")
public class CollectionRestCotroller {
	@Autowired
	CollectionService service;
	
	@GetMapping()
	public List<Collection> getAll() {
		return service.findAll();
	}

	@GetMapping("/{id}")
	public Collection getOne(@PathVariable("id") String id) {
		return service.findById(id);
	}

	@PostMapping()
	public Collection create(@RequestBody Collection collection) {
		return service.create(collection);
	}

	@PutMapping("/{id}")
	public Collection update(@PathVariable("id") String id, @RequestBody Collection collection) {
		return service.update(collection);
	}
	
	@DeleteMapping("/{id}")
	public void delete(@PathVariable("id") String id) {
		service.deleteById(id);
	}
}

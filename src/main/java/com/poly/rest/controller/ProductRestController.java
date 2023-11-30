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

import com.poly.entity.Product;
import com.poly.service.ProductService;

@CrossOrigin("*")
@RestController
@RequestMapping("/elise/rest/products")
public class ProductRestController {
	@Autowired
	ProductService service;
	
	@GetMapping()
	public List<Product> getAll() {
		return service.findAll();
	}

	@GetMapping("/{id}")
	public Product getOne(@PathVariable("id") String id) {
		return service.findById(id);
	}

	@PostMapping()
	public Product create(@RequestBody Product product) {
		return service.create(product);
	}

	@PutMapping("/{id}")
	public Product update(@PathVariable("id") String id, @RequestBody Product product) {
		return service.update(product);
	}
	
	@DeleteMapping("/{id}")
	public void delete(@PathVariable("id") String id) {
		service.deleteById(id);
	}
}

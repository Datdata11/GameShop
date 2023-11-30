package com.poly.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.poly.entity.Collection;
import com.poly.entity.Image;
import com.poly.entity.Product;
import com.poly.repository.ImageDAO;
import com.poly.repository.ProductDAO;
import com.poly.service.ProductService;

@Service
public class ProductServiceImpl implements ProductService {
	@Autowired
	ProductDAO dao;
	@Autowired
	ImageDAO imageDao;

	@Override
	public Page<Product> findAllSortByCreateDate(Pageable page) {
		return dao.findAllSortByCreateDate(page);
	}

	@Override
	public Page<Product> findAllSortByTotalSold(Pageable page) {
		return dao.findAllSortByTotalSold(page);
	}

	@Override
	public Product findById(String id) {
		return dao.findById(id).get();
	}

	@Override
	public List<Product> findAll() {
		return dao.findAll();
	}

	@Override
	public Product create(Product product) {
		if (dao.existsById(product.getId())) {
			throw new RuntimeException("Duplicated primary key");
		}
		product.setTotalSold(0);
		Product newProduct = dao.save(product);
		for (int i = 0; i < 4; i++) {
			Image image = new Image();
			image.setProduct(newProduct);
			String name = product.getImages().get(i).getName();
			if (name == null) {
				image.setName("default-placeholder.png");
			} else {
				image.setName(name);
			}
			imageDao.save(image);
		}
		return newProduct;
	}

	@Override
	public Product update(Product product) {
		if (!dao.existsById(product.getId())) {
			throw new RuntimeException("Not existed");
		}
		for (Image image : product.getImages()) {
			image.setProduct(product);
			imageDao.save(image);
		}
		return dao.save(product);
	}

	@Override
	public void deleteById(String id) {
		Product product = dao.findById(id).get();
		for (Image image : product.getImages()) {
			imageDao.delete(image);
		}
		dao.deleteById(id);
	}

	@Override
	public long countProduct() {
		return dao.count();
	}

	@Override
	public List<Product> findByType(String type) {
		return dao.findByType(type);
	}

	@Override
	public Page<Product> findByType(String type, Pageable page) {
		return dao.findByType(type, page);
	}

	@Override
	public Page<Product> findByCollection(Collection collection, Pageable page) {
		return dao.findByCollection(collection, page);
	}

	@Override
	public List<Product> findByCollection(Collection collection) {
		return dao.findByCollection(collection);
	}

	@Override
	public List<Product> findSoldProducts() {
		return dao.findSoldProducts();
	}
}

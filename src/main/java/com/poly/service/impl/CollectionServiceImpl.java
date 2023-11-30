package com.poly.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.poly.entity.Banner;
import com.poly.entity.Collection;
import com.poly.repository.BannerDAO;
import com.poly.repository.CollectionDAO;
import com.poly.service.CollectionService;

@Service
public class CollectionServiceImpl implements CollectionService {
	@Autowired
	CollectionDAO dao;
	@Autowired
	BannerDAO bannerDao;

	@Override
	public List<Collection> findAll() {
		return dao.findAll();
	}

	@Override
	public Collection findById(String id) {
		return dao.findById(id).get();
	}

	@Override
	public Collection create(Collection collection) {
		if (dao.existsById(collection.getId())) {
			throw new RuntimeException("Duplicated primary key");
		}
		Collection newCollection = dao.save(collection);
		for (int i = 0; i < 3; i++) {
			Banner banner = new Banner();
			banner.setCollection(newCollection);
			String name = collection.getBanners().get(i).getName();
			if (name == null) {
				banner.setName("default-placeholder.png");
			} else {
				banner.setName(name);
			}
			bannerDao.save(banner);
		}
		return newCollection;
	}

	@Override
	public Collection update(Collection collection) {
		if (!dao.existsById(collection.getId())) {
			throw new RuntimeException("Not existed");
		}
		for (Banner banner : collection.getBanners()) {
			banner.setCollection(collection);
			bannerDao.save(banner);
		}
		return dao.save(collection);
	}

	@Override
	public void deleteById(String id) {
		Collection collection = dao.findById(id).get();
		for (Banner banner : collection.getBanners()) {
			bannerDao.delete(banner);
		}
		dao.deleteById(id);
	}

	@Override
	public long countCollection() {
		return dao.count();
	}

	@Override
	public Page<Collection> findAll(Pageable page) {
		return dao.findAll(page);
	}

}

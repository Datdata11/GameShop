package com.poly.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.poly.entity.Collection;
import com.poly.entity.Product;
import com.poly.service.AccountService;
import com.poly.service.CollectionService;
import com.poly.service.ProductService;
import com.poly.utils.service.CookieService;
import com.poly.utils.service.ParamService;
import com.poly.utils.service.SessionService;

@Controller
public class StoreController {
	@Autowired
	SessionService session;
	@Autowired
	CookieService cookie;
	@Autowired
	ParamService param;
	@Autowired
	AccountService accService;
	@Autowired
	ProductService productService;
	@Autowired
	CollectionService collectionService;

	@GetMapping({ "/", "/elise" })
	public String home(Model model, @RequestParam("new") Optional<Integer> newPage,
			@RequestParam("hot") Optional<Integer> hotPage) {
		model.addAttribute("newProducts", productService.findAllSortByCreateDate(PageRequest.of(newPage.orElse(0), 4)));
		model.addAttribute("hotProducts", productService.findAllSortByTotalSold(PageRequest.of(newPage.orElse(0), 4)));
		model.addAttribute("newCollection", collectionService.findAll().get(0));
		return "store/home";
	}
	
	@GetMapping("/elise/about")
	public String about() {
		return "store/about";
	}

	@GetMapping("/elise/item/{id}")
	public String detail(Model model, @PathVariable("id") String id) {
		Product sp = productService.findById(id);
		model.addAttribute("item", sp);
		return "store/detail";
	}

	@GetMapping("/elise/new-arrival")
	public String newArrival(Model model, @RequestParam("page") Optional<Integer> page) {
		model.addAttribute("newProducts", productService.findAllSortByCreateDate(PageRequest.of(page.orElse(0), 8)));
		
		long maxPage = Math.floorDiv(productService.countProduct(), 8);
		model.addAttribute("page", page.orElse(0));
		model.addAttribute("maxPage", maxPage);
		
		return "store/new-arrival";
	}

	@GetMapping("/elise/best-seller")
	public String bestSeller(Model model, @RequestParam("page") Optional<Integer> page) {
		model.addAttribute("hotProducts", productService.findAllSortByTotalSold(PageRequest.of(page.orElse(0), 8)));
		
		long maxPage = Math.floorDiv(productService.countProduct(), 8);
		model.addAttribute("page", page.orElse(0));
		model.addAttribute("maxPage", maxPage);
		
		return "store/best-seller";
	}
	
	@GetMapping("/elise/search")
	public String search(Model model, @RequestParam("page") Optional<Integer> page, @RequestParam("keyword") String keyword) throws Exception {
		model.addAttribute("keyword", keyword);
		return "store/search";
	}
	
	@ResponseBody
	@GetMapping(value = "/elise/search", produces = MediaType.APPLICATION_JSON_VALUE)
	public List<Product> search(@RequestParam("keyword") String keyword) throws Exception {
		List<Product> results = productService.findByKeyword("%" + keyword + "%");
		return results;
	}

	@GetMapping("/elise/product/{type}")
	public String type(Model model, @PathVariable("type") String type, @RequestParam("page") Optional<Integer> page) {
		model.addAttribute("type", type);
		
		List<Product> products = productService.findByType(type);
		long maxPage = Math.floorDiv(products.size(), 8);
		model.addAttribute("page", page.orElse(0));
		model.addAttribute("maxPage", maxPage);
		
		model.addAttribute("products", productService.findByType(type, PageRequest.of(page.orElse(0), 8)));
		return "store/type";
	}
	
	@GetMapping("/elise/collection")
	public String collection(Model model, @RequestParam("page") Optional<Integer> page) {
		long maxPage = Math.floorDiv(collectionService.countCollection(), 4);
		model.addAttribute("page", page.orElse(0));
		model.addAttribute("maxPage", maxPage);
		
		model.addAttribute("collections", collectionService.findAll(PageRequest.of(page.orElse(0), 4)));
		return "store/collection";
	}
	
	@GetMapping("/elise/collection/{id}")
	public String collectionDetail(Model model, @PathVariable("id") String id) {
		Collection collection = collectionService.findById(id);
		model.addAttribute("collection", collection);
		
		model.addAttribute("someProducts", productService.findByCollection(collection, PageRequest.of(0, 4)));
		model.addAttribute("allProducts", productService.findByCollection(collection));
		return "store/collection-detail";
	}
}

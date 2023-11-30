package com.poly.interceptor;

import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.poly.service.ProductService;

@Service
public class GlobalInterceptor implements HandlerInterceptor {
	@Autowired
	ProductService productService;

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		// TODO Auto-generated method stub
		request.setAttribute("uri", request.getRequestURI());
		return true;
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		// Database
		request.setAttribute("types",
				productService.findAll().stream().map(sp -> sp.getType()).distinct().collect(Collectors.toList()));

		// Cart
//		request.setAttribute("carts", cart.getItems());
//		request.setAttribute("cartSize", cart.getCount());
//		request.setAttribute("cartAmount", cart.getAmount());
//		request.setAttribute("shipping", cart.getShipping());
//		request.setAttribute("payment", cart.getPayment());
	}
}

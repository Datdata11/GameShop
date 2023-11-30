package com.poly.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.HandlerInterceptor;

import com.poly.entity.Account;
import com.poly.service.SessionService;

@Service
public class AuthInterceptor implements HandlerInterceptor{
	@Autowired
	SessionService session;
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		// TODO Auto-generated method stub
		String uri = request.getRequestURI();
//		Account acc = session.get("acc");
//		String error = "";
//		if(acc == null) { 
//			error = "Please login!";
//		}
//		else if(!acc.getRole() && uri.startsWith("/admin/")) {
//			error = "Access denied!";
//		}
//		if(error.length() > 0) {
//			session.set("security-uri", uri);
//			response.sendRedirect("/login?error="+error);
//			return false;
//		}
		
		return true;
	}
}

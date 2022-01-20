package com.pcstore.interceptor;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;



import com.pcstore.dao.CategoryDAO;
import com.pcstore.entity.Category;
import com.pcstore.entity.Customer;

import org.springframework.web.servlet.HandlerInterceptor;

@Component
public class AuthorizeIterceptor implements HandlerInterceptor{
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		HttpSession session = request.getSession();
		Customer customer = (Customer) session.getAttribute("user");
		if(customer == null)
		{
			session.setAttribute("back-url",request.getRequestURI());
			response.sendRedirect("/account/login");
			return false;
		}
		// TODO Auto-generated method stub
		return true;
	}
	


	

}

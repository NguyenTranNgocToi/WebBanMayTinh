package com.pcstore.interceptor;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;



import com.pcstore.dao.CategoryDAO;
import com.pcstore.entity.Category;
import org.springframework.web.servlet.HandlerInterceptor;

@Component
public class ShareIterceptor implements HandlerInterceptor{
	
	@Autowired
	CategoryDAO dao;
	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
			List<Category> list = dao.findAll();
			modelAndView.addObject("cates", list);
			
	}


	

}

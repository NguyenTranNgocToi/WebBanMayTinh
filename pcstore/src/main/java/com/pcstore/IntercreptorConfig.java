package com.pcstore;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.pcstore.interceptor.AuthorizeIterceptor;
import com.pcstore.interceptor.ShareIterceptor;



@Configuration
public class IntercreptorConfig implements WebMvcConfigurer {
	
	
	@Autowired
	ShareIterceptor share;
	@Autowired
	AuthorizeIterceptor authorize;
	@Override
	public void addInterceptors(final InterceptorRegistry registry) {
		// TODO Auto-generated method stub
		registry.addInterceptor(share).addPathPatterns("/**");
		registry.addInterceptor(authorize)
			.addPathPatterns("/account/change","/account/edit","/account/logout", "/order/**","/admin/**" );
	}
	

}

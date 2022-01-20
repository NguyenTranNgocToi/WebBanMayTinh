package com.pcstore.controller;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.pcstore.service.CartService;

@Controller
public class ShoppingCartController {
	@Autowired
	CartService cart;
	
	@ResponseBody
	@RequestMapping("/cart/add/{id}")
	public String add(@PathVariable("id") Integer id) {
		
		cart.add(id);
		String info = cart.getCount()+""+","+ cart.getAmount()+"";
		return   info;
	}
	
	
	@RequestMapping("/cart/view")
	public String view() {
		return  "cart/view";
	}
	
	
	@ResponseBody
	@RequestMapping("/cart/clear")
	public String clear() {
		cart.clear();
		return "";
	}
	
	@ResponseBody
	@RequestMapping("/cart/remove/{id}")
	public String remove(@PathVariable("id") Integer id) {
		
		cart.remove(id);
		String info = cart.getCount()+""+","+ cart.getAmount()+"";
		return   info;
	}
	
	@ResponseBody
	@RequestMapping("/cart/update/{id}/{qty}")
	public String update(@PathVariable("id") Integer id, @PathVariable("qty") Integer qty) {
		
		cart.update(id,qty);
		String info = cart.getCount()+""+","+ cart.getAmount()+"";
		return   info;
		
	}
	
}

package com.pcstore.controller;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.pcstore.dao.OrderDAO;
import com.pcstore.dao.OrderDetailDAO;
import com.pcstore.entity.Customer;
import com.pcstore.entity.Order;
import com.pcstore.entity.OrderDetail;
import com.pcstore.entity.Product;
import com.pcstore.service.CartService;

@Controller
public class OrderController {
	@Autowired
	HttpSession session;
	@Autowired
	CartService cartService;
	@Autowired
	OrderDAO oDao;
	@Autowired
	OrderDetailDAO dDao;
	@GetMapping("/order/checkout")
	public String showOrderForm(@ModelAttribute("order") Order order) {
		Customer customer = (Customer) session.getAttribute("user");
		order.setOrderDate(new Date());
		order.setCustomer(customer);
		order.setAmount(cartService.getAmount());
		return  "order/checkout";
	}
	
	@PostMapping("/order/checkout")
	public String purchase(Model model,
			@ModelAttribute("order") Order order) {
		
		Collection<Product> list = cartService.getItems();
		List<OrderDetail> details = new ArrayList<>();
		for(Product p:list) {
			OrderDetail detail = new OrderDetail();
			detail.setOrder(order);
			detail.setProduct(p);
			detail.setUnitPrice(p.getUnitPrice());
			detail.setQuantity(p.getQuantity());
			detail.setDiscount(p.getDiscount());
			details.add(detail);
			
			
		}
		cartService.clear();
		oDao.create(order,details);
		
		return  "redirect:/order/list";
	}
	
	@GetMapping("/order/list")
	public String orderList(Model model) {
		Customer customer = (Customer) session.getAttribute("user");
		List<Order> orders= oDao.findByUser(customer);
		model.addAttribute("orders", orders);
		return  "order/list";
	}
	@GetMapping("/order/detail/{id}")
	public String detail(Model model, @PathVariable("id") int id) {
		
		Order order= oDao.findById(id);
		List<OrderDetail> details = dDao.findByOrder(order); 
		model.addAttribute("order", order);
		model.addAttribute("details", details);
		return  "order/detail";
	}
	
	@GetMapping("/order/items")
	public String items(Model model) {
		
		Customer customer = (Customer) session.getAttribute("user");
		List<Product> products =oDao.finditemsByUser(customer) ; 

		model.addAttribute("list", products);
		return  "product/list";
	}
	
}

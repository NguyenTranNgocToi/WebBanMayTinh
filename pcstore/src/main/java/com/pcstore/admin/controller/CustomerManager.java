package com.pcstore.admin.controller;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.servlet.ServletContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.pcstore.dao.CustomerDAO;
import com.pcstore.dao.OrderDAO;
import com.pcstore.dao.OrderDetailDAO;
import com.pcstore.entity.Customer;
import com.pcstore.entity.Order;
import com.pcstore.entity.OrderDetail;

@Controller
public class CustomerManager {
	
	
	@Autowired
	CustomerDAO cDao;
	@Autowired
	ServletContext context;
	@Autowired
	OrderDetailDAO dDao;
	@Autowired
	OrderDAO oDao;
	@RequestMapping("/admin/customer/index")
	public String index(Model model) {
		Customer entity = new Customer();
		model.addAttribute("entity", entity);
		model.addAttribute("list", cDao.findAll());
		return  "admin/customer/index";
	}
	
	@RequestMapping("/admin/customer/edit/{id}")
	public String edit(Model model, @PathVariable("id") String id) {
		Customer entity = cDao.findById(id);
		model.addAttribute("entity", entity);
		model.addAttribute("list", cDao.findAll());
		return  "admin/customer/index";
	}
	
	@RequestMapping("/admin/customer/create")
	public String create(RedirectAttributes model,
				@Validated	@ModelAttribute("entity") Customer entity ,BindingResult erros,
			@RequestParam("photo_file") MultipartFile file) throws IllegalStateException, IOException {
		if(erros.hasErrors()) {
			model.addAttribute("message", "Please fix some following errors");
			return "admin/customer/index";
		}
		if(file.isEmpty()) {
			entity.setPhoto("user.jpg");
		}else {
			entity.setPhoto(file.getOriginalFilename());
			String path = context.getRealPath("/static/images/customers/"+entity.getPhoto());
			file.transferTo(new File(path));
		}
		cDao.create(entity);
		model.addAttribute("message", "thêm mới thành công");
		return  "redirect:/admin/customer/index";
	}
	
	@RequestMapping("/admin/customer/update")
	public String update(RedirectAttributes model, 
			@Validated	@ModelAttribute("entity") Customer entity ,BindingResult erros,
			@RequestParam("photo_file") MultipartFile file) throws IllegalStateException, IOException {
		
		if(erros.hasErrors()) {
			model.addAttribute("message", "Please fix some following errors");
			return "admin/customer/index";
		}else {
			if(!file.isEmpty()) {
				
				entity.setPhoto(file.getOriginalFilename());
				String path = context.getRealPath("/static/images/customers/"+entity.getPhoto());
				file.transferTo(new File(path));
				
			}
			
			cDao.update(entity);
			model.addAttribute("message", "cập nhật thành công");
			return  "redirect:/admin/customer/edit/"+entity.getId();
		}
		
	}
	
	@RequestMapping( value = {"/admin/customer/delete","/admin/customer/delete/{id}" })
	public String delete(RedirectAttributes model, 
			@RequestParam(value = "id" ,required = false) String id1, 
			@PathVariable(value = "id" , required = false) String id2) {
		String id=(id1!=null)?id1:id2;
		Customer customer = cDao.findById(id);
		
		List<Order> listOrrder =oDao.findByUser(customer);
		for(Order o: listOrrder) {
			List<OrderDetail> listDetail = dDao.findByOrder(o);
			for(OrderDetail p:listDetail) {
				dDao.delete(p.getId());
			}
			oDao.delete(o.getId());
		}
		
		cDao.delete(id);
		model.addAttribute("message", "xóa thành công");
		return  "redirect:/admin/customer/index";
	}
}

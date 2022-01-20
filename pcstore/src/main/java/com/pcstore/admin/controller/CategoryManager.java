package com.pcstore.admin.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.pcstore.dao.CategoryDAO;
import com.pcstore.dao.OrderDAO;
import com.pcstore.dao.OrderDetailDAO;
import com.pcstore.dao.ProductDAO;
import com.pcstore.entity.Category;
import com.pcstore.entity.Customer;
import com.pcstore.entity.Order;
import com.pcstore.entity.OrderDetail;
import com.pcstore.entity.Product;

@Controller
public class CategoryManager {
	
	
	@Autowired
	CategoryDAO cDao;
	@Autowired
	ProductDAO pDao;
	@Autowired
	OrderDetailDAO dDao;
	
	@Autowired
	OrderDAO oDao;
	
	@RequestMapping("/admin/category/index")
	public String index(Model model) {
		Category entity = new Category();
		model.addAttribute("entity", entity);
		model.addAttribute("list", cDao.findAll());
		return  "admin/category/index";
	}
	
	@RequestMapping("/admin/category/edit/{id}")
	public String edit(Model model, @PathVariable("id") int id) {
		Category entity = cDao.findById(id);
		model.addAttribute("entity", entity);
		model.addAttribute("list", cDao.findAll());
		return  "admin/category/index";
	}
	
	@RequestMapping("/admin/category/create")
	public String create(RedirectAttributes model, 
			@Validated	@ModelAttribute("entity") Category entity, BindingResult erros
		 ) {
		if(erros.hasErrors()) {
			model.addAttribute("message", "Please fix some following errors");
			return "admin/category/index";
		}
		cDao.create(entity);
		model.addAttribute("message", "thêm mới thành công");
		return  "redirect:/admin/category/index";
	}
	
	@RequestMapping("/admin/category/update")
	public String update(RedirectAttributes model, @ModelAttribute("entity") Category entity) {
		cDao.update(entity);
		model.addAttribute("message", "cập nhật thành công");
		return  "redirect:/admin/category/edit/"+entity.getId();
	}
	
	@RequestMapping( value = {"/admin/category/delete","/admin/category/delete/{id}" })
	public String delete(RedirectAttributes model, 
			@RequestParam(value = "id" ,required = false) Integer id1, 
			@PathVariable(value = "id" , required = false) Integer id2) {
		int id=(id1!=null)?id1:id2;
	
		
		List<Product> listProduct=	pDao.findByCategory(cDao.findById(id));
		for(Product p:listProduct) {
			List<OrderDetail> listDetail = dDao.findByProduct(p);
			for(OrderDetail d:listDetail) {
				dDao.delete(d.getId());
			}
			
			pDao.delete(p.getId());
			
		}
		cDao.delete(id);
		model.addAttribute("message", "xóa thành công");
		return  "redirect:/admin/category/index";
	}
}

package com.pcstore.admin.controller;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.servlet.ServletContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.pcstore.dao.CategoryDAO;
import com.pcstore.dao.OrderDetailDAO;
import com.pcstore.dao.ProductDAO;
import com.pcstore.entity.Customer;
import com.pcstore.entity.OrderDetail;
import com.pcstore.entity.Product;

@Controller
public class ProductManager {
	
	@Autowired
	CategoryDAO cDao;
	@Autowired
	ProductDAO pDao;
	@Autowired
	OrderDetailDAO dDao;

	@Autowired
	ServletContext context;
	@RequestMapping("/admin/product/index")
	public String index(Model model) {
		Product entity = new Product();
		model.addAttribute("entity", entity);
		model.addAttribute("list", pDao.findAll());
		return  "admin/product/index";
	}
	
	@RequestMapping("/admin/product/edit/{id}")
	public String edit(Model model, @PathVariable("id") int id) {
		Product entity = pDao.findById(id);
		model.addAttribute("entity", entity);
		model.addAttribute("list", pDao.findAll());
		return  "admin/product/index";
	}
	
	@RequestMapping("/admin/product/create")
	public String create(RedirectAttributes model, 
			@ModelAttribute("entity") Product entity ,
			@RequestParam("image_file") MultipartFile file) throws IllegalStateException, IOException {
		if(file.isEmpty()) {
			entity.setImage("user.jpg");
		}else {
			entity.setImage(file.getOriginalFilename());
			String path = context.getRealPath("/static/images/product/"+entity.getImage());
			file.transferTo(new File(path));
			
		}
		pDao.create(entity);
		model.addAttribute("message", "thêm mới thành công");
		return  "redirect:/admin/product/index";
	}
	
	@RequestMapping("/admin/product/update")
	public String update(RedirectAttributes model, 
			@ModelAttribute("entity") Product entity,
			@RequestParam("image_file") MultipartFile file) throws IllegalStateException, IOException {
		
		if(!file.isEmpty()) {
			
			entity.setImage(file.getOriginalFilename());
			String path = context.getRealPath("/static/images/product/"+entity.getImage());
			file.transferTo(new File(path));
			
		}
		
		pDao.update(entity);
		model.addAttribute("message", "cập nhật thành công");
		return  "redirect:/admin/product/edit/"+entity.getId();
	}
	
	@RequestMapping( value = {"/admin/product/delete","/admin/product/delete/{id}" })
	public String delete(RedirectAttributes model, 
			@RequestParam(value = "id" ,required = false) Integer id1, 
			@PathVariable(value = "id" , required = false) Integer  id2) {
		
	
		int id=(id1!=null)?id1:id2;
		Product p = pDao.findById(id);
		
		List<OrderDetail> listDetail = dDao.findByProduct(p);
		for(OrderDetail d:listDetail) {
			dDao.delete(d.getId());
		}
		pDao.delete(p.getId());
		model.addAttribute("message", "xóa thành công");
		return  "redirect:/admin/product/index";
	}
}

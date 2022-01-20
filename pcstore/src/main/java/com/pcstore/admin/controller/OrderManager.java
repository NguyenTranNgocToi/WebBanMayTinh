package com.pcstore.admin.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.pcstore.dao.OrderDAO;
import com.pcstore.dao.OrderDetailDAO;
import com.pcstore.entity.Order;
import com.pcstore.entity.OrderDetail;

@Controller
public class OrderManager {
	
	
	@Autowired
	OrderDAO cDao;
	@Autowired
	OrderDetailDAO dDao;
	
	@RequestMapping("/admin/order/index")
	public String index(Model model) {
		Order entity = new Order();
		model.addAttribute("entity", entity);
		model.addAttribute("details", dDao.findByOrder(entity));
		model.addAttribute("list", cDao.findAll());
		return  "admin/order/index";
	}
	
	@RequestMapping("/admin/order/edit/{id}")
	public String edit(Model model, @PathVariable("id") int id) {
		Order entity = cDao.findById(id);
		model.addAttribute("entity", entity);
		model.addAttribute("details", dDao.findByOrder(entity));
		model.addAttribute("list", cDao.findAll());
		return  "admin/order/index";
	}
	
	@RequestMapping("/admin/order/create")
	public String create(RedirectAttributes model, @ModelAttribute("entity") Order entity ) {
		cDao.create(entity);
		model.addAttribute("message", "thêm mới thành công");
		return  "redirect:/admin/order/index";
	}
	
	@RequestMapping("/admin/order/update")
	public String update(RedirectAttributes model, @ModelAttribute("entity") Order entity) {
		cDao.update(entity);
		model.addAttribute("message", "cập nhật thành công");
		return  "redirect:/admin/order/edit/"+entity.getId();
	}
	
	@RequestMapping( value = {"/admin/order/delete","/admin/order/delete/{id}" })
	public String delete(RedirectAttributes model, 
			@RequestParam(value = "id" ,required = false) Integer id1, 
			@PathVariable(value = "id" , required = false) Integer id2) {
		int id=(id1!=null)?id1:id2;
		
		List<OrderDetail> list = dDao.findByOrder(cDao.findById(id));
		for(OrderDetail p:list) {
			dDao.delete(p.getId());
		}
		cDao.delete(id);
		model.addAttribute("message", "xóa thành công");
		return  "redirect:/admin/order/index";
	}
}

package com.pcstore.admin.controller;

import java.io.File;
import java.io.IOException;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.pcstore.dao.CustomerDAO;
import com.pcstore.entity.Customer;

@Controller
public class AdminAccountManager {
	
	
	@Autowired
	CustomerDAO cdao;
	@Autowired
	HttpSession session;
	@Autowired
	ServletContext app;
	
	
	@GetMapping("/admin/account/change")
	public String change(Model model) {
		return "admin/account/change";
	}
	@PostMapping("/admin/account/change")
	public String change(Model model, 
			@RequestParam("id") String id,
			@RequestParam("pass") String pass,
			@RequestParam("pass1") String pass1,
			@RequestParam("pass2") String pass2) {
		if(!pass1.equalsIgnoreCase(pass2)) {
			model.addAttribute("message", "Confirm pass is not match");
			return "admin/account/change";
		}else {
			Customer user = cdao.findById(id);
			if(user == null) {
				model.addAttribute("message", "Invalid UserName");
				return "admin/account/change";
			}else if(!pass.equals(user.getPassword())) {
				model.addAttribute("message", "Invalid PassWords");
				return "admin/account/change";
			}else if(pass1.length()<6) {
				model.addAttribute("message", "New Pass Word > 6 key");
				return "admin/account/change";
			}else {
				user.setPassword(pass1);
				cdao.update(user);
				model.addAttribute("message", "change pass success");
			}		
		}
		return "redirect:/account/login";
	}
	
	
	
	@GetMapping("/admin/account/edit")
	public String edit(Model model) {
		Customer user = (Customer)session.getAttribute("user");
		model.addAttribute("form", user);
		return "admin/account/edit";
	}
	
	@PostMapping("/admin/account/edit")
	public String edit(Model model,
			@Validated	@ModelAttribute("form") Customer user, BindingResult erros,
			@RequestParam("photo_file") MultipartFile file) throws IllegalStateException, IOException{
		
		if(erros.hasErrors()) {
			model.addAttribute("message", "Please fix some following errors");
			return "admin/account/edit";
		}
		
		
		if(!file.isEmpty()) {
			String dir = app.getRealPath("/static/images/customers");
			File f= new File(dir, file.getOriginalFilename());
			file.transferTo(f);
			user.setPhoto(f.getName());
			
		}
		cdao.update(user);
		session.setAttribute("user", user);
		model.addAttribute("message","update successfully");
		return "admin/account/edit";
	}
}

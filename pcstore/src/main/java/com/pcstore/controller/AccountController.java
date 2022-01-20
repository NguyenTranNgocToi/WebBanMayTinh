package com.pcstore.controller;

import java.io.File;
import java.io.IOException;

import javax.mail.MessagingException;
import javax.servlet.ServletContext;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jmx.export.annotation.ManagedResource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.annotation.JsonCreator.Mode;
import com.pcstore.bean.MailInfo;
import com.pcstore.dao.CustomerDAO;
import com.pcstore.entity.Customer;
import com.pcstore.service.CookieService;
import com.pcstore.service.MailService;


@Controller
public class AccountController {
	@Autowired
	CustomerDAO cdao;
	@Autowired
	HttpSession session;
	@Autowired
	CookieService cookieService;
	@Autowired
	ServletContext app;
	@Autowired
	MailService mservice;
	@Autowired
	HttpServletRequest request;
	
	
	@GetMapping("/account/login")
	public String login(Model model) {
		Cookie cku =  cookieService.read("userId");
		Cookie ckp =  cookieService.read("pass");
		if(cku!= null) {
			String userId =cku.getValue();
			String pass =ckp.getValue();
			model.addAttribute("pw",  pass);
			model.addAttribute("uid", userId);
			
		}
	
		return  "account/login";
	}
	@PostMapping("/account/login")
	public String login(Model model,

			@RequestParam("id") String id, 
			@RequestParam("pw") String pw, 
			@RequestParam(value="rm", defaultValue = "false") boolean rm) {
		Customer user = cdao.findById(id);
		if(user ==null) {
			model.addAttribute("message", "Invalid UserName");
			model.addAttribute("uid", id);
			model.addAttribute("pw", pw);
			return  "account/login";
		}
		else if(!pw.equals(user.getPassword())) {
			model.addAttribute("message", "Invalid PassWords");
			model.addAttribute("uid", id);
			model.addAttribute("pw", pw);
			return  "account/login";
		}
		else if(!user.getActivated()) {
			model.addAttribute("message", "Your Account is not activated");
			model.addAttribute("uid", id);
			model.addAttribute("pw", pw);
			return  "account/login";
		}
		else {
			model.addAttribute("message", "login success");
			session.setAttribute("user", user);
			if(rm==true) {
				cookieService.create("userId", user.getId(), 30);
				cookieService.create("pass", user.getPassword(), 30);
			}else {
				cookieService.delete("userId");
				cookieService.delete("pass");
			}
			
			if(user.getAdmin()) {
				return  "admin/home/index";
			}
			
			String backUrl = (String) session.getAttribute("back-url");
			if(backUrl!= null) {
				return "redirect:"+backUrl;
			}
			return  "home/index";
		}
		
		
	}
	
	@RequestMapping("/account/logout")
	public String logout(Model model) {
		session.removeAttribute("user");
		return  "redirect:/home/index";
	}

	@GetMapping("/account/register")
	public String register(Model model) {
		Customer user = new Customer();
		model.addAttribute("form", user);
		return "account/register";
	}
	
	@PostMapping("/account/register")
	public String register(Model model,
		@Validated	@ModelAttribute("form") Customer user, BindingResult erros,
			@RequestParam("photo_file") MultipartFile file) throws IllegalStateException, IOException, MessagingException {
		
		
		if(erros.hasErrors()) {
			model.addAttribute("message", "Please fix some following errors");
			return "account/register";
		}else {
			Customer user2 =  cdao.findById(user.getId());
			if(user2!=null) {
				model.addAttribute("message", "Username is in used");
				return "account/register";
			}
		}
		
		if(file.isEmpty()) {
			user.setPhoto("user.jpg");
		}else {
			String dir = app.getRealPath("/static/images/customers");
			File f= new File(dir, file.getOriginalFilename());
			file.transferTo(f);
			user.setPhoto(f.getName());
			
		}
		user.setActivated(true);
		user.setAdmin(false);
		
		cdao.create(user);
//		String from ="nguyentoi53@gmail.com";
//		String to = user.getEmail();
//		String subject ="welcome";
//		String url= request.getRequestURI().toString().replace("register", "/active/"+user.getId());
//		String body ="Click <a href='/account"+url+"'> Active</a>";
//		
//		MailInfo mail = new MailInfo(from, to, subject, body);
//		mservice.send(mail);
		
		
		model.addAttribute("message","Register successfully");
		return "account/register";
	}
	@GetMapping("/account/active/{id}")
	public String active(Model model,
			@PathVariable("id") String id) {
		Customer user = cdao.findById(id);
		user.setActivated(true);
		cdao.update(user);
		return "redirect:/account/login";	
	}
	
	@GetMapping("/account/change")
	public String change(Model model) {
		return "account/change";
	}
	@PostMapping("/account/change")
	public String change(Model model, 
			@RequestParam("id") String id,
			@RequestParam("pass") String pass,
			@RequestParam("pass1") String pass1,
			@RequestParam("pass2") String pass2) {
		if(!pass1.equalsIgnoreCase(pass2)) {
			model.addAttribute("message", "Confirm pass is not match");
			return "account/change";
		}else {
			Customer user = cdao.findById(id);
			if(user == null) {
				model.addAttribute("message", "Invalid UserName");
				return "account/change";
			}else if(!pass.equals(user.getPassword())) {
				model.addAttribute("message", "Invalid PassWords");
				return "account/change";
			}else if(pass1.length()<6) {
				model.addAttribute("message", "New Pass Word > 6 key");
				return "account/change";
			}else {
				user.setPassword(pass1);
				cdao.update(user);
				model.addAttribute("message", "change pass success");
			}		
		}
		return "redirect:/account/login";
	}
	
	
	
	@GetMapping("/account/edit")
	public String edit(Model model) {
		Customer user = (Customer)session.getAttribute("user");
		model.addAttribute("form", user);
		return "account/edit";
	}
	
	@PostMapping("/account/edit")
	public String edit(Model model,
			@Validated	@ModelAttribute("form") Customer user, BindingResult erros,
			@RequestParam("photo_file") MultipartFile file) throws IllegalStateException, IOException{
		
		if(erros.hasErrors()) {
			model.addAttribute("message", "Please fix some following errors");
			return "account/edit";
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
		return "account/edit";
	}
}

package com.pcstore.controller;

import java.util.List;

import javax.servlet.http.Cookie;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.pcstore.bean.MailInfo;
import com.pcstore.dao.CategoryDAO;
import com.pcstore.dao.ProductDAO;
import com.pcstore.entity.Category;
import com.pcstore.entity.Product;
import com.pcstore.service.CookieService;
import com.pcstore.service.MailService;

@Controller
public class ProductController {
	@Autowired
	CategoryDAO cdao;
	@Autowired
	ProductDAO pdao;
	@Autowired
	CookieService cookieService;
	@Autowired
	MailService mail;
	@RequestMapping("/product/list-by-category/{cid}")
	public String listByCategory(Model model, @PathVariable("cid") int categoryId) {
		Category category =  cdao.findById(categoryId);
		List<Product> list = category.getProducts();
		model.addAttribute("list", list);
		return  "product/list";
	}
	
	@RequestMapping("/product/list-by-keywords")
	public String listByKeyWords(Model model, @RequestParam("keywords") String keywords) {
	
		List<Product> list =  pdao.findByKeyWords(keywords);
		model.addAttribute("list", list);
		
		return  "product/list";
	}
	
	
	@RequestMapping("/product/list-by-special/{id}")
	public String listBySpecial(Model model, @PathVariable("id") int Id) {
		
		
		List<Product> list =  pdao.findBySpecial(Id);
		model.addAttribute("list", list);
		return  "product/list";
	}
	
	
	
	@RequestMapping("/product/detail/{id}")
	public String detail(Model model,  @PathVariable("id") int id) {
	//lấy sản phẩm
		Product p = pdao.findById(id); 
		model.addAttribute("product", p);
		
	//hàng cùng loại	
		Category category =  cdao.findById(p.getCategory().getId());
		List<Product> list = category.getProducts();
		model.addAttribute("list", list);
	//tăng số lần xem	
		p.setViewCount(p.getViewCount()+1);
		pdao.update(p);
		
	//lấy sản phẩm yêu thích	
		Cookie favo = cookieService.read("favo");
		if(favo!=null) {
			String ids = favo.getValue();
			List<Product> favo_list = pdao.findByIds(ids);
			model.addAttribute("favo", favo_list);
		}
		
	//lấy sản phẩm đã xem	
		Cookie viewed = cookieService.read("viewed");
		String value =id+"";
		if(viewed!=null) {
			value = viewed.getValue();
			if(!value.contains(id+""))
				value +="," +id+"";
		}
		cookieService.create("viewed", value, 10);
		List<Product> view_list = pdao.findByIds(value);
		model.addAttribute("viewed", view_list);
		return  "product/detail";
	}
	@ResponseBody
	@RequestMapping("/product/add-to-favo/{id}")
	public String addToFavorite(Model model,  @PathVariable("id") int id) {
		String value =id+"";
		Cookie favo =  cookieService.read("favo");
		if(favo != null) {
			value = favo.getValue();
			if(!value.contains(id+"")) {
				value +=","+id+"";
			}else {
				return "Đã có rồi";
			}
		}
		cookieService.create("favo", value, 30);
		return  "Thêm vào thành công";
	}
//send mail	
	@ResponseBody
	@RequestMapping("/product/send-to-friend")
	public String sentToFriend(Model model,
			MailInfo info) {
		
		info.setSubject("thông tin hàng hóa");
		try {
			mail.send(info);
			return  "true";
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return  "false";
		}
		
	}
}

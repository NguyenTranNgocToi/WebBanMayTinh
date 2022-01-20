package com.pcstore.admin.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;

import com.pcstore.dao.ReportDAO;

@Controller
public class InventoryReport {
	
	@Autowired
	ReportDAO rDao;
	@RequestMapping("/admin/inventory/index")
	public String index(Model model) {
		model.addAttribute("data", rDao.inventory());
		return  "admin/report/inventory";
	}
	
	@RequestMapping("/admin/revenue/category")
	public String revenueByCategory(Model model) {
		model.addAttribute("data", rDao.revenueByCategory());
		return  "admin/report/revenue-by-category";
	}
	
	
	@RequestMapping("/admin/revenue/customer")
	public String revenueByCustomer(Model model) {
		model.addAttribute("data", rDao.revenueByCustomer());
		return  "admin/report/revenue-by-customer";
	}
	
	@RequestMapping("/admin/revenue/year")
	public String revenueByYear(Model model) {
		model.addAttribute("data", rDao.revenueByYear());
		return  "admin/report/revenue-by-year";
	}
	
	@RequestMapping("/admin/revenue/quarter")
	public String revenueByQuarter(Model model) {
		model.addAttribute("data", rDao.revenueByQuarter());
		return  "admin/report/revenue-by-quarter";
	}
	
	@RequestMapping("/admin/revenue/month")
	public String revenueByMonth(Model model) {
		model.addAttribute("data", rDao.revenueByMonth());
		return  "admin/report/revenue-by-month";
	}
}

package com.cafe24.lms.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.cafe24.lms.domain.Rent;
import com.cafe24.lms.domain.Reserve;
import com.cafe24.lms.service.RentService;
import com.cafe24.lms.service.ReserveService;

// @Auth(value=Auth.Role.ADMIN)
@Controller
@RequestMapping( "/admin" )
public class AdminController {
	
	@Autowired
	private RentService rentService;
	
	@Autowired
	private ReserveService reverseService;
	
	@RequestMapping( { "", "/rent", "/main" } )
	public String main( 
			Model model,
			@RequestParam(required=false, defaultValue="0") Long page) {
		Page<Rent> list = rentService.getAllRent(page);
		model.addAttribute("list", list);
		model.addAttribute("pager", rentService.getPager());
		return "admin/rent";
	}
	
	@RequestMapping( "/reserve" )
	public String reserve(
			Model model,
			@RequestParam(required=false, defaultValue="0") Long page) {
		Page<Reserve> list = reverseService.getAllReserve(page);
		model.addAttribute("list", list);
		model.addAttribute("pager", rentService.getPager());
		return "admin/reserve";
	}
	
}

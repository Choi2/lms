package com.cafe24.lms.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.cafe24.lms.domain.Item;
import com.cafe24.lms.domain.User;
import com.cafe24.lms.service.ItemService;
import com.cafe24.lms.service.RentService;
import com.cafe24.lms.service.ReverseService;

@Controller
@SessionAttributes("authUser")
public class MainController {

	@Autowired
	private ItemService itemService;
	
	@Autowired
	private RentService rentService;
	
	@Autowired
	private ReverseService reverseService;
	
	@RequestMapping( { "", "/main" } )
	public String index(
			Model model, 
			@RequestParam(required=false, defaultValue="0") Long page) {
		Page<Item> list = itemService.getAllItem(page);
		model.addAttribute("list", list);
		model.addAttribute("pager", itemService.getPager());
		return "main/index";
	}
	
	@RequestMapping( "/rent" )
	public String rent(
			@RequestParam ("itemNo") Long itemNo,
			@ModelAttribute("authUser") User user) {
		
		if(user == null) {
			return "user/login";
		} //로그인 안되어있으면 로그인화면
		
		rentService.addRent(itemNo, user);
		return "main/rent";
	}
	
	@RequestMapping( "/reserve" )
	public String reserve(
			@RequestParam ("itemNo") Long itemNo,
			@ModelAttribute("authUser") User user) {
		
		if(user == null) {
			return "user/login";
		} //로그인 안되어있으면 로그인화면
		
		reverseService.addReverse(itemNo, user);
		
		return "main/rent";
	}

	
}

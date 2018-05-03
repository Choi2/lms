package com.cafe24.lms.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.cafe24.lms.domain.Item;
import com.cafe24.lms.domain.Pager;
import com.cafe24.lms.domain.User;
import com.cafe24.lms.interceptor.Auth;
import com.cafe24.lms.interceptor.Auth.Role;
import com.cafe24.lms.service.ItemService;
import com.cafe24.lms.service.RentService;
import com.cafe24.lms.service.ReserveService;
import com.cafe24.lms.service.UserService;

@Controller
@SessionAttributes("authUser")
public class MainController {

	@Autowired
	private ItemService itemService;
	
	@Autowired
	private RentService rentService;
	
	@Autowired
	private ReserveService reverseService;
	
	@Autowired
	private UserService userSerivce;

	@RequestMapping( { "", "/main" } )
	public String index(
			Model model, 
			Pager pager,
			HttpSession session) {
		Page<Item> list = itemService.getAllItem(pager);
		
		if(session.getAttribute("authUser") != null) {
			User user = (User) session.getAttribute("authUser");
			List<Integer> usedRentNoList = userSerivce.checkRent(user);
			Map<Long, Integer> usedReserveNoList = userSerivce.checkReserve(user);
			model.addAttribute("usedRentNoList", usedRentNoList);
			model.addAttribute("usedReserveNoList", usedReserveNoList);
		}
		model.addAttribute("list", list);
		model.addAttribute("pager", pager);

		return "main/index";
	}
		
	
	@RequestMapping( "/rent" )
	@Auth(Role.USER)
	public String rent(
			@RequestParam ("itemNo") Long itemNo,
			@ModelAttribute("authUser") User user) {
		rentService.addRent(itemNo, user);
		return "main/rent";
	}
	
	@RequestMapping( "/reserve" )
	@Auth(Role.USER)
	public String reserve(
			@RequestParam ("itemNo") Long itemNo,
			@ModelAttribute("authUser") User user) {
		reverseService.addReverse(itemNo, user);
		return "main/rent";
	}

	
}

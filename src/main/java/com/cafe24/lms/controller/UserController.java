package com.cafe24.lms.controller;

import java.util.List;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.cafe24.lms.domain.User;
import com.cafe24.lms.service.UserService;



@Controller
@RequestMapping( "/user" )
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@RequestMapping(value="/join", method=RequestMethod.GET)
	public String join(@ModelAttribute User user){
		return "user/join";
	}
	
	@RequestMapping(value="/join", method=RequestMethod.POST)
	public String join(
			@ModelAttribute @Valid User user, 
			BindingResult result) {
		
		if(result.hasErrors()) {
			List<ObjectError> list = result.getAllErrors();
			System.out.println(list);
			return "user/join";
		}
		userService.join(user);
		return "redirect:/user/joinsuccess";
	}
	
	
	@RequestMapping( value="/login", method=RequestMethod.GET )
	public String login() {
		return "user/login";
	}

	
	@RequestMapping(value="/auth", method=RequestMethod.POST)
	public String login(
			HttpSession session,
			@ModelAttribute User user, 
			Model model) {
		System.out.println(user);
		User authUser = userService.getUser(user);
		if(authUser == null) {
			model.addAttribute("result", "fail");
			return "user/login";
		}
		
		session.setAttribute("authUser", authUser); //인증처리
		return "redirect:/";
	}
	
	@RequestMapping( "/joinsuccess" )
	public String joinsuccess(){
		return "user/joinsuccess";
	}
	
	@RequestMapping("/logout")
	public String logout(HttpSession session) {
		session.removeAttribute("authUser");
		session.invalidate();
		return "redirect:/"; 
	}
	
	
	@RequestMapping(value="/modify", method=RequestMethod.GET)
	public String modify(HttpSession session) {
		
		User authUser = (User) session.getAttribute("authUser");
		//접근 제어
		if(authUser == null) {
			return "redirect:/main";
		}

		return "user/modify";
	}
}

package com.hotsse.spsecu.web.controller;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.hotsse.spsecu.api.user.vo.SecurityUser;

@Controller
public class WebController {
	
	@GetMapping(value = "")
	public String index() throws Exception {
		return "redirect:/hello";
	}

	@GetMapping(value = "/login")
	public String userLogin() throws Exception {
		return "pages/user/login";
	}
	
	@GetMapping(value = "/hello")
	public String hello(Model model) throws Exception {
		
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		SecurityUser user = (SecurityUser) auth.getPrincipal();
		model.addAttribute("nickname", user.getNickname());
		
		return "pages/hello";
	}
}

package com.hotsse.spsecu.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.hotsse.spsecu.api.user.service.UserService;
import com.hotsse.spsecu.api.user.vo.SecurityUser;
import com.hotsse.spsecu.api.user.vo.UserVO;

@Controller
public class WebController {
	
	@Autowired
	private UserService userService;
	
	@GetMapping(value = "")
	public String index() throws Exception {
		return "redirect:/hello";
	}

	@GetMapping(value = "/user/login")
	public String userLogin() throws Exception {
		return "pages/user/login";
	}
	
	@GetMapping(value = "/user/signup")
	public String userSignUp() throws Exception {
		return "pages/user/signup";
	}
	
	@PostMapping(value = "/user/signup")
	public String postUserSignUp(
			@RequestParam(name = "username") String username
			, @RequestParam(name = "password") String password
			, @RequestParam(name = "nickname") String nickname) throws Exception {
		
		UserVO user = new UserVO();
		user.setUsername(username);
		user.setPassword(password);
		user.setNickname(nickname);
		
		this.userService.insertUser(user);
		
		return "redirect:/user/login";
	}
	
	@GetMapping(value = "/hello")
	public String hello(Model model) throws Exception {
		
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		SecurityUser user = (SecurityUser) auth.getPrincipal();
		model.addAttribute("nickname", user.getNickname());
		
		return "pages/hello";
	}
}

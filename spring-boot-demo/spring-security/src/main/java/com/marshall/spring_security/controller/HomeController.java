package com.marshall.spring_security.controller;

import com.marshall.spring_security.entity.UserEntity;
import com.marshall.spring_security.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

/**
 * @author yaojie.hou
 * @create 2018/5/3
 */
@Controller
public class HomeController {

	private UserService userService;

	@Autowired
	public HomeController(UserService userService) {
		this.userService = userService;
	}

	@GetMapping({"/", "/index", "/home"})
	public String root() {
		return "home";
	}

	@GetMapping("/login")
	public String login() {
		return "login";
	}

	//@GetMapping("/register")
	//public String register(){
	//	return "register";
	//}

	@PostMapping("/register")
	public String doRegister(UserEntity userEntity){
		// 此处省略校验逻辑
		if (userService.insert(userEntity)) {
			return "redirect:register?success";
		}
		return "redirect:register?error";
	}
}

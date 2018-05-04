package com.marshall.spring_security.controller;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.security.Principal;

/**
 * @author yaojie.hou
 * @create 2018/5/3
 */
@Controller
public class UserController {

	@GetMapping("/user")
	public String user(@AuthenticationPrincipal Principal principal, Model model) {
		model.addAttribute("username", principal.getName());
		return "user/user";
	}
}

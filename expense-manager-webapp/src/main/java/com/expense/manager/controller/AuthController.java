package com.expense.manager.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.expense.manager.dto.UserDTO;
import com.expense.manager.service.UserService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class AuthController {
	
	private final UserService userService;

	@GetMapping({"/","/login"})
	public String showLoginPage() {
		return "login";
	}
	
	@GetMapping("/register")
	public String showRegisterPage(Model model) {
		model.addAttribute("user", new UserDTO());
		return "register";
	}
	
	@PostMapping("/register")
	public String register(@ModelAttribute("user") UserDTO userDTO, Model model) {
		System.out.println("printing the user details :"+userDTO);
		userService.save(userDTO);
		model.addAttribute("successMessage", true);
		return "login"; //return view name instead of redirect. If we redirect we won't have access to successMessage
	}
}

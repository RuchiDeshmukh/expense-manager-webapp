package com.expense.manager.controller;

import javax.validation.Valid;

import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
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
	public String showLoginPage(){
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if(auth == null || auth instanceof AnonymousAuthenticationToken) {
			return "login";
		}
		return "redirect:/expenses";
	}
	
	@GetMapping("/register")
	public String showRegisterPage(Model model) {
		model.addAttribute("user", new UserDTO());
		return "register";
	}
	
	@PostMapping("/register")
	public String register(@Valid @ModelAttribute("user") UserDTO userDTO,  BindingResult result,Model model) {
		if(result.hasErrors()) {
			return "register";
		}
		userService.save(userDTO);
		model.addAttribute("successMessage", true);
		return "response"; //return view name instead of redirect. If we redirect we won't have access to successMessage
	}
}

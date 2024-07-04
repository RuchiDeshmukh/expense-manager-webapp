package com.expense.manager.exception;

import javax.servlet.http.HttpServletRequest;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalDefaultExceptionHandler {
	
	@ExceptionHandler(ExpenseNotFoundException.class)
	public String handleExpenseNotFoundException(HttpServletRequest request,
									ExpenseNotFoundException exception,Model model) {
		
		model.addAttribute("notFound",true);
		model.addAttribute("message", exception.getMessage());
		return "response";
	}
	
	@ExceptionHandler(Exception.class)
	public String handleGlobalException(HttpServletRequest request, 
										Exception ex, Model model) {
		
		model.addAttribute("serverError",true);
		model.addAttribute("message", ex.getMessage());
		model.addAttribute("stackTrace", ex.getStackTrace());
		return "response";
	}

}

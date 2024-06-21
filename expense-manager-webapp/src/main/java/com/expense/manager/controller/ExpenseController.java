package com.expense.manager.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.expense.manager.service.ExpenseService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class ExpenseController {
	
	private final ExpenseService expenseService;
	

	@GetMapping("/expenses")
	public String showExpenseList(Model model) {
		
		model.addAttribute("expenses",expenseService.getAllExpense());
		return "expenses-list";
	}
}

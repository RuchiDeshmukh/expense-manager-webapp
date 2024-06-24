package com.expense.manager.controller;

import java.text.ParseException;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.expense.manager.dto.ExpenseDTO;
import com.expense.manager.dto.ExpenseFilterDTO;
import com.expense.manager.service.ExpenseService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class ExpenseController {
	
	private final ExpenseService expenseService;
	

	@GetMapping("/expenses")
	public String showExpenseList(Model model) {
		model.addAttribute("expenses",expenseService.getAllExpense());
		model.addAttribute("filter", new ExpenseFilterDTO());
		return "expenses-list";
	}
	
	@GetMapping("/createExpense")
	public String showExpenseForm(Model model) {
		model.addAttribute("expense", new ExpenseDTO());
		return "expense-form";
	}
	
	@PostMapping("/saveOrUpdateExpense")
	public String saveOrUpdateExpenseDetails(@ModelAttribute("expense") ExpenseDTO expenseDTO) throws ParseException {
		expenseService.saveExpenseDetails(expenseDTO);
		return "redirect:/expenses";
	}
	
	@GetMapping("/deleteExpense")
	public String deleteExpense(@RequestParam String id) {
		expenseService.deleteExpense(id);
		return "redirect:/expenses";
	}
	
	@GetMapping("/updateExpense")
	public String updateExpense(@RequestParam String id, Model model) {
		ExpenseDTO expenseDTO = expenseService.getExpenseId(id);
		model.addAttribute("expense", expenseDTO);
		return "expense-form";
	}
}

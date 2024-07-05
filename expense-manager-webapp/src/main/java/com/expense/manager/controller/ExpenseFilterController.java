package com.expense.manager.controller;

import java.text.ParseException;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

import com.expense.manager.dto.ExpenseDTO;
import com.expense.manager.dto.ExpenseFilterDTO;
import com.expense.manager.service.ExpenseService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class ExpenseFilterController {
	
	
	private final ExpenseService expenseService;

	@GetMapping("/filterExpenses")
	public String filterExpenses(@ModelAttribute("filter") ExpenseFilterDTO expenseFilterDTO, Model model) throws ParseException {
		List<ExpenseDTO> list = expenseService.getFilteredExpenses(expenseFilterDTO);
		model.addAttribute("expenses", list);
		String totalExpenses =  expenseService.totalingExpenses(list);
		model.addAttribute("totalExpenses", totalExpenses);
		return "expenses-list";
	}
	
}
